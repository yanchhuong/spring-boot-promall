package com.code.service;

import java.util.List;

import com.code.model.CommentBeanOut_R001;
import com.code.model.CommentBean_001;

public interface ICommentsService {
	/*
	 * 
	 * comment*/
	public void insertComments(CommentBean_001 input);
	public List<CommentBeanOut_R001> listComments(CommentBean_001 input);
	public void updateComment(CommentBean_001 input);
	public void deleteComment(CommentBean_001 input);
}
