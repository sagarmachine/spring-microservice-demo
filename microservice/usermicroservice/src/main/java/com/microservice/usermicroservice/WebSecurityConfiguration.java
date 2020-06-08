package com.microservice.usermicroservice;


import com.microservice.usermicroservice.service.IUserService;
import com.microservice.usermicroservice.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {


    @Autowired
    IUserService userService;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Value("${zuul.ipaddress}")
    String ipAddress;

    @Bean
    PasswordEncoder getPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }




    @Override
    protected void configure(HttpSecurity http) throws Exception{

        http.authorizeRequests().antMatchers("/console/**","/register/**").permitAll();
        http.headers().frameOptions().disable().and().csrf().disable();
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
//                anyRequest().authenticated()
           //  http.addFilter(getAuthenticationFilter());
////                .and()
//                .authorizeRequests().and().addFilter(getAuthenticationFilter());

    }

//    protected  AuthenticationFilter getAuthenticationFilter() throws Exception{
//        AuthenticationFilter authenticationFilter=new AuthenticationFilter();
//        authenticationFilter.setAuthenticationManager(authenticationManager());
//        return authenticationFilter;
//    }

//    @Override
//    protected  void configure(AuthenticationManagerBuilder auth)throws Exception{
//
//        auth.userDetailsService(userService).passwordEncoder(passwordEncoder);
//
//    }

}
