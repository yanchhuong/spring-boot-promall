package com.code.dao;

import java.util.List;

import com.code.model.FileUploadBean;
import com.code.model.UserDetailInOut;

public interface IUserDetailRepostory {
	public List<UserDetailInOut> getUserDetails(String usercd);
	public void updateUserPhonno(UserDetailInOut input);
	public void updateUserEmail(UserDetailInOut input);
	public void updateUserBithdate(UserDetailInOut input);
	public void updateUserGender(UserDetailInOut input);
	public List<FileUploadBean> getProfileImage(UserDetailInOut input);
}
