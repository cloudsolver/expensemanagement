/*
 * Created on Sep 22, 2005
 */
package com.esood.expense.ui.form;

import java.lang.reflect.InvocationTargetException;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.util.Date;


import org.apache.commons.beanutils.BeanUtils;

import com.esood.expense.ExpenseDTO;
import com.esood.ui.WebForm;

/**
 * The web form for capturing data for expenses.
 * 
 * @author Rohit Sood
 */
public class ExpenseForm extends WebForm
{
	private static final String APPROVED = "Approved.";
	private static final String SUBMITTED_BUT_NOT_APPROVED = "Submitted. Please take the survey for approval.";
	private static final String IN_PROCESS = "In process...";
	static final long serialVersionUID=234809813240L;
	//General Information
	private String id;
	private String costCenter;
	private String purpose;
	private String approver;
	private String name;
	private String expenseAmount;
	private String statusDisplay;
	private String dateDisplay;
	private String applicationType;
	private Integer applicationVersion;
	
	
	/**
	 * Populates the form with populate.
	 * @param dto
	 */
	public void populate(ExpenseDTO dto)
	{
		
		log.info("Enter populate ");
		
		try
		{
			BeanUtils.copyProperties(this,dto);
			if(null!=dto.getDate())
			{
				DateFormat format=DateFormat.getDateInstance(DateFormat.MEDIUM);
				this.setDateDisplay(format.format(dto.getDate()));
			}else if (null==dto.getDate())
			{
				setDateDisplay("");
			}
			this.setStatus(dto.getStatus());
			log.debug("Properties copied to the form from dto");
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
	 * @return Returns the approver.
	 */
	public String getApprover()
	{
		return approver;
	}

	/**
	 * @param approver The approver to set.
	 */
	public void setApprover(String approver)
	{
		this.approver = approver;
	}

	/**
	 * @return Returns the name.
	 */
	public String getName()
	{
		return name;
	}

	/**
	 * @param name The name to set.
	 */
	public void setName(String name)
	{
		this.name = name;
	}

	/**
	 * @return Returns the purpose.
	 */
	public String getPurpose()
	{
		return purpose;
	}

	/**
	 * @param purpose The purpose to set.
	 */
	public void setPurpose(String purpose)
	{
		this.purpose = purpose;
	}
	
	public ExpenseDTO getDTO()
	{
		
		ExpenseDTO dto=new ExpenseDTO();
		dto.setUserContextDTO(getContext().getDTO());
		try
		{
			BeanUtils.copyProperties(dto,this);
			if(dateDisplay!=null && !"".equals(dateDisplay))
			{
				dto.setDate(new Date(dateDisplay));
			}
		} catch (IllegalAccessException e)
		{
			log.error(e);
		} catch (InvocationTargetException e)
		{
			log.error(e.getMessage(),e);
			log.error(e.getCause());
			
		}
		
		log.info("Exit getDTO with: \n"+dto);
		return dto;
	}
	
	/**
	 * @return Returns the costCenter.
	 */
	public String getCostCenter()
	{
		return costCenter;
	}
	/**
	 * @param costCenter The costCenter to set.
	 */
	public void setCostCenter(String costCenter)
	{
		this.costCenter = costCenter;
	}
	/**
	 * @return Returns the id.
	 */
	public String getId()
	{
		return id;
	}
	/**
	 * @param id The id to set.
	 */
	public void setId(String id)
	{
		this.id = id;
	}
	/**
	 * @return Returns the expenseAmount.
	 */
	public String getExpenseAmount()
	{
		try
		{
			Double txAmt=new Double(expenseAmount);
			DecimalFormat format=new DecimalFormat("##0.00");
			expenseAmount=format.format(txAmt.doubleValue());
		} catch (Exception e)
		{
			log.warn("Could not format expenseAmount "+e.getMessage());
		}
		return expenseAmount;
	}
	/**
	 * @param expenseAmount The expenseAmount to set.
	 */
	public void setExpenseAmount(String expenseAmount)
	{
		this.expenseAmount = expenseAmount;
	}
	
		
	public void setStatus(Integer status)
	{
		if(status==null)
		{
			return;
		}
		if(status.intValue()==0)
		{
			this.setStatusDisplay(IN_PROCESS);
		}
		if(status.intValue()==1)
		{
			this.setStatusDisplay(SUBMITTED_BUT_NOT_APPROVED);
		}
		if(status.intValue()==2)
		{
			this.setStatusDisplay(APPROVED);
		}
	}
	
	
	
	/**
	 * @return Returns the status.
	 */
	public String getStatusDisplay()
	{
		return statusDisplay;
	}
	/**
	 * @param status The status to set.
	 */
	public void setStatusDisplay(String statusDisplay)
	{
		this.statusDisplay = statusDisplay;
	}
	/**
	 * @return Returns the date
	 */
	public String getDateDisplay()
	{
		return dateDisplay;
	}

	/**
	 * @param date The date to set.
	 */
	public void setDateDisplay(String dateDisplay)
	{
		this.dateDisplay = dateDisplay;
	}


	/**
	 * @return Returns the applicationType.
	 */
	public String getApplicationType()
	{
		return applicationType;
	}


	/**
	 * @param applicationType The applicationType to set.
	 */
	public void setApplicationType(String applicationType)
	{
		this.applicationType = applicationType;
	}


	/**
	 * @return Returns the applicationVersion.
	 */
	public Integer getApplicationVersion()
	{
		return applicationVersion;
	}


	/**
	 * @param applicationVersion The applicationVersion to set.
	 */
	public void setApplicationVersion(Integer applicationVersion)
	{
		this.applicationVersion = applicationVersion;
	}

}
