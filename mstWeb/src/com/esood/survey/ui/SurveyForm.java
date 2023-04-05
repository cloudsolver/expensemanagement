/*
 * Created on Aug 20, 2005
 */
package com.esood.survey.ui;


import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;


import com.esood.ui.WebForm;

public class SurveyForm extends WebForm
{
	static final long serialVersionUID=234809813240L;
	transient Log log=LogFactory.getLog(SurveyForm.class);
	//Answers
	private String eff01;
	private String eff02;
	private String eff03;
	private String eff04;
	private String eff05;
	private String eff06;
	private String eff07;
	private String eff08;
	private int qid;
	
	/**
	 * reset the form
	 */
	public void reset(ActionMapping mappings, HttpServletRequest request)
	{
		populate(request);
	}
	
	public void populate(HttpServletRequest request)
	{
		
		new SurveyHelper().populate(request);
	}
	
	/**
	 * Validates the form on submits.
	 * @param mapping
	 * @param request
	 * @return ActionErrors
	 */
	public ActionErrors validate(ActionMapping mapping, HttpServletRequest request)
	{
		ActionErrors errors=super.validate(mapping,request);
		SurveyHelper helper=new SurveyHelper();
		if(errors==null)
		{
			return errors;
		}
		if(eff01==null)
		{
			errors.add("missing",new ActionError("survey.question.error",helper.getQuestion(0)));	
		}
		if(eff02==null)
		{
			errors.add("missing",new ActionError("survey.question.error",helper.getQuestion(1)));	
		}
		if(eff03==null)
		{
			errors.add("missing",new ActionError("survey.question.error",helper.getQuestion(2)));	
		}
		if(eff04==null)
		{
			errors.add("missing",new ActionError("survey.question.error",helper.getQuestion(3)));	
		}
		if(eff05==null)
		{
			errors.add("missing",new ActionError("survey.question.error",helper.getQuestion(4)));	
		}
		if(eff06==null)
		{
			errors.add("missing",new ActionError("survey.question.error",helper.getQuestion(5)));	
		}
		if(eff07==null)
		{
			errors.add("missing",new ActionError("survey.question.error",helper.getQuestion(6)));	
		}
		if(eff08==null)
		{
			errors.add("missing",new ActionError("survey.question.error",helper.getQuestion(7)));	
		}
		return errors;
	}
	
	/**
	 * @return Returns the eff01.
	 */
	public String getEff01()
	{
		return eff01;
	}
	/**
	 * @param eff01 The eff01 to set.
	 */
	public void setEff01(String eff01)
	{
		this.eff01 = eff01;
	}
	/**
	 * @return Returns the eff02.
	 */
	public String getEff02()
	{
		return eff02;
	}
	/**
	 * @param eff02 The eff02 to set.
	 */
	public void setEff02(String eff02)
	{
		this.eff02 = eff02;
	}
	/**
	 * @return Returns the eff03.
	 */
	public String getEff03()
	{
		return eff03;
	}
	/**
	 * @param eff03 The eff03 to set.
	 */
	public void setEff03(String eff03)
	{
		this.eff03 = eff03;
	}
	/**
	 * @return Returns the eff04.
	 */
	public String getEff04()
	{
		return eff04;
	}
	/**
	 * @param eff04 The eff04 to set.
	 */
	public void setEff04(String eff04)
	{
		this.eff04 = eff04;
	}
	/**
	 * @return Returns the eff05.
	 */
	public String getEff05()
	{
		return eff05;
	}
	/**
	 * @param eff05 The eff05 to set.
	 */
	public void setEff05(String eff05)
	{
		this.eff05 = eff05;
	}
	/**
	 * @return Returns the eff06.
	 */
	public String getEff06()
	{
		return eff06;
	}
	/**
	 * @param eff06 The eff06 to set.
	 */
	public void setEff06(String eff06)
	{
		this.eff06 = eff06;
	}
	/**
	 * @return Returns the eff07.
	 */
	public String getEff07()
	{
		return eff07;
	}
	/**
	 * @param eff07 The eff07 to set.
	 */
	public void setEff07(String eff07)
	{
		this.eff07 = eff07;
	}
	/**
	 * @return Returns the eff08.
	 */
	public String getEff08()
	{
		return eff08;
	}
	/**
	 * @param eff08 The eff08 to set.
	 */
	public void setEff08(String eff08)
	{
		this.eff08 = eff08;
	}

	/**
	 * @return Returns the qid.
	 */
	public int getQid()
	{
		return qid;
	}

	/**
	 * @param qid The qid to set.
	 */
	public void setQid(int qid)
	{
		this.qid = qid;
	}
	

}
