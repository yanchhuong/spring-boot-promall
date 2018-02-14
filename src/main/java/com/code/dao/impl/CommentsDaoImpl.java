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
import com.code.dao.ICommentsRepository;
import com.code.model.CommentBeanOut_R001;
import com.code.model.CommentBean_001;


@Repository
public class CommentsDaoImpl implements ICommentsRepository{
	@Autowired
	protected DataSource dataSource;
	 
	@PostConstruct
	private void initialize(){
	       ConnectionUtils.setDataSource(dataSource);
	}
	
	@Override
	public void insertComments(CommentBean_001 input) {
		String sql = " INSERT INTO comments (regdate, content, prcd, usercd) \r\n" + 
					 " VALUES (:regdate, :content, :prcd, :usercd)";
		try{
			ConnectionUtils.getNamedParameterJdbcTemplate().update(sql,SqlFormatUtils.getSqlParameterSource(input));
		}catch(Exception e){
      	  System.out.println(sql);
        }
	}

	@Override
	public List<CommentBeanOut_R001> listComments(CommentBean_001 input) {
		String sql = "select c.id, c.content, c.regdate, c.usercd, d.fname||' '|| d.lname as fullname, f.randname\r\n" + 
				"from comments c\r\n" + 
				"left join user_detail d on d.usercd = c.usercd \r\n" + 
				"left join filepicture f on f.usercd = c.usercd\r\n" + 
				"where c.prcd = :prcd\r\n"+
				"order by regdate desc" ;

		Map<String, Object> params = new HashMap<String, Object>();
		params.put("prcd", input.getPrcd());
				
	    List<CommentBeanOut_R001> result = null; 
		try{
			result  = ConnectionUtils.getNamedParameterJdbcTemplate().query(sql, params, 
					new BeanPropertyRowMapper<CommentBeanOut_R001>(CommentBeanOut_R001.class));
		}catch(Exception e){
			
		}
		return result;
	}

	@Override
	public void updateComment(CommentBean_001 input) {
		String sql = " update comments    \r\n" + 
				"set content   = :content, \r\n" +
				"	 regdate   = :regdate \r\n" +
				"where	usercd = :usercd  \r\n" + 
				"and 	prcd   = :prcd    \r\n" + 
				"and 	id	   = :id      \r\n";
		try{
			ConnectionUtils.getNamedParameterJdbcTemplate().update(sql,SqlFormatUtils.getSqlParameterSource(input));
		}catch(Exception e){
			System.out.println(sql);
		}
	}

	@Override
	public void deleteComment(CommentBean_001 input) {
		String sql = "delete from comments\r\n" + 
					 "where id = :id";
		try{
			ConnectionUtils.getNamedParameterJdbcTemplate().update(sql,SqlFormatUtils.getSqlParameterSource(input));
		}catch(Exception e){
			System.out.println(sql);
		}		
	}


}
