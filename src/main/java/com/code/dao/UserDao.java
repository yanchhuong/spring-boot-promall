package com.code.dao;

import com.code.model.UserDetailBean;

public interface UserDao {
   
	void  insertRole(UserDetailBean user);
    void  insertUserLog(UserDetailBean user);
    void  insertUserDetail(UserDetailBean user);
    boolean resetPassword(String username,String newpass);
    
    

}
