package com.code.dao;

import java.util.List;


import com.code.model.ChatMessageModel;


 
public interface ChatMessageRepository {
	public List<ChatMessageModel> getMessages(int messageIndex);
	public void addMessage(ChatMessageModel ChatMessageModel) ;
    
}