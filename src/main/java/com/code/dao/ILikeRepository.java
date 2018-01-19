package com.code.dao;

import java.util.List;

import com.code.model.LikeBeanInOut_001;
import com.code.model.ProductBeanIn_U001;

public interface ILikeRepository {
	

	public void insertLikeProduct(LikeBeanInOut_001 input);
	public void deleteLikeProduct(LikeBeanInOut_001 input);
	public List<LikeBeanInOut_001> checkLikeProduct(LikeBeanInOut_001 input);
}
