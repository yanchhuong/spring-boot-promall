package com.code.service.impl;

import java.util.Date;
import java.util.UUID;

import org.apache.commons.lang.time.DateFormatUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionSignUp;
import org.springframework.stereotype.Service;

import com.code.dao.IUserDao;
import com.code.model.UserSignupBeanIn_C001;

@Service
public class FacebookConnectionSignup implements ConnectionSignUp {
    @Autowired
    private IUserDao userDao;
 
	@Override
	public String execute(Connection<?> connection) {
		UserSignupBeanIn_C001 user = new UserSignupBeanIn_C001();
        user.setUsername(connection.getDisplayName());
        user.setPassword("123");
        user.getLname();
        
        user.setEnabled(true);
        user.setUsername(connection.getDisplayName());
        user.setRegdate(DateFormatUtils.format(new Date(), "yyyyMMddHHmmss"));
        user.setUsercd(UUID.randomUUID().toString()+DateFormatUtils.format(new Date(), "yyyyMMddHHmmss"));
        
        AddUser(user);
        return user.getUsername();
	}
	public  boolean AddUser(UserSignupBeanIn_C001 user) {
		userDao.insertUserLog(user);
		userDao.insertRole(user);
		userDao.insertUserDetail(user);
		return true;
	}
}