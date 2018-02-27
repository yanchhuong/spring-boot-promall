package com.code.dao;

import java.util.List;

import com.code.model.GroupMessageBean_002;
import com.code.model.LiveChatBean;
import com.code.model.MessagesBean_001;
import com.code.model.UserListChatBean_In001;
import com.code.model.UserListChatBean_Out001;


public interface IChatMessageRepository {
	public List<LiveChatBean> getMessages(int messageIndex);
	public void addMessage(LiveChatBean chatMessage) ;
	public List<UserListChatBean_Out001> getUserListChat(UserListChatBean_In001 input);
	public void insertGroup(GroupMessageBean_002 input) ;
	public String getGroupcd(GroupMessageBean_002 input);
	public void insertJoin(GroupMessageBean_002 input);
	public List<MessagesBean_001> getChatMessages(String grpcd);
	public void insertChatMessage(MessagesBean_001 input);
//	public String getGroupCDValue(GroupMessageBean_002 input);
	
	//get user chat list
	public List<UserListChatBean_Out001> getUserChatList(UserListChatBean_In001 input);
}