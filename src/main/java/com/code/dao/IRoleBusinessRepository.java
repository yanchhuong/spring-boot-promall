package com.code.dao;
import java.util.List;

import com.code.model.RoleBusinessBeanIn_C002;
import com.code.model.RoleBusinessBeanOut_R001;
public interface IRoleBusinessRepository{
	public List<RoleBusinessBeanOut_R001> findByUsernameAndUsercd(RoleBusinessBeanOut_R001 input);
	public void addRoleBusiness(RoleBusinessBeanIn_C002 input);
	public void removeRoleBusiness(RoleBusinessBeanIn_C002 input);

}
