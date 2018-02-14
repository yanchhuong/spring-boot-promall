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
import com.code.dao.IUserDetailRepostory;
import com.code.model.FileUploadBean;
import com.code.model.UserDetailInOut;

@Repository
public class UserDetailDaoImpl implements IUserDetailRepostory{

	@Autowired
	protected DataSource dataSource;
	 
	@PostConstruct
	private void initialize(){
	       ConnectionUtils.setDataSource(dataSource);
	}


	@Override
	public List<UserDetailInOut> getUserDetails(String usercd) {
		
		usercd = usercd.replace("\"", "");
		
		String sql = "	select fname||' '|| lname as fullname, sex, email, regdate, birthdate, cphone \r\n" + 
					"	from   user_detail\r\n" + 
					"	where usercd = '"+usercd+"'";
		
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("usercd", usercd);

		List<UserDetailInOut> result  = null;
		try{
			result  = ConnectionUtils.getNamedParameterJdbcTemplate().query(sql, params,
					new BeanPropertyRowMapper<UserDetailInOut>(UserDetailInOut.class));
		}catch(Exception e){
			System.out.println("error: "+sql);
		}
		return result;
	}


	@Override
	public void updateUserPhonno(UserDetailInOut input) {
		String sql = "update user_detail set cphone = :cphone where usercd = :usercd ";	     
        try{
        	ConnectionUtils.getNamedParameterJdbcTemplate().update(sql,SqlFormatUtils.getSqlParameterSource(input));
        }catch(Exception e){
        	System.out.println("Error: "+sql);
        }
	}


	@Override
	public void updateUserEmail(UserDetailInOut input) {
		String sql = "update user_detail set email = :email where usercd = :usercd ";	     
        try{
        	ConnectionUtils.getNamedParameterJdbcTemplate().update(sql,SqlFormatUtils.getSqlParameterSource(input));
        }catch(Exception e){
        	System.out.println("Error: "+sql);
        }
	}


	@Override
	public void updateUserBithdate(UserDetailInOut input) {
		String sql = "update user_detail set birthdate = :birthdate where usercd = :usercd ";	     
        try{
        	ConnectionUtils.getNamedParameterJdbcTemplate().update(sql,SqlFormatUtils.getSqlParameterSource(input));
        }catch(Exception e){
        	System.out.println("Error: "+sql);
        }
	}


	@Override
	public void updateUserGender(UserDetailInOut input) {
		String sql = "update user_detail set sex = :sex where usercd = :usercd ";	     
        try{
        	ConnectionUtils.getNamedParameterJdbcTemplate().update(sql,SqlFormatUtils.getSqlParameterSource(input));
        }catch(Exception e){
        	System.out.println("Error: "+sql);
        }
	}

	@Override
	public List<FileUploadBean> getProfileImage(UserDetailInOut input) {
		
//		String usercd = input.getUsercd();
//		usercd.replace("\"", "");	
		String sql = "select orname, randname, regdate, 'type', 'path' \r\n" + 
				     "       'size', prcd, usercd, catgcd, pcd, kind\r\n" + 
				     "from filepicture where usercd = :usercd";
		
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("usercd", input.getUsercd());

		List<FileUploadBean> result  = null;
		try{
			result  = ConnectionUtils.getNamedParameterJdbcTemplate().query(sql, params,
					new BeanPropertyRowMapper<FileUploadBean>(FileUploadBean.class));

			System.out.println("in Repo: "+result);
		}catch(Exception e){
			System.out.println("error: "+sql);
		}
		return result;
	}


}
