package com.ht.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ht.entities.UsersRoles;
import com.ht.repository.RoleRepository;

@Service
public class RoleService {

	@Autowired
	private RoleRepository relo;

	public void create(UsersRoles u) {
		relo.saveAndFlush(u);
	}
}
