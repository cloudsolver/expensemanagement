/*
 * Created on Sep 8, 2005
 */
package com.esood.expense.ui.form;

import java.lang.reflect.InvocationTargetException;
import java.text.DateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionMapping;

import com.esood.expense.ExpenseLineItemDTO;
/**
 * Education Expense Line Item. This contains education specific details of the line item.
 * 
 * @author Rohit Sood
 */
public class EduExpenseLineItem extends ExpenseLineItem
{
	static final long serialVersionUID=234809813240L;
	//Details for Education Type
	private String startDate="";
	private String endDate="";
	private String schoolName="";//or school name
	private String courseName="";
	
	private String grade="";//supported by a drop-down
	private String educationLevel="";//supported by a drop-down
	public String getLineItemType()
	{
		return ExpenseLineItemDTO.TYPE_EDU;
	}
	
	public String getLineItemTypeDisplay()
	{
		return "Education";
	}
	
	public String toString(){
		StringBuffer buffer=new StringBuffer();
		buffer.append(EduExpenseLineItem.class.getClass().getName());
		buffer.append(super.toString());
		buffer.append("\nstartDate="+this.startDate);
		buffer.append("\nendDate="+this.endDate);
		return transactionJustification;
	}

	/**
	 * 
	 * @return Returns the merchantName.
	 */
	public String getMerchantName()
	{
		return schoolName;
	}

	/**
	 * @return Returns the courseEndDate.
	 */
	public String getEndDate()
	{
		return endDate;
	}
	/**
	 * @param courseEndDate The courseEndDate to set.
	 */
	public void setEndDate(String courseEndDate)
	{
		this.endDate = courseEndDate;
	}
	/**
	 * @return Returns the courseName.
	 */
	public String getCourseName()
	{
		return courseName;
	}
	/**
	 * @param courseName The courseName to set.
	 */
	public void setCourseName(String courseName)
	{
		this.courseName = courseName;
	}
	/**
	 * @return Returns the educationLevel.
	 */
	public String getEducationLevel()
	{
		return educationLevel;
	}
	/**
	 * @param educationLevel The educationLevel to set.
	 */
	public void setEducationLevel(String educationLevel)
	{
		this.educationLevel = educationLevel;
	}
	/**
	 * @return Returns the grade.
	 */
	public String getGrade()
	{
		return grade;
	}
	/**
	 * @param grade The grade to set.
	 */
	public void setGrade(String grade)
	{
		this.grade = grade;
	}


	/**
	 * @return Returns the schoolName.
	 */
	public String getSchoolName()
	{
		return schoolName;
	}
	/**
	 * @param schoolName The schoolName to set.
	 */
	public void setSchoolName(String schoolName)
	{
		this.schoolName = schoolName;
	}
	/**
	 * @return Returns the startDate.
	 */
	public String getStartDate()
	{
		return startDate;
	}
	/**
	 * @param startDate The startDate to set.
	 */
	public void setStartDate(String startDate)
	{
		this.startDate = startDate;
	}
	
	public void reset(ActionMapping mapping, HttpServletRequest req)
	{
		populate(req);
	}
	/**
	 * Populates the servlet context with variables
	 * @param request The HTTP request on which the attributes will be saved.
	 */
	public void populate(HttpServletRequest request)
	{
		getServlet().getServletContext().setAttribute("educationLevels",ExpenseFormHelper.getEducationLevels());
		getServlet().getServletContext().setAttribute("flags",ExpenseFormHelper.getFlags());
		getServlet().getServletContext().setAttribute("grades",ExpenseFormHelper.getGrades());
				
	}
	/**
	 * Populate the form with the dto
	 * @param dto
	 * @param request
	 */
	public void populate(ExpenseLineItemDTO dto)
	{
		log.info("Enter populate");
		try
		{
			BeanUtils.copyProperties(this,dto);
			//populate the dates with proper fomatting
			DateFormat format=DateFormat.getDateInstance();
			if(dto.getTransactionDate()!=null && !"".equals(dto.getTransactionDate()))
			{
				setTransactionDate(format.format(dto.getTransactionDate()));
			}
			else
			{
				transactionDate="";
			}
			
			if(dto.getStartDate()!=null && !"".equals(dto.getStartDate()))
			{
				setStartDate(format.format(dto.getStartDate()));
			}
			else
			{
				startDate="";
			}
			
			if(dto.getEndDate()!=null && !"".equals(dto.getEndDate()))
			{
				setEndDate(format.format(dto.getEndDate()));
			}
			else
			{
				endDate="";
			}			
		} catch (IllegalAccessException e)
		{
			log.error(e);
		} catch (InvocationTargetException e)
		{
			log.error(e);
		}
		
		log.info("Exit populate");
	}
	/**
	 * Returns the DTO from the form - A manual transfer.
	 * @todo Incomplete - please complete the entire transfer!!
	 * @return ExpenseLineItem dto
	 */
	public ExpenseLineItemDTO getDTO()
	{
		log.info("Enter getDTO");
		log.debug("ExpenseDetailLineItem id="+getId());
		
		ExpenseLineItemDTO dto=new ExpenseLineItemDTO();
		//a manual conversion is required
		try{
			dto.setId(new Integer(super.getId()));
		}catch(NumberFormatException nfe)
		{
			log.warn("Id was not formatted properly "+nfe.getMessage());
		}
		
		dto.setCourseName(courseName);		
		dto.setEducationLevel(educationLevel);
		
		try
		{
			dto.setEndDate(new Date(endDate));
		} catch (RuntimeException e)
		{
			log.warn("End date could not be set "+e.getMessage());
		}
		
		dto.setGrade(grade);
				
		try
		{
			dto.setQualified(new Boolean(getQualified()));
		} catch (RuntimeException e)
		{
			log.warn("Qualified could not be set "+e.getMessage());
		}
		
			
		dto.setSchoolName(schoolName);
		
		try
		{
			dto.setStartDate(new Date(startDate));
		} catch (RuntimeException e)
		{
			log.warn(" "+e.getMessage());
		}
		
		try
		{
			dto.setTransactionAmount(new Double(transactionAmount));
		} catch (NumberFormatException e)
		{
			log.warn(" "+e.getMessage());
		}
		
		try
		{
			dto.setTransactionDate(new Date(transactionDate));
		} catch (RuntimeException e)
		{
			log.warn(" "+e.getMessage());
		}
		
		dto.setTransactionJustification(transactionJustification);
		//dto transactionType will not be set - because it is an edu already
		dto.setLineItemType(getLineItemType());
				
		log.debug("Dto id="+dto.getId());
		log.info("Exit getDTO");
		return dto;
	}
}
