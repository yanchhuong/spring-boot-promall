package com.code.service;

import java.util.List;

import com.code.model.RoleBusinessBeanIn_C002;
import com.code.model.RoleBusinessBeanOut_R001;

public interface IRoleBusinessService {
    public List<RoleBusinessBeanOut_R001> getRoleBusinessPerUser(RoleBusinessBeanOut_R001 input);
    public void addRoleBusiness(RoleBusinessBeanIn_C002 input);
    public void removeRoleBusiness(RoleBusinessBeanIn_C002 input);
}
