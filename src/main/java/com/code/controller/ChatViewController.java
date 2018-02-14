package com.code.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.code.dao.IChatMessageRepository;
import com.code.model.UserListChatBean_In001;
import com.code.model.UserListChatBean_Out001;

@Controller
public class ChatViewController {
	
	@Autowired
	private IChatMessageRepository iChatMessageRepository;
	
	@RequestMapping(value = "/message/get_userchat_list", method = RequestMethod.POST)
	public  @ResponseBody Map<String,Object> getUserDetail(@RequestBody UserListChatBean_In001 input){
		List<UserListChatBean_Out001> userList = this.iChatMessageRepository.getUserListChat(input);
		return new HashMap<String,Object>(){
	            {
	                put("OUT_REC", userList);
	            }
	        };
	}
	@RequestMapping(value = "/chatting",method = RequestMethod.GET)
	public String chatting(
			ModelMap model,HttpServletRequest request,RedirectAttributes redir) {
		return "index";
	}
	@RequestMapping(value = "/chat",method = RequestMethod.GET)
	public String realtimeChat(
			ModelMap model,HttpServletRequest request,RedirectAttributes redir) {
		return "realtimechat";
	}
	


}
