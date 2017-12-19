package com.code.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import org.apache.commons.lang.time.DateFormatUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.code.model.CategoryBean;
import com.code.model.CategoryBean_R001;

import com.code.service.ICategoryService;
import com.code.service.IFileImageService;



@RestController
@RequestMapping(value = "/category")
public class CategoryController {
	  
	    @Qualifier(value = "categoryService")
	    private ICategoryService CategoryService;
	//    private IFileImageService iFileImageService;
	    
	    @Autowired(required = true)
	    public CategoryController(ICategoryService CategoryService/*,IFileImageService iFileImageService*/){
	    	this.CategoryService= CategoryService;
	    //	this.iFileImageService=iFileImageService;
	    }
	   
		@RequestMapping(value = "/list", method = RequestMethod.GET , produces=MediaType.APPLICATION_JSON_VALUE)
		 public  @ResponseBody Map<String,Object> getCategorys() {
			   List<CategoryBean_R001> obj = this.CategoryService.findAll();
			   long total = this.CategoryService.getCatgidCount();
		        return new HashMap<String,Object>(){
		            {
		                put("TOTAL",total);
		                put("OUT_REC",obj);
		            }
		        };
		  }
		
	    @RequestMapping(value = "/delete/{CategoryId}", method = RequestMethod.DELETE)
	    public Map<String,Object> deleteCategory(@PathVariable final int CategoryId) {
	  
	        this.CategoryService.delete(CategoryId);
	        return new HashMap<String,Object>(){
	            {
	                put("message","Category Deleted");
	                put("CategoryId ",CategoryId);
	            }
	        };
	    }
	    
	    @RequestMapping(value="/save",method = RequestMethod.POST)
	    public Map<String,Object> saveCategory(@RequestBody CategoryBean category){
	    	CategoryBean obj = category;
	    	obj.setRegdate(DateFormatUtils.format(new Date(), "yyyyMMddhhmmss"));
	    	// add new category
	    	if(obj.getCatgcd() == null){
	    		obj.setCatgcd(UUID.randomUUID().toString()+DateFormatUtils.format(new Date(), "yyyyMMddHHmmss"));
	    	}
	    	this.CategoryService.saveCategoryBean(obj);
	        return new HashMap<String,Object>(){
	            {
	              put("message","Category Saved");
	              put("Category","");
	            }
	        };
	    }

	   @RequestMapping(value="/remove",method = RequestMethod.GET)
	   public Map<String,Object> removeMenuTree(@RequestParam(value = "catgid") int rootid){ 
		   this.CategoryService.removeMenuTree(rootid);
		   		return new HashMap<String,Object>(){
		   			{ put("message","Category was delete!"); }
			  };       
	 }
	    
}
