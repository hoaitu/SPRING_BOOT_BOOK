package com.ht.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ht.entities.Sach;

@Repository
public interface BookRepository extends JpaRepository<Sach, Long> {
	
	
	
	@Query(value = "select b from Sach b  where b.theloaisach =:catId")
	public abstract List<Sach> findByCategory(@Param("catId") long id);

	@Query(value = "SELECT c FROM Sach c WHERE c.maSach =:ids")
	public abstract Sach findByName(@Param("ids") long ids);

//	Tìm kiếm tên sách
	@Query(value = "select p.tenSach from Sach p where p.tenSach like %?1%")
	public List<String> findBookName(String tenSach);

	@Query(value = "select p from Sach p where p.tenSach like %?1%")
	public abstract List<Sach> findBookByTitle(String tenSach);

}
