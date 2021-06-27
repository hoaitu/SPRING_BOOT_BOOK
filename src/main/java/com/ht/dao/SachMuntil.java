package com.ht.dao;

import java.util.Date;

import org.springframework.web.multipart.MultipartFile;

////////GIONG BANG SACH THAY DOI KIEU DU LIEU HINH ANH DE UPLOAD-------TRANG
public class SachMuntil {
	private long maSach;
	private long theloaisach;
	private String tenSach;
	private long gia;
	private Integer soLuong;
	private String tenTacGia;
	private MultipartFile hinhAnh;
	private String moTa;
	private int khuyenMai;
	private Date ngayXuatBan;

	public SachMuntil() {
		super();
	}

	public SachMuntil(long theloaisach, String tenSach, long gia, Integer soLuong, String tenTacGia,
			MultipartFile hinhAnh, String moTa, int khuyenMai, Date ngayXuatBan) {
		this.theloaisach = theloaisach;
		this.tenSach = tenSach;
		this.gia = gia;
		this.soLuong = soLuong;
		this.tenTacGia = tenTacGia;
		this.hinhAnh = hinhAnh;
		this.moTa = moTa;
		this.khuyenMai = khuyenMai;
		this.ngayXuatBan = ngayXuatBan;
//		this.comments = comments;
//		this.billcontainsaches = billcontainsaches;
//		this.wishlists = wishlists;
	}

	public long getMaSach() {
		return maSach;
	}

	public void setMaSach(long maSach) {
		this.maSach = maSach;
	}

	public long getTheloaisach() {
		return theloaisach;
	}

	public void setTheloaisach(long theloaisach) {
		this.theloaisach = theloaisach;
	}

	public String getTenSach() {
		return tenSach;
	}

	public void setTenSach(String tenSach) {
		this.tenSach = tenSach;
	}

	public long getGia() {
		return gia;
	}

	public void setGia(long gia) {
		this.gia = gia;
	}

	public Integer getSoLuong() {
		return soLuong;
	}

	public void setSoLuong(Integer soLuong) {
		this.soLuong = soLuong;
	}

	public String getTenTacGia() {
		return tenTacGia;
	}

	public void setTenTacGia(String tenTacGia) {
		this.tenTacGia = tenTacGia;
	}

	public MultipartFile getHinhAnh() {
		return hinhAnh;
	}

	public void setHinhAnh(MultipartFile hinhAnh) {
		this.hinhAnh = hinhAnh;
	}

	public String getMoTa() {
		return moTa;
	}

	public void setMoTa(String moTa) {
		this.moTa = moTa;
	}

	public int getKhuyenMai() {
		return khuyenMai;
	}

	public void setKhuyenMai(int khuyenMai) {
		this.khuyenMai = khuyenMai;
	}

	public Date getNgayXuatBan() {
		return ngayXuatBan;
	}

	public void setNgayXuatBan(Date ngayXuatBan) {
		this.ngayXuatBan = ngayXuatBan;
	}

}
