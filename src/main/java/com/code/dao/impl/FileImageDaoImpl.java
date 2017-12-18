package com.code.dao.impl;

import javax.annotation.PostConstruct;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.code.comm.JdbcDaoSupportUtils;
import com.code.comm.SqlFormatUtils;
import com.code.dao.IFileImageDao;
import com.code.model.FileUploadBean;
@Repository
public class FileImageDaoImpl  implements IFileImageDao {
    @Autowired
	protected DataSource dataSource;
	 
	@PostConstruct
	private void initialize(){
		 JdbcDaoSupportUtils.setDataSource(dataSource);
	}

	@Override
	public void remove(String input) {
		String sql = "delete  from filepicture where randname = :randname";
		 try{
				JdbcDaoSupportUtils.getNamedParameterJdbcTemplate().update(sql,SqlFormatUtils.getSqlParameterSource(input));
			}catch(Exception e){
				
		}
		
	}
	@Override
	public void insertNew(FileUploadBean input) {
		String sql ="insert into filepicture (orname, randname, regdate, type, path, size, prcd, usercd, catgcd)"+
				    " values(:orname, :randname, :regdate, :type, :path, :size, :prcd, :usercd, :catgcd)";
		//			"values(?,?,?,?,?,?,?,?,?)";
		 try{
				JdbcDaoSupportUtils.getNamedParameterJdbcTemplate().update(sql,SqlFormatUtils.getSqlParameterSource(input));
			}catch(Exception e){
				
		}
	}

}
