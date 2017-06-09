package com.code.dao;
import com.code.model.*;
import java.util.List;
public interface ICategory {
	public void addCategory(CategoryBean p);
	public void updateCategory(CategoryBean p);
	public List<CategoryBean> listCategorys();
	public CategoryBean getCategoryById(int id);
	public void removeCategory(int id);
}
