/*
 * Created on Jul 31, 2005
 */
package com.esood.customer.ui;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.util.LabelValueBean;

/**
 * This is the set of view beans used by the registration view process. The purpose is to return a set of
 * LabelValueBeans.
 * 
 * @author Rohit Sood
 */
public class CustomerHelper
{
	Log log = LogFactory.getLog(CustomerHelper.class);

	static void putEducationLevelList(HttpServletRequest req)
	{

		List educationLevels = new ArrayList();
		educationLevels.add(new LabelValueBean("Undisclosed", "EDU_XX"));
		educationLevels.add(new LabelValueBean("High school/Diploma/Associate's Degree", "EDU_01"));
		educationLevels.add(new LabelValueBean("Bachelor's degree", "EDU_02"));
		educationLevels.add(new LabelValueBean("Master's degree", "EDU_03"));
		educationLevels.add(new LabelValueBean("Doctorate", "EDU_04"));
		educationLevels.add(new LabelValueBean("Professional Training", "EDU_05"));
		req.setAttribute("educationLevels", educationLevels);
	}

	public static void putAll(HttpServletRequest req)
	{
		putAgeGroupList(req);
		putGenderList(req);
		putIncomeList(req);
		putInternetExperienceYears(req);
		putInternetHours(req);
		putEducationLevelList(req);
	}

	static List putInternetHours(HttpServletRequest req)
	{
		List internetHours = new ArrayList();
		internetHours.add(new LabelValueBean("Undisclosed", "USAGE_XX"));
		internetHours.add(new LabelValueBean(" 0 to 10 hours per week", "USAGE_01"));
		internetHours.add(new LabelValueBean("11 to 20 hours per week", "USAGE_02"));
		internetHours.add(new LabelValueBean("21 to 30 hours per week", "USAGE_03"));
		internetHours.add(new LabelValueBean("31 to 40 hours per week", "USAGE_04"));
		internetHours.add(new LabelValueBean("41 to 45 hours per week", "USAGE_05"));
		internetHours.add(new LabelValueBean("More than 45 hours per week", "USAGE_06"));
		req.setAttribute("internetHours", internetHours);
		return internetHours;
	}

	/**
	 * Returns the list of possible house hold incomes
	 * 
	 * @return The list of incomes <tt>ValueBean</tt>
	 */
	static List putIncomeList(HttpServletRequest req)
	{

		List incomes = new ArrayList();
		incomes.add(new LabelValueBean("Undisclosed", "INCOME_XX"));
		incomes.add(new LabelValueBean("$20,000 - $49,999", "INCOME_01"));
		incomes.add(new LabelValueBean("$50,000 - $74,999", "INCOME_02"));
		incomes.add(new LabelValueBean("$75,000 - $99,999", "INCOME_03"));
		incomes.add(new LabelValueBean("$100,000 - $124,999", "INCOME_04"));
		incomes.add(new LabelValueBean("$125,000 - $149,999", "INCOME_05"));
		incomes.add(new LabelValueBean("Above $150,000", "INCOME_06"));

		req.setAttribute("incomes", incomes);
		return incomes;
	}

	static List putAgeGroupList(HttpServletRequest req)
	{
		List ageGroups = new ArrayList();
		ageGroups.add(new LabelValueBean("Undisclosed", "AGE_XX"));
		ageGroups.add(new LabelValueBean("18-24", "AGE_00"));
		ageGroups.add(new LabelValueBean("25-34", "AGE_01"));
		ageGroups.add(new LabelValueBean("35-44", "AGE_02"));
		ageGroups.add(new LabelValueBean("45-54", "AGE_03"));
		ageGroups.add(new LabelValueBean("55-64", "AGE_04"));
		ageGroups.add(new LabelValueBean("65 and above", "AGE_05"));

		req.setAttribute("ageGroups", ageGroups);
		return ageGroups;

	}

	static List putGenderList(HttpServletRequest req)
	{

		List genders = new ArrayList();
		genders.add(new LabelValueBean("Undisclosed", "X"));
		genders.add(new LabelValueBean("Male", "M"));
		genders.add(new LabelValueBean("Female", "F"));

		req.setAttribute("genders", genders);
		return genders;
	}

	static List putInternetExperienceYears(HttpServletRequest req)
	{

		List internetExperienceYears = new ArrayList();
		internetExperienceYears.add(new LabelValueBean("Undisclosed", "INTERNET_XX"));
		internetExperienceYears.add(new LabelValueBean("0 yrs - 3 yrs", "INTERNET_01"));
		internetExperienceYears.add(new LabelValueBean("3 yrs - 5 yrs", "INTERNET_02"));
		internetExperienceYears.add(new LabelValueBean("5 yrs - 7 yrs", "INTERNET_03"));
		internetExperienceYears.add(new LabelValueBean("7 yrs - 9 yrs", "INTERNET_04"));
		internetExperienceYears.add(new LabelValueBean("9 yrs and more", "INTERNET_05"));

		req.setAttribute("internetExperienceYears", internetExperienceYears);
		return internetExperienceYears;
	}
}
