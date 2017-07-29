package com.code.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.code.dao.ChatMessageRepository;
import com.code.model.LiveChatBean;
import com.code.service.ChatMessageService;

@Service
public class ChatMessageServiceImpl implements ChatMessageService {

	@Autowired(required = false)
	ChatMessageRepository ChatMessageDao;


	@Override
	public void addMessage(LiveChatBean ChatMessage) {
		ChatMessageDao.addMessage(ChatMessage);
		
	}

}
