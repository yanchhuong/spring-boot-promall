package com.code.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.code.comm.PagingUtils;
import com.code.model.LocatMapBeanIn_C001;
import com.code.model.LocatMapBeanIn_R001;
import com.code.model.LocatMapBeanOut_R001;
import com.code.service.ILocationMapService;

@RestController
@RequestMapping(value = "/location_map")
public class LocationMapController {
	@Autowired
	private ILocationMapService iLocationMapService;
	@RequestMapping(value = "/list", method = RequestMethod.POST)
	 public  @ResponseBody Map<String,Object> getListLocat(@RequestBody LocatMapBeanIn_R001 input) {
		   List<LocatMapBeanOut_R001> obj = this.iLocationMapService.getLocationMapList(input);
		   PagingUtils page=new PagingUtils();
	        return new HashMap<String,Object>(){
	            {
	                put("OUT_REC",obj);
	                put("SMS","SUCCES");
	            }
	        };
	}
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	 public  @ResponseBody Map<String,Object> addLocation(@RequestBody LocatMapBeanIn_C001 input) {
		   this.iLocationMapService.addLocationMap(input);
	        return new HashMap<String,Object>(){
	            {
	                put("OUT_REC",input);
	                put("SMS","SUCCES");
	            }
	        };
	}

}
