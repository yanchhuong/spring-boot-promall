package com.code.service.impl;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.code.comm.PAGINATION;
import com.code.comm.PagingUtils;
import com.code.dao.IRoleListRepository;
import com.code.dao.IUserDao;
import com.code.model.MUpdateUserStatusIn_U001;
import com.code.model.MUserListIn_R001;
import com.code.model.MUserListOut_R001;
import com.code.model.MUserListOut_R002;
import com.code.model.RoleCountOut_R001;
import com.code.model.RoleListBean_R001;
import com.code.model.UserSessionBean;
import com.code.model.UserSignupBeanIn_C001;
import com.code.service.UserService;

@Service 
public class UserServiceImpl implements UserService{
	private IUserDao userDao;
   	private IRoleListRepository iRoleListRepository;
   
	@Autowired
	public UserServiceImpl(IUserDao userDao,IRoleListRepository iRoleListRepository ){
		this.userDao = userDao;
		this.iRoleListRepository = iRoleListRepository;
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
	public List<MUserListOut_R001> getUserList(MUserListIn_R001 input,PAGINATION page) {
		return this.userDao.getUserList(input,page);
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
		return this.iRoleListRepository.getRoleBusiness();
	}

	@Override
	public void addRoleList(RoleListBean_R001 input) {
	    this.iRoleListRepository.insertRole(input);
		
	}

	@Override
	public void removeRoleListByRole(RoleListBean_R001 input) {
		 this.iRoleListRepository.removeRoleListByRole(input.getRole());
		
	}

	@Override
	public UserSessionBean getSessionDao(String input) {
		// TODO Auto-generated method stub
		UserSessionBean data=null;
		for(UserSessionBean obj: this.userDao.getSessionDao(input)){
			data=obj;
		}
		return data;
	}

	@Override
	public PagingUtils getPagingUtils(MUserListIn_R001 input,PAGINATION page) {
		// TODO Auto-generated method stub
		return this.userDao.getPagingUtils(input, page);
	}


}
