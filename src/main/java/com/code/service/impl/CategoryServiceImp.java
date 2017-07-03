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
		
		return this.iCategory.findAlls();
	}
	
	@Transactional(readOnly = false)
	@Override
	public void updateMenu(CategoryBean category) {
		this.iCategory.updateMenu(category);
		
	}
	@Override
	public void saveCategoryBean(CategoryBean CategoryBean) {
		this.iCategory.saveAndFlush(CategoryBean);
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
	public int getCatgidCount() {
		return this.iCategory.getCatgidCount()+1;
	}
	@Override
	public void removeMenuTree(int rootid) {
		this.iCategory.removeMenuTree(rootid);
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

	@Override
	public int getSeqCount(String lvl, long parentid) {
		// TODO Auto-generated method stub
		return this.iCategory.getSeqCount(lvl, parentid);
	}

}


