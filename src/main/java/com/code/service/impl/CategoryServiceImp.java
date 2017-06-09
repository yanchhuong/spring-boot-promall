package com.code.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.code.model.CategoryBean;
import com.code.service.ICategoryService;

@Service
public class CategoryServiceImp implements ICategoryService{

	@Override
	@Transactional
	public void addCategory(CategoryBean p) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateCategory(CategoryBean p) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<CategoryBean> listCategorys() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CategoryBean getCategoryById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void removeCategory(int id) {
		// TODO Auto-generated method stub
		
	}

}
