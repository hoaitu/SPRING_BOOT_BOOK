package com.ht.controller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.validation.ValidationUtils;

import com.ht.entities.Role;
import com.ht.entities.User;
import com.ht.entities.UsersRoles;
import com.ht.service.CustomerValidation;
import com.ht.service.UserService;
import com.ht.service.RoleService;

@RestController
public class SingupController {
	@Autowired
	private UserService userService;
	@Autowired
	private CustomerValidation customerValidation;

	@Autowired
	private RoleService roleService;

	/** 07/06/2021 */

	// Tu: Check user exits in DB (page Singup Ajax)
	@RequestMapping(value = "/checkUserName", method = RequestMethod.GET)
	public boolean checkUserName(String userName) {
//			user : khac rong : ==> ko co user nao ton tai tren DB => đăng ký OK : Lien ket vs Xly Ajax in PageL singup
		User user = userService.findCustomerByUserName(userName);
		if (user != null) {
			return true;
		} else {
			return false;
		}
	}

	// Tu: Show page singup
	@RequestMapping(value = "/signup", method = RequestMethod.GET)
	public ModelAndView signup(Model model) {
		ModelAndView mav = new ModelAndView("signup");
		model.addAttribute("user", new User());
		return mav;
	}

//		Tu: method Post : Ajax in Page Singup.html check UserName is Mail(user name la mail) Exits + JavaScrip check is Valid Mail + Password conform + required  
	@RequestMapping(value = "/signup", method = RequestMethod.POST)
	public ModelAndView signup(@ModelAttribute("user") @Valid User user, Errors errors, BindingResult result,
			Model model) {

		ModelAndView mav = null;

//			if have any Err will return Page Singup 
		if (result.hasErrors()) {
			return mav = new ModelAndView("signup");
//				if User have Exits in DB will return Page: Singup 
		} else if (checkUserName(user.getUsername()) == true) {
//				Return this Page Singup + mess Err
			model.addAttribute("message", "You have ERROR, Please check form again.");

			return mav = new ModelAndView("signup");

		}
//			if everything is OK; will create User in DB + Return Page Login (AUTO ADD USER + ROLE IN TABLE USER_ROLE)
		userService.create(user);
		UsersRoles us = new UsersRoles();
		Role r = new Role();
		r.setId(2);
		us.setRole(r);
		us.setUsers(user);
		roleService.create(us);

		return mav = new ModelAndView("login");
	}

}
