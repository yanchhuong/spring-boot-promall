package com.code.controller;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class ViewsController extends WebMvcConfigurerAdapter {
	
	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
	        registry.addViewController("/").setViewName("main_page");
	        registry.addViewController("/post").setViewName("post_control_001_view");
	        registry.addViewController("/preview").setViewName("preview_page");
	        registry.addViewController("/admin").setViewName("admin");
	        registry.addViewController("/feedback_control_001_view").setViewName("feedback_control_001_view");
	        registry.addViewController("/user_control_001_view").setViewName("user_control_001_view");
	        registry.addViewController("/menu_control_001_view").setViewName("menu_control_001_view");
	        registry.addViewController("/message_control_001_view").setViewName("message_control_001_view");
	        registry.addViewController("/product_control_001_view").setViewName("product_control_001_view");
	        registry.addViewController("/location_control_001_view").setViewName("location_control_001_view");
	        registry.addViewController("/auto_vehicle").setViewName("auto_vehicle");
	}
	
	/*@RequestMapping(value="/",method = RequestMethod.GET)
	public String welcome(HttpServletRequest request, HttpServletResponse response) {
		return "main_page";
	}
	@RequestMapping(value="/post",method = RequestMethod.GET)
	public String postAds() {
		return "post_control_001_view";
	}
	@RequestMapping(value="/preview",method = RequestMethod.GET)
	public String previewPage() {
		return "preview_page";
	}
	@RequestMapping(value="/admin",method = RequestMethod.GET)
	public String admin() {
		return "admin";
	}
	@RequestMapping(value="/feedback_control_001_view",method = RequestMethod.GET)
	public String feedback() {
		return "feedback_control_001_view";
	}
	@RequestMapping(value="/user_control_001_view",method = RequestMethod.GET)
	public String User() {
		return "user_control_001_view";
	}
	@RequestMapping(value="/menu_control_001_view",method = RequestMethod.GET)
	public String menuControl() {
		return "menu_control_001_view";
	}
	@RequestMapping(value="/message_control_001_view",method = RequestMethod.GET)
	public String messageControl() {
		return "message_control_001_view";
	}
	
	@RequestMapping(value="/product_control_001_view",method = RequestMethod.GET)
	public String productControl() {
		return "product_control_001_view";
	}
	@RequestMapping(value="/location_control_001_view",method = RequestMethod.GET)
	public String locationControl001() {
		return "location_control_001_view";
	}
	
	//======= auto vehicle
	@RequestMapping(value="/auto_vehicle",method = RequestMethod.GET)
	public String vehicle() {
		return "auto_vehicle";
	}
	
	*/
}
