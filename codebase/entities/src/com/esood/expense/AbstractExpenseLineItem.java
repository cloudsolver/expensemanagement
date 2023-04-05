/*
 * Created on Dec 23, 2005
 */
package com.esood.expense;

import com.esood.AbstractEntity;

public abstract class AbstractExpenseLineItem extends AbstractEntity
{
	/**
	 * The parent of the line item
	 */
	protected Expense expense;
	/**
	 * The transaction amount
	 */
	protected Double transactionAmount;
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
	 * This determines the type of expense
	 * @return
	 */
	public abstract String getExpenseType();
	
	public String toString()
	{
		StringBuffer buffer=new StringBuffer();
		buffer.append(super.toString());
		buffer.append("\nexpense:"+expense);
		return buffer.toString();
	}
	/**
	 * @return Returns the transactionAmount.
	 */
	public Double getTransactionAmount()
	{
		return transactionAmount;
	}
	/**
	 * @param transactionAmount The transactionAmount to set.
	 */
	public void setTransactionAmount(Double transactionAmount)
	{
		this.transactionAmount = transactionAmount;
	}
}
