package com.api_informers.security;

import java.io.IOException;
import java.util.Date;
import java.util.stream.Collectors;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

public class CustomAuthenticationFilter extends UsernamePasswordAuthenticationFilter{

	private AuthenticationManager authenticationManager;
	
	public CustomAuthenticationFilter(AuthenticationManager authenticationManager) { 
		this.authenticationManager = authenticationManager;
	}
	
	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
		UsernamePasswordAuthenticationToken authtoken = new UsernamePasswordAuthenticationToken(request.getParameter("username"), 
																								request.getParameter("password"));
		return this.authenticationManager.authenticate(authtoken);
	}

	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication auth) throws IOException, ServletException {
		
		User user = (User)auth.getPrincipal();
		Algorithm alg = Algorithm.HMAC256("secret".getBytes());
		String access_token = JWT.create()
								 .withSubject(user.getUsername())
								 .withExpiresAt(new Date(System.currentTimeMillis()+60*60*1000))
								 .withIssuer(request.getRequestURL().toString())
								 .withClaim("roles", user.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList()))
								 .sign(alg);
		
		String refresh_token = JWT.create()
				 .withSubject(user.getUsername())
				 .withExpiresAt(new Date(System.currentTimeMillis()+120*60*1000))
				 .withIssuer(request.getRequestURL().toString())
				 .withClaim("roles", user.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList()))
				 .sign(alg);
		
		response.setHeader("access_token", access_token);
		response.setHeader("refresh_token", refresh_token);
	}

	@Override
	protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException failed) throws IOException, ServletException {
		super.unsuccessfulAuthentication(request, response, failed);
	}	
}
