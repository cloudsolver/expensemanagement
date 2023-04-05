/*
 * Created on Sep 7, 2005
 */
package com.esood.expense.ui;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.esood.ContextDTO;
import com.esood.expense.ExpenseFacade;
import com.esood.expense.ui.form.ExpenseFormHelper;

import com.esood.ui.AbstractAction;
import com.esood.ui.Context;
import com.esood.ui.WebForm;

/**
 * Expense Line Items - this will load all the line items for the expenses.
 * 
 * @author Rohit
 */
public class ExpenseLineItemsAction extends AbstractAction
{

	/**
	 * Process the action
	 * 
	 */
	protected ActionForward doAction(ActionMapping mapping, WebForm form,
			HttpServletRequest req, HttpServletResponse resp)
	{
		log.info("Enter doAction");
		ActionForward forward = mapping.findForward(FWD_READ);
		forward = processCommand(mapping, form,req);

		log.info("Exit doAction");
		return forward;
	}

	/**
	 * Process the command.
	 * @param mapping
	 * @param cef
	 * @param req
	 * @return
	 */
	protected ActionForward processCommand(ActionMapping mapping, WebForm form,
			 HttpServletRequest req)
	{
		log.debug("Enter processCommand");
		String cmd = form.getCmd();
		ActionForward forward = null;
		if (cmd != null && cmd.equals(CMD_CREATE))
		{
			forward = doNext(mapping);
		} else if (cmd != null && cmd.equals(CMD_READ))
		{
			forward = doRead(mapping, req);
		}
		log.debug("Exit processCommand");
		return forward;
	}
/**
 * Just go to the next page
 * @param mapping
 * @param cef
 * @return
 */
	private ActionForward doNext(ActionMapping mapping)
	{
		log.debug("Enter doSave");
		// Map the ef to a dto and call the facade.
		ActionForward returnValue = mapping.findForward(FWD_NEXT);

		log.debug("Exit doSave");
		return returnValue;
	}

	/**
	 * Gets all the cash expenses for the report page.
	 * The cash expense form is responsible to populate itself with the dto values.
	 * @param mapping
	 * @param cef
	 * @param req
	 * @return
	 */
	private ActionForward doRead(ActionMapping mapping, 
			HttpServletRequest req)
	{
		log.debug("Enter doView");
		// If req has
		Context ctx = getContext(req);
		ctx.setExpenseLineItemId(null);
		setContext(ctx,req);
		ContextDTO ucdto = ctx.getDTO();
		ExpenseFacade facade = new ExpenseFacade();
		List dtos = facade.getExpenseLineItems(ucdto);
		log.debug("Number of dtos:" + dtos.size());
		populate(dtos, req);
		ActionForward returnValue = mapping.findForward(FWD_READ);
		log.debug("Exit doView");
		return returnValue;
	}
	/**
	 * Populates the request with the list
	 * @param dtos
	 * @param req
	 */
	private void populate(List dtos,HttpServletRequest req)
	{
		log.info("Enter populate");
		
		List aeli=ExpenseFormHelper.getExpenseLineItems(dtos);
		req.setAttribute("expenseLineItems",aeli);
		
		log.info("Exit populate");
	}//()
}
