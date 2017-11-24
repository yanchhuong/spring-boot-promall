package com.code.dao.impl;

import java.util.List;

import javax.annotation.PostConstruct;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

import com.code.dao.ILocationMapRepository;
import com.code.formater.SqlSmartFormat;
import com.code.formater.SqlSmartFormatFunctions;
import com.code.model.LocatMapBeanIn_C001;
import com.code.model.LocatMapBeanIn_R001;
import com.code.model.LocatMapBeanOut_R001;
import com.google.common.base.Strings;


@Repository 
public class LocationRepositoryImpl extends JdbcDaoSupport implements ILocationMapRepository {
	
	private SqlSmartFormat sqlSmartFormat = new SqlSmartFormatFunctions() ; 
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
	public List<LocatMapBeanOut_R001> getLocationMapList(LocatMapBeanIn_R001 input) {
		String sql="with recursive locat_tree as  "
				  + "  (select *,cast(nm_eng as varchar(1000)) as fullengname"
				  + "   from locat_map where lvl=1 "
				  + "  union all select lc.* ,CAST( lt.fullengname || '>>' || lc.nm_eng As varchar(1000)) As fullengname "
				  + " from locat_map as lc inner join locat_tree as lt on (lc.parentid=cast(lt.id as integer))"
				  + " ) select * from locat_tree where 1=1 ";
		
		StringBuffer sb = new StringBuffer(sql);
		if(!Strings.isNullOrEmpty(input.getKeyword())){
			sb.append("and (nm_eng ilike '%"+ input.getKeyword()+"%'");
			sb.append(" or nm_kh ilike '%"+ input.getKeyword()+"%'");
			sb.append(" or mapcd ilike '%"+ input.getKeyword()+"%'");
			sb.append(" or descs ilike '%"+ input.getKeyword()+"%')");
		}
		if(!Strings.isNullOrEmpty(input.getParentid())){
			sb.append(" and parentid = "+ input.getParentid());
		}
		sb.append(" order by fullengname");
		System.out.println(sb.toString());
		List<LocatMapBeanOut_R001> result = getJdbcTemplate().query(sb.toString(), 
						new BeanPropertyRowMapper<LocatMapBeanOut_R001>(LocatMapBeanOut_R001.class));		
			
	   return result;
	}

	@Override
	public void addLocationMap(LocatMapBeanIn_C001 input) {
		String sql = "INSERT INTO locat_map " +"(parentid,lvl,mapcd,nm_eng,nm_kh) VALUES (:parentid,:lvl,:mapcd,:nm_eng,:nm_kh)" ;
       try{
    	   this.getJdbcTemplate().update(sql, sqlSmartFormat.getSqlParameterByModel(input));
       }catch(Exception e){
    	   System.out.println(sql);
       }
		
		
		
	}

}
