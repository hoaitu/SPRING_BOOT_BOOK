package com.ht.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ht.entities.Sach;
import com.ht.service.BookService;
import com.ht.service.CategoryService;
import com.ht.service.PageService;
import com.ht.service.UserService;

@RestController
public class SearchAjaxController {
	@Autowired
	private BookService book;

//TU: Search Book Name 
	@RequestMapping(value = "/search")
	public @ResponseBody List<Sach> ajaxSearch(HttpServletRequest req, HttpServletResponse res) {
		List<Sach> getSach = book.findBookByTitle(req.getParameter("tenSach"));
		for (Sach sach : getSach) {
			System.out.println(sach + "\n");
		}
		return getSach;
	}

//TU: Receiver book name 1 list if name like name : Using Ajax in page: Shop
	@RequestMapping(value = "/ajaxSearch", method = RequestMethod.GET)
	public List<String> getSach(String tenSach) {
		List<String> getSach = book.findBook(tenSach);
		for (String sach : getSach) {
			System.out.println(sach + "\n");
		}
		return getSach;
	}
}
