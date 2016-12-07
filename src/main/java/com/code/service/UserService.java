package com.code.service;

import java.util.List;
import java.util.Optional;

import com.code.model.User;


public interface UserService {

	User findById(Integer id);
	 
	User getUserByEmail(String email);

	List<User> findAll();
	
	User loginUser(String username,String password);

	void saveOrUpdate(User user);

	void delete(int id);

}