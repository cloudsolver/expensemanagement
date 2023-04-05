/*
 * Created on Aug 1, 2005
 */
package com.esood;

public class Demographics extends AbstractEntity
{
	public static final String MALE="M";
	public static final String FEMALE="F";
	
	private String gender;

	/**
	 * @return Returns the gender.
	 */
	public String getGender()
	{
		return gender;
	}

	/**
	 * @param gender The gender to set.
	 */
	public void setGender(String gender)
	{
		this.gender = gender;
	}
	
}
