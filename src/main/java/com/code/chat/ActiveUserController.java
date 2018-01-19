package com.code.chat;

import java.security.Principal;

import javax.inject.Inject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;
import com.code.service.UserService;
import com.code.service.impl.ActiveUserService;

@Controller
public class ActiveUserController {
  private ActiveUserService activeUserService;
  @Autowired
  private UserService iUserDao;

  @Inject
  public ActiveUserController(ActiveUserService activeUserService){
    this.activeUserService = activeUserService;
  }
  @MessageMapping("/activeUsers")
  public void activeUsers(Message<Object> message) {
    Principal user = message.getHeaders().get(SimpMessageHeaderAccessor.USER_HEADER, Principal.class);
    String str1= SimpMessageHeaderAccessor.getFirstNativeHeader("login", message.getHeaders());
    String str2= SimpMessageHeaderAccessor.getFirstNativeHeader("usercd", message.getHeaders());
    String str4= SimpMessageHeaderAccessor.getFirstNativeHeader("Auth-Token", message.getHeaders());
    String str5= SimpMessageHeaderAccessor.getFirstNativeHeader("accept-version", message.getHeaders());
    String str6= SimpMessageHeaderAccessor.getFirstNativeHeader("name", message.getHeaders());

   // UserSessionBean dat = iUserDao.getSessionDao(user.getName());
    System.out.println("login:"+ str1);
    System.out.println("usercd:"+ str2);
    System.out.println("Auth-Token:"+ str4);
    System.out.println("accept-version:"+ str5);
    System.out.println("name:"+ str6);
    activeUserService.mark(user.getName());
  }
 
}