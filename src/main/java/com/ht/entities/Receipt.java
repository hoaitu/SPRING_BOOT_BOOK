package com.ht.entities;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity()
@Table(name = "receipt", catalog = "book_dacnpm")
public class Receipt implements Serializable {

	@Id
	@GeneratedValue
	private long receiptId;
	private String receiptName;
	private String receiptMail;
	private String receiptPhone;
	private String receiptAddress;
	private Timestamp receiptDate;
	private String receiptStatus;
	private double total;
	@OneToMany(fetch = FetchType.LAZY)
	@JoinColumn(name = "receiptItemId")
	private List<ReceiptItem> listReceiptItem;

	public String getReceiptPhone() {
		return receiptPhone;
	}

	public void setReceiptPhone(String receiptPhone) {
		this.receiptPhone = receiptPhone;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	public long getReceiptId() {
		return receiptId;
	}

	public void setReceiptId(long receiptId) {
		this.receiptId = receiptId;
	}

	public String getReceiptName() {
		return receiptName;
	}

	public void setReceiptName(String receiptName) {
		this.receiptName = receiptName;
	}

	public String getReceiptMail() {
		return receiptMail;
	}

	public void setReceiptMail(String receiptMail) {
		this.receiptMail = receiptMail;
	}

	public String getReceiptAddress() {
		return receiptAddress;
	}

	public void setReceiptAddress(String receiptAddress) {
		this.receiptAddress = receiptAddress;
	}

	public Timestamp getReceiptDate() {
		return receiptDate;
	}

	public void setReceiptDate(Timestamp receiptDate) {
		this.receiptDate = receiptDate;
	}

	public String isReceiptStatus() {
		return receiptStatus;
	}

	public void setReceiptStatus(String receiptStatus) {
		this.receiptStatus = receiptStatus;
	}

	public String getReceiptStatus() {
		return receiptStatus;
	}

	public List<ReceiptItem> getListReceiptItem() {
		return listReceiptItem;
	}

	public void setListReceiptItem(List<ReceiptItem> listReceiptItem) {
		this.listReceiptItem = listReceiptItem;
	}

}
