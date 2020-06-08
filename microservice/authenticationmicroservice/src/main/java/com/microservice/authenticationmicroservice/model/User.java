package com.microservice.authenticationmicroservice.model;


import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "UserCredentials")
@Data
public class User {

    @Id
    @Column(name="id")
    @GeneratedValue
    int id;

    @Column(name = "email",nullable = false,unique = true)
    String email;

    @Column(name="password",nullable = false)
    String password;

    @Column(name="userid",nullable = false,unique = true)
    String userId;




}

