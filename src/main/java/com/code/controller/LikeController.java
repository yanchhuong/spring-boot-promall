package com.code.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.code.model.LikeBeanInOut_001;
import com.code.model.ProductBeanIn_U001;
import com.code.service.ILikeService;

@RestController
@RequestMapping(value = "/likes")
public class LikeController {
	
	private ILikeService iLikeService;
	
	@Autowired
	public LikeController(ILikeService iLikeService) {
		this.iLikeService = iLikeService;
	}
	
	
	@RequestMapping(value = "/insert_like", method = RequestMethod.POST)
	public  @ResponseBody Map<String,Object> insertLikeProduct(@RequestBody LikeBeanInOut_001 input) {
		this.iLikeService.insertLikeProduct(input);
		return new HashMap<String,Object>(){
				{
	                put("SMS","Insert Like success!");
	            }
	        };
	}
	
	@RequestMapping(value = "/delete_like", method = RequestMethod.POST)
	public  @ResponseBody Map<String,Object> deleteLikeProduct(@RequestBody LikeBeanInOut_001 input) {
		this.iLikeService.deleteLikeProduct(input);
		return new HashMap<String,Object>(){
				{
	                put("SMS","Delete Like success!");
	            }
	        };
	}

	@RequestMapping(value = "/check_like", method = RequestMethod.POST)
	public  @ResponseBody Map<String,Object> checkLikeProduct(@RequestBody LikeBeanInOut_001 input) {
		List<LikeBeanInOut_001> likeData = this.iLikeService.checkLikeProduct(input);
		return new HashMap<String,Object>(){
				{
	                put("OUT_REC", likeData);
	            }
	        };
	}


}
