/*
 * Created on Jul 17, 2005
 */
package com.esood.ui;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.esood.ContextDTO;


/**
 * Context
 * @author Rohit Sood
 */
public class Context implements Serializable 
{
	public static final long serialVersionUID=123456789L; 
	public static final String NAME="ctx";
	public static final int NO_DELAY=0;
	public static final int MEDIUM_DELAY=1;
	public static final int LONG_DELAY=2;
	private int delay;
	private long startTime;
	private Log log=LogFactory.getLog(Context.class);
	
	private Integer applicationVersion;
	private String applicationType;
	private String userName;
	private Integer userId;
	private Integer customerId;
	private Integer expenseId;	
	private Integer expenseLineItemId;
	private boolean registered;
	
	/**
	 * Context
	 * @return
	 */
	public ContextDTO getDTO(){
		log.info("Enter public ContextDTO getDTO()");
		ContextDTO dto=new ContextDTO();
		
		try
		{
			BeanUtils.copyProperties(dto,this);
		} catch (IllegalAccessException e)
		{
			log.warn("Could not copy to the dto "+e.getMessage());
		} catch (InvocationTargetException e)
		{
			log.warn("Could not copy to the dto "+e.getMessage());
		}
		
		/*dto.setUserName(userName);
		dto.setCustomerId(customerId);
		dto.setExpenseId(expenseId);
		dto.setExpenseLineItemId(expenseLineItemId);*/
		log.info("Exit public ContextDTO getDTO()");
		return dto;
	}
	

	/**
	 * toString
	 */
	public String toString()
	{
		StringBuffer buffer=new StringBuffer();
		buffer.append("\nuserName="+userName);
		buffer.append("\nuserId="+userId);
		buffer.append("\ncustomerId="+customerId);
		buffer.append("\nexpenseId="+expenseId);
		buffer.append("\nexpenseLineItemId="+expenseLineItemId);
		buffer.append("\ndelay="+delay);
		buffer.append("\nstartTime="+startTime);
		buffer.append("\napplicationType="+applicationType);
		buffer.append("\napplicationVersion="+applicationVersion);
		return buffer.toString();
	}
	/**
	 * Checks to see if this is 
	 * @return
	 */
	public boolean isAuthenticated()
	{
		if(userName==null)
		{
			return false;
		}
		return true;
	}

	/**
	 * @return Returns the userName.
	 */
	public String getUserName() {
		return userName;
	}
	/**
	 * @param userName The userName to set.
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

	/**
	 * @return Returns the userId.
	 */
	public Integer getUserId()
	{
		return userId;
	}

	/**
	 * @param userId The userId to set.
	 */
	public void setUserId(Integer userId)
	{
		this.userId = userId;
	}
	/**
	 * @return Returns the registered.
	 */
	public boolean isRegistered()
	{
		return registered;
	}
	/**
	 * @param registered The registered to set.
	 */
	public void setRegistered(boolean registered)
	{
		this.registered = registered;
	}
	/**
	 * @return Returns the customerId.
	 */
	public Integer getCustomerId()
	{
		return customerId;
	}
	/**
	 * @param customerId The customerId to set.
	 */
	public void setCustomerId(Integer customerId)
	{
		this.customerId = customerId;
	}
	/**
	 * @return Returns the expenseId.
	 */
	public Integer getExpenseId()
	{
		return expenseId;
	}
	/**
	 * @param expenseId The expenseId to set.
	 */
	public void setExpenseId(Integer expenseId)
	{
		this.expenseId = expenseId;
	}
	/**
	 * @return Returns the expenseLineItemId.
	 */
	public Integer getExpenseLineItemId()
	{
		return expenseLineItemId;
	}
	/**
	 * @param expenseLineItemId The expenseLineItemId to set.
	 */
	public void setExpenseLineItemId(Integer expenseLineItemId)
	{
		this.expenseLineItemId = expenseLineItemId;
	}

	/**
	 * @return Returns the delay.
	 */
	public int getDelay()
	{
		return delay;
	}

	/**
	 * @param delay The delay to set.
	 */
	public void setDelay(int delay)
	{
		this.delay = delay;
	}



	/**
	 * @return Returns the startTime.
	 */
	long getStartTime()
	{
		return startTime;
	}



	/**
	 * @param startTime The startTime to set.
	 */
	void setStartTime(long startTime)
	{
		this.startTime = startTime;
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
	public void setApplicationType(String type)
	{
		this.applicationType = type;
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
	public void setApplicationVersion(Integer version)
	{
		this.applicationVersion = version;
	}
}
