/*
 * Created on Sep 8, 2005
 */
package com.esood.expense.ui.form;

import java.lang.reflect.InvocationTargetException;
import java.text.DateFormat;
import java.util.ArrayList;

import java.util.Iterator;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.util.LabelValueBean;

import com.esood.expense.ExpenseLineItemDTO;


public class ExpenseFormHelper
{

	private static List expenseTemplates;

	private static List educationLevels;

	private static List grades;

	private static List flags;
	
	private static List transactionTypes;

	protected static Log log = LogFactory.getLog(ExpenseFormHelper.class);

	/**
	 * Gets a list of DTO lineitems.
	 * 
	 * @param dtos A list of ExpensesLineItemDTOs
	 * @return A list of ExpenseLineItem objects
	 */
	public static List getExpenseLineItems(List dtos)
	{
		log.info("Enter getExpenseLineItems");

		Iterator i = dtos.iterator();
		List expenseLineItems = new ArrayList();
		for (; i.hasNext();)
		{
			ExpenseLineItemDTO dto = (ExpenseLineItemDTO) i.next();
			
			
			try
			{
				if(ExpenseLineItemDTO.TYPE_EDU.equals(dto.getLineItemType()))
				{
					ExpenseLineItem eli=new EduExpenseLineItem();
					BeanUtils.copyProperties(eli, dto);
					//Format the transaction Date
					DateFormat format=DateFormat.getDateInstance();
					if(dto.getTransactionDate()!=null && !"".equals(dto.getTransactionDate()))
					{
						eli.setTransactionDate(format.format(dto.getTransactionDate()));
					}
					else
					{
						eli.transactionDate="";
					}
					expenseLineItems.add(eli);
				}
				else if(ExpenseLineItemDTO.TYPE_STD.equals(dto.getLineItemType()))
				{
					ExpenseLineItem seli=new StdExpenseLineItem();
					BeanUtils.copyProperties(seli, dto);
					DateFormat format=DateFormat.getDateInstance();
					if(dto.getTransactionDate()!=null && !"".equals(dto.getTransactionDate()))
					{
						seli.setTransactionDate(format.format(dto.getTransactionDate()));
					}
					else
					{
						seli.transactionDate="";
					}
					expenseLineItems.add(seli);
				}
				
				
			} catch (IllegalAccessException e)
			{
				log.error(e);
			} catch (InvocationTargetException e)
			{
				log.error(e);
			}

			
		}// end loop

		log.debug("Total expenseLineItems=" + expenseLineItems.size());
		log.info("Exit getExpenseLineItems");
		return expenseLineItems;

	}

	
	public static List getTransactionTypes()
	{
		if(null!=transactionTypes)
		{
			return transactionTypes;
		}
		transactionTypes=new ArrayList();
		transactionTypes.add(new LabelValueBean("Flight/Air-travel", "STDTX00"));
		transactionTypes.add(new LabelValueBean("Car Rental", "STDTX01"));
		transactionTypes.add(new LabelValueBean("Hotel Stay", "STDTX02"));
		transactionTypes.add(new LabelValueBean("Parking", "STDTX03"));
		transactionTypes.add(new LabelValueBean("Meal/Snacks", "STDTX04"));
		transactionTypes.add(new LabelValueBean("Tips", "STDTX05"));
		transactionTypes.add(new LabelValueBean("Laundry", "STDTX06"));
		transactionTypes.add(new LabelValueBean("Tolls", "STDTX07"));
		transactionTypes.add(new LabelValueBean("Misc", "STDTX08"));
		
		return transactionTypes;
	}
	public static List getExpenseTemplates()
	{
		if (null != expenseTemplates)
		{
			return expenseTemplates;
		}

		List expenseTemplates = new ArrayList();
		expenseTemplates.add(new LabelValueBean("Standard Expenses", "EXPENSE01"));
		expenseTemplates.add(new LabelValueBean("Training and Education", "EXPENSE02"));

		return expenseTemplates;
	}

	public static List getEducationLevels()
	{
		if (educationLevels == null)
		{
			educationLevels = new ArrayList();
			educationLevels.add(new LabelValueBean("Not Applicable", "EDU_00"));
			educationLevels.add(new LabelValueBean("High School", "EDU_01"));
			educationLevels.add(new LabelValueBean("Diploma", "EDU_02"));
			educationLevels.add(new LabelValueBean("Bachelor's Degree", "EDU_03"));
			educationLevels.add(new LabelValueBean("Master's Degree", "EDU_04"));
			educationLevels.add(new LabelValueBean("Doctorate Program", "EDU_05"));
			educationLevels.add(new LabelValueBean("Professional Training", "EDU_06"));
			;
		}
		return educationLevels;
	}

	/**
	 * Returns possible answers, like Yes, No, NA
	 * 
	 * @return Returns the flags.
	 */
	public static List getFlags()
	{
		if (flags == null)
		{
			flags = new ArrayList();
			flags.add(new LabelValueBean("Yes", "true"));
			flags.add(new LabelValueBean("No", "false"));
	
		}
		return flags;
	}

	/**
	 * Returns the possible grades.
	 * 
	 * @return Returns the grades.
	 */
	public static List getGrades()
	{
		if (grades == null)
		{
			grades = new ArrayList();
			grades.add(new LabelValueBean("A", "A"));
			grades.add(new LabelValueBean("B", "B"));
			grades.add(new LabelValueBean("C", "C"));
			grades.add(new LabelValueBean("D", "D"));
			grades.add(new LabelValueBean("Not Applicable", "X"));
			grades.add(new LabelValueBean("F", "F"));

		}
		return grades;
	}

}