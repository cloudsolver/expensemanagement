/*
 * Created on Oct 5, 2005
 */
package com.esood.expense;

import java.util.Date;

/**
 * This hold the details of the expense. It will map to a separate table.
 * @author  Rohit
 */
public class StandardExpenseLineItem extends AbstractExpenseLineItem
{

	//Expense Line Item types
	public static final String TYPE="STD";


	/**
	 * Date of this transaction
	 */
	protected Date transactionDate;

	/**
	 * The type of the transaction
	 */
	protected String transactionType;
	/**
	 * The justification of the transaction
	 */
	protected String transactionJustification;
	/**
	 * Sub-category of the type. If type is standard then categories vary
	 * If type is education then categories can be PLAN Job Supporting Training, Manager Approved Degree , or Self-Initiated Degree Program.
	 */
	protected String category;

	/**
	 * Qualified for re-imbursement
	 */
	protected Boolean qualified;
	
	public String toString()
	{
		StringBuffer buffer=new StringBuffer();
		buffer.append(super.toString());
		buffer.append("\ntransactionDate:"+transactionDate);
		buffer.append("\ntransactionAmount:"+transactionAmount);
		buffer.append("\ntransactionType:"+transactionType);
		buffer.append("\ntransactionJustification:"+transactionJustification);
		buffer.append("\ncategory:"+category);
		return buffer.toString();
	}
	
	/**
	 * Returns the type for this.
	 * @return
	 */
	public String getExpenseType()
	{
		return TYPE;
	}

	/**
	 * @return Returns the qualified.
	 */
	public Boolean getQualified()
	{
		return qualified;
	}
	/**
	 * @param qualified The qualified to set.
	 */
	public void setQualified(Boolean qualified)
	{
		this.qualified = qualified;
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
	/**
	 * @return Returns the transactionDate.
	 */
	public Date getTransactionDate()
	{
		return transactionDate;
	}
	/**
	 * @param transactionDate The transactionDate to set.
	 */
	public void setTransactionDate(Date transactionDate)
	{
		this.transactionDate = transactionDate;
	}
	/**
	 * @return Returns the transactionJustification.
	 */
	public String getTransactionJustification()
	{
		return transactionJustification;
	}
	/**
	 * @param transactionJustification The transactionJustification to set.
	 */
	public void setTransactionJustification(String transactionJustification)
	{
		this.transactionJustification = transactionJustification;
	}
	/**
	 * @return Returns the transactionType.
	 */
	public String getTransactionType()
	{
		return transactionType;
	}
	/**
	 * @param transactionType The transactionType to set.
	 */
	public void setTransactionType(String transactionType)
	{
		this.transactionType = transactionType;
	}
	/**
	 * @return Returns the category.
	 */
	public String getCategory()
	{
		return category;
	}
	/**
	 * @param category The category to set.
	 */
	public void setCategory(String category)
	{
		this.category = category;
	}
	
}
