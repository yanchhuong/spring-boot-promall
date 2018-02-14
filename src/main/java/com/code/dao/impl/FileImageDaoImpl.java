package com.code.dao.impl;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.code.comm.ConnectionUtils;
import com.code.comm.SqlFormatUtils;
import com.code.dao.IFileImageDao;
import com.code.model.FileUploadBean;
@Repository
public class FileImageDaoImpl  implements IFileImageDao {
    @Autowired
	protected DataSource dataSource;
	 
	@PostConstruct
	private void initialize(){
		 ConnectionUtils.setDataSource(dataSource);
	}

	@Override
	public void remove(String input) {
		String sql = "delete from filepicture where randname = :randname";
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("randname", input);
		try{
			ConnectionUtils.getNamedParameterJdbcTemplate().update(sql, params);
		}catch(Exception e){
			System.out.println("Error sql: "+sql);
			e.printStackTrace();
		}
		
	}
	
	@Override
	public void insertNew(FileUploadBean input) {
		if(input.getOrname().length() > 100) {
			input.setOrname("Big file");
		}
		String sql ="insert into filepicture (orname, randname, regdate, type, path, size, prcd, usercd, catgcd, pcd, kind)"+
				    " values(:orname, :randname, :regdate, :type, :path, :size, :prcd, :usercd, :catgcd, :pcd, :kind)";
		try{
			ConnectionUtils.getNamedParameterJdbcTemplate().update(sql,SqlFormatUtils.getSqlParameterSource(input));
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	@Override
	public void deleteProfileImage(FileUploadBean input) {
		String sql ="delete from filepicture where usercd = :usercd";
		try{
			ConnectionUtils.getNamedParameterJdbcTemplate().update(sql,SqlFormatUtils.getSqlParameterSource(input));
		}catch(Exception e){
			e.printStackTrace();
		}
	}

}
