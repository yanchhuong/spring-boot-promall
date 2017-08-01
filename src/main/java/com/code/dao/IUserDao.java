package com.code.dao;

import java.util.List;

import com.code.model.MUpdateUserStatusIn_U001;
import com.code.model.MUserListIn_R001;
import com.code.model.MUserListOut_R001;
import com.code.model.RoleCountOut_R001;
import com.code.model.UserSignupBeanIn_C001;

public interface IUserDao {
   
	void  insertRole(UserSignupBeanIn_C001 user);
    void  insertUserLog(UserSignupBeanIn_C001 user);
    void  insertUserDetail(UserSignupBeanIn_C001 user);
    boolean resetPassword(String username,String newpass);
    
    public List<MUserListOut_R001> getUserList(MUserListIn_R001 input);
    public List<RoleCountOut_R001> getRoleCount();
    public void updateUserStatus(MUpdateUserStatusIn_U001 input);

}
