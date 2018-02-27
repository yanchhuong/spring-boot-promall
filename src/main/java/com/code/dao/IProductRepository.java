package com.code.dao;

import java.util.List;

import com.code.model.CategoryCount_OUT001;
import com.code.model.FileUploadBean;
import com.code.model.PostProductBean_C001;
import com.code.model.ProductAddressOut_R001;
import com.code.model.ProductBeanIn_U001;
import com.code.model.ProductListBeanIn_R001;
import com.code.model.ProductListBeanOut_R001;
import com.code.model.ProductListBeanOut_R002;
import com.code.model.ProductParam_IN001;


public interface IProductRepository {
	public void updateProductStatus(ProductBeanIn_U001 input);
	
	public boolean insertProduct(PostProductBean_C001 input);
	public void insertPro_Address(PostProductBean_C001 input);
	public List<ProductListBeanOut_R002> listProduct(ProductParam_IN001 input);
	public List<CategoryCount_OUT001> subCatgCount(ProductParam_IN001 input);

	
	/*
	 * 
	 * preview product*/
	public List<ProductAddressOut_R001> getProductAddress(String prcd);
	public List<FileUploadBean> getProductPictures(String prcd);
	public List<ProductListBeanOut_R002> getRelatedProduct(ProductParam_IN001 input);
	
	/*
	 * 
	 * count product view*/
	public void insertViewProduct(ProductBeanIn_U001 input);
	
	public int countProducts(ProductParam_IN001 input);
}
