package com.websystique.springmvc.service;

import com.websystique.springmvc.model.Browser;

import java.util.List;


public interface BrowserService {

	Browser findByName(String name);

	List<Browser> findAllBrowsers();
	


}