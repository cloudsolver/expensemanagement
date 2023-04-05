package com.esood.customer.ui;

import java.lang.reflect.InvocationTargetException;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionMapping;

import com.esood.customer.CustomerDTO;
import com.esood.ui.WebForm;

/**
 * The register form
 * 
 * @author Rohit Sood
 */
/**
 * 
 * @author Rohit Sood
 */
public class RegisterForm extends WebForm
{

	static final long serialVersionUID = 234809813240L;

	private String username;

	private String password;

	private String password2;

	private String incomeCode;

	private String ageGroupCode;

	private String genderCode;

	private String internetYearsCode;
	
	private String internetHoursPerWeekCode;

	private String levelOfEducationCode;

	
	public void reset(ActionMapping mapping,HttpServletRequest req)
	{
		populate(req);
	}
	public void populate(HttpServletRequest req)
	{
		CustomerHelper.putAll(req);
	}

	/**
	 * 
	 * @param dto
	 */
	public void populate(CustomerDTO dto)
	{
		log.info("Enter populate(CustomerDTO dto)");
		try
		{
			BeanUtils.copyProperties(this, dto);
		} catch (IllegalAccessException e)
		{
			log.warn("Could not populate Register Form " + e.getMessage());
		} catch (InvocationTargetException e)
		{
			log.warn("Could not populate Register Form " + e.getMessage());
		}
		log.info("Exit populate(CustomerDTO dto)");
	}

	/**
	 * 
	 * @return
	 */
	public CustomerDTO getDTO()
	{
		CustomerDTO dto = new CustomerDTO();
		try
		{
			BeanUtils.copyProperties(dto, this);
		} catch (IllegalAccessException e)
		{
			log.warn("Could not populate Register Form " + e.getMessage());
		} catch (InvocationTargetException e)
		{
			log.warn("Could not populate Register Form " + e.getMessage());
		}
		return dto;
	}

	/*public ActionErrors validate(ActionMapping mapping, HttpServletRequest req)
	{
		log.info("Enter validate");
		
		ActionErrors errors=super.validate(mapping,req);
		
		log.info("Exit validate");
		return errors;
	}*/
/*	public ActionErrors validate(ActionMapping mapping, HttpServletRequest req)
	{
		ActionErrors errors = new ActionErrors();

		if (username == null)
		{
			errors.add("username", new ActionError("username.missing"));
		}
		if (password == null)
		{
			errors.add("password", new ActionError("password.missing"));
		}
		if (password != null && !password.equals(password2))
		{
			errors.add("password", new ActionError("password.unequal"));
		}
		return errors;
	}*/

	/**
	 * @return Returns the password.
	 */
	public String getPassword()
	{
		return password;
	}

	/**
	 * @param password
	 *            The password to set.
	 */
	public void setPassword(String password)
	{
		this.password = password;
	}

	/**
	 * @return Returns the password2.
	 */
	public String getPassword2()
	{
		return password2;
	}

	/**
	 * @param password2
	 *            The password2 to set.
	 */
	public void setPassword2(String password2)
	{
		this.password2 = password2;
	}

	/**
	 * @return Returns the username.
	 */
	public String getUsername()
	{
		return username;
	}

	/**
	 * @param username
	 *            The username to set.
	 */
	public void setUsername(String username)
	{
		this.username = username;
	}

	/**
	 * @return Returns the ageGroupCode.
	 */
	public String getAgeGroupCode()
	{
		return ageGroupCode;
	}

	/**
	 * @param ageGroupCode
	 *            The ageGroupCode to set.
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
	 * @param genderCode
	 *            The genderCode to set.
	 */
	public void setGenderCode(String genderCode)
	{
		this.genderCode = genderCode;
	}

	/**
	 * @return Returns the incomeCode.
	 */
	public String getIncomeCode()
	{
		return incomeCode;
	}

	/**
	 * @param incomeCode
	 *            The incomeCode to set.
	 */
	public void setIncomeCode(String incomeCode)
	{
		this.incomeCode = incomeCode;
	}

	/**
	 * @return Returns the internetYearsCode.
	 */
	public String getInternetYearsCode()
	{
		return internetYearsCode;
	}

	/**
	 * @param internetYearsCode
	 *            The internetYearsCode to set.
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
	 * @param levelOfEducationCode
	 *            The levelOfEducationCode to set.
	 */
	public void setLevelOfEducationCode(String levelOfEducationCode)
	{
		this.levelOfEducationCode = levelOfEducationCode;
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