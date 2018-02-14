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

import com.code.dao.IFileImageDao;
import com.code.model.FileUploadBean;
import com.code.model.PostProductBean_C001;
import com.code.model.UserDetailInOut;
import com.code.service.IUserDetailService;

@RestController
@RequestMapping(value = "/userdetails")
public class UserDetailController {

	private IUserDetailService iUserDetailService;
	private IFileImageDao iFileImageDao;
	
	@Autowired
	public UserDetailController(IUserDetailService iUserDetailService, IFileImageDao iFileImageDao) {
		this.iUserDetailService = iUserDetailService;
		this.iFileImageDao = iFileImageDao;
	}
	
	@RequestMapping(value = "/getuserdetails", method = RequestMethod.POST)
	public  @ResponseBody Map<String,Object> getUserDetail(@RequestBody String usercd){
		List<UserDetailInOut> userDetail = this.iUserDetailService.getUserDetails(usercd);
		return new HashMap<String,Object>(){
	            {
	                put("OUT_REC", userDetail);
	            }
	        };
	}
	
	@RequestMapping(value = "/updateuserphonno", method = RequestMethod.POST)
	public @ResponseBody Map<String,Object> updateUserPhonno(@RequestBody UserDetailInOut input) {
		this.iUserDetailService.updateUserPhonno(input);
		return new HashMap<String,Object>(){
			{
                put("SMS","Update Phone number success!");
            }
        };
	}
	
	@RequestMapping(value = "/updateuseremail", method = RequestMethod.POST)
	public @ResponseBody Map<String,Object> updateUserEmail(@RequestBody UserDetailInOut input) {
		this.iUserDetailService.updateUserEmail(input);
		return new HashMap<String,Object>(){
			{
                put("SMS","Update Email success!");
            }
        };
	}

	@RequestMapping(value = "/updateuserbirthdate", method = RequestMethod.POST)
	public @ResponseBody Map<String,Object> updateUserBirthdate(@RequestBody UserDetailInOut input) {
		this.iUserDetailService.updateUserBithdate(input);
		return new HashMap<String,Object>(){
			{
                put("SMS","Update Birthdate success!");
            }
        };
	}

	@RequestMapping(value = "/updateusergender", method = RequestMethod.POST)
	public @ResponseBody Map<String,Object> updateUserGender(@RequestBody UserDetailInOut input) {
		this.iUserDetailService.updateUserGender(input);
		return new HashMap<String,Object>(){
			{
                put("SMS","Update Gender success!");
            }
        };
	}
	
	@RequestMapping(value = "/selectprofileimage", method = RequestMethod.POST)
	public @ResponseBody Map<String,Object> updateUserProfile(@RequestBody UserDetailInOut input) {
		List<FileUploadBean> outrec = this.iUserDetailService.getProfileImage(input);
		System.out.println("in Controller: "+outrec.toString());
		return new HashMap<String,Object>(){
			{
                put("OUT_REC", outrec);
            }
        };
	}
	
}
