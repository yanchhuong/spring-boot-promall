package com.code.model;

import java.io.Serializable;


 
public class Customer {
     
    private static final long serialVersionUID = -3009157732242241606L;
    //@GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;
    private String firstname;
    private String lastname;
    public Customer(){
    	
    }
    
    public Customer(Integer id,String firstname,String lastname){
    	this.id=id;
    	this.firstname=firstname;
    	this.lastname=lastname;
    }
    public void setId(Integer id){
        this.id = id;
    }
     
    public Integer getId(){
        return id;
    }
     
    public void setFirstname(String firstName){
        this.firstname = firstName;
    }
     
    public String getFirstname(){
        return this.firstname;
    }
     
    public void setLastname(String lastName){
        this.lastname = lastName;
    }
     
    public String getLastname(){
        return this.lastname;
    }
     
   
 
    @Override
    public String toString() {
        return String.format(
                "Customer[id=%d, firstName='%s', lastName='%s']",
                id, firstname, lastname);
    }
}