package com.code.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.code.dao.IFileImageDao;
import com.code.dao.IProductRepository;
import com.code.model.CategoryCount_OUT001;
import com.code.model.CommentBean_001;
import com.code.model.FileUploadBean;
import com.code.model.PostProductBean_C001;
import com.code.model.ProductAddressOut_R001;
import com.code.model.ProductBeanIn_U001;
import com.code.model.ProductListBeanIn_R001;
import com.code.model.ProductListBeanOut_R001;
import com.code.model.ProductListBeanOut_R002;
import com.code.model.ProductParam_IN001;
import com.code.service.IProductService;

@Service
public class ProductServiceImp  implements IProductService{
	private IProductRepository iProductRepository;
	private IFileImageDao iFileImageDao;
	@Autowired
	public ProductServiceImp(IProductRepository iProductRepository, IFileImageDao iFileImageDao){
		this.iProductRepository = iProductRepository;
		this.iFileImageDao = iFileImageDao;
	}
	
	@Override
	public List<ProductListBeanOut_R001> getProductList(ProductListBeanIn_R001 input) {
		return this.iProductRepository.getListProduct(input);
	}
	
	@Override
	public void updateProductStatus(ProductBeanIn_U001 input) {
		this.iProductRepository.updateProductStatus(input);
	}
	
	@Override
	public boolean insertProducts(PostProductBean_C001 input) {
		boolean check = false;
		List<FileUploadBean> inputImag = input.getInRec();
		
		if(this.iProductRepository.insertProduct(input)) {
			for(FileUploadBean eachImg : inputImag ) {
				eachImg.setPrcd(input.getPrcd());
				this.iFileImageDao.insertNew(eachImg);
			}
//			if(!input.isChk()) {
//				input.setUsercd(null);
//			}
			
		    this.iProductRepository.insertPro_Address(input);
		    check = true;
		}
		return check;
	}

	@Override
	public List<ProductListBeanOut_R002> listProduct(ProductParam_IN001 input) {
		return this.iProductRepository.listProduct(input);
	}

	@Override
	public List<CategoryCount_OUT001> subCatgCount(ProductParam_IN001 input) {
		return this.iProductRepository.subCatgCount(input);
	}


	/*
	 * preview product*/
	@Override
	public List<ProductAddressOut_R001> getProductAddress(String prcd) {
		return this.iProductRepository.getProductAddress(prcd);
	}

	@Override
	public List<FileUploadBean> getProductPictures(String prcd) {
		return this.iProductRepository.getProductPictures(prcd);
	}

	@Override
	public List<ProductListBeanOut_R002> getRelatedProduct(ProductParam_IN001 input) {
		return this.iProductRepository.getRelatedProduct(input);
	}

	@Override
	public void insertViewProduct(ProductBeanIn_U001 input) {
		this.iProductRepository.insertViewProduct(input);
	}


}
