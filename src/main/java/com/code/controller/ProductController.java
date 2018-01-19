package com.code.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.code.comm.PagingUtils;
import com.code.model.CategoryCount_OUT001;
import com.code.model.FileUploadBean;
import com.code.model.PostProductBean_C001;
import com.code.model.ProductAddressOut_R001;
import com.code.model.ProductBeanIn_U001;
import com.code.model.ProductListBeanIn_R001;
import com.code.model.ProductListBeanOut_R001;
import com.code.model.ProductListBeanOut_R002;
import com.code.model.ProductParam_IN001;
import com.code.service.IProductService;

@RestController
@RequestMapping(value = "/products")
public class ProductController {
	private IProductService iProductService;
	
	@Autowired
	public ProductController(IProductService iProductService){
		this.iProductService = iProductService;
		
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
//	public  @ResponseBody Map<String,Object> insertProduct(HttpServletRequest request, HttpServletResponse response, @RequestBody PostProductBean_C001 input) {
	public  @ResponseBody Map<String,Object> insertProduct(@RequestBody PostProductBean_C001 input) {
		String ErrorMGS = "Cannot Insert";

//		UserSessionBean rec = SessionManager.getSession(request, response);
		
		PostProductBean_C001 obj = input;
//		obj.setUsercd(rec.getUsercd());
		obj.setPrcd(UUID.randomUUID().toString()+DateFormatUtils.format(new Date(), "yyyyMMddHHmmss"));
		obj.setRegdate(DateFormatUtils.format(new Date(), "yyyyMMddHHmmss"));
		
		if(this.iProductService.insertProducts(obj)) {
			ErrorMGS="Update success!";
		}
		return new HashMap<String,Object>(){
			{
                put("SMS","Inserted success");
            }
        };
	}
	
	@RequestMapping(value = "/list_product", method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> listProduct(@RequestBody ProductParam_IN001 input){
		List<ProductListBeanOut_R002> listProduct = this.iProductService.listProduct(input);
		List<CategoryCount_OUT001>    subCategory = this.iProductService.subCatgCount(input);
		return new HashMap<String, Object>(){
			{
				put("SUB_OUTREC", subCategory);
				put("OUT_REC", listProduct);
			}
		};
	}
	
	
	@RequestMapping(value = "/preview_product_address", method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> previewAddress(@RequestBody String prcd){
		List<ProductAddressOut_R001> listProductAddress = this.iProductService.getProductAddress(prcd);
		List<FileUploadBean> listProductPictures = this.iProductService.getProductPictures(prcd);
		return new HashMap<String, Object>(){
			{
				put("OUT_REC", listProductAddress);
				put("PICTURE_REC", listProductPictures);
			}
		};
	}
	
	@RequestMapping(value = "/related_product", method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> getRelatedProduct(@RequestBody ProductParam_IN001 input){
		List<ProductListBeanOut_R002> relatedProduct = this.iProductService.getRelatedProduct(input);
		return new HashMap<String, Object>(){
			{
				put("OUT_REC", relatedProduct);
			}
		};
	}
	
	@RequestMapping(value = "/insert_view", method = RequestMethod.POST)
	public  @ResponseBody Map<String,Object> insertViewProduct(@RequestBody ProductBeanIn_U001 input) {
		this.iProductService.insertViewProduct(input);
		return new HashMap<String,Object>(){
				{
	                put("SMS","Insert View success!");
	            }
	        };
	}
	
	
	
}
