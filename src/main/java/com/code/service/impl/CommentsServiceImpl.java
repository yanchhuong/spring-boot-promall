package com.code.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.code.dao.ICommentsRepository;
import com.code.model.CommentBeanOut_R001;
import com.code.model.CommentBean_001;
import com.code.service.ICommentsService;

@Service
public class CommentsServiceImpl implements ICommentsService {
	
	private ICommentsRepository iCommentsRepository;
	
	@Autowired
	public CommentsServiceImpl(ICommentsRepository iCommentsRepository) {
		this.iCommentsRepository = iCommentsRepository;
	}
	
	
	@Override
	public void insertComments(CommentBean_001 input) {
		this.iCommentsRepository.insertComments(input);		
	}


	@Override
	public List<CommentBeanOut_R001> listComments(CommentBean_001 input) {
		return this.iCommentsRepository.listComments(input);
	}


	@Override
	public void updateComment(CommentBean_001 input) {
		this.iCommentsRepository.updateComment(input);
		
	}


	@Override
	public void deleteComment(CommentBean_001 input) {
		this.iCommentsRepository.deleteComment(input);		
	}
	
}
