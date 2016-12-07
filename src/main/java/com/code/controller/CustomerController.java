package com.code.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.code.formater.SmartFormater;
import com.code.model.Customer;

import com.code.service.impl.CustomerServiceImpl;

/*@ComponentScan("com.springjava4dev.postgres.service.impl, com.springjava4dev.postgres.dao.impl")*/
@RestController
@RequestMapping("/customer")
public class CustomerController extends SmartFormater{
	   private CustomerServiceImpl customerServiceImpl;

	 @Autowired
     public CustomerController(CustomerServiceImpl customerServiceImpl){
    	 this.customerServiceImpl = customerServiceImpl;
     }
	
	 @RequestMapping(value="/load/all", method=RequestMethod.GET)
	 public Customer list(HttpServletRequest request){
		 Long id = Long.valueOf(request.getParameter("id"));
		 return customerServiceImpl.getCustomerById(id);
	 }
	 @RequestMapping(value="/load/alls", method=RequestMethod.GET)
	 @ResponseBody
	 public Iterable<Customer> lists(){
		 return customerServiceImpl.loadAllCustomer();
	 }
	 
	 @RequestMapping(value="/load/{id}")
	 public String byfirst(@PathVariable(value="id") Long id){
		 String result="No data in database";
	    	try{
	    		if(customerServiceImpl.getCustomerById(id).equals(null)!=true){
	    			result= objectTOJson(customerServiceImpl.getCustomerById(id));
	    		}
	    	}catch(Exception e){
	    		e.getMessage();
	    	}
	    return result;
	 }
	 
	 @RequestMapping(value="/count",method=RequestMethod.GET)
	 public int byfirst(){
		 return customerServiceImpl.getTotalNumerCustomer();
	 }
	 
	 @RequestMapping(value="/welcome",method=RequestMethod.GET)
	 @ResponseBody
	 public String Hello(){
		 System.out.println("1");
		 return "<h1>Hello Ajax<h1>";
	 }
	 
	  @RequestMapping(value="/form", method=RequestMethod.POST)
	  public String AddCustomer(HttpServletRequest request) {
		  String firstname = request.getParameter("firstname");
		  String lastname = request.getParameter("lastname");
		  Customer customer=new Customer();
		  customer.setFirstname(firstname);
		  customer.setLastname(lastname);
	      customerServiceImpl.InsertAndupdate(customer);
	                 
	       return "add sucess!";
	   }
	  @RequestMapping(value="/delete", method=RequestMethod.POST)
	  public String DeleteCustomer(HttpServletRequest request) {
		  Long id = Long.valueOf(request.getParameter("id"));
	      customerServiceImpl.delete(id);	                 
	      return "Delete sucess!";
	   }
	  
	  @RequestMapping(value="/update", method=RequestMethod.POST)
	  public String UpdateCustomer(HttpServletRequest request) {
		  
		 Integer id = Integer.parseInt(request.getParameter("id"));
		 String firstname = request.getParameter("firstname");
		 String lastname = request.getParameter("lastname");
		 Customer customer=new Customer();
		 customer.setFirstname(firstname);
	     customer.setLastname(lastname);
		 customer.setId(id);
	     customerServiceImpl.InsertAndupdate(customer);
	                 
	       return "update sucess!";
	   }
	
}
