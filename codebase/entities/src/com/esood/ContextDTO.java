/*
 * Created on Aug 21, 2005
 */
package com.esood;

public class ContextDTO extends AbstractDTO
{
	public static final long serialVersionUID=472974L;
	private String userName;
	private Integer customerId;
	private Integer expenseId;
	private Integer expenseLineItemId;
	private Integer applicationVersion;
	private String applicationType;
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
	 * @return Returns the username.
	 */
	public String getUserName()
	{
		return userName;
	}
	/**
	 * @param username The username to set.
	 */
	public void setUserName(String username)
	{
		this.userName = username;
	}
	
	public String toString(){
		StringBuffer buffer=new StringBuffer();
		
		buffer.append("\napplicationType:"+applicationType);
		buffer.append("\napplicationVersion"+applicationVersion);
		buffer.append("\ncustomerId:"+customerId);
		buffer.append("\nexpenseId"+expenseId);
		buffer.append("\nuserName"+userName);
		
		return buffer.toString();
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
