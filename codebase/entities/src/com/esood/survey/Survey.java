/*
 * Created on Aug 21, 2005
 */
package com.esood.survey;

import com.esood.AbstractEntity;
import com.esood.expense.Expense;

/**
 * Persists the Survey results for the customer
 * The survey is per application version.
 * 
 * @author Rohit
 */
public class Survey extends AbstractEntity
{
	private Expense expense;
	private String response01;
	private String response02;
	private String response03;
	private String response04;
	private String response05;
	private String response06;
	private String response07;
	private String response08;
	
	
	/**
	 * @return Returns the expense.
	 */
	public Expense getExpense()
	{
		return expense;
	}
	/**
	 * @param expense The expense to set.
	 */
	public void setExpense(Expense expense)
	{
		this.expense = expense;
	}
	/**
	 * @return Returns the response01.
	 */
	public String getResponse01()
	{
		return response01;
	}
	/**
	 * @param response01 The response01 to set.
	 */
	public void setResponse01(String repsonse01)
	{
		this.response01 = repsonse01;
	}
	/**
	 * @return Returns the response02.
	 */
	public String getResponse02()
	{
		return response02;
	}
	/**
	 * @param response02 The response02 to set.
	 */
	public void setResponse02(String repsonse02)
	{
		this.response02 = repsonse02;
	}
	/**
	 * @return Returns the response03.
	 */
	public String getResponse03()
	{
		return response03;
	}
	/**
	 * @param response03 The response03 to set.
	 */
	public void setResponse03(String repsonse03)
	{
		this.response03 = repsonse03;
	}
	/**
	 * @return Returns the response04.
	 */
	public String getResponse04()
	{
		return response04;
	}
	/**
	 * @param response04 The response04 to set.
	 */
	public void setResponse04(String repsonse04)
	{
		this.response04 = repsonse04;
	}
	/**
	 * @return Returns the response05.
	 */
	public String getResponse05()
	{
		return response05;
	}
	/**
	 * @param response05 The response05 to set.
	 */
	public void setResponse05(String repsonse05)
	{
		this.response05 = repsonse05;
	}
	/**
	 * @return Returns the response06.
	 */
	public String getResponse06()
	{
		return response06;
	}
	/**
	 * @param response06 The response06 to set.
	 */
	public void setResponse06(String repsonse06)
	{
		this.response06 = repsonse06;
	}
	/**
	 * @return Returns the response07.
	 */
	public String getResponse07()
	{
		return response07;
	}
	/**
	 * @param response07 The response07 to set.
	 */
	public void setResponse07(String repsonse07)
	{
		this.response07 = repsonse07;
	}
	/**
	 * @return Returns the response08.
	 */
	public String getResponse08()
	{
		return response08;
	}
	/**
	 * @param response08 The response08 to set.
	 */
	public void setResponse08(String repsonse08)
	{
		this.response08 = repsonse08;
	}
	
	public String toString()
	{
		StringBuffer buffer=new StringBuffer();
		buffer.append(super.toString());
		return buffer.toString();
	}
}
