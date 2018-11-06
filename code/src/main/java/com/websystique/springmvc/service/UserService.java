package com.websystique.springmvc.service;

import com.websystique.springmvc.model.User;

import java.util.List;


public interface UserService {
	
	User findById(int id);

	
	void saveUser(User user);
	
	void updateUser(User user);


	List<User> findAllUsers(); 
	


}