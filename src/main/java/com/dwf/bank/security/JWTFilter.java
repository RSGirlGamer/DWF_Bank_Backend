package com.dwf.bank.security;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import com.dwf.bank.models.User;
import com.dwf.bank.repositories.UserRepository;
import com.dwf.bank.services.UserService;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class JWTFilter extends OncePerRequestFilter {
	@Autowired
	private JWTUtil jwtUtil;
		
	@Autowired
	private UserRepository userRepository;
	
	@Override
	protected void doFilterInternal(HttpServletRequest request,HttpServletResponse response, FilterChain chain) 
	throws ServletException, IOException {
		String authorizationHeader = request.getHeader("Authorization");
		
		String username = null;
		String jwt = null;
		
		if(authorizationHeader !=null && authorizationHeader.startsWith("Bearer ")) {
			jwt = authorizationHeader.substring(7);
			username= jwtUtil.extractUsername(jwt);
		}
				
		if(username!= null && SecurityContextHolder.getContext().getAuthentication()==null) {
			User user = userRepository.findByUsername(username);
			
			if(user != null && jwtUtil.validateToken(jwt, user.getUsername())) {
				UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(user.getUsername(),null,List.of(new SimpleGrantedAuthority(user.getRole().toString())) );
				
				SecurityContextHolder.getContext().setAuthentication(authentication);

			}
	        
		}
		
		
	}
	
	
}
