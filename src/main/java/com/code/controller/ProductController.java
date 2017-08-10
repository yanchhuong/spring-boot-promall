package com.code.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.code.formater.PagingUtils;
import com.code.model.ProductBeanIn_U001;
import com.code.model.ProductListBeanIn_R001;
import com.code.model.ProductListBeanOut_R001;
import com.code.service.IProductService;

@RestController
@RequestMapping(value = "/products")
public class ProductController {
	private IProductService iProductService;
	
	@Autowired
	public ProductController(IProductService iProductService){
		this.iProductService=iProductService;
		
	}
	@RequestMapping(value = "/list", method = RequestMethod.POST)
	 public  @ResponseBody Map<String,Object> getListProduct(@RequestBody ProductListBeanIn_R001 input) {
		   List<ProductListBeanOut_R001> obj = this.iProductService.getProductList(input);
		   PagingUtils page=new PagingUtils();
	        return new HashMap<String,Object>(){
	            {
	                put("OUT_REC",obj);
	                put("PAGINATION",page);
	            }
	        };
	}
	@RequestMapping(value = "/update_status", method = RequestMethod.POST)
	 public  @ResponseBody Map<String,Object> updateStuts(@RequestBody ProductBeanIn_U001 input) {
		    this.iProductService.updateProductStatus(input);
	        return new HashMap<String,Object>(){
	            {
	                put("OUT_REC",input);
	                put("SMS","Update success!");
	            }
	        };
	}

}
