package com.websystique.springmvc.service;

import com.websystique.springmvc.dao.BrowserDao;
import com.websystique.springmvc.model.Browser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service("browserService")
@Transactional
public class BrowserServiceImpl implements BrowserService{

	@Autowired
	private BrowserDao dao;

	public Browser findByName(String name) {
		return dao.findByName(name);
	}


	public List<Browser> findAllBrowsers() {
		return dao.findAllBrowsers();
	}

	
}
