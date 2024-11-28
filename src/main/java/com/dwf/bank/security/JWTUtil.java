package com.dwf.bank.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.Claims;
import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class JWTUtil {
	@Value("${jwt.secret}")
	private String secretKey;

	@Value("${jwt.expiration}")
	private long expirationTime;

	public String generateToken(String username, String role) {
		return Jwts.builder()
				.setSubject(username)
				.claim("role", "ROLE_" + role)
				.setIssuedAt(new Date())
				.setExpiration(new Date(System.currentTimeMillis() + expirationTime))
				.signWith(SignatureAlgorithm.HS256, secretKey)
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

	public Claims getClaims(String token) {
		return Jwts.parser()
				.setSigningKey(secretKey)
				.parseClaimsJws(token)
				.getBody();
	}
}
