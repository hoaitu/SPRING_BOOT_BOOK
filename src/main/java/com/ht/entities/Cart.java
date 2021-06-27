package com.ht.entities;

public class Cart {
	private Sach product;
	private int quantity;

	public Cart() {

	}

	public Cart(Sach product, int quantity) {
		super();
		this.product = product;
		this.quantity = quantity;
	}

	public Sach getProduct() {
		return product;
	}

	public void setProduct(Sach product) {
		this.product = product;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int l) {
		this.quantity = l;
	}

}
