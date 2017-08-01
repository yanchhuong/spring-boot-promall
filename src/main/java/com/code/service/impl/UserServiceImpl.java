package com.code.service.impl;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.code.dao.IRoleListRepository;
import com.code.dao.IUserDao;
import com.code.model.MUpdateUserStatusIn_U001;
import com.code.model.MUserListIn_R001;
import com.code.model.MUserListOut_R001;
import com.code.model.RoleCountOut_R001;
import com.code.model.RoleListBean_R001;
import com.code.model.UserSignupBeanIn_C001;
import com.code.service.UserService;

@Service 
public class UserServiceImpl implements UserService{
   private IUserDao userDao;
   private IRoleListRepository iRoleListRepository ; 
	@Autowired
	public UserServiceImpl(IUserDao userDao,IRoleListRepository iRoleListRepository ){
		this.userDao=userDao;
		this.iRoleListRepository=iRoleListRepository;
	}
	
	@Override
	public boolean AddUser(UserSignupBeanIn_C001 user) {
		userDao.insertUserLog(user);
		userDao.insertRole(user);
		userDao.insertUserDetail(user);
		return true;
	}

	@Override
	public boolean resetPassword(String username, String newpass) {
		return userDao.resetPassword(username, newpass);
	}

	@Override
	public List<MUserListOut_R001> getUserList(MUserListIn_R001 input) {
		return this.userDao.getUserList(input);
	}

	@Override
	public List<RoleCountOut_R001> getRoleCount() {
		return this.userDao.getRoleCount();
	}

	@Override
	public void updateUserStatus(MUpdateUserStatusIn_U001 input) {
		this.userDao.updateUserStatus(input);
		
	}

	@Override
	public List<RoleListBean_R001> getRoleList() {
		// TODO Auto-generated method stub
		return this.iRoleListRepository.findAll();
	}


}
