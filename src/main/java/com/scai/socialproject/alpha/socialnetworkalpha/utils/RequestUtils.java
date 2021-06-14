package com.scai.socialproject.alpha.socialnetworkalpha.utils;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import javax.servlet.http.HttpServletRequest;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;

public class RequestUtils {
	
	public static String idProfileFromToken(HttpServletRequest request) {
		String header = request.getHeader("Authorization");
		String token = header.replace("Bearer ", "");
		Jws<Claims> result =  Jwts.parser().setSigningKey("ciao").parseClaimsJws(token);		
		String idProfile = result.getBody().get("idUser", String.class);
		return idProfile;
	}
	/*
	private static String extractPostRequestBody(HttpServletRequest request) {
	    if ("POST".equalsIgnoreCase(request.getMethod())) {
	        Scanner s = null;
	        try {
	            s = new Scanner(request.getInputStream(), "UTF-8").useDelimiter("\\A");
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	        return s.hasNext() ? s.next() : "";
	    }
	    return "";
	}
	
	public static Map<String, String> getBodyRequest(HttpServletRequest request) throws IOException {
		Map<String, String> ris = new HashMap<>();
		String s = extractPostRequestBody(request);
		s = s.substring(1,s.length()-1);
		System.out.println(s);
		String[] arrayOfResponse = s.split(",");
		
		for(String response : arrayOfResponse) {
			String[] couple = response.split(":");
			ris.put(couple[0], couple[1]);
		}
		
		return ris;
	}*/
}
