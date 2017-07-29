package com.code.dao;

import java.util.List;

import com.code.model.LiveChatBean;


public interface ChatMessageRepository {
	public List<LiveChatBean> getMessages(int messageIndex);
	public void addMessage(LiveChatBean chatMessage) ;
    
}