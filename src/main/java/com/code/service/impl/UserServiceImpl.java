package com.code.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.code.dao.UserDao;
import com.code.model.UserDetailBean;
import com.code.service.UserService;

@Service 
public class UserServiceImpl implements UserService{

	@Autowired
    UserDao userDao;
	
	@Override
	public boolean AddUser(UserDetailBean user) {
		userDao.insertUserLog(user);
		userDao.insertRole(user);
		userDao.insertUserDetail(user);
		return true;
	}

}
