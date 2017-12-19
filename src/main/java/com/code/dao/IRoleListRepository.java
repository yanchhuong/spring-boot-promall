package com.code.dao;

import java.util.List;
import com.code.model.RoleListBean_R001;

public interface IRoleListRepository {
	/* @Transactional
	 @Modifying
	 @Query(value="DELETE FROM roles_list WHERE role=:role", nativeQuery=true)
	public void removeRoleListByRole(@Param(value="role") String input);*/
	public void removeRoleListByRole(String input);
	public void insertRole(RoleListBean_R001 input);
	List<RoleListBean_R001> getRoleBusiness();

}


