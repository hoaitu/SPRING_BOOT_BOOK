package com.ht.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ht.entities.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

//	TU: Used for Check Email User have Exits in DB in Page Regester(dang ky)
	@Query(value = "SELECT c FROM User c where  c.username = :username")
	public User findByUserName(@Param("username") String username);

//	Tu moi them 09/06; 
//	 Using reset Pass

//	TU: Using reset Pass
	public User findByResetPasswordToken(String token);

//	------------------
/////////// LAY USER THEO EMAIL DE HIEN THI TRONG TRANG THONG TIN TAI KHOAN
//	Tu new rename findByEmail_Admin : 14/06.
	@Query(value = "SELECT u FROM User u WHERE u.email = :email")
	public User findByEmail(String email);

//	public abstract User findByEmail(@Param("email") String email);
//	public abstract User findByEmail_Admin(@Param("email") String email);

	User findByUsernameAndPassword(String username, String password);

/////////////TIM THEO ID THUC HIEN CHUC NANG SUA, XOA
	@Query(value = "Select u From User u where u.idUser =:ids")
	public abstract User findByUser(@Param("ids") long id);

	// User name : is email
	User findByUsername(String username);
}
