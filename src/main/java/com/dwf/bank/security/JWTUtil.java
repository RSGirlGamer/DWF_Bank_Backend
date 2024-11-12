package com.dwf.bank.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.Claims;
import java.util.Date;
import org.springframework.stereotype.Component;

@Component
public class JWTUtil {
	private final String SECRET_KEY = "Hello74108520.";
	
	public String generateToken(String username) {
		return Jwts.builder()
				.setSubject(username)
				.setIssuedAt(new Date()).setExpiration(new Date(System.currentTimeMillis() +1000*60*60*10))
				.signWith(SignatureAlgorithm.HS256, SECRET_KEY)
				.compact();
	}
	
	public boolean validateToken(String token, String username) {
		return extractUsername(token).equals(username) && !isTokenExpired(token);
	}
	
	public String extractUsername(String token) {
		return getClaims(token).getSubject();
	}
	
	public Boolean isTokenExpired(String token){
		return getClaims(token).getExpiration().before(new Date());
	}
	
	private Claims getClaims(String token) {
		return Jwts.parser().setSigningKey("SECRET_KEY").parseClaimsJws(token).getBody();
	}
}
