/*
 * Created on Oct 5, 2005
 */
package com.esood.expense;

import java.util.Date;

/**
 * @author  Rohit
 */
public class EducationExpenseLineItem extends StandardExpenseLineItem
{
//	Details for Education only
	protected Date startDate;
	protected Date endDate;
	protected String schoolName;//or school name
	protected String courseName;
	protected String grade;//supported by a drop-down
	protected String educationLevel;//supported by a drop-down
	public static final String TYPE="EDU";
	
	public String toString()
	{
		StringBuffer buffer=new StringBuffer();
		buffer.append(super.toString());
		buffer.append("\nstartDate:"+startDate);
		buffer.append("\nendDate:"+endDate);
		buffer.append("\nschoolName:"+schoolName);
		buffer.append("\ncourseName:"+courseName);
		buffer.append("\ngrade:"+grade);
		buffer.append("\nlevel:"+educationLevel);
		return buffer.toString();
	}
	
	public String getExpenseType()
	{
		return TYPE;
	}
	
	/**
	 * @return  Returns the courseName.
	 * @uml.property  name="courseName"
	 */
	public String getCourseName()
	{
		return courseName;
	}
	/**
	 * @param courseName  The courseName to set.
	 * @uml.property  name="courseName"
	 */
	public void setCourseName(String courseName)
	{
		this.courseName = courseName;
	}
	/**
	 * @return  Returns the grade.
	 * @uml.property  name="grade"
	 */
	public String getGrade()
	{
		return grade;
	}
	/**
	 * @param grade  The grade to set.
	 * @uml.property  name="grade"
	 */
	public void setGrade(String grade)
	{
		this.grade = grade;
	}
	/**
	 * @return  Returns the schoolName.
	 * @uml.property  name="schoolName"
	 */
	public String getSchoolName()
	{
		return schoolName;
	}
	/**
	 * @param schoolName  The schoolName to set.
	 * @uml.property  name="schoolName"
	 */
	public void setSchoolName(String schoolName)
	{
		this.schoolName = schoolName;
	}
	/**
	 * @return Returns the endDate.
	 */
	public Date getEndDate()
	{
		return endDate;
	}
	/**
	 * @param endDate The endDate to set.
	 */
	public void setEndDate(Date endDate)
	{
		this.endDate = endDate;
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
	public void setEducationLevel(String level)
	{
		this.educationLevel = level;
	}
	/**
	 * @return Returns the startDate.
	 */
	public Date getStartDate()
	{
		return startDate;
	}
	/**
	 * @param startDate The startDate to set.
	 */
	public void setStartDate(Date startDate)
	{
		this.startDate = startDate;
	}
}
