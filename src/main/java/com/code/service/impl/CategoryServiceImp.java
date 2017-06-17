package com.code.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.code.dao.ICategory;
import com.code.model.CategoryBean;
import com.code.service.ICategoryService;


@Service(value = "categoryService")
public class CategoryServiceImp implements ICategoryService{
	@Autowired(required = true)
	private ICategory iCategory;

	    
	@Transactional(readOnly = true)
	@Override
	public List<CategoryBean> findAll() {
		// TODO Auto-generated method stub
		return this.iCategory.findAll();
	}

	@Override
	public void saveCategoryBean(CategoryBean CategoryBean) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public CategoryBean findOne(long CategoryBeanId) {
		// TODO Auto-generated method stub
		return this.iCategory.findOne(CategoryBeanId);
	}

	@Override
	public void delete(long CategoryBeanId) {
	this.iCategory.delete(CategoryBeanId);
		
	}

	@Override
	public List<CategoryBean> findByCategoryBeanFirstName(String CategoryBeanFirstName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<CategoryBean> findByCategoryBeanNameAndSalary(String CategoryBeanFirstName, long CategoryBeanSalary) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<CategoryBean> findBySalary(long salary) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<CategoryBean> findByPriceRange(long price1, long price2) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<CategoryBean> findByNameMatch(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<CategoryBean> findByNamedParam(String name, String author, long price) {
		// TODO Auto-generated method stub
		return null;
	}

	
}
