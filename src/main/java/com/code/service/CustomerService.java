package com.code.service;

import java.util.List;

import com.code.model.Customer;

public interface CustomerService {
	

  
    void insertBatch(List<Customer> customers);
    
    //Search=======================
    List<Customer> loadAllCustomer();
    Customer getCustomerById(long cust_id);
    Customer getCustomerByfirstname(String firstname);
    int getTotalNumerCustomer();
    
    
    //Delete=======================
    void delete(long cust_id);
    
    //Insert & update=======================
    void InsertAndupdate(Customer cus_id);
}