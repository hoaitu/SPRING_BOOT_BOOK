package com.ht.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ht.entities.ReceiptItem;
import com.ht.repository.ReceiptItemRepository;

@Service
public class ReceiptItemService {
	@Autowired
	private ReceiptItemRepository receipRepository;

	public void create(ReceiptItem receiptItem) {
		receipRepository.saveAndFlush(receiptItem);
	}

	public List<ReceiptItem> showReceipt(String email) {
		return receipRepository.showReceipt(email);
	}

	public List<ReceiptItem> showAll() {
		return receipRepository.showAll();
	}

	public List<ReceiptItem> findID(long id) {
		return receipRepository.findID(id);
	}
}
