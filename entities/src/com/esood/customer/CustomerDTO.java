/*
 * Created on Aug 3, 2005
 */
package com.esood.customer;

import com.esood.AbstractDTO;

/**
 * Data holder for customer information.
 * 
 * @author Rohit
 */
public class CustomerDTO extends AbstractDTO
{
	static final long serialVersionUID=234809813240L;
	public Integer userId; 
	public String ageGroupCode;
	
	public String genderCode;
	public String internetYearsCode;
	public String internetHoursPerWeekCode;
	public String levelOfEducationCode;
	public Integer id;
	public String toString()
	{
		return "userId="+userId;
	}

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
	public void setInternetYearsCode(String internetExperienceCode)
	{
		this.internetYearsCode = internetExperienceCode;
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
	 * @return Returns the id.
	 */
	public Integer getId()
	{
		return id;
	}

	/**
	 * @param id The id to set.
	 */
	public void setId(Integer id)
	{
		this.id = id;
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
