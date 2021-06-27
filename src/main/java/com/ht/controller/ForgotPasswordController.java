package com.ht.controller;

import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;

import java.io.UnsupportedEncodingException;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.mail.MailSender;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMailMessage;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.ht.entities.User;
import com.ht.service.CustomerServices;

import net.bytebuddy.utility.RandomString;

// Ngày 10/06/2021
@Controller
public class ForgotPasswordController {
	@Autowired
	private JavaMailSender mailSender;

	@Autowired
	private CustomerServices customerService;

//	TU: show page forgot_password_form
	@GetMapping("/forgot_password")
	public String showForgotPasswordForm() {
		return "forgot_password_form";

	}

//	TU: method POST ; receiver Email Check mail have exits in DB Yes or No ; If Yes will hove 1 token send this mail; or Mess Error in Page  (show page forgot_password_form); Call Funtion sendMail Under this Page
	@PostMapping("/forgot_password")
	public String processForgotPassword(HttpServletRequest request, Model model) {
		String email = request.getParameter("email");
		String token = RandomString.make(30);

		try {
			customerService.updateResetPasswordToken(token, email);
			String resetPasswordLink = Utility.getSiteURL(request) + "/reset_password?token=" + token;
			sendEmail(email, resetPasswordLink);
			model.addAttribute("message", "We have sent a reset password link to your email. Please check.");

		} catch (UsernameNotFoundException ex) {
			model.addAttribute("error", ex.getMessage());
		} catch (UnsupportedEncodingException | MessagingException e) {
			model.addAttribute("error", "Error while sending email");
		}

		return "forgot_password_form";
	}

	public void sendEmail() {

	}

//	TU: user click token will have link go to Page: reset_password_form
	@GetMapping("/reset_password")
	public String showResetPasswordForm(@Param(value = "token") String token, Model model) {
		User customer = customerService.getByResetPasswordToken(token);
		model.addAttribute("token", token);

		if (customer == null) {
			model.addAttribute("message", "Invalid Token");
			return "message";
		}

		return "reset_password_form";
	}

//	TU:Method Post ; Show Mess in This page if Err; Show mess in Page: login if change pass Success
	@PostMapping("/reset_password")
	public String processResetPassword(HttpServletRequest request, Model model) {
		String token = request.getParameter("token");
		String password = request.getParameter("password");

		User customer = customerService.getByResetPasswordToken(token);
		model.addAttribute("title", "Reset your password");

		if (customer == null) {
			model.addAttribute("message", "Invalid Token");
			return "message";
		} else {
			customerService.updatePassword(customer, password);

			model.addAttribute("message", "You have successfully changed your password.");
		}
//		return "message";
//		return "redirect:/login"; : dùng này sẽ ko nhận đc Mess thông báo
		return "login";
	}

//	------------------------------------------------------------------
	public void sendEmail(String recipientEmail, String link) throws MessagingException, UnsupportedEncodingException {
		MimeMessage message = mailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message);

		helper.setFrom("contact@BookStore.com", "WRITER Support");
		helper.setTo(recipientEmail);

		String subject = "Here's the link to reset your password";

		String content = "<p>Hello,</p>" + "<p>You have requested to reset your password.</p>"
				+ "<p>Click the link below to change your password:</p>" + "<p><a href=\"" + link
				+ "\">Change my password</a></p>" + "<br>" + "<p>Ignore this email if you do remember your password, "
				+ "or you have not made the request.</p>";

		helper.setSubject(subject);

		helper.setText(content, true);

		mailSender.send(message);
	}
}