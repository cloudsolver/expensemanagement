
package com.esood.customer;

import java.util.HashSet;
import java.util.Set;

import com.esood.AbstractEntity;
import com.esood.expense.Expense;


public class Customer extends AbstractEntity{
	
	private String genderCode;
	private String internetYearsCode;
	private String levelOfEducationCode;
	private String ageGroupCode;
	private String internetHoursPerWeekCode;
	/**
	 * All the expenses associated with the customer
	 */
	private Set expenses=new HashSet();
	/**
	 * @return Returns the ageGroupCode.
	 */
	public String getAgeGroupCode()
	{
		return ageGroupCode;
	}
	/**
	 * @param ageGroupCode The ageGroupCode to set.
	 */
	public void setAgeGroupCode(String ageGroupCode)
	{
		this.ageGroupCode = ageGroupCode;
	}
	/**
	 * @return Returns the genderCode.
	 */
	public String getGenderCode()
	{
		return genderCode;
	}
	/**
	 * @param genderCode The genderCode to set.
	 */
	public void setGenderCode(String genderCode)
	{
		this.genderCode = genderCode;
	}
	/**
	 * @return Returns the internetYearsCode.
	 */
	public String getInternetYearsCode()
	{
		return internetYearsCode;
	}
	/**
	 * @param internetYearsCode The internetYearsCode to set.
	 */
	public void setInternetYearsCode(String internetYearsCode)
	{
		this.internetYearsCode = internetYearsCode;
	}
	/**
	 * @return Returns the levelOfEducationCode.
	 */
	public String getLevelOfEducationCode()
	{
		return levelOfEducationCode;
	}
	/**
	 * @param levelOfEducationCode The levelOfEducationCode to set.
	 */
	public void setLevelOfEducationCode(String levelOfEducationCode)
	{
		this.levelOfEducationCode = levelOfEducationCode;
	}
	/**
	 * @return Returns the expenses.
	 */
	public Set getExpenses()
	{
		return expenses;
	}
	/**
	 * @param expenses The expenses to set.
	 */
	public void setExpenses(Set expenses)
	{
		this.expenses = expenses;
	}
	
	public void addExpense(Expense expense){
		if(expense==null)
		{
			throw new IllegalArgumentException("Expense cannot be added to the customer because expense is "+expense);
		}
		expenses.add(expense);
		
	}
	
	public String toString()
	{
		StringBuffer buffer=new StringBuffer();
		buffer.append("\nAgeGroupCode"+this.ageGroupCode);
		buffer.append("\nInternetHoursPerWeekCode"+this.internetHoursPerWeekCode);
		buffer.append("\nLevelOfEducationCode"+this.levelOfEducationCode);
		buffer.append("\nGenderCodeGender Code"+genderCode);
		buffer.append("\nInternet Years Code"+internetYearsCode);
		return buffer.toString();
	}
	/**
	 * @return Returns the internetHoursPerWeekCode.
	 */
	public String getInternetHoursPerWeekCode()
	{
		return internetHoursPerWeekCode;
	}
	/**
	 * @param internetHoursPerWeekCode The internetHoursPerWeekCode to set.
	 */
	public void setInternetHoursPerWeekCode(String internetHoursPerWeekCode)
	{
		this.internetHoursPerWeekCode = internetHoursPerWeekCode;
	}

}
