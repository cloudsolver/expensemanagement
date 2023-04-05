/*
 * Created on Aug 8, 2005
 */
package com.esood.survey.ui;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.util.LabelValueBean;


/**
 * This survey form captures information regarding the ServQual survey
 * This form has hardcoded data to make programming easier. This could have come from a reference database.
 * updated to be Java 5 friendly.
 * @version 2009-01-10
 * @author Rohit
 */
public class SurveyHelper 
{
	Log log=LogFactory.getLog(this.getClass());
	static List <LabelValueBean> efficiencyAnswers;
	static List <LabelValueBean> efficiencyQuestions;
	public final static String NAME="surveyLabelValueBeans";
	/**
	 * Returns the efficiency answers
	 * @return List of efficiency answers
	 */
	public List <LabelValueBean> getEfficientAnswers()
	{
		return efficiencyAnswers;
	}
	public String getQuestion(int index)
	{
		log.info("Enter getQuestion with index="+index);
		LabelValueBean qb=(LabelValueBean) efficiencyQuestions.get(index);
		String question=qb.getLabel();
		log.info("Exit getQuestion with question:"+question);
		return question;
	}
	public void setQuestion(int index,String question)
	{
		throw new IllegalArgumentException("Cannot set a question");
	}
	/**
	 * 
	 * @param req
	 */
	public void populate(HttpServletRequest req)
	{
		log.debug("Enter populate");
		initializeQuestions();
		initializeAnswers();
		req.setAttribute(NAME,this);
		req.setAttribute("efficiencyAnswers",efficiencyAnswers);
		log.debug("Exit populate");
	}
/**
 * Initializes the possible answers for the survey
 *
 */
	protected void initializeAnswers()
	{
		
		if(efficiencyAnswers==null)
		{
			efficiencyAnswers=new ArrayList<LabelValueBean>();
			efficiencyAnswers.add(new LabelValueBean("Strongly Agree","SA"));
			efficiencyAnswers.add(new LabelValueBean("Agree","A"));
			efficiencyAnswers.add(new LabelValueBean("Undecided","U"));
			efficiencyAnswers.add(new LabelValueBean("Disagree","D"));
			efficiencyAnswers.add(new LabelValueBean("Strongly Disagree","SD"));
		}
	}
	
	/**
	 * Initialize the questions.
	 *
	 */
	protected void initializeQuestions()
	{
		if(efficiencyQuestions==null)
		{
			efficiencyQuestions=new ArrayList <LabelValueBean>();
			efficiencyQuestions.add(new LabelValueBean("This site makes it easy to find what I need.","EFF01"));
			efficiencyQuestions.add(new LabelValueBean("It makes it easy to get anywhere on the site.","EFF02"));
			efficiencyQuestions.add(new LabelValueBean("It enables me to complete a transaction quickly.","EFF03"));
			efficiencyQuestions.add(new LabelValueBean("Information at this site is well organized.","EFF04"));
			efficiencyQuestions.add(new LabelValueBean("It loads its pages fast.","EFF05"));
			efficiencyQuestions.add(new LabelValueBean("This site is simple to use.","EFF06"));
			efficiencyQuestions.add(new LabelValueBean("This site enables me to get on it quickly.","EFF07"));
			efficiencyQuestions.add(new LabelValueBean("This site is well organized.","EFF08"));
			
		}
		
	}
}
