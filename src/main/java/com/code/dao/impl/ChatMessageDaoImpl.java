package com.code.dao.impl;

import java.util.List;

import javax.annotation.PostConstruct;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

import com.code.dao.ChatMessageRepository;
import com.code.model.LiveChatBean;



@Repository
public class ChatMessageDaoImpl extends JdbcDaoSupport implements ChatMessageRepository{
	
	NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	@Autowired
	public void setNamedParameterJdbcTemplate(
		NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
		this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
	}
	@Autowired
	protected DataSource dataSource;
	 
	 @PostConstruct
	 private void initialize(){
	        setDataSource(dataSource);
	 }

	@Override
	public List<LiveChatBean> getMessages(int messageIndex) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public void addMessage(LiveChatBean chatMessage) {
		String sql = "INSERT INTO CHATMESSAGE " +"(message,sender,recipient,ip,dtm) VALUES (?,?,?,?,?)" ;
        this.getJdbcTemplate().update(sql, new Object[]{chatMessage.getMessage(),chatMessage.getSender(),chatMessage.getRecipient(),chatMessage.getIp(),chatMessage.getDate()});
		
	}

}
