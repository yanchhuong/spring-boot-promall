package com.code.service;

import java.util.List;

import com.code.model.CategoryBean;

public interface ICategoryService {
	public void addCategory(CategoryBean p);
	public void updateCategory(CategoryBean p);
	public List<CategoryBean> listCategorys();
	public CategoryBean getCategoryById(int id);
	public void removeCategory(int id);

}
