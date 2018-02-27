package com.code.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.code.dao.IChatMessageRepository;
import com.code.model.GroupMessageBean_001;
import com.code.model.GroupMessageBean_002;
import com.code.model.LiveChatBean;
import com.code.model.MessagesBean_001;
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

	@Override
	public void insertGroup(GroupMessageBean_002 input) {
		this.ChatMessageDao.insertGroup(input);
	}


	@Override
	public String getGroupcd(GroupMessageBean_002 input) {
		return this.ChatMessageDao.getGroupcd(input);
	}


	@Override
	public void insertJoin(GroupMessageBean_002 input) {
		this.ChatMessageDao.insertJoin(input);
	}


	@Override
	public List<UserListChatBean_Out001> getUserChatList(UserListChatBean_In001 input) {
		return this.ChatMessageDao.getUserChatList(input);
	}


	@Override
	public List<MessagesBean_001> getChatMessages(String grpcd) {
		return this.ChatMessageDao.getChatMessages(grpcd);
	}


	@Override
	public void insertChatMessage(MessagesBean_001 input) {
		this.ChatMessageDao.insertChatMessage(input);
	}

}
