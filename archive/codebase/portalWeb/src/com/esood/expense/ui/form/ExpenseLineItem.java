/*
 * Created on Dec 18, 2005
 */
package com.esood.expense.ui.form;


import java.text.DecimalFormat;

import com.esood.ui.AbstractLineItem;

/**
 *  Expense Line Item The line item
 * 
 * @author Rohit Sood
 */
public abstract class ExpenseLineItem extends AbstractLineItem
{
	public final static long serialVersionUID = 1327198423L;

	protected String lineItemType;

	protected String transactionDate = "";

	protected String transactionAmount = "";

	protected String transactionJustification = "";

	private String qualified = "";

	/**
	 * This will format the transaction amount
	 * @return Returns the transactionAmount.
	 */
	public String getTransactionAmount()
	{
		try
		{
			Double txAmt=new Double(transactionAmount);
			DecimalFormat format=new DecimalFormat("##0.00");
			transactionAmount=format.format(txAmt.doubleValue());
		} catch (Exception e)
		{
			log.warn("Could not format transaction amount "+transactionAmount+"\n"+e.getMessage());
		}
		return transactionAmount;
	}

	/**
	 * @param transactionAmount
	 *            The transactionAmount to set.
	 */
	public void setTransactionAmount(String transactionAmount)
	{
		this.transactionAmount = transactionAmount;
	}

	/**
	 * @return Returns the transactionDate.
	 */
	public String getTransactionDate()
	{

		return transactionDate;

	}

	/**
	 * @param transactionDate
	 *            The transactionDate to set.
	 */
	public void setTransactionDate(String transactionDate)
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
	 * @param transactionJustification
	 *            The transactionJustification to set.
	 */
	public void setTransactionJustification(String transactionJustification)
	{
		this.transactionJustification = transactionJustification;
	}

	/**
	 * @return Returns the lineItemType.
	 */
	public String getLineItemType()
	{
		return lineItemType;
	}

	public abstract String getLineItemTypeDisplay();

	/**
	 * @param lineItemType
	 *            The lineItemType to set.
	 */
	public void setLineItemType(String lineItemType)
	{
		this.lineItemType = lineItemType;
	}

	/**
	 * @return Returns the qualified.
	 */
	public String getQualified()
	{
		return qualified;
	}

	/**
	 * @param qualified The qualified to set.
	 */
	public void setQualified(String qualified)
	{
		this.qualified = qualified;
	}

}
