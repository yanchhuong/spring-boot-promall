package com.code.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.code.model.UserSessionBean;
import com.code.service.UserService;
import com.code.session.SessionManager;

@Controller
public class SessionControler {
	@Autowired
	private UserService iUserDao;
	
	@RequestMapping(value = "/set_sesssion")
	public void setSession(HttpServletResponse response,HttpServletRequest request) {
		String currentUser = SecurityContextHolder.getContext().getAuthentication().getName();
		UserSessionBean user = iUserDao.getSessionDao(currentUser);
		SessionManager.setSession(request, response, user);
	}
	
	@RequestMapping(value = "/get_sesssion", method = RequestMethod.GET , produces=MediaType.APPLICATION_JSON_VALUE)
	public@ResponseBody Map<String,Object> getSession(HttpServletResponse response,HttpServletRequest request) {
		UserSessionBean user = SessionManager.getSession(request, response);
		 return new HashMap<String,Object>(){
	            {
	                put("SESSION_IS",user);
	            }
	        };
	}
	
	@RequestMapping(value = "/clear_session", method = RequestMethod.GET , produces=MediaType.APPLICATION_JSON_VALUE)
	public void getClear(HttpServletResponse response,HttpServletRequest request) {
		SessionManager.logout(request, response);
	}

}
