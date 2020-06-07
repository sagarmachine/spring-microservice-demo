//package com.microservice.usermicroservice.filter;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.microservice.usermicroservice.model.LoginDto;
//import com.microservice.usermicroservice.model.User;
//import com.microservice.usermicroservice.repo.UserRepository;
//import com.microservice.usermicroservice.service.IUserService;
//import lombok.Data;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.AuthenticationException;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
//
//import javax.servlet.FilterChain;
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//
//
//@Data
//public class AuthenticationFilter extends UsernamePasswordAuthenticationFilter {
//
//    PasswordEncoder passwordEncoder;
//    UserRepository userRepository;
//    IUserService userService;
//    JwtU
//
//
//
//
//    @Override
//    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
//
//        try{
//        LoginDto loginDto= new ObjectMapper().readValue(request.getInputStream(),LoginDto.class);
//
//        return getAuthenticationManager().authenticate(new UsernamePasswordAuthenticationToken(loginDto.getEmail(),loginDto.getPassword()));
//
//        }catch(IOException ex){}
//        return null;
//    }
//
//    @Override
//    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
//
//        UserDetails userDetails= userService.loadUserByUsername(authResult.getName());
//        User user= userRepository.findByEmail(authResult.getName());
//        String userID=user.getUserid();
//
//        String jwtToken=
//
//    }
//}
