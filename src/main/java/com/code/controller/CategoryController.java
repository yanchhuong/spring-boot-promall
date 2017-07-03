package com.code.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.code.dao.ICategory;
import com.code.model.CategoryBean;
import com.code.service.ICategoryService;



@RestController
@RequestMapping(value = "/category")
public class CategoryController {
	
	    @Autowired(required = true)
	    @Qualifier(value = "categoryService")
	    private ICategoryService CategoryService;


	    @RequestMapping(value = "/find/{CategoryId}"
	            , method = RequestMethod.GET
	            , produces = {"application/json", "application/xml"})
	    public CategoryBean getCategory(@PathVariable int CategoryId) {
	    	CategoryBean Category = CategoryService.findOne(CategoryId);
	        return Category;
	    }
       /*
	   @RequestMapping(value = "/list"
	            , method = RequestMethod.GET
	            , produces=MediaType.APPLICATION_JSON_VALUE)
	    public  @ResponseBody List<CategoryBean> getCategorys() {
	        return this.CategoryService.findAll();
	    }*/
	   
		@RequestMapping(value = "/list", method = RequestMethod.GET , produces=MediaType.APPLICATION_JSON_VALUE)
		 public  @ResponseBody Map<String,Object> getCategorys() {
			   List<CategoryBean > obj = this.CategoryService.findAll();
			   int total= this.CategoryService.getCatgidCount();
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
	           /*,consumes = {"application/json", "application/xml"}
	            , produces = {"application/json", "application/xml"})*/
	    public Map<String,Object> saveCategory(@RequestBody CategoryBean category){  
	    	CategoryBean obj= category;
	    	obj.setVscatgid(this.CategoryService.getCatgidCount());
	    	obj.setSeq(this.CategoryService.getSeqCount(category.getLvl(),category.getParentid())+1);
	    	this.CategoryService.saveCategoryBean(obj);
	    
	        return new HashMap<String,Object>(){
	            {
	              put("message","Category Saved");
	              put("Category",category);
	            }
	        };
	    }
	    @RequestMapping(value="/update",method = RequestMethod.POST)
        /*,consumes = {"application/json", "application/xml"}
         , produces = {"application/json", "application/xml"})*/
       public Map<String,Object> updateCategory(@RequestBody CategoryBean category){  	
	    	
 	   this.CategoryService.updateMenu(category);
          return new HashMap<String,Object>(){
         {
            put("message","Category Saved");
            put("Category",category);
          }
        };
      }
	  @RequestMapping(value="/remove",method = RequestMethod.GET)
	  public Map<String,Object> removeMenuTree(@RequestParam(value = "catgid") int rootid){  	
		  System.out.println("@@@"+rootid);
		  this.CategoryService.removeMenuTree(rootid);
			  return new HashMap<String,Object>(){
		           { put("message","Category was delete!"); }
			  };      
		
	         
	 }
	    
}
