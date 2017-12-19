package com.code.config;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import com.code.model.UserSessionBean;
import com.code.service.UserService;
import com.code.session.SessionManager;

public class SuccessLoginHandler implements AuthenticationSuccessHandler{
	@Autowired
	private UserService iUserDao;
	SuccessLoginHandler(UserService iUserDao){
		this.iUserDao=iUserDao;
	}
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication arg2)
			throws IOException, ServletException {
		HttpSession session = request.getSession();
		String currentUser = SecurityContextHolder.getContext().getAuthentication().getName();
		UserSessionBean user = iUserDao.getSessionDao(currentUser);
		SessionManager.setSession(request, response, user);
		
		UserSessionBean bb=SessionManager.getSession(request, response);
		
		System.out.println(bb.getUsercd());
	
		
	}

}
