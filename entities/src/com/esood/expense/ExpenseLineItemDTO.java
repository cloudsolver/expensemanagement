/*
 * Created on Sep 25, 2005
 */
package com.esood.expense;

import com.esood.AbstractDTO;
import java.util.Date;

/**
 * @author  Rohit
 */
public class ExpenseLineItemDTO extends AbstractDTO
{
	static final long serialVersionUID=2384902342L;
	private Integer id;
	private String lineItemType;
	
	private Date transactionDate;
	private Double transactionAmount;
	private String transactionType;
	private String transactionJustification;
	
	//Details for Education Type
	private Date startDate;
	private Date endDate;
	private String schoolName;//or school name
	private String courseName;
	private String grade;//supported by a drop-down
	private String educationLevel;//supported by a drop-down
	private Boolean qualified;//supported by a drop-down
	public static final String TYPE_STD="s";
	public static final String TYPE_EDU="e";
	
	
	public String toString(){
		StringBuffer buffer=new StringBuffer();
		buffer.append(super.toString());
		buffer.append("\ntransactionDate:"+this.transactionDate);
		buffer.append("\ntransactionAmount:"+this.transactionAmount);
		buffer.append("\ntransactionType:"+this.transactionType);
		buffer.append("\ntransactionJustification:"+this.transactionJustification);
		buffer.append("startDate:"+this.startDate);
		buffer.append("\nendDate"+this.endDate);
		buffer.append("\nschoolName"+this.schoolName);
		buffer.append("\ngrade"+this.grade);
		buffer.append("\neducationLevel"+this.educationLevel);
		buffer.append("\nqualified"+this.qualified);
		return buffer.toString();
	}
	
	public String getLineItemType()
	{
		return this.lineItemType;
	}

	public void setLineItemType(String lineItemType)
	{
		this.lineItemType=lineItemType;
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
	 * @return  Returns the educationLevel.
	 * @uml.property  name="educationLevel"
	 */
	public String getEducationLevel()
	{
		return educationLevel;
	}

	/**
	 * @param educationLevel  The educationLevel to set.
	 * @uml.property  name="educationLevel"
	 */
	public void setEducationLevel(String educationLevel)
	{
		this.educationLevel = educationLevel;
	}

	/**
	 * @return  Returns the endDate.
	 * @uml.property  name="endDate"
	 */
	public Date getEndDate()
	{
		return endDate;
	}

	/**
	 * @param endDate  The endDate to set.
	 * @uml.property  name="endDate"
	 */
	public void setEndDate(Date endDate)
	{
		this.endDate = endDate;
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
	 * @return  Returns the id.
	 * @uml.property  name="id"
	 */
	public Integer getId()
	{
		return id;
	}

	/**
	 * @param id  The id to set.
	 * @uml.property  name="id"
	 */
	public void setId(Integer id)
	{
		this.id = id;
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
	 * @return  Returns the startDate.
	 * @uml.property  name="startDate"
	 */
	public Date getStartDate()
	{
		return startDate;
	}

	/**
	 * @param startDate  The startDate to set.
	 * @uml.property  name="startDate"
	 */
	public void setStartDate(Date startDate)
	{
		this.startDate = startDate;
	}

	/**
	 * @return  Returns the transactionAmount.
	 * @uml.property  name="transactionAmount"
	 */
	public Double getTransactionAmount()
	{
		return transactionAmount;
	}

	/**
	 * @param transactionAmount  The transactionAmount to set.
	 * @uml.property  name="transactionAmount"
	 */
	public void setTransactionAmount(Double transactionAmount)
	{
		this.transactionAmount = transactionAmount;
	}

	/**
	 * @return  Returns the transactionDate.
	 * @uml.property  name="transactionDate"
	 */
	public Date getTransactionDate()
	{
		return transactionDate;
	}

	/**
	 * @param transactionDate  The transactionDate to set.
	 * @uml.property  name="transactionDate"
	 */
	public void setTransactionDate(Date transactionDate)
	{
		this.transactionDate = transactionDate;
	}

	/**
	 * @return  Returns the transactionJustification.
	 * @uml.property  name="transactionJustification"
	 */
	public String getTransactionJustification()
	{
		return transactionJustification;
	}

	/**
	 * @param transactionJustification  The transactionJustification to set.
	 * @uml.property  name="transactionJustification"
	 */
	public void setTransactionJustification(String transactionJustification)
	{
		this.transactionJustification = transactionJustification;
	}

	/**
	 * @return  Returns the transactionType.
	 * @uml.property  name="transactionType"
	 */
	public String getTransactionType()
	{
		return transactionType;
	}

	/**
	 * @param transactionType  The transactionType to set.
	 * @uml.property  name="transactionType"
	 */
	public void setTransactionType(String transactionType)
	{
		this.transactionType = transactionType;
	}

	/**
	 * @return Returns the qualified.
	 */
	public Boolean getQualified()
	{
		return qualified;
	}

	/**
	 * @param qualified The qualified to set.
	 */
	public void setQualified(Boolean qualified)
	{
		this.qualified = qualified;
	}
}
