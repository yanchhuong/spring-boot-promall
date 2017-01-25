package com.code.dao.impl;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;

import com.code.dao.UserDao;
import com.code.model.UserDetailBean;

@Repository
public class UserDaoImpl extends JdbcDaoSupport implements UserDao{
	
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

	@Override
	public boolean resetPassword(String username, String newpass) {
		boolean result = false;
		try{
			 String sql = "UPDATE USERS set password=? where username=?";
	         this.getJdbcTemplate().update(sql, new Object[] {newpass,username});
		 
		}catch(Exception e){
			 e.printStackTrace();
		}
		
		return result;
	}


	//Dynamic parameter search sample
	
	
	public UserDetailBean findOneByEmail(String email) {
		Map<String, Object> params = new HashMap<String, Object>();
		String sql = "SELECT * FROM USER_DETAIL WHERE ";
		
		//Dynamic parameter condition
		if(!email.endsWith("")){
			params.put("email", email);
			sql  = sql + "EMAIL=:email";
		}
		
		UserDetailBean result = null;
		try {
			result = namedParameterJdbcTemplate.queryForObject(sql, params, new UserMapper());
		} catch (EmptyResultDataAccessException e) {
			// do nothing, return null
		}
		return result;

	}
	
	
	public void updates(UserDetailBean user) {

		String sql = "UPDATE USER_DETAIL SET FNAME=:fname, EMAIL=:email, ADDRESS=:address, "
			+ "PASSWORD=:password, SEX=:sex, LNAME=:lname, "
			+ "SEX=:sex, CPHONE=:phone, REGDATE=:regdate, BIRTHDATE=:birthdate WHERE USERNAME=:username";

		namedParameterJdbcTemplate.update(sql, getSqlParameterByModel(user));

	}
	
	public List<UserDetailBean> findAll() {

		String sql = "SELECT * FROM USER_DETAIL";
		List<UserDetailBean> result = namedParameterJdbcTemplate.query(sql, new UserMapper());
		return result;

	}
	
	
	
	private static final class UserMapper implements RowMapper<UserDetailBean> {

		public UserDetailBean mapRow(ResultSet rs, int rowNum) throws SQLException {
			UserDetailBean user = new UserDetailBean();
			user.setId(rs.getInt("id"));
			user.setFirst(rs.getString("fname"));
			user.setLast(rs.getString("lname"));
			user.setAddress(rs.getString("address"));
			user.setBirthOfDate(rs.getString("birthdate"));
			user.setEmail(rs.getString("email"));
			user.setLocat_id(rs.getString("locatId"));
			user.setPassword(rs.getString("password"));
			user.setPhone(rs.getString("phone"));
			user.setRegisterDate(rs.getString("regdate"));
			user.setEnable(rs.getBoolean("enable"));
			user.setSex(rs.getBoolean("sex"));
			user.setProfile_fname(rs.getString("profilename"));
			
			user.setRole(rs.getString("role"));
			user.setUserCd(rs.getString("usercd"));
            user.setUsername(rs.getString("username"));
			
			return user;
		}
	}
	
    // for update Dynamic parameter	
	
	private SqlParameterSource getSqlParameterByModel(UserDetailBean user) {

		MapSqlParameterSource paramSource = new MapSqlParameterSource();
		paramSource.addValue("id", user.getId());
		paramSource.addValue("username", user.getUsername());
		paramSource.addValue("usercd", user.getUserCd());
		paramSource.addValue("email", user.getEmail());
		paramSource.addValue("address", user.getAddress());
		paramSource.addValue("password", user.getPassword());
		paramSource.addValue("fname", user.getFirst());
		paramSource.addValue("lname", user.getLast());
		paramSource.addValue("role", user.getRole());
		paramSource.addValue("registerdate", user.getRegisterDate());
		paramSource.addValue("profilename", user.getProfile_fname());
		paramSource.addValue("role", user.isEnable());
		paramSource.addValue("birthdate", user.getBirthOfDate());
		
		
		// join String
		paramSource.addValue("sex", user.isSex());
		paramSource.addValue("phone", user.getPhone());
		paramSource.addValue("locatId", user.getLocat_id());
		
		return paramSource;
	}
	

}
