package com.ht.entities;
// Generated Dec 20, 2020, 10:04:20 AM by Hibernate Tools 5.1.10.Final

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Billcontainsach generated by hbm2java
 */
@Entity
@Table(name = "billcontainsach", catalog = "book_dacnpm")
public class Billcontainsach implements java.io.Serializable {

	private BillcontainsachId id;
	private Bill bill;
	private Sach sach;
	private Integer quantity;

	public Billcontainsach() {
	}

	public Billcontainsach(BillcontainsachId id, Bill bill, Sach sach) {
		this.id = id;
		this.bill = bill;
		this.sach = sach;
	}

	public Billcontainsach(BillcontainsachId id, Bill bill, Sach sach, Integer quantity) {
		this.id = id;
		this.bill = bill;
		this.sach = sach;
		this.quantity = quantity;
	}

	@EmbeddedId

	@AttributeOverrides({ @AttributeOverride(name = "idBill", column = @Column(name = "idBill", nullable = false)),
			@AttributeOverride(name = "idSach", column = @Column(name = "idSach", nullable = false)) })
	public BillcontainsachId getId() {
		return this.id;
	}

	public void setId(BillcontainsachId id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "idBill", nullable = false, insertable = false, updatable = false)
	public Bill getBill() {
		return this.bill;
	}

	public void setBill(Bill bill) {
		this.bill = bill;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "idSach", nullable = false, insertable = false, updatable = false)
	public Sach getSach() {
		return this.sach;
	}

	public void setSach(Sach sach) {
		this.sach = sach;
	}

	@Column(name = "quantity")
	public Integer getQuantity() {
		return this.quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

}