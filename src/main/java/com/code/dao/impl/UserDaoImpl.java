package com.code.dao.impl;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;

import com.code.comm.ConnectionUtils;
import com.code.comm.SqlFormatUtils;
import com.code.dao.IUserDao;
import com.code.model.MUpdateUserStatusIn_U001;
import com.code.model.MUserListIn_R001;
import com.code.model.MUserListOut_R001;
import com.code.model.RoleCountOut_R001;
import com.code.model.UserSessionBean;
import com.code.model.UserSignupBeanIn_C001;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.Strings;

@Repository
public class UserDaoImpl implements IUserDao{	
	@Autowired
	protected DataSource dataSource;
	 @PostConstruct
	 private void initialize(){
	        ConnectionUtils.setDataSource(dataSource);
	 }

	@Override
	public void insertRole(UserSignupBeanIn_C001 user) {
		//String sql = "INSERT INTO USER_ROLES " +"(username,role,regdate,usercd) VALUES (?,?,?,?)" ;
		String sql = "INSERT INTO USER_ROLES " +"(username,role,regdate,usercd) VALUES (:username,:role,:regdate,:usercd)" ;
		try{
			ConnectionUtils.getNamedParameterJdbcTemplate().update(sql,SqlFormatUtils.getSqlParameterSource(user));
		}catch(Exception e){
			
		}
	}
	@Override
	public void insertUserLog(UserSignupBeanIn_C001 input) {
		//String sql = "INSERT INTO USERS " +"(usercd,username,password) VALUES (?,?,?)" ;
		String sql = "INSERT INTO USERS " +"(usercd,username,password) VALUES (:usercd,:username,:password)" ;
		try{
			ConnectionUtils.getNamedParameterJdbcTemplate().update(sql,SqlFormatUtils.getSqlParameterSource(input));
		}catch(Exception e){
			
		}
	}
	@Override
	public void insertUserDetail(UserSignupBeanIn_C001 input) {
		String sql = "INSERT INTO USER_DETAIL " +"(regdate,fname,lname,username_fk,email,usercd) VALUES (:regdate,:fname,:lname,:username,:email,:usercd)" ;
		try{
			ConnectionUtils.getNamedParameterJdbcTemplate().update(sql, SqlFormatUtils.getSqlParameterSource(input));
		}catch(Exception e){
			
		}
	}
	
	//insert batch
	public void inserBatch(List<UserSignupBeanIn_C001> batch){
	   /* String sql = "INSERT INTO USER_DETAIL " + "(fname, lname) VALUES ( ?, ?)";
	    try{
	    	  JdbcDaoSupportUtils.getNamedParameterJdbcTemplate().batchUpdate(sql, new BatchPreparedStatementSetter() {
	  	        public void setValues(PreparedStatement ps, int i) throws SQLException {
	  	        	UserSignupBeanIn_C001 rec = batch.get(i);
	  	            ps.setString(1, rec.getFname());
	  	            ps.setString(2, rec.getLname());
	  	        }
	  	 
	  	        public int getBatchSize() {
	  	            return batch.size();
	  	        }
	  	    });
	    }catch(Exception e){
	    	
	    }*/
	  
	}
	
	@Override
	public List<MUserListOut_R001> getUserList(MUserListIn_R001 input) {
		String sql =  "select u.id,u.usercd,u.enabled,ur.role,	           "
		             +"       CONCAT(ud.fname,' ',ud.lname) as name,	   "
		             +"       ud.sex,ud.cphone,ud.email,ud.regdate,	       "
		             +"       ud.birthdate,fp.randname,				       "
		             +"       CONCAT(addr.country,'|',addr.province,'|',addr.detail) as address"
		             +"       from users u				                   "
		             +" left join user_roles ur on u.username  = ur.username   "
		             +" left join user_detail ud on u.username = ud.username_fk"
		             +" left join filepicture fp on u.usercd = fp.usercd"
//		             +" left join (select * from  address ad where status='1') as addr   on u.username= addr.username"
		             +" left join address addr on u.usercd = addr.usercd"
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
		List<MUserListOut_R001> result  = null;
		try{
			result  = ConnectionUtils.getNamedParameterJdbcTemplate().query(sb.toString(), 
					new BeanPropertyRowMapper<MUserListOut_R001>(MUserListOut_R001.class));
		}catch(Exception e){
			
		}
		return result;
	}

	@Override
	public boolean resetPassword(String username, String newpass) {
		boolean result = false;
		try{
			/* String sql = "UPDATE USERS set password=? where username=?";
	         this.getJdbcTemplate().update(sql, new Object[] {newpass,username});*/
		 
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
			result = ConnectionUtils.getNamedParameterJdbcTemplate().queryForObject(sql, params, new UserMapper());
		} catch (EmptyResultDataAccessException e) {
			// do nothing, return null
		}
		return result;

	}
	public void updates(UserSignupBeanIn_C001 user) {
		String sql = "UPDATE USER_DETAIL SET FNAME=:fname, EMAIL=:email, ADDRESS=:address, "
			+ "PASSWORD=:password, SEX=:sex, LNAME=:lname, "
			+ "SEX=:sex, CPHONE=:phone, REGDATE=:regdate, BIRTHDATE=:birthdate WHERE USERNAME=:username";
		ConnectionUtils.getNamedParameterJdbcTemplate().update(sql, getSqlParameterByModel(user));

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
		List<RoleCountOut_R001> result  = null;
		 try{
			 result = ConnectionUtils.getNamedParameterJdbcTemplate().query(sql, 
						new BeanPropertyRowMapper<RoleCountOut_R001>(RoleCountOut_R001.class));
			}catch(Exception e){	
	        }
		return result;
	}
	@Override
	public void updateUserStatus(MUpdateUserStatusIn_U001 input) {
		String sql = "UPDATE USERS set enabled=:enabled where usercd=:usercd";       
        try{
			ConnectionUtils.getNamedParameterJdbcTemplate().update(sql, SqlFormatUtils.getSqlParameterSource(input));
		}catch(Exception e){
			
		}
	}

	@Override
	public List <UserSessionBean> getSessionDao(String input) {
		String sql = "select a.usercd,b.fname||' '||b.lname as fullname,b.sex, "
					+ "       c.randname,b.cphone,b.email from users a "
					+ "       left join user_detail b on a.usercd = b.usercd "
					+ "       left join filepicture c on c.usercd = a.usercd "
					+ "             where 1=1 and a.username = :username";
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("username", input);
		
		List <UserSessionBean> data=null;
	    try {
	         data = ConnectionUtils.getNamedParameterJdbcTemplate().query(sql,params,
					new BeanPropertyRowMapper<UserSessionBean>(UserSessionBean.class));
		 } catch (Exception e) {
					// do nothing, return null
	    }
	    return data;
	}
}
