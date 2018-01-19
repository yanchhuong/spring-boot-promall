package com.code.chat;

import java.security.Principal;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessagingTemplate;

import org.springframework.stereotype.Controller;

import com.code.model.LiveChatBean;
import com.code.model.UserSessionBean;
import com.code.service.ChatMessageService;
import com.code.session.SessionManager;



@Controller
public class MessageController {
  
  private SimpMessagingTemplate template;

  
  @Autowired
  private ChatMessageService chatMessageService;
  
  @Inject
  public MessageController(SimpMessagingTemplate template) {
    this.template = template;

  }

  @MessageMapping("/chat")
  public void greeting(Message<Object> message, @Payload LiveChatBean chatMessage) throws Exception {
    Principal principal = message.getHeaders().get(SimpMessageHeaderAccessor.USER_HEADER, Principal.class);
    String authedSender = principal.getName();
    chatMessage.setSender(authedSender);
    String recipient = chatMessage.getRecipient();
    
    String authedSender1= SimpMessageHeaderAccessor.getFirstNativeHeader("name", message.getHeaders());
    System.out.println("recipient:"+ recipient);
    System.out.println("authedSender1:"+ authedSender1);
    
    if (!authedSender.equals(recipient)) {
      template.convertAndSendToUser(authedSender.trim(), "/queue/messages", chatMessage);
    }
    System.out.println(chatMessage.toString());
   // chatMessageService.addMessage(chatMessage);
    template.convertAndSendToUser(recipient.trim(), "/queue/messages", chatMessage);
  }
  


}