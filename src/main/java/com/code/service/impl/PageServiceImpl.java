package com.code.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.code.dao.IPageRepository;
import com.code.model.ProductListBeanOut_R002;
import com.code.model.ProductParam_IN001;
import com.code.model.UserPageInOut_001;
import com.code.service.IPageService;

@Service
public class PageServiceImpl implements IPageService{

	private IPageRepository iUserPageRepository;
	
	@Autowired
	public PageServiceImpl(IPageRepository iUserPageRepository) {
		this.iUserPageRepository = iUserPageRepository;
	}
	
	@Override
	public List<UserPageInOut_001> getUserPage(UserPageInOut_001 input) {
		return iUserPageRepository.getUserPage(input);
	}

	@Override
	public void updatePageName(UserPageInOut_001 input) {
		this.iUserPageRepository.updatePageName(input);
	}

	
}
