package com.microservice.authenticationmicroservice.repo;

//import  com.example.demo.enti


import com.microservice.authenticationmicroservice.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,String> {

    User findByEmail(String email);
	
	
}

