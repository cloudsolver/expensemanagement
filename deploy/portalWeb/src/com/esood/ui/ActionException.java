/*
 * Created on Dec 23, 2005
 */
package com.esood.ui;
/**
 * 
 * 
 * @author Rohit Sood
 */
public class ActionException extends RuntimeException
{
	static final long serialVersionUID =1231280L;
	
	public ActionException()
	{
		super();
	}
	
	public ActionException(String msg)
	{
		super(msg);
	}
}
