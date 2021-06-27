package com.ht.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.ht.entities.Sach;
import com.ht.repository.BookRepository;

@Service
public class BookService {
	@Autowired
	private BookRepository bookRepository;

//	Sort sort = Sort.by(
//		    Sort.Order.asc("name"),
//		    Sort.Order.desc("numberOfHands"));
//		return personRepository.findAll(sort);

	/// HIEN THI TAT CA SACH
	public List<Sach> findAll() {
//			Sort sort = Sort.by(
		return bookRepository.findAll(Sort.by(Sort.Direction.DESC, "maSach"));
// hiển thị DS ra theo ID giảm dần: sách mới ra nhất sẽ hiện lên đầu
	}

	public List<Sach> findByCategory(long id) {
		return bookRepository.findByCategory(id);

	}

	public Sach findById(long ids) {
		return bookRepository.findByName(ids);
	}

	public Optional<Sach> findByID(long ids) {
		return bookRepository.findById(ids);
	}
//	Tìm kím

//	public List<String> findBook(String name) {
//		return bookRepository.findBookName(name);
//	}
//
////	choose
//	public List<Sach> findBookByTitle(String tenSach) {
//		return bookRepository.findBookByTitle(tenSach);
//	}

	public List<Sach> findBookByTitle(String tenSach) {
		return bookRepository.findBookByTitle(tenSach);
	}

	public List<String> findBook(String name) {
		return bookRepository.findBookName(name);
	}

	/// THEM MOI SACH
	public Sach createBook(Sach sach) {
		return bookRepository.saveAndFlush(sach);
	}

	/// CHINH SUA SACH
	public Sach update(Sach sach) {
		Sach book = bookRepository.findByName(sach.getMaSach());
		book.setHinhAnh(sach.getHinhAnh());
		book.setGia(sach.getGia());
		book.setMoTa(sach.getMoTa());
		book.setNgayXuatBan(sach.getNgayXuatBan());
		book.setTenSach(sach.getTenSach());
		book.setTenTacGia(sach.getTenTacGia());
		book.setTheloaisach(sach.getTheloaisach());
		book.setKhuyenMai(sach.getKhuyenMai());
		book.setSoLuong(sach.getSoLuong());
		return bookRepository.saveAndFlush(book);
	}

	///// XOA SACH
	public void delete(long id) {
		Sach sach = bookRepository.findByName(id);
		if (sach != null) {
			bookRepository.delete(sach);
		}
	}
}
