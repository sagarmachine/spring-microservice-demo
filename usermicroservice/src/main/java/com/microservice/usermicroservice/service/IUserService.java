package com.microservice.usermicroservice.service;


import com.microservice.usermicroservice.model.UserModel;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface IUserService  {

	
	UserModel createUser(UserModel userModel);
	
}
