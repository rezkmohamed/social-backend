package com.scai.socialproject.alpha.socialnetworkalpha.filter;

import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;


//@Component
@Order(Ordered.LOWEST_PRECEDENCE)
public class PostsFilter extends OncePerRequestFilter{
	List<String> requestsWithIdRequired = new LinkedList<>();
	List<String> requestsWithoutIdRequired = new LinkedList<>();
	
	{
		requestsWithIdRequired.add("/posts/homepage");
		
		
		requestsWithoutIdRequired.add("/posts");
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		if(request.getHeader("Authorization") != null) {
			String header = request.getHeader("Authorization");

			String token = header.replace("Bearer ", "");
			Jws<Claims> result =  Jwts.parser().setSigningKey("ciao").parseClaimsJws(token);

			int position = request.getRequestURI().lastIndexOf("/");
			String requestSubstring = request.getRequestURI().substring(0, position);
			String idRequest = request.getRequestURI().substring(position + 1);
			String idProfile = result.getBody().get("idUser", String.class);

			System.out.println(requestSubstring);
			if(requestsWithIdRequired.contains(requestSubstring)) {
				if(!idRequest.equals(idProfile)) {
					response.sendError(401);
					return;
				}
				System.out.println("la chiamata è sicura");
				filterChain.doFilter(request, response);
				return;
			} 
			
			else if(requestsWithoutIdRequired.contains(requestSubstring)) {
				if("POST".equalsIgnoreCase(request.getMethod()) || "PUT".equalsIgnoreCase(request.getMethod())) {
					System.out.println("request without id required");
					request.getAttribute("post");

					Map<String, String> bodyResponse =  getBodyRequest(request);
					String bodyResponseId = bodyResponse.get("\"idProfile\"");
					bodyResponseId = bodyResponseId.substring(1, bodyResponseId.length()-1);
					System.out.println(bodyResponseId);
					if(!bodyResponseId.equals(idProfile)) {
						response.sendError(401);
						return;
					}
					
					System.out.println("la chiamata è sicura");
					filterChain.doFilter(request, response);
					return;
				}
			}
			
			
		}		
		filterChain.doFilter(request, response);
	}
	
	static String extractPostRequestBody(HttpServletRequest request) {
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
	
	static Map<String, String> getBodyRequest(HttpServletRequest request) throws IOException {
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
	}
}
