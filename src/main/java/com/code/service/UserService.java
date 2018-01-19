package com.code.service;

import java.util.List;

import com.code.comm.PAGINATION;
import com.code.comm.PagingUtils;
import com.code.model.*;

public interface UserService {

	boolean  AddUser(UserSignupBeanIn_C001 user);
    boolean  resetPassword(String username,String newpass);
    
    public List<MUserListOut_R001> getUserList(MUserListIn_R001 input,PAGINATION page);
	//pagination
    public PagingUtils getPagingUtils(MUserListIn_R001  input,PAGINATION page);
	
    public List<RoleCountOut_R001> getRoleCount();
    public void updateUserStatus(MUpdateUserStatusIn_U001 input);
    
    //list role
    public List<RoleListBean_R001> getRoleList();
  //list add role
    public void addRoleList(RoleListBean_R001 input);
    //list remove role
    public void removeRoleListByRole(RoleListBean_R001 input);
    
    
    public UserSessionBean getSessionDao(String input);
}
