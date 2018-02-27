package com.code.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.time.DateFormatUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.code.dao.IChatMessageRepository;
import com.code.model.GroupMessageBean_002;
import com.code.model.MessagesBean_001;
import com.code.model.UserListChatBean_In001;
import com.code.model.UserListChatBean_Out001;

@Controller
public class ChatViewController {
	String grpcd = "";
	String chkgroup = "";
	
	@Autowired
	private IChatMessageRepository iChatMessageRepository;
	
	@RequestMapping(value = "/message/get_usercontact_list", method = RequestMethod.POST)
	public  @ResponseBody Map<String,Object> getUserDetail(@RequestBody UserListChatBean_In001 input){
		List<UserListChatBean_Out001> userList = this.iChatMessageRepository.getUserListChat(input);
		return new HashMap<String,Object>(){
            {
                put("OUT_REC", userList);
            }
        };
	}
	
	@RequestMapping(value = "/message/get_userchat_list", method = RequestMethod.POST)
	public  @ResponseBody Map<String,Object> getUserChatList(@RequestBody UserListChatBean_In001 input){
		List<UserListChatBean_Out001> userChatList = this.iChatMessageRepository.getUserChatList(input);
		return new HashMap<String,Object>(){
            {
                put("OUT_REC", userChatList);
            }
        };
	}
	
	@RequestMapping(value = "/message/insert_group", method = RequestMethod.POST)
	public  @ResponseBody Map<String,Object> insertChat(@RequestBody GroupMessageBean_002 input, HttpServletRequest request){
		grpcd = UUID.randomUUID().toString().substring(0,9);
		input.setGrpcd(grpcd);
		input.setRegdate(DateFormatUtils.format(new Date(), "yyyyMMddHHmmss"));
		List<String> userCD = new ArrayList<String>();
		userCD.add(input.getSendercd());
		userCD.add(input.getRecievecd());
		
		if(this.iChatMessageRepository.getGroupcd(input).equals("")) {
			this.iChatMessageRepository.insertGroup(input);
			for(String usercd : userCD) {
				input.setAccept(true);
				input.setSendercd(usercd);
				this.iChatMessageRepository.insertJoin(input);
			}
		}else {
			grpcd = this.iChatMessageRepository.getGroupcd(input);
			chkgroup = "yes";
		}
		return new HashMap<String,Object>(){
            {
                put("GRPCD", grpcd);
                put("CHKGROUP", chkgroup);
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
	
	
	
	@RequestMapping(value = "/message/list_chat", method = RequestMethod.POST)
	public  @ResponseBody Map<String,Object> getChatMessage(@RequestParam String grpcd){
		List<MessagesBean_001> chatmessage = this.iChatMessageRepository.getChatMessages(grpcd);
		return new HashMap<String,Object>(){
            {
                put("OUT_REC", chatmessage);
            }
        };
	}
	
	@RequestMapping(value = "/message/insert_chat", method = RequestMethod.POST)
	public  @ResponseBody Map<String,Object> insertChatMessage(@RequestBody MessagesBean_001 input){
		
		String currentUser = SecurityContextHolder.getContext().getAuthentication().getName();
		input.setMcd(currentUser);
		input.setRegdate(DateFormatUtils.format(new Date(), "yyyyMMddHHmmss"));
		this.iChatMessageRepository.insertChatMessage(input);
		return new HashMap<String,Object>(){
            {
                put("OUT_REC", "insert success");
            }
        };
	}
	


}
