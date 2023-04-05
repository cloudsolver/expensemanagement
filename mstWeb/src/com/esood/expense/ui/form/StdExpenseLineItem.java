/*
 * Created on Dec 18, 2005
 */
package com.esood.expense.ui.form;

import java.lang.reflect.InvocationTargetException;
import java.text.DateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionMapping;

import com.esood.expense.ExpenseLineItemDTO;


public class StdExpenseLineItem extends ExpenseLineItem
{
	static final long serialVersionUID=234809813240L;
	
	protected String transactionType = "";
	

	
	public String toString()
	{
		StringBuffer buffer=new StringBuffer();
		
		return buffer.toString();
	}
	/**
	 * Identifies the line item
	 */
	public String getLineItemType()
	{
		return ExpenseLineItemDTO.TYPE_STD;
	}
	
	public String getLineItemTypeDisplay()
	{
		return "Standard";
	}
	
	public void reset(ActionMapping mapping, HttpServletRequest req)
	{
		populate(req);
	}
	/**
	 * Use the form helper to populate the drop-downs etc.
	 * @param req
	 */
	public void populate(HttpServletRequest req)
	{
		req.setAttribute("flags",ExpenseFormHelper.getFlags());
		req.setAttribute("transactionTypes",ExpenseFormHelper.getTransactionTypes());
	}
	/**
	 * Populates this form
	 * @param DTO
	 */
	public void populate(ExpenseLineItemDTO dto)
	{
		log.info("Enter populate (dto)");
		try
		{
			BeanUtils.copyProperties(this,dto);
			this.setTransactionJustification(dto.getTransactionJustification());
			if(null!=dto.getQualified()){
				this.setQualified(dto.getQualified().toString());	
			}
			
			this.setTransactionType(dto.getTransactionType());
			DateFormat format=DateFormat.getDateInstance();
			if(dto.getTransactionDate()!=null && !"".equals(dto.getTransactionDate()))
			{
				setTransactionDate(format.format(dto.getTransactionDate()));
			}
			else
			{
				transactionDate="";
			}
		} catch (IllegalAccessException e)
		{
			log.warn("Could not copy properties "+e.getMessage());
		} catch (InvocationTargetException e)
		{
			log.warn("Could not copy properties "+e.getMessage());
		}
		log.info("Exit populate (dto)");
	}
	
	
	/**
	 * Returns the populated DTO from the form.
	 * @return
	 */
	public ExpenseLineItemDTO getDTO()
	{
		ExpenseLineItemDTO dto=new ExpenseLineItemDTO();
		
		
			dto.setLineItemType(ExpenseLineItemDTO.TYPE_STD);
			dto.setTransactionType(getTransactionType());
			dto.setQualified(new Boolean(getQualified()));
			
			try
			{
				dto.setTransactionAmount(new Double(getTransactionAmount()));
			} catch (NumberFormatException e1)
			{
				log.debug("Format for the number was not good "+e1.getMessage());
			}
			
			if(!"".equals(getTransactionDate())&& null!=getTransactionDate())
			{
			try
			{
				dto.setTransactionDate(new Date(getTransactionDate()));
			} catch (RuntimeException e)
			{
				log.warn("Problem setting date: "+e.getMessage());
			}
			}
			dto.setTransactionJustification(getTransactionJustification());
			
		
		return dto;
		
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

}
