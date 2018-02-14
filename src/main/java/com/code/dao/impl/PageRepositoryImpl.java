package com.code.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.hsqldb.lib.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import com.code.comm.ConnectionUtils;
import com.code.comm.SqlFormatUtils;
import com.code.dao.IPageRepository;
import com.code.model.ProductListBeanOut_R001;
import com.code.model.ProductListBeanOut_R002;
import com.code.model.ProductParam_IN001;
import com.code.model.UserPageInOut_001;
import com.google.common.base.Strings;

import antlr.StringUtils;

@Repository
public class PageRepositoryImpl implements IPageRepository{

	@Autowired
	protected DataSource dataSource;
	 
	@PostConstruct
	private void initialize(){
	       ConnectionUtils.setDataSource(dataSource);
	}
	
	@Override
	public List<UserPageInOut_001> getUserPage(UserPageInOut_001 input) {
		
//		usercd = usercd.replace("\"", "");
		
		String sql = "select ps.pcd, ps.pnm, ps.title, ps.usercd,\r\n" + 
					 "           ps.url, ps.enabled, ps.regdate, \r\n" + 
					 "	         f.randname\r\n" + 
					 "from page_store ps left join filepicture f\r\n" + 
					 "on ps.pcd = f.pcd\r\n" + 
//					 "where ps.usercd = :usercd\r\n"+
					 "where   ps.pcd = :pcd";
		
		Map<String, Object> params = new HashMap<String, Object>();
//		params.put("usercd", input.getUsercd());
		params.put("pcd", input.getPcd());

		List<UserPageInOut_001> result  = null;
		try{
			result  = ConnectionUtils.getNamedParameterJdbcTemplate().query(sql, params,
					new BeanPropertyRowMapper<UserPageInOut_001>(UserPageInOut_001.class));
		}catch(Exception e){
			System.out.println("error: "+sql);
		}
		return result;
	}

	@Override
	public void updatePageName(UserPageInOut_001 input) {
		String sql = "update page_store set pnm = :pnm \r\n"+ 
					 "where usercd = :usercd \r\n"+
					 "and pcd = :pcd \r\n";
//		Map<String, Object> params = new HashMap<String, Object>();
//		params.put("pnm", input.getPnm());
//		params.put("usercd", input.getUsercd());
//		params.put("pcd", input.getPcd());
        try{
        	ConnectionUtils.getNamedParameterJdbcTemplate().update(sql, SqlFormatUtils.getSqlParameterSource(input));
        	/*ConnectionUtils.getNamedParameterJdbcTemplate().query(sql, params,
					new BeanPropertyRowMapper<UserPageInOut_001>(UserPageInOut_001.class));*/
        }catch(Exception e){
        	System.out.println("Error: "+sql);
        }
	}
	
}
