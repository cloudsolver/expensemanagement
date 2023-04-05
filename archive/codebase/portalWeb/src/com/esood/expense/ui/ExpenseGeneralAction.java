/*
 * Created on Sep 7, 2005
 */
package com.esood.expense.ui;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.esood.ContextDTO;
import com.esood.expense.ExpenseException;
import com.esood.expense.ExpenseFacade;
import com.esood.expense.ExpenseDTO;
import com.esood.expense.ui.form.ExpenseForm;
import com.esood.ui.AbstractAction;
import com.esood.ui.Context;
import com.esood.ui.WebForm;

/**
 * General action is Page #1
 * 
 * @author Rohit Sood
 */
public class ExpenseGeneralAction extends AbstractAction
{

	protected ActionForward doAction(ActionMapping mapping, WebForm form, HttpServletRequest req, HttpServletResponse resp)
	{
		log.info("Enter doAction");
		ActionForward forward = mapping.findForward(FWD_READ);
		startTimer(req);
		ExpenseForm gef = null;
		try
		{
			gef = (ExpenseForm) form;
			gef.setPage(1);
			forward = processCommand(mapping, gef, req);
		} catch (ExpenseException e)
		{
			ActionErrors errors = new ActionErrors();
			errors.add("Could not process expense", new ActionError("expense.error"));
			saveErrors(req, errors);
			log.error("ExpenseException occurred", e);
		}

		log.info("Exit doAction");
		return forward;
	}

	protected ActionForward processCommand(ActionMapping mapping, ExpenseForm gef, HttpServletRequest req) throws ExpenseException
	{
		log.info("Enter processCommand");
		ActionForward forward = null;
		String cmd = null;
		Integer expenseId = getContext(req).getExpenseId();
		if (gef != null && gef.getCmd() != null && !"".equals(gef.getCmd()))
		{
			cmd = gef.getCmd();
		} else
		{
			cmd = CMD_READ;
		}
		if (cmd != null && cmd.equals(CMD_CREATE) && null == expenseId)
		{
			forward = doCreate(mapping, gef, req);
		} else if (cmd != null && cmd.equals(CMD_READ))
		{
			forward = doRead(mapping, gef, req);
		} else if (cmd.equals(CMD_CREATE) && null != expenseId)
		{
			forward = doUpdate(mapping, gef, req);
		}
		log.info("Exit processCommand");
		return forward;
	}

	/**
	 * ActionForward - updates the expense.
	 * 
	 * @param mapping
	 * @param ef
	 *            The expense form
	 * @param req
	 * @return
	 */
	private ActionForward doUpdate(ActionMapping mapping, ExpenseForm ef, HttpServletRequest req)
	{
		log.debug("Enter doUpdate");
		ActionForward forward = mapping.findForward(FWD_NEXT);
		ExpenseFacade facade = new ExpenseFacade();

		/*
		 * Integer expenseId=new Integer(expenseIdStr); Context ctx=getContext(req); ctx.setExpenseId(expenseId);
		 * setContext(ctx,req);
		 */

		ExpenseDTO dto = ef.getDTO();
		dto.setId(getContext(req).getExpenseId());// set the expense id on the dto, because the form will not have it.
		ContextDTO cdto = getContext(req).getDTO();

		try
		{
			facade.updateExpense(cdto, dto);
		} catch (ExpenseException e)
		{
			log.warn("Issues with updating " + e.getMessage());

		}

		log.debug("Exit doUpdate");
		return forward;
	}

	/**
	 * Creates the expense.
	 * 
	 * @param mapping
	 * @param gef
	 * @param req
	 * @return
	 * @throws ExpenseException
	 */
	private ActionForward doCreate(ActionMapping mapping, ExpenseForm gef, HttpServletRequest req) throws ExpenseException
	{
		log.debug("Enter doCreate");
		ExpenseFacade facade = new ExpenseFacade();
		ExpenseDTO dto = gef.getDTO();
		log.debug("Calling facade");
		Integer expenseId = facade.addExpense(getContext(req).getDTO(), dto);
		// set the expenseId on the context.
		Context context = getContext(req);
		context.setExpenseId(expenseId);
		setContext(context, req);
		log.debug("Done calling Facade");
		ActionForward returnValue = mapping.findForward(FWD_NEXT);
		log.debug("Exit doCreate");
		return returnValue;
	}

	/**
	 * DoView - this is the view. If an id is provided, the form should load the values.
	 * 
	 * @param mapping
	 * @param ef
	 * @param req
	 * @return
	 */
	private ActionForward doRead(ActionMapping mapping, ExpenseForm ef, HttpServletRequest req)
	{
		log.debug("Enter doRead");
		String expenseIdStr = ef.getId();
		ActionForward returnValue = mapping.findForward(FWD_READ);
		if (null == expenseIdStr || "".equals(expenseIdStr))
		{
			log.debug("doRead with no id");
			return returnValue;
		}
		Integer expenseId = new Integer(expenseIdStr);
		Context ctx = getContext(req);
		ctx.setExpenseId(expenseId);
		setContext(ctx, req);
		ExpenseFacade facade = new ExpenseFacade();

		try
		{
			ExpenseDTO expenseDTO = facade.getExpense(super.getContext(req).getDTO());
			ef.populate(expenseDTO);
		} catch (ExpenseException e)
		{
			log.error("Could not process the expense " + e.getMessage());
		}

		log.debug("Exit doRead");
		return returnValue;
	}

}
