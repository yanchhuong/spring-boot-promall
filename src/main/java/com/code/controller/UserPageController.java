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

import com.code.model.ProductListBeanOut_R002;
import com.code.model.UserPageInOut_001;
import com.code.service.IPageService;

@RestController
@RequestMapping(value = "/userpage")
public class UserPageController {

	private IPageService iUserPageService;
	
	@Autowired
	public UserPageController (IPageService iUserPageService) {
		this.iUserPageService = iUserPageService;
	}
	
	@RequestMapping(value = "/userpagedata", method = RequestMethod.POST)
	public  @ResponseBody Map<String,Object> getPageDetail(@RequestBody UserPageInOut_001 input){
		List<UserPageInOut_001> userpage = this.iUserPageService.getUserPage(input);
		return new HashMap<String,Object>(){
	            {
	                put("OUT_REC", userpage);
	            }
	        };
	}
	
	@RequestMapping(value = "/updatepagename", method = RequestMethod.POST)
	public  @ResponseBody Map<String,Object> updatePageName(@RequestBody UserPageInOut_001 input){
		this.iUserPageService.updatePageName(input);
		return new HashMap<String,Object>(){
	            {
	                put("SMS", "Updated pagename success.");
	            }
	        };
	}
	
//	@RequestMapping(value = "/getpageproducts", method = RequestMethod.POST)
//	public  @ResponseBody Map<String,Object> getPageProducts(@RequestBody UserPageInOut_001 input){
//		List<ProductListBeanOut_R002> pageproducts = this.iUserPageService.getPageProducts(input);
//		int cnt = this.iUserPageService.countProductsPage(input);
//		return new HashMap<String,Object>(){
//	            {
//	                put("OUT_REC", pageproducts);
//	                put("CNT", cnt);
//	            }
//	        };
//	}

	
}
