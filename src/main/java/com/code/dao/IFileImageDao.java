package com.code.dao;

/*import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;*/

import com.code.model.FileUploadBean;

public interface IFileImageDao /* extends JpaRepository<FileUploadBean,Long>*/{
	 public void remove(String randname);
	 public void insertNew(FileUploadBean input);
	 public void deleteProfileImage(FileUploadBean input);
}
