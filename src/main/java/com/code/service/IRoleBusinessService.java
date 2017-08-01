package com.code.service;

import java.util.List;

import com.code.model.RoleBusinessBeanOut_R001;

public interface IRoleBusinessService {
    public List<RoleBusinessBeanOut_R001> getRoleBusinessPerUser(RoleBusinessBeanOut_R001 input);
}
