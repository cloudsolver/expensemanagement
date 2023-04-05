/*
 * Created on Jun 17, 2005
 */
package com.esood.refdata;

import com.esood.AbstractEntity;

/**
 * 
 * @author Rohit Sood
 */
public class Questionnaire extends AbstractEntity{
	private String name;
	
	/**
	 * @return Returns the name.
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name The name to set.
	 */
	public void setName(String name) {
		this.name = name;
	}
}
