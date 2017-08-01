package com.code.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

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
	@RequestMapping(value = "/rolelist", method = RequestMethod.POST)
	 public  @ResponseBody Map<String,Object> updateUsers(@RequestBody RoleBusinessBeanOut_R001 input) {
		System.out.println("TestUpdate");
		//List<RoleBusinessBeanOut_R001> rec = this.iRoleBusinessService.getRoleBusinessPerUser(input);
	        return new HashMap<String,Object>(){
	            {
	          //      put("OUT_REC",rec);
	                put("CODE","200");
	               
	            }
	        };
	}

}
