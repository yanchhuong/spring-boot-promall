package com.code.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.code.model.Customer;
import com.code.service.impl.CustomerServiceImpl;

@ComponentScan("com.springjava4dev.postgres.service.impl, com.springjava4dev.postgres.dao.impl")

@Controller
public class SimpleWebController {
 
    @Autowired
    CustomerServiceImpl customerServiceImpl;
     
    Logger log = LoggerFactory.getLogger(this.getClass());
     
    @RequestMapping(value="/form", method=RequestMethod.GET)
    public String customerForm(Model model) {
    	Customer customer=new Customer();
    	int count= (int) customerServiceImpl.getTotalNumerCustomer();
    	
    	customer.setId(count+1);
        model.addAttribute("customer", customer);
        return "form";
    }
 
    @RequestMapping(value="/form", method=RequestMethod.POST)
    public String customerSubmit(@ModelAttribute Customer customer, Model model) {
         
        model.addAttribute("customer", customer);
        String info = String.format("Customer Submission: id = %d, firstname = %s, lastname = %s", 
                                        customer.getId(), customer.getFirstname(), customer.getLastname());
        log.info(info);
        customerServiceImpl.InsertAndupdate(customer);
                 
        return "result";
    }
    
    @RequestMapping(value="/cusedit", method=RequestMethod.GET)
    public String customerEditpdate(@RequestParam("id") long id, Model model) {
  
    	  model.addAttribute("customer",customerServiceImpl.getCustomerById(id));
          return "cusedit";
    }
    
    @RequestMapping(value="/cusedit", method=RequestMethod.POST)
    public String customerUpdate(@ModelAttribute Customer customer, Model model) {
    	 
    	customerServiceImpl.InsertAndupdate(customer);
    	model.addAttribute("customer",customer);
        log.info(customer.toString());
    	  
          return "redirect:loadall";
    }
    
    @RequestMapping(value="/delet", method=RequestMethod.GET)
    public String customerDelet(@RequestParam("id") int id, Model model) {
    	  Customer cus=new Customer();
    	  cus.setId(id);
    //	  model.addAttribute("customer",cus);
    	  customerServiceImpl.delete(id);
    	  log.info(""+id);
          return "redirect:loadall";
    }
    
    
     //Display by id 
    @RequestMapping(value="/load", method=RequestMethod.GET)
    public String customerSubmit(@RequestParam("id") long id, Model model) {
         
        Customer customer = customerServiceImpl.getCustomerById(id);
        model.addAttribute("customer", customer);
         
        return "load";
    }
    
    //Display all
    @RequestMapping(value="/loadall", method=RequestMethod.GET)
    public String customerAll(Model model) {
    
 	   List<Customer> list=new ArrayList<Customer>(); 
 	   
    	for(Customer cust : customerServiceImpl.loadAllCustomer()){
    	       list.add(cust);
    	       log.info(cust.toString());
    	}
        model.addAttribute("list", list);
         
        return "loadall";
    }
    
    
    //Display all
    @RequestMapping(value="/loadby", method=RequestMethod.GET)
    public String customerByfirst(@RequestParam("firstname") String firstname,Model model) {
    	
        model.addAttribute("list", customerServiceImpl.getCustomerByfirstname(firstname));
         
        return "loadall";
    }
    
    //  test jsp
    @RequestMapping(value="/index", method=RequestMethod.GET)
    public String index(Model model) {
    
    //    model.addAttribute("customer", customer);
        return "index";
    }
}
