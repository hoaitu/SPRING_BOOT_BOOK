package com.ht.service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ht.entities.Receipt;
import com.ht.repository.ReceiptRepository;

@Service
public class ReceiptService {
	@Autowired
	private ReceiptRepository reRepository;

	public List<Receipt> findAll() {
		return reRepository.findAll();
	}

	public void create(Receipt receipt) {
		reRepository.saveAndFlush(receipt);
//		receipt.getReceiptMail().substring(0, receipt.getReceiptMail().length()-1);	
	}

	public Receipt findid(long id) {
		return reRepository.findByID(id);
	}

	public void delete(long id) {
		Receipt r = reRepository.findByID(id);
		if (r != null) {
			reRepository.delete(r);
		}
	}

	public void update(Receipt receipt) {
		Receipt re = reRepository.findByID(receipt.getReceiptId());
		re.setReceiptStatus(receipt.getReceiptStatus());
		re.setReceiptDate(new Timestamp(new Date().getTime()));

		reRepository.saveAndFlush(re);
	}
}
