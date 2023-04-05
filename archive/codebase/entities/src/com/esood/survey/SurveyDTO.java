/*
 * Created on Aug 21, 2005
 */
package com.esood.survey;

import com.esood.AbstractDTO;

public class SurveyDTO extends AbstractDTO
{
	static final long serialVersionUID=234809813240L;
	
	private String response01;
	private String response02;
	private String response03;
	private String response04;
	private String response05;
	private String response06;
	private String response07;
	private String response08;
	
	public String toString()
	{
		StringBuffer buffer=new StringBuffer();
		buffer.append("Response 1="+response01+"\n");
		buffer.append("Response 2="+response02+"\n");
		buffer.append("Response 3="+response03+"\n");
		buffer.append("Response 4="+response04+"\n");
		buffer.append("Response 5="+response05+"\n");
		buffer.append("Response 6="+response06+"\n");
		buffer.append("Response 7="+response07+"\n");
		buffer.append("Response 8="+response08+"\n");
		return buffer.toString();
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
	public void setResponse01(String response01)
	{
		this.response01 = response01;
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
	public void setResponse02(String response02)
	{
		this.response02 = response02;
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
	public void setResponse03(String response03)
	{
		this.response03 = response03;
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
	public void setResponse04(String response04)
	{
		this.response04 = response04;
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
	public void setResponse05(String response05)
	{
		this.response05 = response05;
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
	public void setResponse06(String response06)
	{
		this.response06 = response06;
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
	public void setResponse07(String response07)
	{
		this.response07 = response07;
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
	public void setResponse08(String response08)
	{
		this.response08 = response08;
	}


}
