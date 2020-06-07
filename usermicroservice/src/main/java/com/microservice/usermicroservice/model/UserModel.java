package com.microservice.usermicroservice.model;

import lombok.Data;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


@Data
@Setter
public class UserModel {

	@NotNull
	@Size(min = 2, max = 20)
	String firstname;

	@Size(min = 2, max = 20)
	String lastname;

	@NotNull
	@Email
	String email;

	@NotNull
	@Size(min = 5)
	String password;

	String userId;

	public UserModel() {

	}

	public UserModel(@Size(min = 2, max = 20) String firstname, @Size(min = 2, max = 20) String lastname,
			@Email String email, @Size(min = 5) String password) {
		super();
		this.firstname = firstname;
		this.lastname = lastname;
		this.email = email;
		this.password = password;
	}


}
