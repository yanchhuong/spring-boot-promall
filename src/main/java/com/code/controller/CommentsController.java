package com.code.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.code.model.CommentBeanOut_R001;
import com.code.model.CommentBean_001;
import com.code.service.ICommentsService;

@RestController
@RequestMapping(value = "/comments")
public class CommentsController {

	
private ICommentsService iCommentsService;
	
	@Autowired
	public CommentsController(ICommentsService iCommentsService){
		this.iCommentsService = iCommentsService;
		
	}
	
	
	@RequestMapping(value = "/comments_add", method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> insertCommentAdd(@RequestBody CommentBean_001 input){

		input.setRegdate(DateFormatUtils.format(new Date(), "yyyyMMddHHmmss"));
		this.iCommentsService.insertComments(input);
		
		return new HashMap<String, Object>(){
			{
				put("SMS", "comment success");
			}
		};
	}
	
	
	@RequestMapping(value = "/comments_list", method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> listCommentAdd(@RequestBody CommentBean_001 input){
		List<CommentBeanOut_R001> result = this.iCommentsService.listComments(input);
		
		return new HashMap<String, Object>(){
			{
				put("OUT_REC", result);
			}
		};
	}
	
	
	@RequestMapping(value = "/comments_update", method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> updateComment(@RequestBody CommentBean_001 input){
		
		input.setRegdate(DateFormatUtils.format(new Date(), "yyyyMMddHHmmss"));
		this.iCommentsService.updateComment(input);
		return new HashMap<String, Object>(){
			{
				put("SMS", "comment success");
			}
		};
	}
	
	@RequestMapping(value = "/comments_delete", method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> deleteComment(@RequestBody CommentBean_001 input){
		
		this.iCommentsService.deleteComment(input);
		return new HashMap<String, Object>(){
			{
				put("SMS", "delete success");
			}
		};
	}
	
	
}
