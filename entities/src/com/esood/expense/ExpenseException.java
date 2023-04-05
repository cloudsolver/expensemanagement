/*
 * Created on Sep 11, 2005
 */
package com.esood.expense;

/**
 * This is the exception class used specifically for Expenses.
 * 
 * @author Rohit
 */
public class ExpenseException extends Exception
{
	static final long serialVersionUID=234809813240L;
	
	public ExpenseException(Throwable t)
	{
		super(t);
	}
	public ExpenseException(String msg){
		super(msg);
	}
}
