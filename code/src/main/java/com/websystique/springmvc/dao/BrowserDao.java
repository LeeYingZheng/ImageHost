package com.websystique.springmvc.dao;

import com.websystique.springmvc.model.Browser;

import java.util.List;


public interface BrowserDao {

	Browser findByName(String name);

	List<Browser> findAllBrowsers();

}

