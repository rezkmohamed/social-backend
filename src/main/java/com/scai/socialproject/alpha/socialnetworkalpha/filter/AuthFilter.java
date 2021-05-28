package com.scai.socialproject.alpha.socialnetworkalpha.filter;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;

@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class AuthFilter extends OncePerRequestFilter {
	List<String> requestsWithIdRequired = new LinkedList<>();

	{
		requestsWithIdRequired.add("/posts/homepage");
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		if (HttpMethod.OPTIONS.name().equalsIgnoreCase(request.getMethod())) {
			filterChain.doFilter(request, response);
			return;
		}
		
		if (
				"/login".equalsIgnoreCase(request.getRequestURI()) ||
				"/register".equalsIgnoreCase(request.getRequestURI()) ||
				"/resetpassword".equalsIgnoreCase(request.getRequestURI())) {
			filterChain.doFilter(request, response);
			return;
		}
		
		String header = request.getHeader("Authorization");
		if (header == null || !header.startsWith("Bearer ")) {
			response.sendError(401);
			return;
		}
		
		
		String token = header.replace("Bearer ", "");
		Jws<Claims> result =  Jwts.parser().setSigningKey("ciao").parseClaimsJws(token);
		
		int position = request.getRequestURI().lastIndexOf("/");
		//System.out.println(position);
		String requestSubstring = request.getRequestURI().substring(0, position);
		String idRequest = request.getRequestURI().substring(position + 1);
		//System.out.println(idRequest);
		//System.out.println(requestSubstring);
		if(requestsWithIdRequired.contains(requestSubstring)) {
			String idProfile = result.getBody().get("idUser", String.class);
			if(!idRequest.equals(idProfile)) {
				response.sendError(401);
				return;
			}
		}
		else {
			System.out.println(request.getRequestURI());
			System.out.println("request doesn't require id");
		}
		
		filterChain.doFilter(request, response);
	}
	
}
