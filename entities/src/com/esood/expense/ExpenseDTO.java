/*
 * Created on Sep 11, 2005
 */
package com.esood.expense;

import java.util.Date;

import com.esood.AbstractDTO;

/**
 * @author  Rohit
 */
public class ExpenseDTO extends AbstractDTO
{
	static final long serialVersionUID=234891031L; 
	private String costCenter;
	private String purpose;
	private String approver;
	private String name;
	private Integer status;
	private Date date;
	private Double expenseAmount;
	private Integer applicationVersion;
	private String applicationType;
	
	public String toString(){
		
		StringBuffer buffer=new StringBuffer();
		buffer.append("Id :"+id+"\n");
		buffer.append("Expense Cost Center:"+costCenter+"\n");
		buffer.append("Purpose:"+purpose+"\n");
		buffer.append("Approver:"+approver+"\n");
		buffer.append("Date:"+date+"\n");
		buffer.append("Name:"+name+"\n");
		buffer.append("Status:"+status+"\n");		
		return buffer.toString();
	}

	/**
	 * @return  Returns the approver.
	 * @uml.property  name="approver"
	 */
	public String getApprover()
	{
		return approver;
	}

	/**
	 * @param approver  The approver to set.
	 * @uml.property  name="approver"
	 */
	public void setApprover(String approver)
	{
		this.approver = approver;
	}

	/**
	 * @return  Returns the name.
	 * @uml.property  name="name"
	 */
	public String getName()
	{
		return name;
	}

	/**
	 * @param name  The name to set.
	 * @uml.property  name="name"
	 */
	public void setName(String name)
	{
		this.name = name;
	}

	/**
	 * @return  Returns the purpose.
	 * @uml.property  name="purpose"
	 */
	public String getPurpose()
	{
		return purpose;
	}

	/**
	 * @param purpose  The purpose to set.
	 * @uml.property  name="purpose"
	 */
	public void setPurpose(String purpose)
	{
		this.purpose = purpose;
	}

	/**
	 * @return  Returns the costCenter.
	 * @uml.property  name="costCenter"
	 */
	public String getCostCenter()
	{
		return costCenter;
	}

	/**
	 * @param costCenter  The costCenter to set.
	 * @uml.property  name="costCenter"
	 */
	public void setCostCenter(String costCenter)
	{
		this.costCenter = costCenter;
	}

	/**
	 * @return Returns the expenseAmount.
	 */
	public Double getExpenseAmount()
	{
		return expenseAmount;
	}

	/**
	 * @param expenseAmount The expenseAmount to set.
	 */
	public void setExpenseAmount(Double expenseAmount)
	{
		this.expenseAmount = expenseAmount;
	}

	/**
	 * @return Returns the date.
	 */
	public Date getDate()
	{
		return date;
	}

	/**
	 * @param date The date to set.
	 */
	public void setDate(Date date)
	{
		this.date = date;
	}

	/**
	 * @return Returns the status.
	 */
	public Integer getStatus()
	{
		return status;
	}

	/**
	 * @param status The status to set.
	 */
	public void setStatus(Integer status)
	{
		this.status = status;
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
