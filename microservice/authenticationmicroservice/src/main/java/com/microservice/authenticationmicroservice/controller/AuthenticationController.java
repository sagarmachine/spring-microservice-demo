package com.microservice.authenticationmicroservice.controller;


import com.microservice.authenticationmicroservice.model.User;
import com.microservice.authenticationmicroservice.repo.UserRepository;
import com.netflix.discovery.converters.Auto;
import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import reactor.util.Loggers;

@RestController
public class AuthenticationController {

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    UserRepository userRepository;



    Logger logg= Logger.getLogger(AuthenticationController.class);

    @PostMapping(value = "/user")
     public String authenticate(@RequestBody User user){

        logg.info(user.toString());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
      userRepository.save(user);
         return "success";
    }

    @GetMapping(value = "/logout")
    public String logout(){
        return "Logout Successfull";
    }
}
