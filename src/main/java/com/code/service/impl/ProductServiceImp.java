package com.code.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.code.dao.IProductRepository;
import com.code.model.ProductBeanIn_U001;
import com.code.model.ProductListBeanIn_R001;
import com.code.model.ProductListBeanOut_R001;
import com.code.service.IProductService;

@Service
public class ProductServiceImp  implements IProductService{
	private IProductRepository iProductRepository;
	@Autowired
	public ProductServiceImp(IProductRepository iProductRepository){
		this.iProductRepository=iProductRepository;	
	}
	@Override
	public List<ProductListBeanOut_R001> getProductList(ProductListBeanIn_R001 input) {
		// TODO Auto-generated method stub
		return this.iProductRepository.getListProduct(input);
	}
	@Override
	public void updateProductStatus(ProductBeanIn_U001 input) {
		this.iProductRepository.updateProductStatus(input);
		
	}

}
