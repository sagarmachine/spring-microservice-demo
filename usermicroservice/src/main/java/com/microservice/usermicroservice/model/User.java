package com.microservice.usermicroservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="user")
@Data
@AllArgsConstructor
public class User {

	
	@Id
	@Column(name="id")
	@GeneratedValue
	int id;

	
	@Column(name = "firstname",nullable = false)
	String firstname;
	
	@Column(name = "lastname")
	String lastname;

	@Column(name="email",nullable = false,unique = true)
	String email;
	
	@Column(name="userid",nullable = false,unique = true)
	String userid;


	
	
	
	public User() {}

//
//
//	public User(String email, String firstname, String lastname, String password, String userid) {
//		super();
//		this.email = email;
//		this.firstname = firstname;
//		this.lastname = lastname;
//		this.password = password;
//		this.userid = userid;
//	}


//
//	public int getId() {
//		return id;
//	}
//
//
//
//	public void setId(int id) {
//		this.id = id;
//	}
//
//
//
//	public String getPassword() {
//		return password;
//	}
//
//
//
//	public void setPassword(String password) {
//		this.password = password;
//	}
//
//
//
//	public String getUserid() {
//		return userid;
//	}
//
//
//
//	public void setUserid(String userid) {
//		this.userid = userid;
//	}
//
//
//
//	public String getEmail() {
//		return email;
//	}
//
//	public void setEmail(String email) {
//		this.email = email;
//	}
//
//	public String getFirstname() {
//		return firstname;
//	}
//
//	public void setFirstname(String firstname) {
//		this.firstname = firstname;
//	}
//
//	public String getLastname() {
//		return lastname;
//	}
//
//	public void setLastname(String lastname) {
//		this.lastname = lastname;
//	}
//
	
	
	
	
}
