package com.code.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import com.code.comm.ConnectionUtils;
import com.code.comm.PagingUtils;
import com.code.comm.SqlFormatUtils;
import com.code.dao.IChatMessageRepository;
import com.code.model.LiveChatBean;
import com.code.model.MUserListOut_R001;
import com.code.model.UserListChatBean_In001;
import com.code.model.UserListChatBean_Out001;



@Repository
public class ChatMessageDaoImpl implements IChatMessageRepository{
	
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

	@Override
	public List<UserListChatBean_Out001> getUserListChat(UserListChatBean_In001 input) {
		String sql =  " select a.username as chatId,"
                     + "\r\n b.\"role\","
                     + "\r\n INITCAP( CONCAT (c.fname,' ',c.lname)) AS fullname,"
                     + "\r\n (select logout_time from log_histr where usercd= '' order by regdate desc limit 1) as logoutTime,"
                     + "\r\n d.randname"
		             + "\r\n from users a "
		           	 + "\r\n left join user_roles b on a.usercd = b.usercd" 
		           	 + "\r\n left join user_detail c on a.usercd= c.usercd"
		           	 + "\r\n left join filepicture d on a.usercd = d.usercd"
		           	 + "\r\n where 1=1 and enabled= true ";
	StringBuffer dQuery = new StringBuffer(sql);
	Map<String, Object> params = new HashMap<String, Object>();
	params.put("usercd", input);
	List<UserListChatBean_Out001> result  = null;
	try{
		result  = ConnectionUtils.getNamedParameterJdbcTemplate().query(dQuery.toString(), params,
				new BeanPropertyRowMapper<UserListChatBean_Out001>(UserListChatBean_Out001.class));
	}catch(Exception e){
	}
	return result;
	}

}
