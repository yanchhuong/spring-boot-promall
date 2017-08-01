package com.code.dao;
import java.util.List;
import com.code.model.RoleBusinessBeanOut_R001;
public interface IRoleBusinessRepository{
	public List<RoleBusinessBeanOut_R001> findByUsernameAndUsercd(RoleBusinessBeanOut_R001 In);

}
