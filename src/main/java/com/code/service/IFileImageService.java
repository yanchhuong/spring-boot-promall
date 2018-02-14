package com.code.service;

import java.util.List;

import com.code.model.FileUploadBean;

public interface IFileImageService {
	public List<FileUploadBean> findAll();
    public void saveFileUploadBean(FileUploadBean fileUploadBean);
    public FileUploadBean findOne(long fileUploadBeanId);
    public void remove(String randname);
    public long getPIDCount();
    public void insertNew(FileUploadBean fileUploadBean);
    public void deleteProfileImage(FileUploadBean fileUploadBean);

}
