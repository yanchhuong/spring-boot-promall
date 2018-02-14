package com.code.service.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.code.dao.IFileImageDao;
import com.code.dao.IUserDetailRepostory;
import com.code.model.FileUploadBean;
import com.code.model.UserDetailInOut;
import com.code.model.UserPageInOut_001;
import com.code.service.IUserDetailService;

@Service
public class UserDetailServiceImpl implements IUserDetailService{

	private IUserDetailRepostory iUserDetailRepository;
	private IFileImageDao iFileImageDao;

	@Autowired
	public UserDetailServiceImpl(IUserDetailRepostory iUserDetailRepository, IFileImageDao iFileImageDao) {
		this.iUserDetailRepository = iUserDetailRepository;
		this.iFileImageDao = iFileImageDao;
	}

	@Override
	public List<UserDetailInOut> getUserDetails(String usercd) {
		return iUserDetailRepository.getUserDetails(usercd);
	}

	@Override
	public void updateUserPhonno(UserDetailInOut input) {
		this.iUserDetailRepository.updateUserPhonno(input);		
	}

	@Override
	public void updateUserEmail(UserDetailInOut input) {
		this.iUserDetailRepository.updateUserEmail(input);
	}

	@Override
	public void updateUserBithdate(UserDetailInOut input) {
		this.iUserDetailRepository.updateUserBithdate(input);		
	}

	@Override
	public void updateUserGender(UserDetailInOut input) {
		this.iUserDetailRepository.updateUserGender(input);
	}

	@Override
	public void updateUserProfile(FileUploadBean input) {
		this.iFileImageDao.insertNew(input);
	}

	@Override
	public List<FileUploadBean> getProfileImage(UserDetailInOut input) {
		return this.iUserDetailRepository.getProfileImage(input);
	}

	
}
