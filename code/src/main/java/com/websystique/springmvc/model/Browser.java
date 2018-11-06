package com.websystique.springmvc.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="BROWSER_LIST")
public class Browser {

	@Id
	private String name;

	@Column(name="webdriver", length=100)
	private String webdriver;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getWebdriver() {
		return webdriver;
	}

	public void setWebdriver(String webdriver) {
		this.webdriver = webdriver;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Browser))
			return false;
		Browser other = (Browser) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;

		return true;
	}

	@Override
	public String toString() {
		return "Browser [name=" + name + ", webdriver=" + webdriver +"]";
	}

	
	
	
}
