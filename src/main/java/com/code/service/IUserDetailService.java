package com.code.service;

import java.util.List;

import com.code.model.FileUploadBean;
import com.code.model.UserDetailInOut;
import com.code.model.UserPageInOut_001;

public interface IUserDetailService {
	public List<UserDetailInOut> getUserDetails(String usercd);
	public void updateUserPhonno(UserDetailInOut input);
	public void updateUserEmail(UserDetailInOut input);
	public void updateUserBithdate(UserDetailInOut input);
	public void updateUserGender(UserDetailInOut input);
	public void updateUserProfile(FileUploadBean input);
	public List<FileUploadBean> getProfileImage(UserDetailInOut input);
	
}
