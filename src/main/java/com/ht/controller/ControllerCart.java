package com.ht.controller;

import java.io.UnsupportedEncodingException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.ht.entities.Cart;
import com.ht.entities.Receipt;
import com.ht.entities.ReceiptItem;
import com.ht.entities.Sach;
import com.ht.service.BookService;
import com.ht.service.ReceiptItemService;
import com.ht.service.ReceiptService;

////-------------TRANG-------------
@Controller
@RequestMapping(value = "cart")
public class ControllerCart {
	@Autowired
	private JavaMailSender mailSender;

	@Autowired
	private BookService bookService;
	@Autowired
	private ReceiptService receiptService;
	@Autowired
	private ReceiptItemService receiptItemService;

//////////THEM SAN PHAM VAO GIO HANG
	@RequestMapping(value = "add/{productId}", method = RequestMethod.GET)
	public String viewAdd(ModelMap mm, HttpSession session, @PathVariable("productId") long productId) {
		HashMap<Long, Cart> cartItems = (HashMap<Long, Cart>) session.getAttribute("myCartItems");
		if (cartItems == null) {
			cartItems = new HashMap<>();
		}
		Sach product = bookService.findById(productId);
		if (product != null) {
			if (cartItems.containsKey(productId)) {
				Cart item = cartItems.get(productId);
				item.setProduct(product);
				item.setQuantity(item.getQuantity() + 1);
				cartItems.put(productId, item);
			} else {
				Cart item = new Cart();
				item.setProduct(product);
				item.setQuantity(1);
				cartItems.put(productId, item);
			}
		}
		session.setAttribute("myCartItems", cartItems);
		session.setAttribute("myCartTotal", totalPrice(cartItems));
		session.setAttribute("myCartNum", cartItems.size());
		return "redirect:/shop";
	}

//////////TANG SO LUONG SAN PHAM TRONG GIO HANG
	@RequestMapping(value = "sub/{productId}", method = RequestMethod.GET)
	public String viewUpdate(ModelMap mm, HttpSession session, @PathVariable("productId") long productId) {
		HashMap<Long, Cart> cartItems = (HashMap<Long, Cart>) session.getAttribute("myCartItems");
		if (cartItems == null) {
			cartItems = new HashMap<>();
		}
		Sach product = bookService.findById(productId);
		if (product != null) {
			if (cartItems.containsKey(productId)) {
				Cart item = cartItems.get(productId);
				item.setProduct(product);
				item.setQuantity(item.getQuantity() + 1);
				cartItems.put(productId, item);
			} else {
				Cart item = new Cart();
				item.setProduct(product);
				item.setQuantity(1);
				cartItems.put(productId, item);
			}
		}
		session.setAttribute("myCartItems", cartItems);
		session.setAttribute("myCartTotal", totalPrice(cartItems));
		session.setAttribute("myCartNum", cartItems.size());
		return "redirect:/shopping_cart";
	}

////////////GIAM SO LUONG TRONG GIO HANG
	@RequestMapping(value = "subs/{productId}", method = RequestMethod.GET)
	public String viewUpdates(ModelMap mm, HttpSession session, @PathVariable("productId") long productId) {
		HashMap<Long, Cart> cartItems = (HashMap<Long, Cart>) session.getAttribute("myCartItems");
		if (cartItems == null) {
			cartItems = new HashMap<>();
		}
		Sach product = bookService.findById(productId);
		if (product != null) {
			if (cartItems.containsKey(productId)) {
				Cart item = cartItems.get(productId);
				item.setProduct(product);
				if (item.getQuantity() > 1) {
					item.setQuantity(item.getQuantity() - 1);
				}
				cartItems.put(productId, item);
			} else {
				Cart item = new Cart();
				item.setProduct(product);
				item.setQuantity(1);
				cartItems.put(productId, item);
			}
		}
		session.setAttribute("myCartItems", cartItems);
		session.setAttribute("myCartTotal", totalPrice(cartItems));
		session.setAttribute("myCartNum", cartItems.size());
		return "redirect:/shopping_cart";
	}

//////////////XOA SAN PHAM TRONG GIO HANG
	@RequestMapping(value = "remove/{productId}", method = RequestMethod.GET)
	public String viewRemove(ModelMap mm, HttpSession session, @PathVariable("productId") long productId) {
		HashMap<Long, Cart> cartItems = (HashMap<Long, Cart>) session.getAttribute("myCartItems");
		if (cartItems == null) {
			cartItems = new HashMap<>();
		}
		if (cartItems.containsKey(productId)) {
			cartItems.remove(productId);
		}
		session.setAttribute("myCartItems", cartItems);
		session.setAttribute("myCartTotal", totalPrice(cartItems));
		session.setAttribute("myCartNum", cartItems.size());
		return "redirect:/shopping_cart";
	}

////////////XOA TOAN BO SAN PHAM TRONG GIO HANG
	@RequestMapping(value = "removeAll", method = RequestMethod.GET)
	public String viewRemoveAll(ModelMap mm, HttpSession session) {
		HashMap<Long, Cart> cartItems = (HashMap<Long, Cart>) session.getAttribute("myCartItems");

		session.removeAttribute("myCartItems");
		session.setAttribute("myCartNum", 0);
		session.setAttribute("myCartTotal", 0);
		return "redirect:/shopping_cart";
	}

////////////TONG TIEN
	public double totalPrice(HashMap<Long, Cart> cartItems) {
		int count = 0;
		for (Map.Entry<Long, Cart> list : cartItems.entrySet()) {
			count += list.getValue().getProduct().getGia() * list.getValue().getQuantity();
		}
		return count;
	}

/////////////HIEN THI TRANG THANH TOAN
	@RequestMapping(value = "checkout", method = RequestMethod.GET)
	public ModelAndView viewCheckout(ModelMap mm) {
		ModelAndView mav = new ModelAndView("checkout");
		mm.addAttribute("receipt", new Receipt());
		return mav;
	}

/////////////XU LY THANH TOAN
	@RequestMapping(value = "checkout", method = RequestMethod.POST)
	public String viewCheckout(ModelMap mm, HttpSession session, @ModelAttribute("receipt") Receipt receipt,
			HttpServletRequest request) throws MessagingException, UnsupportedEncodingException {
		HashMap<Long, Cart> cartItems = (HashMap<Long, Cart>) session.getAttribute("myCartItems");
		if (cartItems == null) {
			cartItems = new HashMap<>();
		}
		receipt.setReceiptAddress(receipt.getReceiptAddress().substring(0, receipt.getReceiptAddress().length()-1));
		receipt.setReceiptDate(new Timestamp(new Date().getTime()));
//		receipt.setReceiptDate(new Timestamp(new Date().getTime()));
		//tran - xu ly cat ki tu cuoi cua chuoi 
		String receipt_mail = receipt.getReceiptMail().substring(0, receipt.getReceiptMail().length()-1);
		receipt.setReceiptMail(receipt_mail);
		receipt.setReceiptName(receipt.getReceiptName().substring(0, receipt.getReceiptName().length()-1));
		receipt.setReceiptPhone(receipt.getReceiptPhone().substring(0, receipt.getReceiptPhone().length()-1));
		receipt.setReceiptStatus("Đặt hàng thành công");
		receipt.setTotal(totalPrice(cartItems));
		receiptService.create(receipt);
	
		
		
		for (Map.Entry<Long, Cart> entry : cartItems.entrySet()) {
			ReceiptItem receiptItem = new ReceiptItem();
			receiptItem.setReceipt(receipt);
			receiptItem.setProduct(entry.getValue().getProduct());
			receiptItem.setReceiptItemPrice(entry.getValue().getProduct().getGia());
			receiptItem.setReceiptItemSale(entry.getValue().getProduct().getKhuyenMai());
			receiptItem.setReceiptItemQuantity(entry.getValue().getQuantity());
			receiptItem.setReceiptItemStatus(true);
			receiptItemService.create(receiptItem);
			
		}
		cartItems = new HashMap<>();
		session.setAttribute("myCartItems", cartItems);
		session.setAttribute("myCartTotal", 0);
		session.setAttribute("myCartNum", 0);
		
// tran - lay du lieu de gui mail thong tin don hang
		String name = request.getParameter("receiptName");
		String mail = request.getParameter("receiptMail");
		String phone = request.getParameter("receiptPhone");
		String address = request.getParameter("receiptAddress");
		String note = request.getParameter("note");

		String nameBook = request.getParameter("nameBook");
		String price = request.getParameter("price");

		String total = request.getParameter("total");
		// SimpleMailMessage message = new SimpleMailMessage();
		// khoi tao mail gui
		MimeMessage message = mailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message, true);
		// noi dung mail gui
		String mailSubject = "Thông tin giao hàng";
		String mailContent = "<p><b>Người nhận: </b>" + name + "</p>";
		mailContent += "<p><b>Email: </b>" + mail + "</p>";
		mailContent += "<p><b>Số điện thoại: </b>" + phone + "</p>";
		mailContent += "<p><b>Địa chỉ: </b>" + address + "</p>";
		mailContent += "<p><b>Ghi chú: </b>" + note + "</p>";

		mailContent += "<p><b>Tên sách: </b>" + nameBook + "</p>";
		mailContent += "<p><b>Giá: </b>" + price + "</p>";

		mailContent += "<p><b>Thanh toán: </b>" + total + "</p>";
		mailContent += "<p>Đơn hàng đang tiến hành đóng gói để giao đến bạn.<p>"
				+ "Cảm ơn bạn đã tin tưởng và ủng hộ Writer!</p>";
		mailContent += "<br><h3>Bạn có thể liên hệ với WRITER về đơn hàng bằng các cách sau:</h3>"
				+ "<p>	- Gọi đến Hotline (+800) 123 4567 890</p>"
				+ "<p>	- Gửi mail về địa chỉ: info@bookstore.com</p>" + "<p>Chúc bạn có một ngày mới vui vẻ!</p>";

		mailContent += "<img src='cid:logoImage'/>";
		// gui tu mai hoaitugl@gmail.com voi ten goi WRITER Order
		helper.setFrom("hoaitugl@gmail.com", "WRITER Order");
		// den mail nguoi dung nhap
		helper.setTo(mail);
		helper.setSubject(mailSubject);
		helper.setText(mailContent, true);
		// dung de gui mail
		mailSender.send(message);

		ClassPathResource resource = new ClassPathResource("/static/resource/user/img/logo.png");
		helper.addInline("logoImage", resource);
		// tra ve trang success

		return "success";
	}
}
