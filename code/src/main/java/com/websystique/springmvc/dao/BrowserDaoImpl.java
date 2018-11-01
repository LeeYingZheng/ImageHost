package com.websystique.springmvc.dao;

import com.websystique.springmvc.model.Browser;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository("browserDao")
public class BrowserDaoImpl extends AbstractDao<Integer, Browser> implements BrowserDao {

	public Browser findByName(String name) {
		System.out.println("name : "+name);
		Criteria crit = createEntityCriteria();
		crit.add(Restrictions.eq("name", name));
		Browser browser = (Browser)crit.uniqueResult();
		return browser;
	}


	@SuppressWarnings("unchecked")
	public List<Browser> findAllBrowsers() {
		Criteria criteria = createEntityCriteria();
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);//To avoid duplicates.
		List<Browser> browsers = (List<Browser>) criteria.list();
		
		return browsers;
	}


}
