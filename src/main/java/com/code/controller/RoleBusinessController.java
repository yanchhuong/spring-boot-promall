package com.code.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.time.DateFormatUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.code.model.RoleBusinessBeanIn_C002;
import com.code.model.RoleBusinessBeanOut_R001;
import com.code.service.IRoleBusinessService;


@RestController
@RequestMapping(value = "/roles")
public class RoleBusinessController {
	private IRoleBusinessService  iRoleBusinessService;
	
	@Autowired
	public RoleBusinessController(IRoleBusinessService  iRoleBusinessService){
		this.iRoleBusinessService=iRoleBusinessService;
	}
	@RequestMapping(value = "/list_roles_for_user", method = RequestMethod.POST)
	 public  @ResponseBody Map<String,Object> updateUsers(@RequestBody RoleBusinessBeanOut_R001 input) {
		List<RoleBusinessBeanOut_R001> rec = this.iRoleBusinessService.getRoleBusinessPerUser(input);
	        return new HashMap<String,Object>(){
	            {
	                put("OUT_REC",rec);
	                put("CODE","200");
	               
	            }
	        };
	}
	@RequestMapping(value = "/add_business_role_for_user", method = RequestMethod.POST)
	 public  @ResponseBody Map<String,Object> addRoleBusiness(@RequestBody RoleBusinessBeanIn_C002 input) {
		RoleBusinessBeanIn_C002 inRec = input;
		   inRec.setRegdate(DateFormatUtils.format(new Date(), "yyyyMMddhhmmss"));
		if(inRec!=null){
			this.iRoleBusinessService.addRoleBusiness(inRec);
		}
	    return new HashMap<String,Object>(){
	            {
	                put("CODE","200");  
	            }
	    };
	}
	@RequestMapping(value = "/remove_business_role", method = RequestMethod.POST)
	 public  @ResponseBody Map<String,Object> removeRoleBusiness(@RequestBody RoleBusinessBeanIn_C002 input) {
		System.out.println("TEST");
		RoleBusinessBeanIn_C002 inRec = input;
		if(inRec!=null){
			this.iRoleBusinessService.removeRoleBusiness(inRec);
		}
	    return new HashMap<String,Object>(){
	            {
	            	put("IN_REC",inRec);  
	                put("CODE","200");  
	            }
	    };
	}

}
