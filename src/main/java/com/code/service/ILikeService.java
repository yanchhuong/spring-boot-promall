package com.code.service;

import java.util.List;

import com.code.model.LikeBeanInOut_001;

public interface ILikeService {

	public void insertLikeProduct(LikeBeanInOut_001 input);
	public void deleteLikeProduct(LikeBeanInOut_001 input);
	public List<LikeBeanInOut_001> checkLikeProduct(LikeBeanInOut_001 input);
}
