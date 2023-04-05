/*
 * Created on Dec 23, 2005
 */
package com.esood.ui;
/**
 * 
 * 
 * @author Rohit Sood
 */
public class TimeoutException extends RuntimeException
{
	static final long serialVersionUID =1231280L;
	
	public TimeoutException()
	{
		super();
	}
	
	public TimeoutException(String msg)
	{
		super(msg);
	}
	public TimeoutException(Exception e)
	{
		super(e);
	}
}
