package com.code.dao.impl;

import java.util.List;

import javax.annotation.PostConstruct;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

import com.code.comm.ConnectionUtils;
import com.code.comm.SqlFormatUtils;
import com.code.dao.ChatMessageRepository;
import com.code.model.LiveChatBean;



@Repository
public class ChatMessageDaoImpl implements ChatMessageRepository{
	
	@Autowired
	protected DataSource dataSource;
	 
	 @PostConstruct
	 private void initialize(){
		 ConnectionUtils.setDataSource(dataSource);
	 }

	@Override
	public List<LiveChatBean> getMessages(int messageIndex) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public void addMessage(LiveChatBean input) {
		String sql = "INSERT INTO CHATMESSAGE " +"(message,sender,recipient,ip,dtm) VALUES (:message,:sender,:recipient,:ip,:dtm)" ;
        try{
			ConnectionUtils.getNamedParameterJdbcTemplate().update(sql,SqlFormatUtils.getSqlParameterSource(input));
		}catch(Exception e){
			
		}
	}

}
