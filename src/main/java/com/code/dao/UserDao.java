package com.code.dao;
import java.util.List;
import java.util.Optional;

import com.code.model.User;


public interface UserDao {

	User findById(Integer id);
	
	User findOneByEmail(String email);
	
	User loginUser(String username,String password);

	List<User> findAll();

	void save(User user);

	void update(User user);

	void delete(Integer id);

}