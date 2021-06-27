package com.ht.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.config.ConfigDataLocationNotFoundException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ht.entities.User;
import com.ht.repository.UserRepository;

@Service
@Transactional
public class CustomerServices {
//TU: Page doing Reset Pass
	@Autowired
	private UserRepository customerRepo;

//	Check Your's email have exits in DB ; if not show mess
	public void updateResetPasswordToken(String token, String email) throws UsernameNotFoundException {
		User customer = customerRepo.findByEmail(email);
		if (customer != null) {
			customer.setResetPasswordToken(token);
			customerRepo.save(customer);
		} else {
			throw new UsernameNotFoundException("Could not find any customer with the email " + email);
		}
	}

//give token send to user (form page: UserResposive)
	public User getByResetPasswordToken(String token) {
		return customerRepo.findByResetPasswordToken(token);
	}

//Update Pass because Pass Encoder by BCryptPassword + update Token == null in DB
	public void updatePassword(User customer, String newPassword) {
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String encodedPassword = passwordEncoder.encode(newPassword);
		customer.setPassword(encodedPassword);

		customer.setResetPasswordToken(null);
		customerRepo.saveAndFlush(customer);
	}
}