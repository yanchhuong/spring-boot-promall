
package com.code.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import java.util.Map;

@Controller
public class FacebookController {

	@Value("${application.message}")
	private String message;
	

	@RequestMapping(value="/facebook",method = RequestMethod.GET)
	public String hello(Map<String, Object> model) {

		return "facebook";
	}

	

}
