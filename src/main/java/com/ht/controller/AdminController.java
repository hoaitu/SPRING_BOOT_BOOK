package com.ht.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.ht.dao.SachMuntil;
import com.ht.entities.Receipt;
import com.ht.entities.Sach;
import com.ht.entities.Theloaisach;
import com.ht.entities.User;
import com.ht.service.BookService;
import com.ht.service.CategoryService;
import com.ht.service.ReceiptItemService;
import com.ht.service.ReceiptService;
import com.ht.service.UserService;

//////-----------TRANG----------
@Controller
public class AdminController {
	@Autowired
	private BookService book;
	@Autowired
	private CategoryService category;
	@Autowired
	private UserService userSevice;
	@Autowired
	private ReceiptService receiptService;
	@Autowired
	private ReceiptItemService receiptItemService;

//////////////HIEN THI DANH SACH SAN PHAM---------
	@GetMapping("/admin")
	public ModelAndView listProduct(ModelMap model) {
		ModelAndView mav = new ModelAndView("admin/index");
		model.addAttribute("listbook", book.findAll());
		model.addAttribute("listcategory", category.findAll());
		return mav;
	}

//////////THEM SAN PHAM----------
	@GetMapping("/admin/productAdd")
	public ModelAndView addProduct(ModelMap model) {
		ModelAndView mav = new ModelAndView("admin/product-add");
		model.addAttribute("books", new SachMuntil());
		model.addAttribute("listbook", book.findAll());
		model.addAttribute("listcategory", category.findAll());
		return mav;
	}

///THEM MOI SAN PHAM
	@PostMapping(value = "/admin/productAdd")
	public String addProduct(ModelMap model, @ModelAttribute("books") SachMuntil dto) throws IOException {
		Optional<Sach> optional = Optional.ofNullable(book.findById(dto.getMaSach()));
		Sach sach = null;
		String images = "/resource/user/img/shop/td12.jpg";
		File file = new ClassPathResource("static/resource/user/img/shop").getFile();
		String canonicalPath = file.getCanonicalPath();
		Path path = Paths.get(canonicalPath);
		if (optional.isPresent()) {
			// update
			if (dto.getHinhAnh().isEmpty()) {
				images = optional.get().getHinhAnh();
			} else {
				try {
					InputStream inputStream = dto.getHinhAnh().getInputStream();
					Files.copy(inputStream, path.resolve(dto.getHinhAnh().getOriginalFilename()),
							StandardCopyOption.REPLACE_EXISTING);
					images = "/resource/user/img/shop/" + dto.getHinhAnh().getOriginalFilename().toString();
					System.out.println(images);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		} else {
			// add new
			if (!dto.getHinhAnh().isEmpty()) {
				try {
					InputStream inputStream = dto.getHinhAnh().getInputStream();
					Files.copy(inputStream, path.resolve(dto.getHinhAnh().getOriginalFilename()),
							StandardCopyOption.REPLACE_EXISTING);
					images = "/resource/user/img/shop/" + dto.getHinhAnh().getOriginalFilename().toString();
					System.out.println(images);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		sach = new Sach(dto.getTheloaisach(), dto.getTenSach(), dto.getGia(), dto.getSoLuong(), dto.getTenTacGia(),
				images, dto.getMoTa(), dto.getKhuyenMai(), dto.getNgayXuatBan());

		book.createBook(sach);

		model.addAttribute("listbook", book.findAll());
		return "redirect:/admin";
	}

//////CHINH SUA SAN PHAM ID
//	@RequestMapping(value = "/admin/productEdit/{ids}", method = RequestMethod.GET)
//	public ModelAndView productEdit(ModelMap model, @PathVariable(value = "ids") long id) {
//		ModelAndView mav = new ModelAndView("admin/product-edit");
//		model.addAttribute("books", book.findById(id));
//		return mav;
//	}

//////////CHINH SUA SAN PHAM ID
	@RequestMapping(value = "/admin/productEdit/{ids}", method = RequestMethod.POST)
	public String productEdit(ModelMap model, @ModelAttribute("books") SachMuntil dto) throws IOException {
		Optional<Sach> optional = Optional.ofNullable(book.findById(dto.getMaSach()));
		Sach sach = null;
		String images = "/resource/user/img/shop/td12.jpg";
		File file = new ClassPathResource("static/resource/user/img/shop").getFile();
		String canonicalPath = file.getCanonicalPath();
		Path path = Paths.get(canonicalPath);
		if (optional.isPresent()) {
			// update
			if (dto.getHinhAnh().isEmpty()) {
				images = optional.get().getHinhAnh();
			} else {
				try {
					InputStream inputStream = dto.getHinhAnh().getInputStream();
					Files.copy(inputStream, path.resolve(dto.getHinhAnh().getOriginalFilename()),
							StandardCopyOption.REPLACE_EXISTING);
					images = "/resource/user/img/shop/" + dto.getHinhAnh().getOriginalFilename().toString();
					System.out.println(images);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		} else {
			// add new
			if (!dto.getHinhAnh().isEmpty()) {
				try {
					InputStream inputStream = dto.getHinhAnh().getInputStream();
					Files.copy(inputStream, path.resolve(dto.getHinhAnh().getOriginalFilename()),
							StandardCopyOption.REPLACE_EXISTING);
					images = "/resource/user/img/shop/" + dto.getHinhAnh().getOriginalFilename().toString();
					System.out.println(images);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		sach = new Sach(dto.getTheloaisach(), dto.getTenSach(), dto.getGia(), dto.getSoLuong(), dto.getTenTacGia(),
				images, dto.getMoTa(), dto.getKhuyenMai(), dto.getNgayXuatBan());

		book.createBook(sach);

		model.addAttribute("listbook", book.findAll());
		return "redirect:/admin";
	}

////////CHINH SUA SAN PHAM ID
	@RequestMapping(value = "/admin/productEdit/{ids}")
	public String productEdit(ModelMap model, @PathVariable(value = "ids") long id, HttpServletRequest request)
			throws IOException {
		Optional<Sach> optional = book.findByID(id);
		SachMuntil productDTO = null;
		if (optional.isPresent()) {
			Sach book = optional.get();
			File file = new ClassPathResource("static" + book.getHinhAnh()).getFile();
			try {
				FileInputStream input = new FileInputStream(file);
				MultipartFile multiImage = new MockMultipartFile("file", file.getName(), "text/plan",
						IOUtils.toByteArray(input));
				productDTO = new SachMuntil(book.getTheloaisach(), book.getTenSach(), book.getGia(), book.getSoLuong(),
						book.getTenTacGia(), multiImage, book.getMoTa(), book.getKhuyenMai(), book.getNgayXuatBan());
			} catch (Exception e) {
				e.printStackTrace();
			}
			model.addAttribute("books", productDTO);
		} else {
			model.addAttribute("books", new SachMuntil());
		}

		model.addAttribute("listbook", book.findAll());
		return "/admin/product-edit";
	}

////XOA SAN PHAM THEO ID
	@RequestMapping(value = "/admin/productDelete/{ids}", method = RequestMethod.GET)
	public String productDelete(ModelMap model, @PathVariable(value = "ids") long id) {
		book.delete(id);
		model.addAttribute("listbook", book.findAll());
		return "redirect:/admin";
	}

	@RequestMapping("/admin/productDetail")
	public ModelAndView adminProductDetail(ModelMap model) {
		ModelAndView mav = new ModelAndView("admin/product-detail");

		return mav;
	}

/////////////HIEN THI DANH SACH USER
	@RequestMapping("/admin/listCustomer")
	public ModelAndView listCustomer(Model model) {
		ModelAndView mav = new ModelAndView("admin/customer");
		model.addAttribute("listAllUser", userSevice.findAll());
		return mav;
	}

/// XOA TAI KHOAN THEO IDUSER
	@RequestMapping("/admin/deleteUser/{idUser}")
	public String deleteUser(ModelMap model, @PathVariable("idUser") long idUser) {
		userSevice.delete(idUser);
		model.addAttribute("listAllUser", userSevice.findAll());
		return "redirect:/admin/listCustomer";
	}

// THEM MOI MOT TAI KHOAN
	@RequestMapping(value = "/admin/addUser", method = RequestMethod.GET)
	public ModelAndView addUser(ModelMap model) {
		ModelAndView mav = new ModelAndView("admin/customer-add");
		model.addAttribute("user", new User());
		model.addAttribute("listAllUser", userSevice.findAll());
		return mav;
	}

// THEM MOI MOT TAI KHOAN
	@RequestMapping(value = "/admin/addUser", method = RequestMethod.POST)
	public String addUser(Model model, @ModelAttribute("user") User user, BindingResult result) {
		if (result.hasErrors()) {
			return "redirect:/admin/addUser";
		}

		userSevice.create(user);
//		model.addAttribute("listAllUser", userSevice.findAll());
		return "redirect:/admin/listCustomer";
	}

// CHINH SUA TAI KHOAN
	@RequestMapping(value = "/admin/editCustomter/{idUser}", method = RequestMethod.GET)
	public ModelAndView editCustomter(ModelMap model, @PathVariable(value = "idUser") long id) {
		ModelAndView mav = new ModelAndView("admin/customer-edit");
		model.addAttribute("user", userSevice.findById(id));
		return mav;
	}

	@RequestMapping(value = "/admin/editCustomter/{idUser}", method = RequestMethod.POST)
	public String editCustomter(ModelMap model, @ModelAttribute("user") User user) {
		userSevice.update(user);
		model.addAttribute("listAllUser", userSevice.findAll());
		return "redirect:/admin/listCustomer";
	}

///////////HIEN THI DANH SACH THE LOAI
	@RequestMapping("/admin/listProductType")
	public ModelAndView listProductType(Model model) {
		ModelAndView mav = new ModelAndView("admin/productType");
		model.addAttribute("listcategory", category.findAll());
		return mav;
	}

//// SAN PHAM THEO LOAI
	@RequestMapping(value = "/admin/listProductType/{id}", method = RequestMethod.GET)
	public ModelAndView productType(Model model, @PathVariable(value = "id") int id) {
		ModelAndView mav = new ModelAndView("admin/index");
		model.addAttribute("listbook", book.findByCategory(id));
		return mav;
	}

/// XOA LOAI SAN PHAM THEO MA THE LOAI
	@RequestMapping("/admin/deleteType/{maTheLoai}")
	public String deleteType(ModelMap model, @PathVariable("maTheLoai") long maTheLoai) {
		category.delete(maTheLoai);
		model.addAttribute("listcategory", category.findAll());
		return "redirect:/admin/listProductType";
	}

/// THEM LOAI SAN PHAM MOI
	@RequestMapping(value = "/admin/addType", method = RequestMethod.GET)
	public ModelAndView addType(ModelMap model) {
		ModelAndView mav = new ModelAndView("admin/productType-add");
		model.addAttribute("category", new Theloaisach());
		model.addAttribute("listcategory", category.findAll());
		return mav;
	}

//// THEM LOAI SAN PHAM MOI
	@RequestMapping(value = "/admin/addType", method = RequestMethod.POST)
	public String addType(ModelMap model, @ModelAttribute("category") Theloaisach theloaisach) {
		category.create(theloaisach);
		model.addAttribute("listcategory", category.findAll());
		return "redirect:/admin/listProductType";
	}

// CHINH SUA LOAI SAN PHAM
	@RequestMapping(value = "/admin/editType/{maTheLoai}", method = RequestMethod.GET)
	public ModelAndView editType(ModelMap model, @PathVariable(value = "maTheLoai") long id) {
		ModelAndView mav = new ModelAndView("admin/productType-edit");
		model.addAttribute("category", category.findById(id));
		return mav;
	}

// CHINH SUA LOAI SAN PHAM
	@RequestMapping(value = "/admin/editType/{maTheLoai}", method = RequestMethod.POST)
	public String editType(ModelMap model, @ModelAttribute("category") Theloaisach theloaisach) {
		category.update(theloaisach);
		model.addAttribute("listcategory", category.findAll());
		return "redirect:/admin/listProductType";
	}

/////DANH SACH DON HANG
	@RequestMapping("/admin/listOrder")
	public ModelAndView listOrder(Model model) {
		ModelAndView mav = new ModelAndView("admin/cart");
		model.addAttribute("listOrders", receiptItemService.showAll());
		return mav;
	}
	/// CHI TIET DON HANG

	@RequestMapping("/admin/orderDetail/{id}")
	public ModelAndView orderDetail(Model model, @PathVariable("id") long id) {
		ModelAndView mav = new ModelAndView("admin/cart-detail");
		model.addAttribute("listdetail", receiptItemService.findID(id));
		model.addAttribute("order", receiptService.findid(id));
		return mav;
	}

	/// XOA DON HANG
	@RequestMapping("/admin/deleteOrder/{id}")
	public String deleteOrder(ModelMap model, @PathVariable("id") long id) {
		receiptService.delete(id);
		return "redirect:/admin/listOrder";
	}

	@RequestMapping(value = "/admin/addOrder", method = RequestMethod.GET)
	public ModelAndView addOrder(ModelMap model) {
		ModelAndView mav = new ModelAndView("admin/cart-edit");
		model.addAttribute("order", new Receipt());
		model.addAttribute("listOrder", receiptService.findAll());
		return mav;
	}

	/// CHINH SUA DON HANG
	@RequestMapping(value = "/admin/editOrder/{ids}", method = RequestMethod.GET)
	public ModelAndView editOrder(ModelMap model, @PathVariable("ids") long ids) {
		ModelAndView mav = new ModelAndView("admin/cart-edit");
//		model.addAttribute("order", new Receipt());
		model.addAttribute("order", receiptService.findid(ids));
		return mav;
	}

	@RequestMapping(value = "/admin/editOrder/{ids}", method = RequestMethod.POST)
	public String editOrder(ModelMap model, @ModelAttribute("order") Receipt re) {
		receiptService.update(re);
		model.addAttribute("listOrders", receiptItemService.showAll());
		return "redirect:/admin/listOrder";

	}

}
