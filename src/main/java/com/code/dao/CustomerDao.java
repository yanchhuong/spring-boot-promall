package com.code.dao;

import java.util.List;

import com.code.model.Customer;

public interface CustomerDao {
	
	//Insert ========================
    void insert(Customer cus);
    void inserBatch(List<Customer> customers);
    
    //Search ========================
    List<Customer> loadAllCustomer();
    Customer findCustomerById(long cust_id);
    Customer findCustomerByfirstname(String firstname);
    int getTotalNumberCustomer();
    
    
    //delete=========================
    void delete(long id);
    
    
    //update =======================
    void update(Customer cus);
}