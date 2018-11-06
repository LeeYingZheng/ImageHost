package com.websystique.springmvc.service;

import com.websystique.springmvc.dao.UserDocumentDao;
import com.websystique.springmvc.model.UserDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("userDocumentService")
@Transactional
public class UserDocumentServiceImpl implements UserDocumentService{

	@Autowired
	UserDocumentDao dao;

	public UserDocument findById(int id) {
		return dao.findById(id);
	}

	public UserDocument findByName(String name) {
		return dao.findByName(name);
	}

	public List<UserDocument> findAll() {
		return dao.findAll();
	}

	public List<UserDocument> findAllByUserId(int userId) {
		return dao.findAllByUserId(userId);
	}
	
	public void saveDocument(UserDocument document){
		dao.save(document);
	}

	public void deleteById(int id){
		dao.deleteById(id);
	}

	public void updateDocument(UserDocument document) {
		UserDocument entity = dao.findById(document.getId());
		if(entity!=null){
			entity.setName(document.getName());
			entity.setDescription(document.getDescription());
			entity.setContent(document.getContent());
			entity.setCron(document.getCron());
		}
	}
}
