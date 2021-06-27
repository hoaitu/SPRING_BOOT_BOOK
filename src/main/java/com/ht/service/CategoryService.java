package com.ht.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.ht.entities.Theloaisach;
import com.ht.repository.CategoryRepository;

@Service
public class CategoryService {
	@Autowired
	private CategoryRepository categoryRepository;

////HIEN THI DANH SACH TAT CA THE LOAI
	public List<Theloaisach> findAll() {
		return categoryRepository.findAll(Sort.by(Sort.Direction.DESC, "maTheLoai"));
	}

//////TIM VA XOA THE LOAI THEO ID
	public void delete(Long maTheLoai) {
		Theloaisach tl = categoryRepository.findByCategory(maTheLoai);
		if (tl != null) {
			categoryRepository.delete(tl);
		}
	}

/////THEM MOI MOT THE LOAI
	public void create(Theloaisach theloaisach) {
		categoryRepository.saveAndFlush(theloaisach);

	}

////////TIM THE LOAI THEO ID
	public Theloaisach findById(Long id) {
		return categoryRepository.findByCategory(id);
	}

//////CHINH SUA THE LOAI THEO ID
	public void update(Theloaisach theloaisach) {
		Theloaisach t = categoryRepository.findByCategory(theloaisach.getMaTheLoai());
		t.setTenTheLoai(theloaisach.getTenTheLoai());
		categoryRepository.saveAndFlush(t);
	}

}
