/*
 * Created on Aug 20, 2005
 */
package com.esood.survey.ui;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.esood.ContextDTO;
import com.esood.expense.ExpenseException;
import com.esood.expense.ExpenseFacade;
import com.esood.survey.SurveyFacade;
import com.esood.ui.AbstractAction;
import com.esood.ui.Context;
import com.esood.ui.WebForm;

/**
 * This action should submit the page for display.
 * 
 * @author Rohit Sood
 */
public class SurveyWizardAction extends AbstractAction
{


	protected ActionForward doAction(ActionMapping mapping, WebForm form, HttpServletRequest req, HttpServletResponse resp)
	{
		log.info("Enter protected ActionForward doAction(ActionMapping mapping, WebForm form, HttpServletRequest req, HttpServletResponse resp)");
		SurveyWizardForm survey = (SurveyWizardForm) form;
		ActionForward returnValue = processCommand(mapping, survey, req);
		log.info("Exit protected ActionForward doAction(ActionMapping mapping, WebForm form, HttpServletRequest req, HttpServletResponse resp)");
		return returnValue;
	}

	/**
	 * Process the command.
	 * @param mapping
	 * @param survey
	 * @param req
	 * @return
	 */
	protected ActionForward processCommand(ActionMapping mapping, SurveyWizardForm survey, HttpServletRequest req)
	{
		log.info("Enter protected ActionForward processCommand(ActionMapping mapping, SurveyWizardForm survey, HttpServletRequest req)");
		ActionForward returnValue = null;
		String cmd = survey.getCmd();
		log.debug("cmd=" + cmd);
		if (cmd != null && !"".equals(cmd))
		{
			if (cmd.equals(CMD_CREATE))
			{
				returnValue = doCreate(mapping, survey, req);
			} else if (cmd.equals(CMD_READ))
			{
				returnValue = doRead(mapping, survey, req);
			}
		} else
		{
			log.warn("Could not understand cmd:"+cmd);
			returnValue = doRead(mapping, survey, req);
		}
		log.info("Exit protected ActionForward processCommand(ActionMapping mapping, SurveyWizardForm survey, HttpServletRequest req)");
		return returnValue;
	}

	/**
	 * doRead - Reads the qid form the form and displays the appropriate question.
	 * Read from the database the value previously answered and populates the form.
	 * If not value exists, it is blank.
	 * @param mapping
	 * @param form
	 * @param req
	 * @return
	 */
	protected ActionForward doRead(ActionMapping mapping, SurveyWizardForm form, HttpServletRequest req)
	{
		log.info("Enter protected ActionForward doRead(ActionMapping mapping, SurveyWizardForm survey, HttpServletRequest req)");
		ActionForward returnValue = mapping.findForward(FWD_READ);
		//get current qid, if 0, then start with the 1st. Increment till the max.
		log.debug("doRead qid= "+form.getQuestionId());
		Context ctx = getContext(req);;
		Integer customerId = null;
		Integer expenseId = null;
		
		if (ctx != null)
		{
			
			SurveyFacade facade=new SurveyFacade();
			ContextDTO cdto=ctx.getDTO();
			String answer=facade.getAnswer(cdto,Integer.parseInt(form.getQuestionId()));
			log.debug("Loaded Answer="+answer);
			form.setAnswer(answer);
			customerId = ctx.getCustomerId();
			expenseId = ctx.getExpenseId();
		}
		if (null == customerId || null == expenseId)
		{
			returnValue = mapping.findForward(FWD_FAILURE);
		}
		
		log.info("Exit protected ActionForward doRead(ActionMapping mapping, SurveyWizardForm survey, HttpServletRequest req)");
		return returnValue;
	}

	/**
	 * Save the response from the survey
	 * 
	 * @param mapping
	 * @param form
	 * @param req
	 * @return
	 */
	protected ActionForward doCreate(ActionMapping mapping, SurveyWizardForm form, HttpServletRequest req)
	{
		log.info("Enter protected ActionForward doCreate(ActionMapping mapping, SurveyWizardForm survey, HttpServletRequest req)");
		ActionForward returnValue = mapping.findForward(FWD_READ);

		log.debug("questionId="+form.getQuestionId()+" answer="+form.getAnswer());
	
			SurveyFacade facade = new SurveyFacade();
			ContextDTO cdto = getContext(req).getDTO();
			facade.saveAnswer(cdto,Integer.parseInt(form.getQuestionId()),form.getAnswer());
			if(form.getQuestionId().equals("7")){
			ExpenseFacade expenseFacade = new ExpenseFacade();
			try
			{
				expenseFacade.approveExpense(cdto);
			} catch (ExpenseException e)
			{
				log.warn("Expense could not be approved "+e.getMessage());
			}
			returnValue=mapping.findForward(FWD_SUCCESS);
			}
			else
			{
				log.debug("setting qid to "+form.getNext());
				String questionId=""+form.getNext();
				form.setQuestionId(questionId);
				form.setAnswer(facade.getAnswer(cdto,Integer.parseInt(questionId)));
			}
			//Set the answer if one was previously saved.
			
			
		
		log.info("Exit protected ActionForward doCreate(ActionMapping mapping, SurveyWizardForm survey, HttpServletRequest req)");
		return returnValue;
	}

}
