package com.code.service;

import com.code.model.UserDetailBean;

public interface UserService {

	boolean  AddUser(UserDetailBean user);
    boolean  resetPassword(String username,String newpass);
}
