package com.microservices.zuulgateway.filter;

import com.microservices.zuulgateway.util.JWTUtil;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;



@Data
public class AuthenticationFilter  extends BasicAuthenticationFilter {

    JWTUtil jwtUtil;

    AuthenticationManager authenticationManager;


//    public AuthenticationFilter(JWTUtil jwt){
//        this.jwtUtil=jwt;
//    }


    public AuthenticationFilter(JWTUtil jwt,AuthenticationManager authenticationManager){
        super(authenticationManager);
        this.jwtUtil=jwt;
    }
    Logger logg= Logger.getLogger(AuthenticationFilter.class);

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {



       String jwtToken= request.getHeader("Authentication");
       String username=request.getHeader("Username");
       String userID= request.getHeader("UserID");

       if(jwtToken !=null && username!=null && userID!=null && SecurityContextHolder.getContext().getAuthentication()==null) {

           String usernameJWT = jwtUtil.getUsernameFromToken(jwtToken);
           logg.info(username+"   "+usernameJWT);
           if(username.equals(usernameJWT)){
               SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(username,"",new ArrayList<>()));
           }

       }
       filterChain.doFilter(request,response);

    }
}
