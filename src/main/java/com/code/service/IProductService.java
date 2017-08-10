package com.code.service;

import java.util.List;

import com.code.model.ProductBeanIn_U001;
import com.code.model.ProductListBeanIn_R001;
import com.code.model.ProductListBeanOut_R001;

public interface IProductService {
	public List<ProductListBeanOut_R001> getProductList(ProductListBeanIn_R001 input);
	public void updateProductStatus(ProductBeanIn_U001 input) ;

}
