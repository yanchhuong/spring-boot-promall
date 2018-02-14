package com.code.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.code.model.UserSessionBean;
import com.code.service.UserService;
import com.code.session.SessionManager;

@RestController
@RequestMapping(value = "/test")
public class TestController {

	UserService userService;
	@Autowired
	public TestController(UserService userService) {
		this.userService=userService;
	}
	@RequestMapping(value = "/list_user_session", method = RequestMethod.GET)
	 public  @ResponseBody Map<String,Object> listRole(HttpServletRequest request, HttpServletResponse response) {
		UserSessionBean rec = SessionManager.getSession(request, response);
	        return new HashMap<String,Object>(){
	            {
	                put("OUT_REC",rec);
	                put("CODE","200");
	            }
	        };
	}
	
}
