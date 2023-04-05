/*
 * Created on Oct 1, 2005
 */
package com.esood.expense;

import com.esood.AbstractEntity;
import com.esood.customer.Customer;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * This is the expense report entity.
 * 
 * @author Rohit
 */
public class Expense extends AbstractEntity
{
	// Expense status
	public static final Integer STATUS_PROCESSING = new Integer(0);

	public static final Integer STATUS_SUBMITTED = new Integer(1);

	public static final Integer STATUS_APPROVED = new Integer(2);

	// LineItems
	private Set expenseLineItems = new HashSet();

	// Customer
	private Customer customer;

	// general
	private String name;

	private Date date;

	private String costCenter;

	private String purpose;

	private String approver;

	private Integer status;

	private Long  taskDuration;
	/**
	 * Version determines the order
	 * 0,1,2
	 */
	private Integer applicationVersion;
	/**
	 * Type determines the actual speed: A is fastest, C - is slowest.
	 */
	private String applicationType;

	/**
	 * Accepts a super class line item
	 * 
	 * @param eli
	 */
	public void addStandardExpenseLineItem(StandardExpenseLineItem eli)
	{
		expenseLineItems.add(eli);
		eli.setExpense(this);
	}

	public void addEducationExpenseLineItem(EducationExpenseLineItem eeli)
	{
		expenseLineItems.add(eeli);
		eeli.setExpense(this);
	}

	public void addTaskDuration(Long seconds)
	{
		log.info("Enter addDuration(Long seconds) "+seconds);
		if (seconds == null)
		{
			return;
		}
		long addDuration = seconds.intValue();
		long currentDuration = 0;

		if (taskDuration != null)
		{
			currentDuration = getTaskDuration().intValue();
		}
		long newDuration = currentDuration + addDuration;
		setTaskDuration(new Long(newDuration));
		log.info("Exit addDuration(Long seconds) "+newDuration);
		return;
	}

	/**
	 * @return Returns the approver.
	 * @uml.property name="approver"
	 */
	public String getApprover()
	{
		return approver;
	}

	/**
	 * @param approver
	 *            The approver to set.
	 * @uml.property name="approver"
	 */
	public void setApprover(String approver)
	{
		this.approver = approver;
	}

	/**
	 * @return Returns the costCenter.
	 * @uml.property name="costCenter"
	 */
	public String getCostCenter()
	{
		return costCenter;
	}

	/**
	 * @param costCenter
	 *            The costCenter to set.
	 * @uml.property name="costCenter"
	 */
	public void setCostCenter(String costCenter)
	{
		this.costCenter = costCenter;
	}

	/**
	 * @return Returns the name.
	 * @uml.property name="name"
	 */
	public String getName()
	{
		return name;
	}

	/**
	 * @param name
	 *            The name to set.
	 * @uml.property name="name"
	 */
	public void setName(String name)
	{
		this.name = name;
	}

	/**
	 * @return Returns the purpose.
	 * @uml.property name="purpose"
	 */
	public String getPurpose()
	{
		return purpose;
	}

	/**
	 * @param purpose
	 *            The purpose to set.
	 * @uml.property name="purpose"
	 */
	public void setPurpose(String purpose)
	{
		this.purpose = purpose;
	}

	/**
	 * @return Returns the status.
	 * @uml.property name="status"
	 */
	public Integer getStatus()
	{
		return status;
	}

	/**
	 * @param status
	 *            The status to set.
	 * @uml.property name="status"
	 */
	public void setStatus(Integer state)
	{
		this.status = state;
	}

	/**
	 * @return Returns the date.
	 * @uml.property name="date"
	 */
	public Date getDate()
	{
		return date;
	}

	/**
	 * @param date
	 *            The date to set.
	 * @uml.property name="date"
	 */
	public void setDate(Date date)
	{
		this.date = date;
	}

	/**
	 * @return Returns the expenseLineItems.
	 */
	public Set getExpenseLineItems()
	{
		return expenseLineItems;
	}

	/**
	 * @param expenseLineItems
	 *            The expenseLineItems to set.
	 */
	public void setExpenseLineItems(Set expenseLineItems)
	{
		this.expenseLineItems = expenseLineItems;
	}

	/**
	 * @return Returns the customer.
	 */
	public Customer getCustomer()
	{
		return customer;
	}

	/**
	 * @param customer
	 *            The customer to set.
	 */
	public void setCustomer(Customer customer)
	{
		this.customer = customer;
	}

	/**
	 * @return Returns the taskDuration.
	 */
	public Long getTaskDuration()
	{
		return taskDuration;
	}

	/**
	 * @param taskDuration
	 *            The taskDuration to set.
	 */
	public void setTaskDuration(Long taskDuration)
	{
		this.taskDuration = taskDuration;
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
