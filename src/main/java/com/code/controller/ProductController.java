package com.code.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.commons.lang.time.DateFormatUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.code.comm.PagingUtils;
import com.code.model.FileUploadBean;
import com.code.model.PostProductBean_C001;
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
	public  @ResponseBody Map<String,Object> getListProduct(@RequestBody ProductListBeanIn_R001 input){
		List<ProductListBeanOut_R001> obj = this.iProductService.getProductList(input);
		PagingUtils page = new PagingUtils();
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

	@RequestMapping(value = "/insert_product", method = RequestMethod.POST)
	public  @ResponseBody Map<String,Object> insertProduct(@RequestBody PostProductBean_C001 input) {
		String ErrorMGS = "Cannot Insert";
		for(FileUploadBean in:input.getInRec()) {
			System.out.println(in.getRandname());
		}

		PostProductBean_C001 obj = input;
		obj.setPrcd(UUID.randomUUID().toString()+DateFormatUtils.format(new Date(), "yyyyMMddHHmmss"));
		obj.setRegdate(DateFormatUtils.format(new Date(), "yyyyMMddHHmmss"));
		System.out.println(obj.getCatgcd());
		
		if(this.iProductService.insertProducts(obj)) {
			ErrorMGS="Update success!";
		}
		return new HashMap<String,Object>(){
			{
                put("SMS","Inserted@");
            }
        };
	}

}
