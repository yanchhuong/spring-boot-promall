package com.code.dao.impl;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

import com.code.dao.UserDao;
import com.code.model.UserDetailBean;

@Repository
public class UserDaoImpl extends JdbcDaoSupport implements UserDao{

	@Autowired
	protected DataSource dataSource;
	 
	 @PostConstruct
	 private void initialize(){
	        setDataSource(dataSource);
	 }
	    //...
	
	@Override
	public void insertRole(UserDetailBean user) {
		String sql = "INSERT INTO USER_ROLES " +"(username,role) VALUES (?,?)" ;
        this.getJdbcTemplate().update(sql, new Object[]{user.getUsername(),user.getRole()});
		
	}
	@Override
	public void insertUserLog(UserDetailBean user) {
		String sql = "INSERT INTO USERS " +"(usercd,username,password) VALUES (?,?,?)" ;
        this.getJdbcTemplate().update(sql, new Object[]{user.getUserCd(),user.getUsername(),user.getPassword()});
        
        
		
	}
	@Override
	public void insertUserDetail(UserDetailBean user) {
		String sql = "INSERT INTO USER_DETAIL " +"(regdate,fname,lname,username_fk,email,usercd) VALUES (?,?,?,?,?,?)" ;
        this.getJdbcTemplate().update(sql, new Object[]{user.getRegisterDate(),user.getFirst(),user.getLast()
        		,user.getUsername(),user.getEmail(),user.getUserCd()});
		
	}
	
	//insert batch
	public void inserBatch(List<UserDetailBean> batch){
	    String sql = "INSERT INTO USER_DETAIL " + "(fname, lname) VALUES ( ?, ?)";
	    getJdbcTemplate().batchUpdate(sql, new BatchPreparedStatementSetter() {
	        public void setValues(PreparedStatement ps, int i) throws SQLException {
	        	UserDetailBean rec = batch.get(i);
	            ps.setString(1, rec.getFirst());
	            ps.setString(2, rec.getLast());
	        }
	 
	        public int getBatchSize() {
	            return batch.size();
	        }
	    });
	}
	
	// for example list all
	public List<UserDetailBean> listUser(){
		  String sql = "SELECT usercd,username FROM USERS";
		    List< Map < String, Object>> rows = this.getJdbcTemplate().queryForList(sql);
		    List<UserDetailBean> result = new ArrayList<UserDetailBean>();
		    for(Map <String, Object> row:rows){
		    	UserDetailBean rec = new UserDetailBean();
		    	rec.setUserCd((String)row.get("usercd"));
		        rec.setUsername((String)row.get("username"));
		        result.add(rec);
		    }
		    return result;
		
    }
	
	
	public UserDetailBean SearchByname(String firstname) {
		String sql = "SELECT * FROM USER_DETAIL WHERE fname = ? ORDER BY detid";
	     return (UserDetailBean)this.getJdbcTemplate().queryForObject(sql, new Object[]{firstname}, new RowMapper<UserDetailBean>(){
	         @Override
	         public UserDetailBean mapRow(ResultSet rs, int rwNumber) throws SQLException {
	        	 UserDetailBean cust = new UserDetailBean();
	             cust.setId(rs.getInt("id"));
	             cust.setFirst(rs.getString("fname"));
	             cust.setLast(rs.getString("lname"));
	             return cust;
	        }
	    });
	}
	
	// delete 
	public void delete(long id) {
		 String sql = "DELETE  FROM USERS where id=?";
		 this.getJdbcTemplate().update(sql,id);
	}
	
	//update sample
	
	public void update(UserDetailBean user) {
		 String sql = "UPDATE USER_DETAIL set fname=?, lname=? where id=?";
		 this.getJdbcTemplate().update(sql, new Object[] {user.getFirst(),user.getLast(),user.getId()});
	}
		
}
