package com.microservice.usermicroservice.service;


import com.microservice.usermicroservice.model.User;
import com.microservice.usermicroservice.model.UserModel;
import com.microservice.usermicroservice.repo.UserRepository;
import org.jboss.logging.Logger;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.UUID;


@Service
public class UserServiceImpl implements IUserService{

	@Autowired
	UserRepository userrepo;

	@Autowired
	PasswordEncoder passwordEncoder;


	@Autowired
	RestTemplate restTemplate;

	Logger logg= Logger.getLogger(UserServiceImpl.class);

	@Override
	public UserModel createUser(UserModel userModel) {

		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		User user=modelMapper.map(userModel,User.class);
		user.setUserid(UUID.randomUUID().toString());

		userrepo.save(user);
		userModel.setUserId(user.getUserid());

logg.info(userModel.toString());
		ResponseEntity<String> result= restTemplate.postForEntity("http://authentication-microservice/user",userModel,String.class);

	logg.info(result.getBody());

		return userModel;
	}

//	@Override
//	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//
//		User user = userrepo.findByEmail(username);
//
//		if(user==null)
//			throw  new UsernameNotFoundException(username);
//
//		return new org.springframework.security.core.userdetails.User(user.getEmail(),user.getPassword(),new ArrayList<>());
//	}

//	@Autowired
//	UserRepository userrepo;
//
//	@Override
//	public UserModel createUser(UserModel userModel) {
//
//		String userid= UUID.randomUUID().toString();
//
//	ModelMapper modelMapper= new ModelMapper();
//	modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
//
//		org.apache.catalina.User user = modelMapper.map(userModel,User.class);
//
//		user.setUserid(userid);
//		user.setPassword("Encrypted Pass");
//
//		userrepo.save(user);
//
//		UserModel userdto=modelMapper.map(user,UserModel.class);
//
//
		
		
		
//		return userdto;
//	}
	
	

}
