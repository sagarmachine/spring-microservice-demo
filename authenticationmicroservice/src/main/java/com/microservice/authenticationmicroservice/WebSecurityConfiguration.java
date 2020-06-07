package com.microservice.authenticationmicroservice;

import com.microservice.authenticationmicroservice.filiter.AuthencationFilter;
import com.microservice.authenticationmicroservice.repo.UserRepository;
import com.microservice.authenticationmicroservice.service.IUserService;
import com.microservice.authenticationmicroservice.util.JWTUtil;
import com.netflix.discovery.converters.Auto;
import org.apache.http.client.AuthenticationStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.session.SessionAuthenticationStrategy;


@Configuration
@EnableWebSecurity
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    IUserService userService;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    JWTUtil jwtUtil;


    @Autowired
    UserRepository userRepository;


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().antMatchers(HttpMethod.POST,"/**").permitAll();
        http.csrf().disable()
        .addFilterAt(getAuthencationFilter(), UsernamePasswordAuthenticationFilter.class);
        http .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.headers().frameOptions().disable().and().csrf().disable();
    }

    @Bean
    AuthencationFilter getAuthencationFilter() throws  Exception{
        AuthencationFilter auth= new AuthencationFilter(jwtUtil,userService,userRepository);
        auth.setAuthenticationManager(authenticationManager());
        return auth;

    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService).passwordEncoder(passwordEncoder);
    }

    @Bean
    PasswordEncoder getPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }

}
