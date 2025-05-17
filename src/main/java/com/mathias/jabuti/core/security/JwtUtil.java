package com.mathias.jabuti.core.security;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtUtil {

	@Value("${jwt.secret}")
	private String jwtSecret;

	public String generateToken(AuthUser authUser) {
		var authorities = authUser.getAuthorities().stream().map(auth -> auth.getAuthority()).toList();

		return Jwts.builder()
				.setSubject(authUser.getUsername())
				.claim("authorities", authorities)
				.claim("user_id", authUser.getUserId())
				.setIssuedAt(new Date())
				.setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60))
				.signWith(Keys.hmacShaKeyFor(jwtSecret.getBytes()), SignatureAlgorithm.HS256)
				.compact();
	}

	public String getJwtSecret() {
		return this.jwtSecret;
	}

	public Long extractUserId(String token) {
		return extractAllClaims(token).get("user_id", Long.class);
	}

	public List<String> getAuthorities(String token) {
		return (List<String>) extractAllClaims(token).get("authorities");
	}

	public Claims extractAllClaims(String token) {
		return Jwts.parserBuilder()
			.setSigningKey(Keys.hmacShaKeyFor(jwtSecret.getBytes()))
			.build()
			.parseClaimsJws(token)
			.getBody();
	}

}
