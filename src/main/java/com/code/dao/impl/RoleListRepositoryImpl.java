package com.code.dao.impl;

import java.util.List;

import javax.annotation.PostConstruct;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import com.code.comm.ConnectionUtils;
import com.code.comm.SqlFormatUtils;
import com.code.dao.IRoleListRepository;
import com.code.model.RoleListBean_R001;

@Repository
public class RoleListRepositoryImpl  implements IRoleListRepository{
	
	@Autowired
	protected DataSource dataSource;
	 @PostConstruct
	 private void initialize(){
	        ConnectionUtils.setDataSource(dataSource);
	 }


	@Override
	public void removeRoleListByRole(String input) {
		String sql = "DELETE FROM roles_list WHERE role= "+input ;
		try{
			ConnectionUtils.getNamedParameterJdbcTemplate().update(sql, SqlFormatUtils.getSqlParameterSource(input));
		}catch(Exception e){
			
		}
	}

	@Override
	public void insertRole(RoleListBean_R001 input) {
		String sql = "insert into roles_list(role,regdate,usercd)values(:role,:regdate,:usercd)" ;
		try{
			ConnectionUtils.getNamedParameterJdbcTemplate().update(sql, SqlFormatUtils.getSqlParameterSource(input));
		}catch(Exception e){
			
		}
		
	}

	@Override
	public List<RoleListBean_R001> getRoleBusiness() {
		String sql =  "select id,role ,regdate,usercd from roles_list where 1=1";
		List<RoleListBean_R001> result  = null;
		try{
	          result  = ConnectionUtils.getNamedParameterJdbcTemplate().query(sql, 
						new BeanPropertyRowMapper<RoleListBean_R001>(RoleListBean_R001.class));
		}catch(Exception e){
			
		}
		
	   return result;
	}

}
