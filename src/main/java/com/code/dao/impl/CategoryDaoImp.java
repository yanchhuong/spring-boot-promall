package com.code.dao.impl;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

import com.code.comm.ConnectionUtils;
import com.code.comm.SqlFormatUtils;
import com.code.dao.ICategoryRepository;
import com.code.model.CategoryBean;
import com.code.model.CategoryBean_R001;

@Repository
public class CategoryDaoImp implements ICategoryRepository{
	
	@Autowired
	protected DataSource dataSource;
	
	@PostConstruct
	private void initialize(){
	       ConnectionUtils.setDataSource(dataSource);
	}

	@Override
	public List<CategoryBean_R001> findAlls() {
		String sql = " WITH RECURSIVE category_tree AS "
				 + " (SELECT *,CAST(nm_eng As varchar(1000)) As fullengname,CAST(nm_kh As varchar(1000)) As fullkhname"
				 + " FROM category  WHERE parentid = 0"
				 + " UNION all  SELECT si.*, CAST( sp.fullengname || '>>' || si.nm_eng As varchar(1000)) As fullengname ,"
				 + " CAST( sp.fullkhname || '>>' ||  si.nm_kh As varchar(1000)) As fullkhname"
				 + " FROM category As si INNER JOIN category_tree AS sp"
				 + " ON (si.parentid = cast(sp.catgid as integer))  "
				 + " )SELECT t.* ,f.randname FROM category_tree  t "
				 + " left join  filepicture f on t.catgcd = f.catgcd ORDER BY fullengname;";
		StringBuffer sb = new StringBuffer(sql);
	    List<CategoryBean_R001> result = null; 
		try{
			result  = ConnectionUtils.getNamedParameterJdbcTemplate().query(sb.toString(), 
					new BeanPropertyRowMapper<CategoryBean_R001>(CategoryBean_R001.class));
		}catch(Exception e){
			System.out.println("old sql "+sb.toString());
		}
		return result;
	}

	@Override
	public int getCatidCount() {
		String sql = "select count (catgid) from category";
		int cnt = 0;
		try{
			cnt = ConnectionUtils.getJdbcTemplate().update(sql);
		}catch(Exception e){

		}
		return cnt;
	}

	@Override
	public void removeMenuTree(int rootid) {
		String sql = "with recursive all_posts as (\r\n" + 
				"   select catgid, parentid, catgid as rootid from category t1 \r\n" + 
				"   union all\r\n" + 
				"     select c1.catgid,c1.parentid,p.rootid\r\n" + 
				"   from category c1 join all_posts p on p.catgid = c1.parentid) \r\n" + 
				"     DELETE FROM category WHERE catgid IN (SELECT catgid FROM all_posts WHERE rootid = "+ rootid+");";       
        try{
			ConnectionUtils.getNamedParameterJdbcTemplate().update(sql, SqlFormatUtils.getSqlParameterSource(rootid));
		}catch(Exception e){
			
		}
	}

	@Override
	public void delete(long CategoryId) {
		String sql = "delete from category where catgid = ?";       
        try{
			ConnectionUtils.getNamedParameterJdbcTemplate().update(sql, SqlFormatUtils.getSqlParameterSource(CategoryId));
		}catch(Exception e){
			
		}
		
	}

	@Override
	public void insertCategory(CategoryBean input) {				
		String sql = "INSERT INTO category (parentid, nm_eng, nm_kh, lvl, usercd, regdate, catgcd, catgparent) "
				+ "   values(:parentid, :nm_eng, :nm_kh, :lvl, :usercd, :regdate, :catgcd, :catgparent)" ;
		try{
			ConnectionUtils.getNamedParameterJdbcTemplate().update(sql,SqlFormatUtils.getSqlParameterSource(input));
		}catch(Exception e){
		
		}
	}

	@Override
	public void updateCategory(CategoryBean input) {
		String sql = "UPDATE category SET nm_eng=:nm_eng, nm_kh=:nm_kh, regdate=:regdate where catgid = :catgid";
		try{
			ConnectionUtils.getNamedParameterJdbcTemplate().update(sql,SqlFormatUtils.getSqlParameterSource(input));
		}catch(Exception e){
		
		}
	}
	
}
