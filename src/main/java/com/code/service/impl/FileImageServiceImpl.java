package com.code.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.code.dao.IFileImageDao;
import com.code.model.FileUploadBean;
import com.code.service.IFileImageService;
@Service(value="fileService")
public class FileImageServiceImpl implements IFileImageService{
 
	IFileImageDao iFileImageDao ;
	@Autowired
	public FileImageServiceImpl(IFileImageDao iFileImageDao){
		this.iFileImageDao=iFileImageDao;
	}
	@Override
	public List<FileUploadBean> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void saveFileUploadBean(FileUploadBean fileUploadBean) {
		this.iFileImageDao.insertNew(fileUploadBean);
	}

	@Override
	public FileUploadBean findOne(long fileUploadBeanId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void remove(String randname) {
		this.iFileImageDao.remove(randname);
	}
	@Override
	public long getPIDCount() {
		// TODO Auto-generated method stub
		return 1;
	}
	@Override
	public void insertNew(FileUploadBean fileUploadBean) {
		this.iFileImageDao.insertNew(fileUploadBean);
		
	}
	@Override
	public void deleteProfileImage(FileUploadBean fileUploadBean) {
		this.iFileImageDao.deleteProfileImage(fileUploadBean);
	}


}
