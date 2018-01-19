package com.code.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.lang.time.DateFormatUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.code.comm.PAGINATION;
import com.code.comm.PagingUtils;
import com.code.model.MUpdateUserStatusIn_U001;
import com.code.model.MUserListIn_R001;
import com.code.model.MUserListOut_R001;
import com.code.model.RoleCountOut_R001;
import com.code.model.RoleListBean_R001;
import com.code.model.UserSignupBeanIn_C001;
import com.code.service.UserService;
@RestController
@RequestMapping(value = "/users")
public class UserController {
	UserService userService;
	@Autowired
	public UserController(UserService userService) {
		this.userService=userService;
	}
	@RequestMapping(value = "/sign_up",method = RequestMethod.POST)
	public @ResponseBody Map<String,Object> addUser(ModelMap model,HttpServletRequest request,@RequestBody UserSignupBeanIn_C001 input){
		UserSignupBeanIn_C001 record =  input;
        record.setEnabled(true);
        record.setUsername(input.getEmail());
        record.setRegdate(DateFormatUtils.format(new Date(), "yyyyMMddHHmmss"));
        record.setUsercd(UUID.randomUUID().toString()+DateFormatUtils.format(new Date(), "yyyyMMddHHmmss"));
        userService.AddUser(record); 
        return new HashMap<String,Object>(){
            {
                put("SUCCES","Save complete");
                put("SMS","Welcome ngday online!");
                put("ROLE_REC",record);
            }
        };
	}
	@RequestMapping(value = "/list", method = RequestMethod.POST)
	 public  @ResponseBody Map<String,Object> getListUsers(@RequestBody MUserListIn_R001 input) {	   
		   PAGINATION pageIn = new PAGINATION();
		       pageIn.setPageNo(input.getPageNo());
		       pageIn.setPageSize(input.getPageSize());
    	  
		   List<MUserListOut_R001> obj = this.userService.getUserList(input,pageIn);
		   List<RoleCountOut_R001> rolecnt = this.userService.getRoleCount(); 
		   PagingUtils page = this.userService.getPagingUtils(input,pageIn);
	        return new HashMap<String,Object>(){
	            {
	                put("OUT_REC",obj);
	                put("PAGINATION",page.getPagination());
	                put("ROLE_REC",rolecnt);
	            }
	        };
	}
	@RequestMapping(value = "/updatestatus", method = RequestMethod.POST)
	public  @ResponseBody Map<String,Object> updateUsers(@RequestBody MUpdateUserStatusIn_U001 input) {
		this.userService.updateUserStatus(input);
			return new HashMap<String,Object>(){
	            {
	                put("ERROR_SMS","");
	                put("CODE","200");
	               
	            }
	        };
	}
	
	@RequestMapping(value = "/list_roles", method = RequestMethod.GET)
	 public  @ResponseBody Map<String,Object> listRole() {
		List<RoleListBean_R001> rec= this.userService.getRoleList();
	        return new HashMap<String,Object>(){
	            {
	                put("OUT_REC",rec);
	                put("CODE","200");
	            }
	        };
	}
	@RequestMapping(value = "/add_roles_list", method = RequestMethod.POST)
	 public  @ResponseBody Map<String,Object> addRoleList(@RequestBody RoleListBean_R001 input) {
		  if(input!=null){
			  RoleListBean_R001 inRec= input;
			  inRec.setRegdate(DateFormatUtils.format(new Date(), "yyyyMMddHHmmss"));
			  this.userService.addRoleList(input);
		  }
	        return new HashMap<String,Object>(){
	            {
	                put("OUT_REC",input);
	                put("CODE","200"); 
	            }
	        };
	}
	@RequestMapping(value = "/remove_roles_list", method = RequestMethod.POST)
	 public  @ResponseBody Map<String,Object> removeRoleList(@RequestBody RoleListBean_R001 input) {
		  if(input!=null){
			  this.userService.removeRoleListByRole(input);
		  }
	       return new HashMap<String,Object>(){
	            {
	                put("OUT_REC",input);
	                put("CODE","200"); 
	            }
	       };
	}

	
	
	
	
}
