package com.ht.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.ht.entities.Sach;
import com.ht.service.BookService;
import com.ht.service.CategoryService;
import com.ht.service.PageService;

//Tu
@Controller
public class PagingController {
	@Autowired
	private PageService pageService;
	@Autowired
	private CategoryService category;
	@Autowired
	private BookService bookService;

	@RequestMapping("/shops")
	@ResponseBody
	public List<Sach> viewPhanTrang2(Model model, @RequestParam int currentPage) {
		Page<Sach> page = pageService.listAll(currentPage);
		List<Sach> listSach = page.getContent();
		model.addAttribute("listcategory", category.findAll());
		return listSach;
	}

//Show page Shop + totalPage From book ; Categary Book
	@RequestMapping("/shop")
	public String viewPhanTrang(ModelMap model) {
		int currentPage = 1;
		Page<Sach> page = pageService.listAll(currentPage);
		List<Sach> listSach = page.getContent();
		int totalPage = page.getTotalPages();
		model.addAttribute("totalPage", totalPage);
		model.addAttribute("listbook", listSach);
		model.addAttribute("listcategory", category.findAll());
		return "shop";
	}
}
