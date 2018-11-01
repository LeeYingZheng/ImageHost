package com.websystique.springmvc.dao;

import com.websystique.springmvc.model.UserDocument;

import java.util.List;

public interface UserDocumentDao {

	List<UserDocument> findAll();
	
	UserDocument findById(int id);

	UserDocument findByName(String name);
	
	void save(UserDocument document);
	
	List<UserDocument> findAllByUserId(int userId);
	
	void deleteById(int id);
}
