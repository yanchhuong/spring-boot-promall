package com.code.controller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
@Controller
public class ViewsController {
	
	@RequestMapping(value="/",method = RequestMethod.GET)
	public String welcome() {
		return "main_page";
	}
	@RequestMapping(value="/post",method = RequestMethod.GET)
	public String postAds() {
		return "post_001";
	}
	@RequestMapping(value="/preview",method = RequestMethod.GET)
	public String previewPage() {
		return "preview_page";
	}

}
