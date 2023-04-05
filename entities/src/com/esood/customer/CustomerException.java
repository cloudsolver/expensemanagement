/*
 * Created on May 8, 2005
 */
package com.esood.customer;

/**
 * 
 * @author Rohit Sood
 */
public class CustomerException extends Exception {
	static final long serialVersionUID=234809813240L;
	public CustomerException(Throwable t)
	{
		super(t);
	}
	public CustomerException(String msg){
		super(msg);
	}
}
