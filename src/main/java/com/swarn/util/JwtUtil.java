package com.swarn.util;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Base64;
import java.util.Date;
import java.util.UUID;

@Component
public class JwtUtil {
	
	public static final String SECRET = "asdfSFS34wfsdfsdfSDSD32dfsddDDerQSNCK34SOWEK5354fdgdf4";

	public String generateAccessToken()
	{
		String token = "";
		
		

		Key hmacKey = new SecretKeySpec(Base64.getDecoder().decode(SECRET), 
		                            SignatureAlgorithm.HS256.getJcaName());

		Instant now = Instant.now();
		String jwtToken = Jwts.builder()
		        .claim("name", "Jane Doe")
		        .claim("email", "jane@example.com")
		        .setSubject("jane")
		        .setId(UUID.randomUUID().toString())
		        .setIssuedAt(Date.from(now))
		        .setExpiration(Date.from(now.plus(5l, ChronoUnit.MINUTES)))
		        .signWith(hmacKey)
		        .compact();
		
		return jwtToken;
	}
	
	public static boolean validateAccessToken(String token)
	{
		Jws<Claims> jwt = parseJwt(token);
		Claims claims = jwt.getBody();
		String nameVal = claims.get("name", String.class);
		String emailVal = claims.get("email", String.class);
		
		System.out.println("#######: "+nameVal+" / "+emailVal);
		return true;
	}
	
	public static Jws<Claims> parseJwt(String jwtString) {
	    
	    Key hmacKey = new SecretKeySpec(Base64.getDecoder().decode(SECRET), 
	                                    SignatureAlgorithm.HS256.getJcaName());

	    Jws<Claims> jwt = Jwts.parserBuilder()
	            .setSigningKey(hmacKey)
	            .build()
	            .parseClaimsJws(jwtString);

	    return jwt;
	}
}
