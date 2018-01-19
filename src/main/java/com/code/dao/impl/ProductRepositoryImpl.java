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
import com.code.dao.IProductRepository;
import com.code.model.CategoryCount_OUT001;
import com.code.model.CommentBean_001;
import com.code.model.FileUploadBean;
import com.code.model.PostProductBean_C001;
import com.code.model.ProductAddressOut_R001;
import com.code.model.ProductBeanIn_U001;
import com.code.model.ProductListBeanIn_R001;
import com.code.model.ProductListBeanOut_R001;
import com.code.model.ProductListBeanOut_R002;
import com.code.model.ProductParam_IN001;
import com.google.common.base.Strings;
@Repository
public class ProductRepositoryImpl implements IProductRepository{
	
	@Autowired
	protected DataSource dataSource;
	 
	@PostConstruct
	private void initialize(){
	       ConnectionUtils.setDataSource(dataSource);
	}
	
	@Override
	public List<ProductListBeanOut_R001> getListProduct(ProductListBeanIn_R001 input) {
		String sql = "select   p.prid, p.prcd, p.title,                      \r\n" + 
				"			   p.regdate, p.price, p.desc,                   \r\n" + 
				"			   p.url, p.enabled, p.catgcd,                   \r\n" + 
				"			cat.nm_eng as type,                              \r\n" + 
				"	concat(ud.fname ,ud.lname) as owner,                     \r\n" + 
				"	count(v.prcd)  as viewcnt,                               \r\n" + 
				"	count(pl.prcd) as likecnt , sp.store_nm from products p  \r\n" + 
				"full join products_views v on v.prcd   = p.prcd   		     \r\n" + 
				"full join products_likes pl on pl.prcd = p.prcd  		     \r\n" + 
				"left join user_detail ud on ud.usercd  = p.usercd  		 \r\n" + 
				"left join store_page sp on sp.usercd   = p.usercd           \r\n" + 
				"left join category cat on cat.catgcd   = p.catgcd           \r\n" + 
				"where 1=1" ;

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
	    sb.append(" group by  p.prcd, owner, sp.store_nm, cat.nm_eng");
	    List<ProductListBeanOut_R001> result = null; 
		try{
			result  = ConnectionUtils.getNamedParameterJdbcTemplate().query(sb.toString(), 
					new BeanPropertyRowMapper<ProductListBeanOut_R001>(ProductListBeanOut_R001.class));
		}catch(Exception e){
			System.out.println(sb.toString());
		}
		return result;
	}
	
	@Override
	public void updateProductStatus(ProductBeanIn_U001 input) {
		 String sql = "update products set enabled=:enabled where prcd = :prcd";
	     
          try{
        	  ConnectionUtils.getNamedParameterJdbcTemplate().update(sql,SqlFormatUtils.getSqlParameterSource(input));
          }catch(Exception e){
        	  System.out.println(sql);
          }
	   
	}
	
	@Override
	public void insertPro_Address(PostProductBean_C001 input) {
	
		String sql = "insert into address \r\n" + 
				"	(usercd, cphone, country, province, detail, prcd)\r\n" + 
				"   values(:usercd, :cphone, :country, :province, :detail, :prcd)";
		try{
			ConnectionUtils.getNamedParameterJdbcTemplate().update(sql,SqlFormatUtils.getSqlParameterSource(input));
		}catch(Exception e){

        }
	}
	
	@Override
	public boolean insertProduct(PostProductBean_C001 input) {
		boolean chk = false;
		String sql = "insert into products \r\n" + 
				"	(prcd, title, regdate, price, url, \"desc\", catgcd, usercd)\r\n" + 
				"	values(:prcd, :title, :regdate, :price, :prcd, :desc, :catgcd, :usercd)\r\n";
		try{
			ConnectionUtils.getNamedParameterJdbcTemplate().update(sql,SqlFormatUtils.getSqlParameterSource(input));      	  
      	  	chk = true;
        }catch(Exception e){

        }
		return chk;
	}

	@Override
	public List<ProductListBeanOut_R002> listProduct(ProductParam_IN001 input) {
		String sql = "with recursive all_posts as (\r\n" + 
			"				select catgid, parentid, catgid as rootid from category t1 \r\n" + 
			"			union all \r\n" + 
			"				select c1.catgid, c1.parentid, p.rootid					  \r\n" + 
			"			from category c1 join all_posts p on p.catgid = c1.parentid)  \r\n" + 
			"				select c.parentid, c.nm_eng, c.catgcd, p.usercd, p.title, \r\n"+ 
			"					   p.prcd, p.regdate, p.price, p.desc as description, \r\n" + 
			"				(select randname from filepicture where  prcd=p.prcd  limit 1)  as randname ,\r\n" + 
			"				(select count(*) from products_likes  where  prcd=p.prcd) as likecnt ,\r\n" + 
			"		        (select count(*) from products_views where  prcd=p.prcd) as viewcnt\r\n" + 
			"		  FROM category c right join products p on c.catgcd=p.catgcd  ";
//			"		WHERE catgid  IN (SELECT catgid FROM all_posts WHERE 1=1 and rootid=:catgid)\r\n";

         
		Map<String, Object> params = new HashMap<String, Object>();
		
	    StringBuffer sb = new StringBuffer(sql);
	    
	    
	   if(!Strings.isNullOrEmpty(input.getCatgid())){
	    	sb.append("WHERE catgid  IN (SELECT catgid FROM all_posts WHERE 1=1 and rootid = cast (:catgid as numeric)) ");
		    params.put("catgid", input.getCatgid());
	    }else {
	    	sb.append("WHERE catgid  IN (SELECT catgid FROM all_posts WHERE 1=1)");
	    }
	    
	    if(!Strings.isNullOrEmpty(input.getRegdate())) {
	    	sb.append("\nAND p.regdate = :regdate");
	    	params.put("regdate", input.getRegdate());
	    }
	   
	    if(!Strings.isNullOrEmpty(input.getPrcd())) {
	    	sb.append("\nAND p.prcd = :prcd");
	    	params.put("prcd", input.getPrcd());
	    }
		
	    
	    List<ProductListBeanOut_R002> result = null; 
		try{
			result  = ConnectionUtils.getNamedParameterJdbcTemplate().query(sb.toString(), params,
					new BeanPropertyRowMapper<ProductListBeanOut_R002>(ProductListBeanOut_R002.class));
		}catch(Exception e){
			System.out.println("error: "+sb.toString());
		}
		return result;
	}

	@Override
	public List<CategoryCount_OUT001> subCatgCount(ProductParam_IN001 input) {
		String sql = "	with recursive all_posts as (\r\n" + 
				"	    select catgid, parentid, catgid as rootid from category t1 \r\n" + 
				"	    union all \r\n" + 
				"		select  c1.catgid,c1.parentid,p.rootid \r\n" + 
				"	    from category c1 join all_posts p on p.catgid = c1.parentid)\r\n" + 
				"     select  count(*) as cnt,c.nm_eng\r\n" + 
				"        FROM category c right join products p on c.catgcd=p.catgcd \r\n";
				/*"        WHERE c.catgid  IN (SELECT catgid FROM all_posts where 1=1 and rootid=9 )*/
	         
			  Map<String, Object> params = new HashMap<String, Object>();	
		    StringBuffer sb = new StringBuffer(sql);
		    if(!Strings.isNullOrEmpty(input.getCatgid())){
		    	sb.append("WHERE catgid  IN (SELECT catgid FROM all_posts WHERE 1=1 and  rootid=cast (:catgid as numeric))");
			    params.put("catgid", input.getCatgid());
		    }else {
		    	sb.append("WHERE catgid  IN (SELECT catgid FROM all_posts WHERE 1=1)");
		    }
		    
		    if(!Strings.isNullOrEmpty(input.getRegdate())) {
		    	sb.append("\nAND p.regdate=:regdate");
		    	params.put("regdate", input.getRegdate());
		    }
		   
		    if(!Strings.isNullOrEmpty(input.getPrcd())) {
		    	sb.append("\nAND p.prcd=:prcd");
		    	params.put("prcd", input.getPrcd());
		    }
			
		    sb.append("\ngroup by c.catgid, c.nm_eng");
		    List<CategoryCount_OUT001> result = null; 
			try{
				result  = ConnectionUtils.getNamedParameterJdbcTemplate().query(sb.toString(), params,
						new BeanPropertyRowMapper<CategoryCount_OUT001>(CategoryCount_OUT001.class));
			}catch(Exception e){
				System.out.println("error: "+sb.toString());
			}
			return result;
		}
	
	
	/*
	 * Preview product*/
	@Override
	public List<ProductAddressOut_R001> getProductAddress(String prcd) {
		
		String sql =  " select  fname||' '|| lname as fullname,  d.cphone, d.province, d.detail from user_detail  a \n" + 
					  "     join users b on a.usercd=b.usercd \n" + 
					  "     join products c on a.usercd = c.usercd\n" + 
					  "     join address d on d.prcd = c.prcd\n" + 
					  "	where c.prcd='"+prcd+"'" ;
		
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("prcd", prcd);
	 
		List<ProductAddressOut_R001> result  = null;
		try{
			result  = ConnectionUtils.getNamedParameterJdbcTemplate().query(sql, params,
					new BeanPropertyRowMapper<ProductAddressOut_R001>(ProductAddressOut_R001.class));
		}catch(Exception e){
			
		}
	return result;
	
	}

	@Override
	public List<FileUploadBean> getProductPictures(String prcd) {
		
		String sql =  " SELECT \r\n" + 
					  "		orname, randname, regdate,\r\n" + 
					  "		\"type\", \"path\", \"size\",\r\n" + 
					  "		prcd, usercd, catgcd\r\n"+
					  " FROM filepicture WHERE prcd = '"+prcd+"'" ;
		
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("prcd", prcd);
	 
		List<FileUploadBean> result  = null;
		try{
			result  = ConnectionUtils.getNamedParameterJdbcTemplate().query(sql, params,
					new BeanPropertyRowMapper<FileUploadBean>(FileUploadBean.class));
		}catch(Exception e){
			
		}
	return result;
	}

	@Override
	public List<ProductListBeanOut_R002> getRelatedProduct(ProductParam_IN001 input) {

		String sql = "with recursive all_posts as (\r\n" + 
			"				select catgid, parentid, catgid as rootid from category t1 \r\n" + 
			"			union all \r\n" + 
			"				select c1.catgid,c1.parentid,p.rootid \r\n" + 
			"			from category c1 join all_posts p on p.catgid = c1.parentid) \r\n" + 
			"				select  c.parentid, c.nm_eng, c.catgcd, p.title, p.prcd, p.regdate, p.price, p.desc as description,\r\n" + 
			"				(select randname from filepicture where  prcd=p.prcd  limit 1)  as randname ,\r\n" + 
			"				(select count(*) from products_likes  where  prcd=p.prcd) as likecnt ,\r\n" + 
			"		        (select count(*) from products_views where  prcd=p.prcd) as viewcnt\r\n" + 
			"		  FROM category c right join products p on c.catgcd=p.catgcd  ";
//			"		WHERE catgid  IN (SELECT catgid FROM all_posts WHERE 1=1 and rootid=:catgid)\r\n";

         
		Map<String, Object> params = new HashMap<String, Object>();
		
	    StringBuffer sb = new StringBuffer(sql);
	    
	    
	   if(!Strings.isNullOrEmpty(input.getParentid())){
	    	sb.append("WHERE catgid  IN (SELECT catgid FROM all_posts WHERE 1=1 and rootid = cast (:parentid as numeric)) ");
		    params.put("parentid", input.getParentid());
	   }else {
		   	sb.append("WHERE catgid  IN (SELECT catgid FROM all_posts WHERE 1=1)");
	   }

	   if(!Strings.isNullOrEmpty(input.getPrcd())) {
	    	sb.append("\nAND p.prcd <> :prcd");
	    	params.put("prcd", input.getPrcd());
	    }
		
	    
	    List<ProductListBeanOut_R002> result = null; 
		try{
			result  = ConnectionUtils.getNamedParameterJdbcTemplate().query(sb.toString(), params,
					new BeanPropertyRowMapper<ProductListBeanOut_R002>(ProductListBeanOut_R002.class));
		}catch(Exception e){
			System.out.println("error: "+sb.toString());
		}
		return result;
	}

	@Override
	public void insertViewProduct(ProductBeanIn_U001 input) {

		String sql = "insert into products_views (prcd, usercd) "
				+ "	  values (:prcd, :usercd)";
		try{
			ConnectionUtils.getNamedParameterJdbcTemplate().update(sql,SqlFormatUtils.getSqlParameterSource(input));
        }catch(Exception e){
        	System.out.println("error sql: "+sql);
        }
	}


}
