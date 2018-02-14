package com.code.config;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.AuthenticationEntryPoint;

public class RESTAuthenticationEntryPoint implements AuthenticationEntryPoint {
	private static  Logger LOGGER =  LoggerFactory.getLogger(CustomAccessDeniedHandler.class);
	
	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException)
			throws IOException, ServletException {
		 Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		
 	   LOGGER.warn("User: " + auth.getName() 
       + " attempted to access the protected URL: "
       + request.getRequestURI());
 		System.out.println("THE CUSTOM AUTHENTICATION ENTRY POINT");
 	   
		response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
	}
}