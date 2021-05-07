package com.scai.socialproject.alpha.socialnetworkalpha.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.Jwts;

//@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class AuthFilter extends OncePerRequestFilter {

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		if (HttpMethod.OPTIONS.name().equalsIgnoreCase(request.getMethod())) {
			filterChain.doFilter(request, response);
			return;
		}
		
		if ("/login".equalsIgnoreCase(request.getRequestURI()) ||
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
		Jwts.parser().setSigningKey("ciao").parseClaimsJws(token).getBody();
		filterChain.doFilter(request, response);
	}
	
}
