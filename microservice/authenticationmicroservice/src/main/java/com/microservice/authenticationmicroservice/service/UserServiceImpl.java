package com.microservice.authenticationmicroservice.service;


import com.microservice.authenticationmicroservice.model.User;
import com.microservice.authenticationmicroservice.repo.UserRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.UUID;


@Service
public class UserServiceImpl implements IUserService{

	@Autowired
	UserRepository userrepo;

//	@Autowired
//	PasswordEncoder passwordEncoder;


//	@Override
//	public UserModel createUser(UserModel userModel) {
//
//		ModelMapper modelMapper = new ModelMapper();
//		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
//		User user=modelMapper.map(userModel,User.class);
//		user.setPassword(passwordEncoder.encode(userModel.getPassword()));
//		user.setUserid(UUID.randomUUID().toString());
//
//		userrepo.save(user);
//
//		userModel=modelMapper.map(user,UserModel.class);
//		return userModel;
//	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		User user = userrepo.findByEmail(username);

		if(user==null)
			throw  new UsernameNotFoundException(username);

		return new org.springframework.security.core.userdetails.User(user.getEmail(),user.getPassword(),new ArrayList<>());
	}

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
