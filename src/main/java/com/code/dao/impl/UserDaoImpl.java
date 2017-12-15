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
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;

import com.code.comm.JdbcDaoSupportUtils;
import com.code.comm.SqlSmartFormatFunctions;
import com.code.dao.IUserDao;
import com.code.model.MUpdateUserStatusIn_U001;
import com.code.model.MUserListIn_R001;
import com.code.model.MUserListOut_R001;
import com.code.model.RoleCountOut_R001;
import com.code.model.UserSignupBeanIn_C001;
import com.google.common.base.Strings;

@Repository
public class UserDaoImpl extends JdbcDaoSupport implements IUserDao{	
	NamedParameterJdbcTemplate namedParameterJdbcTemplate;
//	ExampleJdbcDaoSupport exampleJdbcDaoSupport=new ExampleJdbcDaoSupport();
	SqlSmartFormatFunctions sqlSmartFormatFunctions =new SqlSmartFormatFunctions();
	@Autowired
	public void setNamedParameterJdbcTemplate(
		NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
		this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
		//this.ExampleJdbcDaoSupport=exampleJdbcDaoSupport;
	}
	@Autowired
	protected DataSource dataSource;
	 
	 @PostConstruct
	 private void initialize(){
	        setDataSource(dataSource);
	        JdbcDaoSupportUtils.setDataSource(dataSource);
	 }

	@Override
	public void insertRole(UserSignupBeanIn_C001 user) {
		//String sql = "INSERT INTO USER_ROLES " +"(username,role,regdate,usercd) VALUES (?,?,?,?)" ;
		String sql = "INSERT INTO USER_ROLES " +"(username,role,regdate,usercd) VALUES (:username,:role,:regdate,:usercd)" ;
		try{
			JdbcDaoSupportUtils.getNamedParameterJdbcTemplate().update(sql, sqlSmartFormatFunctions.getSqlParameterByModel(user));
		}catch(Exception e){
			
		}
	}
	@Override
	public void insertUserLog(UserSignupBeanIn_C001 user) {
		String sql = "INSERT INTO USERS " +"(usercd,username,password) VALUES (?,?,?)" ;
        this.getJdbcTemplate().update(sql, new Object[]{user.getUsercd(),user.getUsername(),user.getPassword()});
	}
	@Override
	public void insertUserDetail(UserSignupBeanIn_C001 user) {
		String sql = "INSERT INTO USER_DETAIL " +"(regdate,fname,lname,username_fk,email,usercd) VALUES (?,?,?,?,?,?)" ;
        this.getJdbcTemplate().update(sql, new Object[]{user.getRegdate(),user.getFname(),user.getLname()
        		,user.getUsername(),user.getEmail(),user.getUsercd()});
		
	}
	
	//insert batch
	public void inserBatch(List<UserSignupBeanIn_C001> batch){
	    String sql = "INSERT INTO USER_DETAIL " + "(fname, lname) VALUES ( ?, ?)";
	    getJdbcTemplate().batchUpdate(sql, new BatchPreparedStatementSetter() {
	        public void setValues(PreparedStatement ps, int i) throws SQLException {
	        	UserSignupBeanIn_C001 rec = batch.get(i);
	            ps.setString(1, rec.getFname());
	            ps.setString(2, rec.getLname());
	        }
	 
	        public int getBatchSize() {
	            return batch.size();
	        }
	    });
	}
	// for example list all
	public List<UserSignupBeanIn_C001> listUser(){
		  String sql = "SELECT usercd,username FROM USERS";
		    List< Map < String, Object>> rows = this.getJdbcTemplate().queryForList(sql);
		    List<UserSignupBeanIn_C001> result = new ArrayList<UserSignupBeanIn_C001>();
		    for(Map <String, Object> row:rows){
		    	UserSignupBeanIn_C001 rec = new UserSignupBeanIn_C001();
		    	rec.setUsercd((String)row.get("usercd"));
		        rec.setUsername((String)row.get("username"));
		        result.add(rec);
		    }
		    return result;
		
    }
	
	
	public UserSignupBeanIn_C001 SearchByname(String firstname) {
		String sql = "SELECT * FROM USER_DETAIL WHERE fname = ? ORDER BY detid";
	     return (UserSignupBeanIn_C001)this.getJdbcTemplate().queryForObject(sql, new Object[]{firstname}, new RowMapper<UserSignupBeanIn_C001>(){
	         @Override
	         public UserSignupBeanIn_C001 mapRow(ResultSet rs, int rwNumber) throws SQLException {
	        	 UserSignupBeanIn_C001 cust = new UserSignupBeanIn_C001();
	             cust.setId(rs.getInt("id"));
	             cust.setFname(rs.getString("fname"));
	             cust.setLname(rs.getString("lname"));
	             return cust;
	        }
	    });
	}
	
	@Override
	public List<MUserListOut_R001> getUserList(MUserListIn_R001 input) { 
		String sql =  "select u.id,u.usercd,u.enabled,ur.role,	           "
		             +"       CONCAT(ud.fname,' ',ud.lname) as name,	       "
		             +"       ud.sex,ud.cphone,ud.email,ud.regdate,	       "
		             +"       ud.birthdate,fp.randname,				       "
		             +"       CONCAT(addr.country,'|',addr.province,'|',addr.detail) as address"
		             +"       from users u				                   "
		             +" left join user_roles ur on u.username=ur.username   "
		             +" left join user_detail ud on u.username=ud.username_fk"
		             +" left join filepicture fp on u.username= fp.username"
		             +" left join (select * from  address ad where status='1') as addr   on u.username= addr.username"
		             +" where 1=1";
		 
		StringBuffer sb = new StringBuffer(sql);
		if(input!=null){
			if(!Strings.isNullOrEmpty(input.getKeyword())){
				sb.append(" and ( ud.fname ilike '%"+input.getKeyword()+"%'");
				sb.append(" or  ud.lname ilike '%"+input.getKeyword()+"%'");
				sb.append(" or  ud.email ilike '%"+input.getKeyword()+"%'");
				sb.append(" or  ud.cphone ilike '%"+input.getKeyword()+"%')");
			}
		}
		if(!Strings.isNullOrEmpty(input.getRegdate())){
			sb.append(" and ud.regdate = '"+input.getRegdate()+"%'");
		}
	    if(!Strings.isNullOrEmpty(input.getBirthdate())){
	    	sb.append(" and ud.birthdate = '"+input.getBirthdate()+"'");
		}
	    if(!Strings.isNullOrEmpty(input.getRole())){
	    	sb.append(" and ur.role = '"+input.getRole()+"'");
		}
	    if(!Strings.isNullOrEmpty(input.getStatus())){
	    	sb.append(" and  u.enabled = '"+input.getStatus()+"'");
		}
	    
		System.out.println(sb.toString());
		List<MUserListOut_R001> result  = getJdbcTemplate().query(sb.toString(), 
					new BeanPropertyRowMapper<MUserListOut_R001>(MUserListOut_R001.class));

		return result;
	}
	
	// delete 
	public void delete(long id) {
		 String sql = "DELETE  FROM USERS where id=?";
		 this.getJdbcTemplate().update(sql,id);
	}
	
	//update sample
	public void update(UserSignupBeanIn_C001 user) {
		 String sql = "UPDATE USER_DETAIL set fname=?, lname=? where id=?";
		 this.getJdbcTemplate().update(sql, new Object[] {user.getFname(),user.getLname(),user.getId()});
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
	
	
	public UserSignupBeanIn_C001 SearchDynamicParam(String email) {
		Map<String, Object> params = new HashMap<String, Object>();
		String sql = "SELECT * FROM USER_DETAIL WHERE ";
		
		//Dynamic parameter condition
		if(!email.endsWith("")){
			params.put("email", email);
			sql  = sql + "EMAIL=:email";
		}
		
		UserSignupBeanIn_C001 result = null;
		try {
			result = namedParameterJdbcTemplate.queryForObject(sql, params, new UserMapper());
		} catch (EmptyResultDataAccessException e) {
			// do nothing, return null
		}
		return result;

	}
	
	
	public void updates(UserSignupBeanIn_C001 user) {

		String sql = "UPDATE USER_DETAIL SET FNAME=:fname, EMAIL=:email, ADDRESS=:address, "
			+ "PASSWORD=:password, SEX=:sex, LNAME=:lname, "
			+ "SEX=:sex, CPHONE=:phone, REGDATE=:regdate, BIRTHDATE=:birthdate WHERE USERNAME=:username";
		namedParameterJdbcTemplate.update(sql, getSqlParameterByModel(user));

	}
	
	public List<UserSignupBeanIn_C001> findAll() {

		String sql = "SELECT * FROM USER_DETAIL";
		List<UserSignupBeanIn_C001> result = namedParameterJdbcTemplate.query(sql, new UserMapper());
		return result;

	}
	
	private static final class UserMapper implements RowMapper<UserSignupBeanIn_C001> {

		public UserSignupBeanIn_C001 mapRow(ResultSet rs, int rowNum) throws SQLException {
			UserSignupBeanIn_C001 user = new UserSignupBeanIn_C001();
			user.setId(rs.getInt("id"));
			user.setFname(rs.getString("fname"));
			user.setLname(rs.getString("lname"));
			user.setPassword(rs.getString("password"));
			user.setEnabled(rs.getBoolean("enable"));
			user.setRole(rs.getString("role"));
			user.setUsercd(rs.getString("usercd"));
            user.setUsername(rs.getString("username"));
			
			return user;
		}
	}
	
    // for update Dynamic parameter	
	private SqlParameterSource getSqlParameterByModel(UserSignupBeanIn_C001 user) {
		MapSqlParameterSource paramSource = new MapSqlParameterSource();
		paramSource.addValue("id", user.getId());
		paramSource.addValue("username", user.getUsername());
		paramSource.addValue("role", user.getRole());
		// join String
		return paramSource;
	}
	@Override
	public List<RoleCountOut_R001> getRoleCount() {
		String sql =  "select count(*) as cnt , role from user_roles group by  role"
				+ " union select count(*) , 'ALL' from  user_roles";
		System.out.println(sql);
		List<RoleCountOut_R001> result  = getJdbcTemplate().query(sql, 
					new BeanPropertyRowMapper<RoleCountOut_R001>(RoleCountOut_R001.class));
		return result;
	}


	@Override
	public void updateUserStatus(MUpdateUserStatusIn_U001 input) {
		String sql = "UPDATE USERS set enabled=? where usercd=?";
        this.getJdbcTemplate().update(sql, new Object[] {input.isEnabled(),input.getUsercd()});	
	}

	
}
