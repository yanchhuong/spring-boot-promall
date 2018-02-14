package com.code.service;

import java.util.List;

import com.code.model.ProductListBeanOut_R002;
import com.code.model.ProductParam_IN001;
import com.code.model.UserPageInOut_001;

public interface IPageService {
	public List<UserPageInOut_001> getUserPage(UserPageInOut_001 input);
	public void updatePageName(UserPageInOut_001 input);
//	public List<ProductListBeanOut_R002> getPageProducts(UserPageInOut_001 input);
//	public int countProductsPage(ProductParam_IN001 input);
	
}
