/*
 * Created on Sep 1, 2005
 */
package com.esood.expense.ui;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.esood.expense.ExpenseDTO;
import com.esood.expense.ExpenseException;
import com.esood.expense.ExpenseFacade;

import com.esood.expense.ui.form.ExpenseForm;
import com.esood.ui.AbstractAction;
import com.esood.ui.Context;
import com.esood.ui.WebForm;

/**
 * Expense Home Action is responsible for setting the application type on the session. Type 1=No delay. Type 2=3 seconds
 * delay. Type 3=7 seconds delay.
 * 
 * @author Rohit Sood
 */
public class ExpenseHomeAction extends AbstractAction
{

	/**
	 * Do the action!
	 * 
	 * @param mapping
	 * @param webform
	 * @param req
	 * @param resp
	 */
	public ActionForward doAction(ActionMapping mapping, WebForm webform, HttpServletRequest req, HttpServletResponse resp)
	{
		log.info("Enter ExpenseHomeAction");
		ActionForward forward = null;
		ExpenseForm form = (ExpenseForm) webform;
		forward = processCommand(mapping, form, req);
		log.info("Exit ExpenseHomeAction");
		return forward;
	}

	/**
	 * Process Command
	 * 
	 * @param mapping
	 * @param form
	 * @param req
	 * @return
	 */
	private ActionForward processCommand(ActionMapping mapping, ExpenseForm form, HttpServletRequest req)
	{
		log.debug("Enter processCommand");
		String cmd = form.getCmd();
		ActionForward forward = null;
		processDelay(mapping, form, req);
		if (cmd == null)
		{
			forward = doView(mapping, form, req);
		} else if (cmd.equals(CMD_READ))
		{
			forward = doView(mapping, form, req);
		} else if (cmd.equals(CMD_DELETE))
		{
			forward = doDelete(mapping, form, req);
		}
		log.debug("Exit processCommand");
		return forward;

	}

	/**
	 * Deletes the expense.
	 * 
	 * @param mapping -
	 *            The mapping.
	 * @param form -
	 *            The form.
	 * @param req -
	 *            The servlet request.
	 */
	private ActionForward doDelete(ActionMapping mapping, ExpenseForm form, HttpServletRequest req)
	{
		log.debug("Enter doDelete");
		ActionForward forward = mapping.findForward(FWD_READ);
		ExpenseFacade facade = new ExpenseFacade();
		String id = form.getId();
		Integer expenseId = new Integer(id);
		Context ctx = getContext(req);
		ctx.setExpenseId(expenseId);
		setContext(ctx, req);
		try
		{
			facade.deleteExpense(ctx.getDTO());
			forward = doView(mapping, form, req);
		} catch (ExpenseException e)
		{
			log.error("Could not delete the expense ", e);
		}
		log.debug("Exit doDelete");
		return forward;
	}

	private void clearSession(HttpServletRequest req)
	{
		Context ctx = getContext(req);
		stopTimer(req);
		ctx.setExpenseId(null);
		ctx.setExpenseLineItemId(null);
		setContext(ctx, req);
	}

	/**
	 * Gets executed when the user visits the home page. The list of Expenses are provided to the page and the page
	 * lists them
	 * 
	 * @param mapping
	 * @param req
	 * @return
	 */
	private ActionForward doView(ActionMapping mapping, ExpenseForm form, HttpServletRequest req)
	{
		log.info("Enter doView");
		clearSession(req);

		ActionForward forward = mapping.findForward(FWD_READ);
		ExpenseFacade facade = new ExpenseFacade();
		Context context = getContext(req);
		Integer customerId = context.getCustomerId();
		
		if (null!= form.getApplicationType() && ! "".equals(form.getApplicationType()))
		{
			context.setApplicationType(form.getApplicationType());
			log.debug("setting application type: "+context.getApplicationType());
		}
		if (null != form.getApplicationVersion() && !"".equals(form.getApplicationVersion()))
		{
			context.setApplicationVersion(form.getApplicationVersion());
			log.debug("setting application version: "+context.getApplicationVersion());
		}
		setContext(context,req);
		//try stuff out?
		try
		{
			List dtos = facade.getExpenses(customerId);
			List forms = new ArrayList();
			Iterator dtoIterator = dtos.iterator();
			while (dtoIterator.hasNext())
			{
				// cast to the DTO
				ExpenseDTO dto = (ExpenseDTO) dtoIterator.next();
				// transfer dto to form
				ExpenseForm lineItem=new ExpenseForm();
				lineItem.populate(dto);
				forms.add(lineItem);
			}

			req.setAttribute("forms", forms);

		} catch (ExpenseException e)
		{
			log.error(e);
		}
		log.info("Exit doView");
		return forward;
	}

	/**
	 * Delay process??
	 * 
	 * @param mapping
	 * @param req
	 * @param applicationType
	 */
	private void processDelay(ActionMapping mapping, ExpenseForm form, HttpServletRequest req)
	{
		log.debug("Enter processDelay");

		String applicationType=form.getApplicationType();
		log.debug("delayType is " + applicationType);
		Context ctx = getContext(req);

		if (applicationType != null && !"".equals(applicationType))
		{

			if (applicationType.equals("A"))
			{
				log.debug("No delay");
				ctx.setDelay(Context.NO_DELAY);
			} else if (applicationType.equals("B"))
			{
				log.debug("Medium delay");
				ctx.setDelay(Context.MEDIUM_DELAY);
			} else if (applicationType.equals("C"))
			{
				log.debug("Long delay");
				ctx.setDelay(Context.LONG_DELAY);
			}
		} else
		{
			log.warn("Delay type cannot be processed");
		}

		setContext(ctx, req);
		log.debug("Exit processDelay");
	}

}
