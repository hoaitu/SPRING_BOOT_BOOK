package com.ht.service;

import java.time.LocalDate;

import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.ht.entities.User;

@Service
public class CustomerValidation implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void validate(Object target, Errors errors) {
		// TODO Auto-generated method stub

	}

//	@Override
//	public boolean supports(Class<?> clazz) {
//		return User.class.equals(clazz);
//	}
//
//	@Override
//	public void validate(Object target, Errors errors) {
//
//		ValidationUtils.rejectIfEmpty(errors, "username", "notempty.user.username");
//		ValidationUtils.rejectIfEmpty(errors, "password", "notempty.user.password");
//		ValidationUtils.rejectIfEmpty(errors, "address", "notempty.user.address");
//		ValidationUtils.rejectIfEmpty(errors, "dateOfBirth", "notempty.user.dateOfBirth");
//		ValidationUtils.rejectIfEmpty(errors, "phone", "notempty.user.phone");
//		ValidationUtils.rejectIfEmpty(errors, "email", "notempty.user.email");
//
//		User user = (User) target;
//		if (user.getPassword().length() < 8) {
//			errors.rejectValue("password", "password.length.user");
//		} else if (user.getPhone().length() < 10) {
//			errors.rejectValue("phone", "phone.user.notvalid.length");
//		}
//	}

}