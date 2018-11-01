package com.websystique.springmvc.dao;

import com.websystique.springmvc.model.User;

import java.util.List;


public interface UserDao {

	User findById(int id);

	
	void save(User user);
	

	
	List<User> findAllUsers();

}

