package com.microservice.authenticationmicroservice.filiter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.microservice.authenticationmicroservice.model.LoginDto;
import com.microservice.authenticationmicroservice.model.User;
import com.microservice.authenticationmicroservice.repo.UserRepository;
import com.microservice.authenticationmicroservice.service.IUserService;
import com.microservice.authenticationmicroservice.util.JWTUtil;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@Data
@AllArgsConstructor
public class AuthencationFilter extends UsernamePasswordAuthenticationFilter{


    JWTUtil jwtUtil;


    IUserService userService;


    UserRepository userRepository;



    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {

        try {

            LoginDto loginDto = new ObjectMapper().readValue(request.getInputStream(), LoginDto.class);

            return getAuthenticationManager().authenticate(new UsernamePasswordAuthenticationToken(loginDto.getEmail(),loginDto.getPassword()));

        }catch(IOException io){}
        return null;

    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {

        User user = userRepository.findByEmail(authResult.getName());


        UserDetails userDetails = userService.loadUserByUsername(authResult.getName());

        String jwtToken = jwtUtil.generateToken(userDetails);

        response.setHeader("Authentication",jwtToken);
        response.setHeader("UserID",user.getUserId());

        chain.doFilter(request,response);


    }
}

