package com.ht.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ht.entities.UsersRoles;

public interface RoleRepository extends JpaRepository<UsersRoles, Long> {

}
