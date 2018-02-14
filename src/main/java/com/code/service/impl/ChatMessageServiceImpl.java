package com.code.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.code.dao.IChatMessageRepository;
import com.code.model.LiveChatBean;
import com.code.model.UserListChatBean_In001;
import com.code.model.UserListChatBean_Out001;
import com.code.service.ChatMessageService;

@Service
public class ChatMessageServiceImpl implements ChatMessageService {

	@Autowired(required = false)
	IChatMessageRepository ChatMessageDao;


	@Override
	public void addMessage(LiveChatBean ChatMessage) {
		ChatMessageDao.addMessage(ChatMessage);
		
	}


	@Override
	public List<UserListChatBean_Out001> getUserListChat(UserListChatBean_In001 input) {
		// TODO Auto-generated method stub
		return this.ChatMessageDao.getUserListChat(input);
	}

}
