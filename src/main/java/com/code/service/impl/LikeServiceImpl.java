package com.code.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.code.dao.ILikeRepository;
import com.code.model.LikeBeanInOut_001;
import com.code.service.ILikeService;

@Service
public class LikeServiceImpl implements ILikeService{

	private ILikeRepository iLikeRepository;
	
	@Autowired
	public LikeServiceImpl(ILikeRepository iLikeRepository) {
		this.iLikeRepository = iLikeRepository;
	}

	@Override
	public void insertLikeProduct(LikeBeanInOut_001 input) {
		this.iLikeRepository.insertLikeProduct(input);		
	}

	@Override
	public void deleteLikeProduct(LikeBeanInOut_001 input) {
		this.iLikeRepository.deleteLikeProduct(input);		
	}

	@Override
	public List<LikeBeanInOut_001> checkLikeProduct(LikeBeanInOut_001 input){
		return this.iLikeRepository.checkLikeProduct(input);
	}
	
	
}
