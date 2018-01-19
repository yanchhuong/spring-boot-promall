package com.code.config;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CustomLoginFailureHandler extends SimpleUrlAuthenticationFailureHandler {
	CustomLoginFailureHandler(){}

	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
		String message = "";

		if(exception.getClass() == UsernameNotFoundException.class) {
			message = "cannot find a user";
		} else if(exception.getClass() == BadCredentialsException.class) {
			message = "check your password";
		}
		
		super.onAuthenticationFailure(request, response, exception);
	//	request.getRequestDispatcher(String.format("/?message=%s", message)).forward(request, response);
	//	 response.sendRedirect(request.getContextPath() + "/login?message="+message);
	}
}