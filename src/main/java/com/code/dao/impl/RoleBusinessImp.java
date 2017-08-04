package com.code.dao.impl;
import java.util.List;
import javax.annotation.PostConstruct;
import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

import com.code.dao.IRoleBusinessRepository;
import com.code.model.RoleBusinessBeanIn_C002;
import com.code.model.RoleBusinessBeanOut_R001;

@Repository
public class RoleBusinessImp extends JdbcDaoSupport implements IRoleBusinessRepository{
	
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
	public List<RoleBusinessBeanOut_R001> findByUsernameAndUsercd(RoleBusinessBeanOut_R001 input) {
		String sql =  "select * from user_roles where 1=1";
		StringBuffer sb = new StringBuffer(sql);
		if(input!=null){
			sb.append(" and usercd='"+ input.getUsercd() +"'");
		}
		List<RoleBusinessBeanOut_R001> result  = getJdbcTemplate().query(sb.toString(), 
				new BeanPropertyRowMapper<RoleBusinessBeanOut_R001>(RoleBusinessBeanOut_R001.class));

	   return result;
	}

	@Override
	public void addRoleBusiness(RoleBusinessBeanIn_C002 input) {
		String sql = "INSERT INTO USER_ROLES " +"(username,role,regdate,usercd,sdate,edate,title) VALUES (?,?,?,?,?,?,?)" ;
        System.out.println(sql);
		this.getJdbcTemplate().update(sql, 
        		new Object[]{input.getUsername(),
        				     input.getRole(),
        				     input.getRegdate(),
        				     input.getUsercd(),
        				     input.getSdate(),
        				     input.getEdate(),
        				     input.getTitle()
                            });
	}

	@Override
	public void removeRoleBusiness(RoleBusinessBeanIn_C002 input) {
		String sql = "DELETE from user_roles where username=? and usercd=? and role=?";
        System.out.println(sql);
		this.getJdbcTemplate().update(sql, 
        		new Object[]{input.getUsername(),
        				     input.getUsercd(),
        				     input.getRole()
        				    });
	}

}
