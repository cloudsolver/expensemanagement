/*
 * Created on Aug 20, 2005
 */
package com.esood.survey.ui;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.esood.ContextDTO;
import com.esood.expense.ExpenseFacade;
import com.esood.survey.SurveyDTO;
import com.esood.survey.SurveyFacade;
import com.esood.ui.AbstractAction;
import com.esood.ui.Context;
import com.esood.ui.WebForm;

/**
 * This action should submit the page for display.
 * 
 * @author Rohit Sood
 */
public class SurveyAction extends AbstractAction
{

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.esood.ui.AbstractAction#doAction(org.apache.struts.action.ActionMapping,
	 *      org.apache.struts.action.ActionForm, javax.servlet.http.HttpServletRequest,
	 *      javax.servlet.http.HttpServletResponse)
	 */
	protected ActionForward doAction(ActionMapping mapping, WebForm form, HttpServletRequest req, HttpServletResponse resp)
	{
		log.info("Enter protected ActionForward doAction(ActionMapping mapping, WebForm form, HttpServletRequest req, HttpServletResponse resp)");
		SurveyForm survey = (SurveyForm) form;
		ActionForward returnValue = processCommand(mapping, survey, req);
		log.info("Exit protected ActionForward doAction(ActionMapping mapping, WebForm form, HttpServletRequest req, HttpServletResponse resp)");
		return returnValue;
	}

	/**
	 * 
	 * @param mapping
	 * @param survey
	 * @param req
	 * @return
	 */
	protected ActionForward processCommand(ActionMapping mapping, SurveyForm survey, HttpServletRequest req)
	{
		log.info("Enter protected ActionForward processCommand(ActionMapping mapping, SurveyForm survey, HttpServletRequest req)");
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
			returnValue = doRead(mapping, survey, req);
		}
		log.info("Exit protected ActionForward processCommand(ActionMapping mapping, SurveyForm survey, HttpServletRequest req)");
		return returnValue;
	}

	/**
	 * doRead
	 * 
	 * @param mapping
	 * @param form
	 * @param req
	 * @return
	 */
	protected ActionForward doRead(ActionMapping mapping, SurveyForm form, HttpServletRequest req)
	{
		log.info("Enter protected ActionForward doRead(ActionMapping mapping, SurveyForm survey, HttpServletRequest req)");
		ActionForward returnValue = mapping.findForward(FWD_READ);

		Context ctx = null;
		Integer customerId = null;
		Integer expenseId = null;

		ctx = getContext(req);
		if (ctx != null)
		{
			customerId = ctx.getCustomerId();
			expenseId = ctx.getExpenseId();
		}
		if (null == customerId || null == expenseId)
		{
			returnValue = mapping.findForward(FWD_FAILURE);
		}

		form.populate(req);

		log.info("Exit protected ActionForward doRead(ActionMapping mapping, SurveyForm survey, HttpServletRequest req)");
		return returnValue;
	}

	/**
	 * This will create the survey
	 * 
	 * @param mapping
	 * @param survey
	 * @param req
	 * @return
	 */
	protected ActionForward doCreate(ActionMapping mapping, SurveyForm survey, HttpServletRequest req)
	{
		log.info("Enter protected ActionForward doCreate(ActionMapping mapping, SurveyForm survey, HttpServletRequest req)");
		ActionForward returnValue = mapping.findForward(FWD_SUCCESS);

		ActionErrors errors = survey.validate(mapping, req);
		if (null != errors && !errors.isEmpty())
		{
			returnValue = mapping.findForward(FWD_READ);
			saveErrors(req, errors);
			return returnValue;
		}
		SurveyDTO dto = new SurveyDTO();
		dto.setResponse01(survey.getEff01());
		dto.setResponse02(survey.getEff02());
		dto.setResponse03(survey.getEff03());
		dto.setResponse04(survey.getEff04());
		dto.setResponse05(survey.getEff05());
		dto.setResponse06(survey.getEff06());
		dto.setResponse07(survey.getEff07());
		dto.setResponse08(survey.getEff08());
		log.debug("SurveyDTO=" + dto);
		Context ctx = getContext(req);
		ContextDTO uc = new ContextDTO();
		if (null != ctx)
		{
			uc.setUserName(ctx.getUserName());
			dto.setUserContextDTO(uc);
		} else
		{
			log.warn("Context was not supposed to be null when calling this action");
		}
		try
		{
			SurveyFacade facade = new SurveyFacade();
			ContextDTO cdto = getContext(req).getDTO();
			facade.saveSurvey(cdto, dto);
			ExpenseFacade expenseFacade = new ExpenseFacade();
			expenseFacade.approveExpense(cdto);
		} catch (Exception e)
		{
			log.error("Exception caught Calling Facade Save", e);
		}
		log.info("Exit protected ActionForward doCreate(ActionMapping mapping, SurveyForm survey, HttpServletRequest req)");
		return returnValue;
	}

}
