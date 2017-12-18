package com.code.dao;

import java.util.List;

import com.code.model.CategoryBean;
import com.code.model.CategoryBean_R001;

public interface ICategoryRepository {
	public List<CategoryBean_R001> findAlls();
	public int getCatidCount();
	public void insertCategory(CategoryBean input);
	public void updateCategory(CategoryBean input);
	public void removeMenuTree(int rootid);
	public void delete(long CategoryId);
}
