/*
 * Created on Sep 7, 2005
 */
package com.esood.expense.ui;

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

public class ExpenseReviewAction extends AbstractAction
{

	protected ActionForward doAction(ActionMapping mapping, WebForm form,
			HttpServletRequest req, HttpServletResponse resp)
	{
		log.info("Enter doAction");
		ActionForward returnValue=null;
		ExpenseForm expenseForm=(ExpenseForm) form;
		String cmd=expenseForm.getCmd();
		
		returnValue = processCommand(mapping, expenseForm, cmd,getContext(req),req);
		
		log.info("Exit doAction");		
		return returnValue;
	}
	
	private ActionForward processCommand(ActionMapping mapping, ExpenseForm expenseForm, String cmd, Context ctx,HttpServletRequest request)
	{
		ActionForward returnValue;
		if(cmd!=null & cmd.equals(CMD_CREATE)){
			returnValue=doSave(mapping,expenseForm,request);
		}else {
			returnValue=doView(mapping,expenseForm, ctx,request);
		}
		return returnValue;
	}
	/**
	 * Get the pending transactions for the given user and populate the page with it.
	 * @param mapping
	 * @param form
	 * @return
	 */
	private ActionForward doView(ActionMapping mapping, ExpenseForm form, Context ctx,HttpServletRequest req)
	{
		log.debug("Enter doView");
		ActionForward returnValue=mapping.findForward(FWD_READ);
		ExpenseFacade facade=new ExpenseFacade();
		try
		{
			ExpenseDTO dto=facade.getExpense(ctx.getDTO());
			form.populate(dto);
		} catch (ExpenseException e)
		{
			log.warn("Could not get expense for review "+e.getMessage());
		}
		log.debug("Exit doView");
		return returnValue;
	}
	/**
	 * Get the selected transactions of the user, and associate it with the expense report.
	 * @param mapping
	 * @param form
	 * @return
	 */
	private ActionForward doSave(ActionMapping mapping, ExpenseForm form, HttpServletRequest req)
	{
		log.debug("Enter doSave");
		ActionForward returnValue=mapping.findForward(FWD_NEXT);
		ExpenseFacade facade=new ExpenseFacade();
		
		//disassociate the current expenseId
		Context ctx=getContext(req);
		
		try
		{
			facade.submitExpense(ctx.getDTO());
		} catch (ExpenseException e)
		{
			log.warn("Could not submit the expense "+e.getMessage());
		}
		stopTimer(req);
		log.debug("Exit doSave");
		return returnValue;
	}
	

}
