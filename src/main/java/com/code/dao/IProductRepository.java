package com.code.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.code.model.PostProductBean_C001;
import com.code.model.ProductBeanIn_U001;
import com.code.model.ProductListBeanIn_R001;
import com.code.model.ProductListBeanOut_R001;


public interface IProductRepository {
	public List<ProductListBeanOut_R001> getListProduct(ProductListBeanIn_R001 input);
	public void updateProductStatus(ProductBeanIn_U001 input);
	
	boolean insertProduct(PostProductBean_C001 input);
	void insertPro_Address(PostProductBean_C001 input);

}
