package com.code.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.code.dao.IRoleBusinessRepository;
import com.code.model.RoleBusinessBeanIn_C002;
import com.code.model.RoleBusinessBeanOut_R001;
import com.code.service.IRoleBusinessService;
@Service
public class RoleBusinessServiceImpl implements IRoleBusinessService{
	
    private IRoleBusinessRepository iRoleBusiness;
    
    @Autowired
    public RoleBusinessServiceImpl(IRoleBusinessRepository iRoleBusiness){
    	this.iRoleBusiness=iRoleBusiness;
    }
	@Override
	public List<RoleBusinessBeanOut_R001> getRoleBusinessPerUser(RoleBusinessBeanOut_R001 input) {
		return this.iRoleBusiness.findByUsernameAndUsercd(input);
	}
	@Override
	public void addRoleBusiness(RoleBusinessBeanIn_C002 input) {
		this.iRoleBusiness.addRoleBusiness(input);
		
	}
	@Override
	public void removeRoleBusiness(RoleBusinessBeanIn_C002 input) {
		this.iRoleBusiness.removeRoleBusiness(input);	
	}

}
