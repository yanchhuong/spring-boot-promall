package com.code.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.code.dao.ICategoryRepository;
import com.code.model.CategoryBean;
import com.code.model.CategoryBean_R001;
import com.code.service.ICategoryService;


@Service(value = "categoryService")
public class CategoryServiceImp implements ICategoryService{
	
	private ICategoryRepository iCategory;

	@Autowired(required = true)
	public CategoryServiceImp(ICategoryRepository iCategory){
		this.iCategory=iCategory;		
	}

	    
	@Transactional(readOnly = true)
	@Override
	public List<CategoryBean_R001> findAll() {
		return this.iCategory.findAlls();
	}
	
	@Transactional(readOnly = false)
	@Override
	public void saveCategoryBean(CategoryBean input) {
		if(input.getCatgid() > 0) {
			this.iCategory.updateCategory(input);
		}else {
			this.iCategory.insertCategory(input);
		}	
	}
	@Override
	public void delete(long CategoryBeanId) {
	   this.iCategory.delete(CategoryBeanId);
	}
	
	@Override
	public long getCatgidCount() {
		return this.iCategory.getCatidCount();
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
	public CategoryBean findOne(long CategoryBeanId) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public void updateMenu(long pid, String usercd, long catgid) {
		// TODO Auto-generated method stub
		
	}

}


