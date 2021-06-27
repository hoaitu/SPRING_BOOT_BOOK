package com.ht.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ht.entities.Receipt;
import com.ht.entities.Sach;

public interface ReceiptRepository extends JpaRepository<Receipt, Long> {
	@Query(value = "SELECT c FROM Receipt c WHERE c.receiptId =:ids")
	public abstract Receipt findByID(@Param("ids") long ids);
	
}
