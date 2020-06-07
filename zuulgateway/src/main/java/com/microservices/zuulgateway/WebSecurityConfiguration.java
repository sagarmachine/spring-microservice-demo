package com.microservices.zuulgateway;

import com.microservices.zuulgateway.filter.AuthenticationFilter;
import com.microservices.zuulgateway.util.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@Configuration
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    JWTUtil jwtUtil;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().antMatchers(HttpMethod.POST,"/authentication-microservice/**").permitAll()
                .antMatchers(HttpMethod.POST,"/user-microservice/user").permitAll()
        .anyRequest().authenticated();
                //.antMatchers(HttpMethod.POST,"/user-microservice/user").permitAll()
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.csrf()  .disable().headers().frameOptions().disable();
        http.addFilter(getAuthenticationFilter());
    }


    AuthenticationFilter getAuthenticationFilter()throws  Exception{
        return new AuthenticationFilter(jwtUtil,authenticationManager());
    }
}
