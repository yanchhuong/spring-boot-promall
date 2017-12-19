package com.code.dao.impl;

import java.util.List;

import javax.annotation.PostConstruct;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;
import com.code.dao.IProductRepository;
import com.code.model.PostProductBean_C001;
import com.code.model.ProductBeanIn_U001;
import com.code.model.ProductListBeanIn_R001;
import com.code.model.ProductListBeanOut_R001;
import com.google.common.base.Strings;
@Repository
public class ProductRepositoryImpl extends JdbcDaoSupport implements IProductRepository{
	
	@Autowired
	protected DataSource dataSource;
	 
	@PostConstruct
	private void initialize(){
	       setDataSource(dataSource);
	}
	
	@Override
	public List<ProductListBeanOut_R001> getListProduct(ProductListBeanIn_R001 input) {
		String sql = "select   p.prid,                                       \r\n" + 
				"			p.prcd,                                          \r\n" + 
				"			p.title,                                         \r\n" + 
				"			p.regdate,                                       \r\n" + 
				"			p.price,                                         \r\n" + 
				"			p.desc,                                          \r\n" + 
				"			p.url,                                           \r\n" + 
				"			p.enabled,                                       \r\n" + 
				"			p.catgcd,                                        \r\n" + 
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
			      result = getJdbcTemplate().query(sb.toString(), 
					new BeanPropertyRowMapper<ProductListBeanOut_R001>(ProductListBeanOut_R001.class));
		}catch(Exception e){
			System.out.println(sb.toString());
		}
		return result;
	}
	
	@Override
	public void updateProductStatus(ProductBeanIn_U001 input) {
		 String sql = "update products set enabled=:enabled where prcd = ?";
	     
          try{
        	  this.getJdbcTemplate().update(sql, new Object[]{input.getPrcd()});
          }catch(Exception e){
        	  System.out.println(sql);
          }
	   
	}
	
	@Override
	public void insertPro_Address(PostProductBean_C001 input) {
	
		String sql = "insert into address \r\n" + 
				"	(usercd, cphone, country, province, detail, prcd)\r\n" + 
				"   values(?,?,?,?,?,?)";
		try{
			this.getJdbcTemplate().update(sql, new Object[]{input.getUsercd(), input.getCphone(), input.getCountry(),
															input.getProvince(), input.getDetail(), input.getPrcd()});
        }catch(Exception e){
      	  System.out.println(sql);
        }
	}
	
	@Override
	public boolean insertProduct(PostProductBean_C001 input) {
		boolean chk = false;
		String sql = "insert into products \r\n" + 
				"	(prcd, title, regdate, price, url, \"desc\", catgcd, usercd)\r\n" + 
				"	values(?,?,?,?,?,?,?,?)\r\n";
		try{
			this.getJdbcTemplate().update(sql, new Object[] {input.getPrcd(), input.getTitle(), input.getRegdate(), input.getPrice(), "test",
															 input.getDesc(), input.getCatgcd(), input.getUsercd()});      	  
      	  chk = true;
        }catch(Exception e){
      	  System.out.println(sql);
        }
		return chk;
	}
	
}
