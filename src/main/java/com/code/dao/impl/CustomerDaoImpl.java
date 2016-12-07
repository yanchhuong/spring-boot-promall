package com.code.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

import com.code.dao.CustomerDao;
import com.code.model.Customer;


@Repository
public class CustomerDaoImpl extends JdbcDaoSupport implements CustomerDao{
  
    @Autowired
    DataSource dataSource;
  
    @PostConstruct
    private void initialize(){
        setDataSource(dataSource);
    }
    //...

    @Override
    public void insert(Customer cus) {
        String sql = "INSERT INTO customer " +"(firstname, lastname) VALUES (?, ?)" ;
        this.getJdbcTemplate().update(sql, new Object[]{
         cus.getFirstname(), cus.getLastname()
    });
        
    }

    @Override
	public void inserBatch(List<Customer> cus) {
	    String sql = "INSERT INTO customer " + "(id, firstname, lastname) VALUES (?, ?, ?)";
	    getJdbcTemplate().batchUpdate(sql, new BatchPreparedStatementSetter() {
	        public void setValues(PreparedStatement ps, int i) throws SQLException {
	            Customer customer = cus.get(i);
	            ps.setLong(1, customer.getId());
	            ps.setString(2, customer.getFirstname());
	            ps.setString(3, customer.getLastname());
	        }
	 
	        public int getBatchSize() {
	            return cus.size();
	        }
	    });
	}
	
	@Override
	public List<Customer> loadAllCustomer(){
	    String sql = "SELECT * FROM customer";
	    List< Map < String, Object>> rows = this.getJdbcTemplate().queryForList(sql);
	 
	    List<Customer> result = new ArrayList<Customer>();
	    for(Map <String, Object> row:rows){
	        Customer cus = new Customer();
	        cus.setId((int)row.get("id"));
	        cus.setFirstname((String)row.get("firstname"));
	        cus.setLastname((String) row.get("lastname"));
	        result.add(cus);
	    }
	    return result;
	}

	@Override
	 public Customer findCustomerById(long cust_id) {
	     String sql = "SELECT * FROM customer WHERE id = ? ORDER BY id";
	     return (Customer)this.getJdbcTemplate().queryForObject(sql, new Object[]{cust_id}, new RowMapper<Customer>(){
	         @Override
	         public Customer mapRow(ResultSet rs, int rwNumber) throws SQLException {
	             Customer cust = new Customer();
	             cust.setId(rs.getInt("id"));
	             cust.setFirstname(rs.getString("firstname"));
	             cust.setLastname(rs.getString("lastname"));
	             return cust;
	        }
	    });
	 }


	@Override
	public int getTotalNumberCustomer() {
		 String sql = "SELECT count(*) FROM customer ";
		 int count= this.getJdbcTemplate().queryForObject(sql,new Object[]{}, Integer.class);
		return count;
	}
	@Override
	public Customer findCustomerByfirstname(String firstname) {
		String sql = "SELECT * FROM customer WHERE firstname = ? ORDER BY id";
	     return (Customer)this.getJdbcTemplate().queryForObject(sql, new Object[]{firstname}, new RowMapper<Customer>(){
	         @Override
	         public Customer mapRow(ResultSet rs, int rwNumber) throws SQLException {
	             Customer cust = new Customer();
	             cust.setId(rs.getInt("id"));
	             cust.setFirstname(rs.getString("firstname"));
	             cust.setLastname(rs.getString("lastname"));
	             return cust;
	        }
	    });
	}
	
	@Override
	public void delete(long id) {
		 String sql = "DELETE  FROM customer where id=?";
		 this.getJdbcTemplate().update(sql,id);
	}
	@Override
	public void update(Customer cus) {
		 String sql = "UPDATE customer set firstname=?, lastname=? where id=?";
		 this.getJdbcTemplate().update(sql, new Object[] {cus.getFirstname(),cus.getLastname(),cus.getId()});
	}

}