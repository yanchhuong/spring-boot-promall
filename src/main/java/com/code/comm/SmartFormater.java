package com.code.comm;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class SmartFormater {

	public SmartFormater() {
		// TODO Auto-generated constructor stub
	}
	public static String objectTOJson(Object obj) throws JsonProcessingException{
		
		ObjectMapper mapper = new ObjectMapper();
		
		return mapper.writeValueAsString(obj);
		
	}
	
	

}
