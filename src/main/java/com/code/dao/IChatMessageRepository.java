package com.code.dao;

import java.util.List;

import com.code.model.LiveChatBean;
import com.code.model.UserListChatBean_In001;
import com.code.model.UserListChatBean_Out001;


public interface IChatMessageRepository {
	public List<LiveChatBean> getMessages(int messageIndex);
	public void addMessage(LiveChatBean chatMessage) ;
	public List<UserListChatBean_Out001> getUserListChat(UserListChatBean_In001 input);
}