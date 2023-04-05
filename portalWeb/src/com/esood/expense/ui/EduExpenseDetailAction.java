/*
 * Created on Sep 7, 2005
 */
package com.esood.expense.ui;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.esood.ContextDTO;
import com.esood.expense.ExpenseException;
import com.esood.expense.ExpenseLineItemDTO;
import com.esood.expense.ExpenseFacade;
import com.esood.expense.ui.form.EduExpenseLineItem;
import com.esood.ui.AbstractAction;
import com.esood.ui.Context;
import com.esood.ui.WebForm;

/**
 * EduExpenseDetailAction - CRUD UI functionality for ExpenseLIneItem
 * 
 * @author Rohit
 */
public class EduExpenseDetailAction extends AbstractAction
{

	/**
	 * Controller method for all actions.
	 * 
	 *  @param mapping - 
	 *  @param form -
	 *  @param req -
	 *  @param resp -
	 */
	protected ActionForward doAction(ActionMapping mapping, WebForm form,
			HttpServletRequest req, HttpServletResponse resp)
	{
		log.info("Enter doAction");
		EduExpenseLineItem edli = (EduExpenseLineItem) form;
		ActionForward forward = processCommand(mapping, edli, req);
		log.info("Exit doAction");
		return forward;
	}
/**
 * Looks for the command "cmd" and processes the form against the corresponding method.
 * If the cmd=read and no id is provided then the appropriate form will be given.
 * @param mapping
 * @param edli
 * @param req
 * @return The forward.
 */
	private ActionForward processCommand(ActionMapping mapping,
			EduExpenseLineItem edli, HttpServletRequest req)
	{
		log.info("Enter processCommand");
		ActionForward returnValue = null;
		String cmd = edli.getCmd();
		log.debug("Cmd is " + cmd);
		if (cmd.equals(CMD_READ))
		{
			returnValue = doView(mapping, edli, req);// do read
		} else if (cmd.equals(CMD_CREATE))
		{
			returnValue = doAdd(mapping, edli, req);// do update
		} else if (cmd.equals(CMD_UPDATE))
		{
			returnValue = doUpdate(mapping, edli, req);// do add
		}else if(cmd.equals(CMD_DELETE))
		{
			returnValue=doDelete(mapping,edli,req);
		}else if (cmd.equals(CMD_BACK))
		{
			returnValue=doBack(mapping,edli,req);
		}
		log.info("Exit processCommand");
		return returnValue;
	}

	/**
	 * Gets the cash expense detail for the cash expense id. If the ID is null
	 * then puke?
	 * 
	 * @param mapping
	 * @param eduExpenseLineItem
	 * @param req
	 * @return return the view.
	 */
	private ActionForward doView(ActionMapping mapping,
			EduExpenseLineItem eduExpenseLineItem, HttpServletRequest req)
	{
		log.debug("Enter doView");
		ActionForward forward = mapping.findForward(FWD_READ);
		eduExpenseLineItem.populate(req);//either way this is going to be needed
		String idStr = eduExpenseLineItem.getId();
		log.debug("idStr is " + idStr);
		if (idStr == null || "".equals(idStr))
		{
			//return doAdd(mapping, cef, req);
			return forward;
		}
		Integer id = new Integer(Integer.parseInt(idStr));
		log.debug("Viewing EduExpenseLineItem id =" + id);
		Context ctx=getContext(req);
		ctx.setExpenseLineItemId(id);
		setContext(ctx,req);
		// Check to see if this exists in the DataBase
		// if it does, then load it and populate the form from the DTO.
		ExpenseFacade facade = new ExpenseFacade();
		ExpenseLineItemDTO dto = facade.getExpenseLineItemDTO((getContext(req))
				.getDTO(), id);
		// translate the dto to the cef
		eduExpenseLineItem.populate(dto);
		log.debug("Exit doView");
		return forward;
	}

	/**
	 * Will add a new expense line item each time. It will reject addition if
	 * there is an id on the cef.
	 * 
	 * @param mapping
	 * @param eeli
	 * @param req
	 * @return return add
	 */
	private ActionForward doAdd(	ActionMapping mapping,
													EduExpenseLineItem eeli, 
													HttpServletRequest req)
	{
		log.debug("Enter doAdd");
		ActionForward forward = mapping.findForward(FWD_NEXT);
		Context ctx = super.getContext(req);
		// save the cef into CashExpenseForm
		ExpenseLineItemDTO dto = eeli.getDTO();
		ContextDTO ucdto = ctx.getDTO();
		ExpenseFacade facade = new ExpenseFacade();

		try
		{
			log.debug("expense type on dto is "+dto.getTransactionType());
			Integer expenseLineItemId=facade.addExpenseLineItem(ucdto, dto);
			ctx.setExpenseLineItemId(expenseLineItemId);
			setContext(ctx,req);
		} catch (ExpenseException e)
		{
			log.error(e);
		}
		log.debug("Exit doAdd");
		return forward;
	}

	/**
	 * This will save the details for the cef, and add it to the CashExpenseForm
	 * 
	 * @param mapping
	 * @param edli
	 * @return
	 */
	private ActionForward doUpdate(ActionMapping mapping,
			EduExpenseLineItem edli, HttpServletRequest req)
	{
		log.debug("Enter doUpdate");
		ActionForward forward = mapping.findForward(FWD_NEXT);
		Context ctx = super.getContext(req);
		Integer expenseLineItemId=ctx.getExpenseLineItemId();
		// save the cef into CashExpenseForm
		ExpenseLineItemDTO dto = edli.getDTO();
		dto.setId(expenseLineItemId);
		ContextDTO ucdto = ctx.getDTO();
		log.debug("\nCalling expense facade with \nExpenseLineItemDTO:\n"+dto);
		ExpenseFacade facade = new ExpenseFacade();
		try
		{
			log.debug("Calling facade");
			facade.updateExpenseLineItem(ucdto, dto);
			ctx.setExpenseLineItemId(null);
			setContext(ctx,req);
		} catch (ExpenseException e)
		{
			log.error(e);
		}

		log.debug("Exit doUpdate");
		return forward;
	}
	
	private ActionForward doBack (ActionMapping mapping,EduExpenseLineItem edli, HttpServletRequest req)
	{
		log.debug("Enter doUpdate");
		ActionForward forward = mapping.findForward(FWD_NEXT);
		Context ctx = super.getContext(req);
		ctx.setExpenseLineItemId(null);
		setContext(ctx,req);
		return forward;
	}
	/**
	 * Performs a delete without warning.
	 * @param mapping
	 * @param edli
	 * @param req
	 * @return
	 */
	private ActionForward doDelete(ActionMapping mapping,
			EduExpenseLineItem edli, HttpServletRequest req)
	{
		log.debug("Enter doDelete");
		ActionForward forward = mapping.findForward(FWD_NEXT);
		Context ctx = super.getContext(req);
		Integer expenseLineItemId=ctx.getExpenseLineItemId();
		ExpenseFacade facade = new ExpenseFacade();
		try
		{
			log.debug("Calling facade");
			facade.deleteExpenseLineItem(ctx.getDTO(), expenseLineItemId);
			ctx.setExpenseLineItemId(null);
			setContext(ctx,req);
		} catch (ExpenseException e)
		{
			log.error(e);
		}

		log.debug("Exit doDelete");
		return forward;
	}
}
