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
import com.code.dao.ILikeRepository;
import com.code.model.LikeBeanInOut_001;

@Repository
public class LikeRepositoryDaoImpl implements ILikeRepository {

	@Autowired
	protected DataSource dataSource;
	 
	@PostConstruct
	private void initialize(){
	       ConnectionUtils.setDataSource(dataSource);
	}
	

	@Override
	public void insertLikeProduct(LikeBeanInOut_001 input) {

		String sql = "insert into products_likes (prcd, usercd) "
				+ "	  values (:prcd, :usercd)";
		try{
			ConnectionUtils.getNamedParameterJdbcTemplate().update(sql,SqlFormatUtils.getSqlParameterSource(input));
        }catch(Exception e){
        	System.out.println("error sql: "+sql);
        }
	}

	@Override
	public void deleteLikeProduct(LikeBeanInOut_001 input) {

		String sql = "delete from products_likes	\r\n" + 
				"where prcd = :prcd     \r\n" + 
				"and usercd = :usercd	";
		try{
			ConnectionUtils.getNamedParameterJdbcTemplate().update(sql,SqlFormatUtils.getSqlParameterSource(input));
        }catch(Exception e){
        	System.out.println("error sql: "+sql);
        }
	}


	@Override
	public List<LikeBeanInOut_001> checkLikeProduct(LikeBeanInOut_001 input) {
		String sql = "select id, prcd, usercd from products_likes\r\n" + 
				 "where prcd = :prcd";
	    
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("prcd", input.getPrcd());
		
	    List<LikeBeanInOut_001> result = null; 
		try{
			result  = ConnectionUtils.getNamedParameterJdbcTemplate().query(sql, params, 
					new BeanPropertyRowMapper<LikeBeanInOut_001>(LikeBeanInOut_001.class));
		}catch(Exception e){
			System.out.println("error sql"+sql);
		}
		return result;
	}


}
