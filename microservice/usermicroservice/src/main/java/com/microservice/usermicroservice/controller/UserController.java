package com.microservice.usermicroservice.controller;

import com.microservice.usermicroservice.model.UserModel;
import com.microservice.usermicroservice.service.IUserService;
import com.microservice.usermicroservice.util.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.validation.Valid;

import java.util.ArrayList;

import static org.springframework.http.MediaType.*;

@RestController
public class UserController {

	
	@Autowired
	RestTemplate restTemplate;

	@Autowired
	IUserService userservice;

	@Autowired
	JWTUtil jwtUtil;

	
	@RequestMapping(value ="/")
	public String test() {
		return "sucess";
	}
	
	@GetMapping(value = "/details/{id}")
	public String getServiceDetails(@PathVariable int id) {
if (id == 2) {
			
	return restTemplate.getForObject("http://demo-microservice/details/2", String.class);
	
			

		} else
			return "user functionality is managed by me";
	}


	@PostMapping(value="/user",consumes ={APPLICATION_JSON_VALUE})
	public ResponseEntity<String> createUser(@Valid @RequestBody UserModel userModel){

		User user= new User(userModel.getEmail(),userModel.getPassword(),new ArrayList<>());

	UserModel userModel1=	userservice.createUser(userModel);

	String jwtToken=jwtUtil.generateToken(user);

		HttpHeaders httpHeaders= new HttpHeaders();
		httpHeaders.add("Authentication",jwtToken);
		httpHeaders.add("UserID","TEST1");

	ResponseEntity<String> responseEntity= new ResponseEntity<>("SUCCESS",httpHeaders, HttpStatus.CREATED);


	return responseEntity;
	}



}
