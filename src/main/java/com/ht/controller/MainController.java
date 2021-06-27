package com.ht.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.ht.WebUtils;
import com.ht.entities.Sach;
import com.ht.service.BookService;
import com.ht.service.CategoryService;
import com.ht.service.CustomerValidation;
import com.ht.service.PageService;
import com.ht.service.ReceiptItemService;
import com.ht.service.ReceiptService;
import com.ht.service.UserService;
import com.ht.entities.User;
//

import java.security.Principal;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@RestController
public class MainController {

	@Autowired
	private BookService book;
	@Autowired
	private CategoryService category;
	@Autowired
	private PageService pageService;

	@Autowired
	private UserService userService;
	@Autowired
	private ReceiptService receiptService;
	@Autowired
	private ReceiptItemService receiptItemService;

	@RequestMapping("/")
	public ModelAndView home(ModelMap model) {
		ModelAndView mav = new ModelAndView("index");

		return mav;
	}

//	------------------------------------------------------------

//	Tu new cmment 06/06
	@RequestMapping(value = "/shop/{id}", method = RequestMethod.GET)
	public ModelAndView shop(Model model, @PathVariable(value = "id") int id) {
		ModelAndView mav = new ModelAndView("shop");
		model.addAttribute("listbook", book.findByCategory(id));
		model.addAttribute("listcategory", category.findAll());
		return mav;
	}

//Tu: Show page Login	

	@RequestMapping(value = "/login", method = RequestMethod.GET)

	public ModelAndView login(ModelMap model) {
		ModelAndView mav = new ModelAndView("login");

		return mav;
	}

	@RequestMapping("/single-product/{ids}")

	public ModelAndView single_product(Model model, @PathVariable(value = "ids") int ids) {
		ModelAndView mav = new ModelAndView("single-product");
		mav.addObject("books", book.findById(ids));
//		model.addAttribute("listBook", bookService.findSameCategory());
		return mav;
	}

	@RequestMapping("/wishlist")
	public ModelAndView wishlist(ModelMap model) {
		ModelAndView mav = new ModelAndView("wishlist");

		return mav;
	}

/////////TRANG------THONG TIN TAI KHOAN
	@RequestMapping("/my-account/{email}")
	public ModelAndView account(ModelMap model, @PathVariable("email") String email) {
		ModelAndView mav = new ModelAndView("my-account");
		model.addAttribute("user", new User());
		model.addAttribute("users", userService.findByEmail(email));
		model.addAttribute("receipts", receiptItemService.showReceipt(email));
		return mav;
	}

/////////TRANG------THONG TIN TAI KHOAN
	@PostMapping("/my-account/{email}")
	public ModelAndView account(ModelMap model, @ModelAttribute("user") User u) {
		ModelAndView mav = new ModelAndView("my-account");
		userService.updates(u);
		return mav;
	}

	@RequestMapping("/shopping_cart")
	public ModelAndView cart(ModelMap model) {
		ModelAndView mav = new ModelAndView("cart");

		return mav;
	}

	/**
	 * 2/06/2021 : thêm trang 404
	 */

//to Tu test : no important
	@RequestMapping(value = "/userInfo", method = RequestMethod.GET)
	public ModelAndView userInfo(Model model, Principal principal) {
		ModelAndView mav = new ModelAndView("userInfoPage");
		// After user login successfully.
		String userName = principal.getName();

		System.out.println("User Name: " + userName);

//		User loginedUser = (User) ((Authentication) principal).getPrincipal();
//
//		String userInfo = WebUtils.toString(loginedUser);
//		model.addAttribute("userInfo", userInfo);

		return mav;
	}

//Tu: if after Login user want to logout + return Page: logoutSuccessfulPage.html 
	@RequestMapping(value = "/logoutSuccessful", method = RequestMethod.GET)
	public ModelAndView logoutSuccessfulPage(Model model) {
		ModelAndView mav = new ModelAndView("logoutSuccessfulPage");
		model.addAttribute("title", "Logout");
		return mav;
	}

//TEST: no important (if don't hvae permission will return page: 404: Sercurity) 
	@RequestMapping(value = "/403", method = RequestMethod.GET)
	public ModelAndView accessDenied(Model model, Principal principal) {
		ModelAndView mav = new ModelAndView("404");
		if (principal != null) {
//			User loginedUser = (User) ((Authentication) principal).getPrincipal();

//			String userInfo = WebUtils.toString(loginedUser);
//
//			model.addAttribute("userInfo", userInfo);

//			String message = "Hi " + principal.getName() //
			String message = "Hi, You do not have permission to access this page!, Thankyou";
			model.addAttribute("message", message);

		}

		return mav;
	}

//TEST: only test
	@RequestMapping("/test")

	public ModelAndView testPG(ModelMap model) {
		ModelAndView mav = new ModelAndView("test");

		return mav;
	}

}
