package com.code.service;

import java.util.List;

import com.code.model.*;

public interface UserService {

	boolean  AddUser(UserDetailBean user);
    boolean  resetPassword(String username,String newpass);
    
    public List<MUserListOut_R001> getUserList(MUserListIn_R001 input);
    public List<RoleCountOut_R001> getRoleCount();
    public void updateUserStatus(MUpdateUserStatusIn_U001 input);

}
