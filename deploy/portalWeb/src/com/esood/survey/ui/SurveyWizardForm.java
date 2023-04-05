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

/**
 * SurveyWizardForm: This is the wizard.
 * 
 * @author Rohit Sood
 */
public class SurveyWizardForm extends WebForm
{
	static final long serialVersionUID = 234809813240L;

	transient Log log = LogFactory.getLog(SurveyWizardForm.class);

	// Answers 0-7
	String answer;

	private int qid;
	private String questionId;
	/**
	 * reset the form
	 */
	public void reset(ActionMapping mappings, HttpServletRequest request)
	{
		populate(request);
	}
	
	public String getQuestion()
	{
		return new SurveyHelper().getQuestion(qid);
	}
	public void setAnswer(String answer)
	{
		log.debug("Enter setAnswer:"+answer);
		this.answer=answer;
	}

	public String getAnswer()
	{
		return answer;
	}
	

	public void populate(HttpServletRequest request)
	{

		new SurveyHelper().populate(request);
	}

	/**
	 * Validates the form on submits.
	 * 
	 * @param mapping
	 * @param request
	 * @return ActionErrors
	 */
	public ActionErrors validate(ActionMapping mapping, HttpServletRequest request)
	{
		ActionErrors errors = super.validate(mapping, request);
		SurveyHelper helper = new SurveyHelper();
		if (errors == null)
		{
			return errors;
		}

		if (answer== null)
		{
			errors.add("missing", new ActionError("survey.question.error", helper.getQuestion(qid)));
		}
		return errors;
	}
	

	/**
	 * @return Returns the qid.
	 */
	public int getQid()
	{
		return qid;
	}
	
	/**
	 * @param qid
	 *            The qid to set.
	 */
	public void setQid(int qid)
	{
		this.qid = qid;
	}
	
	public int getNext()
	{
		return 1+qid;
	}
	public int getBack()
	{
		return qid-1;
	}
	
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString()
	{
		StringBuffer buffer=new StringBuffer();
		buffer.append("\nanswer"+answer);
		//for(int a=0;a<answers.length;a++)
		{
			//buffer.append("\n"+a+" answer="+answers[a]);
		}
		return buffer.toString();
	}

	/**
	 * @return Returns the questionId.
	 */
	public String getQuestionId()
	{
			questionId=""+qid;
			return questionId;
	}

	/**
	 * @param questionId The questionId to set.
	 */
	public void setQuestionId(String questionId)
	{
		log.debug("Setting questionId");
		this.questionId = questionId;
		if(questionId!=null)
		{
			setQid(Integer.parseInt(questionId));
		}
	}

}
