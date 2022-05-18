package com.api_informers.config;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

public class AuthSuccessHandler implements AuthenticationSuccessHandler{

	private Integer maxTime = 300;
	
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
	        throws IOException,  ServletException {
	    request.getSession(false).setMaxInactiveInterval(this.maxTime); 
	    //response.sendRedirect(request.getContextPath()+"/messages");
	}
}
