package com.code.dao.impl;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.annotation.PostConstruct;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

import com.code.dao.IProductRepository;
import com.code.model.ProductBeanIn_U001;
import com.code.model.ProductListBeanIn_R001;
import com.code.model.ProductListBeanOut_R001;
import com.code.model.UserSignupBeanIn_C001;
import com.google.common.base.Strings;
@Repository
public class ProductRepositoryImpl extends JdbcDaoSupport implements IProductRepository{
	
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
	@Override
	public List<ProductListBeanOut_R001> getListProduct(ProductListBeanIn_R001 input) {
		String sql = "select p.prid,p.prcd,p.title,p.regdate,p.price, "
					+"       p.description,p.url,p.enabled,p.catgcd,  " 
					+"       concat(ud.fname ,ud.lname) as owner,     "
					+"     count(v.prcd) as viewcnt,                  "
					+"     count(pl.prcd) as likecnt ,"
					+"    sp.store_nm  " 
					+" from products p  "  
					+" 		full join products_views v on v.prcd = p.prcd  "
					+" 		full join products_likes pl on pl.prcd = p.prcd "
					+" 		left join user_detail ud on ud.username_fk=p.username "
					+" 		left join store_page sp on sp.username = p.username "
					+" where 1=1 " ;

	    StringBuffer sb = new StringBuffer(sql);
	    
		if(input!=null){
			if(!Strings.isNullOrEmpty(input.getKeyword())){
				sb.append(" and (ud.fname ilike '%"+input.getKeyword()+"%'");
				sb.append(" or  ud.lname ilike '%"+input.getKeyword()+"%'");
				sb.append(" or  p.description ilike '%"+input.getKeyword()+"%'");
				sb.append(" or  p.url ilike '%"+input.getKeyword()+"%'");
				sb.append(" or  p.title ilike '%"+input.getKeyword()+"%'");
				sb.append(" or  sp.store_nm ilike '%"+input.getKeyword()+"%')");
				
			}
			if(!Strings.isNullOrEmpty(input.getRegdate())){
				sb.append(" and p.regdate = '"+input.getRegdate()+"%'");
			}
			if(!Strings.isNullOrEmpty(input.getSprice()) || !Strings.isNullOrEmpty(input.getEprice())){
				  sb.append(" and p.price between "+input.getSprice()+" and "+ input.getEprice());
			}
			if(!Strings.isNullOrEmpty(input.getEnabled())){
		    	sb.append(" and  p.enabled = '"+input.getEnabled()+"'");
			}
		}
	    sb.append(" group by  p.prcd, owner,sp.store_nm");
	    List<ProductListBeanOut_R001> result = null; 
		try{
			      result= getJdbcTemplate().query(sb.toString(), 
					new BeanPropertyRowMapper<ProductListBeanOut_R001>(ProductListBeanOut_R001.class));
		}catch(Exception e){
			System.out.println(sb.toString());
		}
		return result;
	}
	@Override
	public void updateProductStatus(ProductBeanIn_U001 input) {
		 String sql = "update products set enabled=:enabled where prcd=:prcd";
		 ArrayList<String> param = new ArrayList<String>();
	     System.out.println(sql);
          try{
        	  this.namedParameterJdbcTemplate.update(sql, getSqlParameterByModel(input));
          }catch(Exception e){
        	  
          }
	   
	}
	  //Static param
    /*	private SqlParameterSource getSqlParameterByModel(ProductBeanIn_U001 input) {
		MapSqlParameterSource paramSource = new MapSqlParameterSource();
		paramSource.addValue("enabled", input.getEnabled());
		paramSource.addValue("prcd", input.getPrcd());
		paramSource.addValue("username", input.getUsername());
		// join String
		return paramSource;
	}*/
	
	//Dymic paramter
	private SqlParameterSource getSqlParameterByModel(Object input) throws IllegalArgumentException, IllegalAccessException {
		MapSqlParameterSource paramSource = new MapSqlParameterSource();
		for (Field field : input.getClass().getDeclaredFields()) {
			field.setAccessible(true);
			Object value = field.get(input); 
			paramSource.addValue(field.getName(),value);
		}
		// join String
		return paramSource;
	}

}
