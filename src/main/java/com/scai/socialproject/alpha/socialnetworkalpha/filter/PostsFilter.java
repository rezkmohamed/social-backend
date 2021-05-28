package com.scai.socialproject.alpha.socialnetworkalpha.filter;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;


@Component
@Order(Ordered.LOWEST_PRECEDENCE)
public class PostsFilter extends OncePerRequestFilter{
	List<String> requestsWithIdRequired = new LinkedList<>();
	List<String> requestsWithoutIdRequired = new LinkedList<>();
	
	{
		requestsWithIdRequired.add("/posts/homepage");
		requestsWithIdRequired.add("/profiles/newpassword");
		
		
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
				System.out.println("la chiamata è sicura");
				filterChain.doFilter(request, response);
				return;
			}
			
			if(requestsWithoutIdRequired.contains(requestSubstring)) {
				
			}

		}		
		filterChain.doFilter(request, response);
	}
}
