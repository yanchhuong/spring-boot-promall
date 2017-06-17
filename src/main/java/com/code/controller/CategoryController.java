package com.code.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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

	    @RequestMapping(value = "/list"
	            , method = RequestMethod.GET
	           /* , produces = {"application/json", "application/xml"}*/)
	    public List<CategoryBean> getCategorys() {
	        return this.CategoryService.findAll();
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

	/*    @RequestMapping(value="/save",method = RequestMethod.POST
	            ,consumes = {"application/json", "application/xml"}
	            , produces = {"application/json", "application/xml"})
	    public Map<String,Object> saveCategory(@RequestBody final CategoryBean Category){
	        this.CategoryService.saveCategory(Category);
	        return new HashMap<String,Object>(){
	            {
	              put("message","Category Saved");
	              put("Category",Category);
	            }
	        };
	    }
*/
}
