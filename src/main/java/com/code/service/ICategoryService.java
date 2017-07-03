package com.code.service;

import java.util.List;

import com.code.model.CategoryBean;


public interface ICategoryService {
	public List<CategoryBean> findAll();
    public void saveCategoryBean(CategoryBean CategoryBean);
    public CategoryBean findOne(long CategoryBeanId);
    public void delete(long CategoryBeanId);
    
    public List<CategoryBean> findByCategoryBeanFirstName(String CategoryBeanFirstName);
    public List<CategoryBean> findByCategoryBeanNameAndSalary(String CategoryBeanFirstName, long CategoryBeanSalary);
    public List<CategoryBean> findBySalary(long salary);
    List<CategoryBean> findByPriceRange(long price1, long price2);
    List<CategoryBean> findByNameMatch(String name);
    List<CategoryBean> findByNamedParam(String name, String author, long price);
    
    public void updateMenu(CategoryBean category);
    public int getSeqCount(String lvl,long parentid);
    public int getCatgidCount();
    public void removeMenuTree(int rootid);
}
