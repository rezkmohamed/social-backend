package com.scai.socialproject.alpha.socialnetworkalpha.utils;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;

@Component
public class RequestUtils {
	@Value("${signingKey}")
	private String signingKey;
	
	public String idProfileFromToken(HttpServletRequest request) {
		String header = request.getHeader("Authorization");
		String token = header.replace("Bearer ", "");
		Jws<Claims> result =  Jwts.parser().setSigningKey(signingKey).parseClaimsJws(token);		
		String idProfile = result.getBody().get("idUser", String.class);
		
		return idProfile;
	}
	
	public String idProfileFromToken(String token) {
		token = token.replace("Bearer ", "");
		token = token.replace("\"","");
		
		Jws<Claims> result =  Jwts.parser().setSigningKey(signingKey).parseClaimsJws(token);		
		String idProfile = result.getBody().get("idUser", String.class);

		return idProfile;
	}
	
}
