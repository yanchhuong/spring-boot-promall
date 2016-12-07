package com.code.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.code.dao.CustomerDao;
import com.code.model.Customer;
import com.code.service.CustomerService;

@Service
	public class CustomerServiceImpl implements CustomerService{
	    @Autowired CustomerDao customerDao;
	  
	    public List<Customer> loadAllCustomer(){
	        return   customerDao.loadAllCustomer();
	    }
	    @Override
	    public Customer getCustomerById(long cust_id) {
	    	return customerDao.findCustomerById(cust_id);
	    }
		@Override
		public void insertBatch(List<Customer> customers) {
			customerDao.inserBatch(customers);
		}
		@Override
		public void delete(long cust_id) {
			customerDao.delete(cust_id);
		}
		@Override
		public Customer getCustomerByfirstname(String firstname) {
			return customerDao.findCustomerByfirstname(firstname);
		}

		@Override
		public int getTotalNumerCustomer() {
			return customerDao.getTotalNumberCustomer();
			
		}
		@Override
		public void InsertAndupdate(Customer cus_id) {
			if(cus_id.getId()!=null){
				customerDao.update(cus_id);
			}else{
				customerDao.insert(cus_id);
			}
			
		}
	
	 
	}


