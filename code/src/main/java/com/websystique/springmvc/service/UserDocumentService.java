package com.websystique.springmvc.service;

import com.websystique.springmvc.model.UserDocument;

import java.util.List;

public interface UserDocumentService {

	UserDocument findById(int id);

	List<UserDocument> findAll();
	
	List<UserDocument> findAllByUserId(int id);
	
	void saveDocument(UserDocument document);

	void updateDocument(UserDocument document);
	
	void deleteById(int id);

	UserDocument findByName(String name);
}
