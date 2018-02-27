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
import com.code.comm.SqlFormatUtils;
import com.code.dao.IChatMessageRepository;
import com.code.model.GroupMessageBean_001;
import com.code.model.GroupMessageBean_002;
import com.code.model.LiveChatBean;
import com.code.model.MessagesBean_001;
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
		String sql =  " select a.username as chatId,\r\n "+
					" 		b.\"role\", a.usercd, \r\n "+
					" 		INITCAP( CONCAT (c.fname,' ',c.lname)) AS fullname,\r\n "+
					" 		(select logout_time from log_histr where usercd= '' order by regdate desc limit 1) as logoutTime,\r\n "+
					"		d.randname\r\n "+
					" from users a \r\n "+
					" 	left join user_roles b on a.usercd  = b.usercd\r\n "+
					" 	left join user_detail c on a.usercd = c.usercd\r\n "+
					" 	left join filepicture d on a.usercd = d.usercd\r\n "+
					" where 1=1 and enabled= true ";
	StringBuffer dQuery = new StringBuffer(sql);
	Map<String, Object> params = new HashMap<String, Object>();
	params.put("usercd", input);
	List<UserListChatBean_Out001> result  = null;
	try{
		result  = ConnectionUtils.getNamedParameterJdbcTemplate().query(dQuery.toString(), params,
				new BeanPropertyRowMapper<UserListChatBean_Out001>(UserListChatBean_Out001.class));
	}catch(Exception e){
		System.out.println("error sql: "+dQuery.toString());
	}
	return result;
	}

	@Override
	public void insertGroup(GroupMessageBean_002 input) {
	
		String sql = "insert into group_message \r\n" + 
					"(grpcd, regdate, grpname, usercd)\r\n" + 
					"values (:grpcd, :regdate, :grpname, :sendercd)";
		try{
			ConnectionUtils.getNamedParameterJdbcTemplate().update(sql,SqlFormatUtils.getSqlParameterSource(input));
		}catch(Exception e){
			System.out.println("error sql: "+sql);
        }
	}

	@Override
	public String getGroupcd(GroupMessageBean_002 input) {
		String recievecd = input.getRecievecd().replace("\"", "");
		String sql =  "select grpcd from join_group	\r\n" + 
				"		where grpcd in (select  jg.grpcd from join_group jg\r\n" + 
				"			join group_message gm on gm.grpcd =  jg.grpcd\r\n" + 
				"				where jg.usercd = :sendercd) \r\n" + 
				"			and usercd <> :sendercd \r\n" + 
				"			and usercd = '"+recievecd+"'";
		
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("sendercd", input.getSendercd());
		params.put("sendercd", input.getSendercd());
//		params.put("recievecd", input.getRecievecd());
		List<UserListChatBean_Out001> result  = null;
		String grpcd="";
		try{
			result  = ConnectionUtils.getNamedParameterJdbcTemplate().query(sql, params,
					new BeanPropertyRowMapper<UserListChatBean_Out001>(UserListChatBean_Out001.class));
			if(result!=null) {
				for(UserListChatBean_Out001 grpcdObj : result) {
					grpcd = grpcdObj.getGrpcd();
				}
			}
		}catch(Exception e){
			 System.out.println("error sql: "+sql);
		}
		return grpcd;
	}

	@Override
	public void insertJoin(GroupMessageBean_002 input) {
	
		String sql = "insert into join_group \r\n" + 
					"(usercd, grpcd, regdate, accept)\r\n" + 
					"values (:sendercd, :grpcd, :regdate, :accept)";
		try{
			ConnectionUtils.getNamedParameterJdbcTemplate().update(sql,SqlFormatUtils.getSqlParameterSource(input));
		}catch(Exception e){
			System.out.println("error sql: "+sql);
        }
	}

	@Override
	public List<UserListChatBean_Out001> getUserChatList(UserListChatBean_In001 input) {
		String sql = " select a.username as chatId, b.\"role\", a.usercd, j2.grpcd, \r\n" + 
					"		   INITCAP( CONCAT (c.fname,' ',c.lname)) AS fullname,\r\n" + 
					"		   (select logout_time from log_histr where usercd= '' order by regdate desc limit 1) as logoutTime,\r\n" + 
					"		   d.randname\r\n" + 
					" from users a \r\n" + 
					"   left join join_group j2 on a.usercd = j2.usercd \r\n" +
					"	left join user_roles b on a.usercd = b.usercd \r\n" + 
					"	left join user_detail c on a.usercd= c.usercd\r\n" + 
					"	left join filepicture d on a.usercd = d.usercd\r\n" + 
					"		where j2.grpcd in ( \r\n" + 
					"	   			select j.grpcd  from join_group j\r\n" + 
					"			           left join messages m on m.grpcd = j.grpcd   \r\n" + 
					"		    	where j.grpcd in (\r\n" + 
					"		    	                    select  jg.grpcd from join_group jg\r\n" + 
					"				                             join group_message gm on gm.grpcd =  jg.grpcd\r\n" + 
					" 						            where jg.usercd = :usercd)\r\n" + 
					" 			and m.reply <> ' '\r\n" + 
					"			and j.usercd <> :usercd order by m.regdate)\r\n"+
					"			and a.usercd <> :usercd";
		StringBuffer dQuery = new StringBuffer(sql);
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("usercd", input.getUsercd());
		List<UserListChatBean_Out001> result  = null;
		try{
			result  = ConnectionUtils.getNamedParameterJdbcTemplate().query(dQuery.toString(), params,
					new BeanPropertyRowMapper<UserListChatBean_Out001>(UserListChatBean_Out001.class));
		}catch(Exception e){
			System.out.println("error sql: "+dQuery.toString());
		}
		return result;
}

	@Override
	public List<MessagesBean_001> getChatMessages(String grpcd) {
		String sql = "	select  reply, mcd, grpcd, usercd, \r\n" + 
				"			fullname, regdate, ip \r\n" + 
				"		from messages \r\n" + 
				"		where grpcd = :grpcd \r\n"+ 
				"		order by regdate";

	Map<String, Object> params = new HashMap<String, Object>();
	params.put("grpcd", grpcd);
	
	List<MessagesBean_001> result  = null;
	try{
		result  = ConnectionUtils.getNamedParameterJdbcTemplate().query(sql, params,
				new BeanPropertyRowMapper<MessagesBean_001>(MessagesBean_001.class));
	}catch(Exception e){
		System.out.println("error sql: "+sql);
	}
	return result;
}

	@Override
	public void insertChatMessage(MessagesBean_001 input) {
		String sql = "insert into messages (reply, mcd, grpcd, usercd, fullname, regdate, ip)\r\n" + 
					 "		 values	(:reply, :mcd, :grpcd, :usercd, :fullname, :regdate, :ip)" ;
        try{
			ConnectionUtils.getNamedParameterJdbcTemplate().update(sql,SqlFormatUtils.getSqlParameterSource(input));
		}catch(Exception e){
			System.out.println("error sql: "+sql);
		}
	}

}
