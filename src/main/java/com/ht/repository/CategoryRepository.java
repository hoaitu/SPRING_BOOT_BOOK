package com.ht.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ht.entities.Theloaisach;

public interface CategoryRepository extends JpaRepository<Theloaisach, Long> {
	@Query(value = "select b from Theloaisach b  where b.maTheLoai =:catId")
	public abstract Theloaisach findByCategory(@Param("catId") long id);
}
