package com.esood.customer.ui;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;

import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.commons.logging.LogFactory;
import org.apache.commons.logging.Log;

import com.esood.customer.CustomerFacade;
import com.esood.customer.CustomerException;
import com.esood.ui.AbstractAction;
import com.esood.ui.ActionException;
import com.esood.ui.Context;
import com.esood.ui.WebForm;

/**
 * This is called when the registration page is submitted. The username is checked against the current registered users.
 * 
 * @author Rohit Sood
 */
public class RegisterAction extends AbstractAction
{
	private Log log = LogFactory.getLog(this.getClass());

	/**
	 * Process the registration
	 */
	public ActionForward doAction(ActionMapping mapping, WebForm form, HttpServletRequest req, HttpServletResponse resp)
	{
		log.info("Enter Register Action");

		RegisterForm cf = (RegisterForm) form;
		ActionErrors errors = new ActionErrors();
		ActionForward forward = processCommand(mapping, cf, req, errors);

		log.info("Exit Register Action");
		return forward;
	}

	/**
	 * Process the command
	 * 
	 * @param mapping
	 * @param form
	 * @param req
	 * @param errors
	 * @return
	 */
	private ActionForward processCommand(ActionMapping mapping, RegisterForm form, HttpServletRequest req, ActionErrors errors)
	{
		log.info("Enter processCommand");
		ActionForward forward = null;

		String cmd = form.getCmd();
		if (cmd != null && cmd.equals(CMD_CREATE))
		{
			forward = doSave(mapping, form, req, errors);
		} else if (cmd != null && cmd.equals(CMD_READ))
		{
			forward = doView(mapping, form, req, errors);
		} else
		{
			errors.add("cmd", new ActionError("error.Command.Missing"));
			forward = mapping.findForward(FWD_READ);
			return forward;
		}
		log.info("Exit processCommand");

		return forward;
	}

	/**
	 * Process the read command. Just shows the register screen.
	 * 
	 * @param mapping
	 * @param form
	 * @param req
	 * @param errors
	 * @return
	 */
	private ActionForward doView(ActionMapping mapping, RegisterForm form, HttpServletRequest req, ActionErrors errors)
	{
		log.debug("Enter doView");

		ActionForward forward = mapping.findForward(FWD_READ);

		log.debug("Exit doView");
		return forward;
	}

	/**
	 * Saves the registration information
	 * 
	 * @param mapping
	 * @param form
	 * @param req
	 * @param errors
	 * @return
	 */
	private ActionForward doSave(ActionMapping mapping, RegisterForm form, HttpServletRequest req, ActionErrors errors)
	{
		log.debug("Enter doSave");
		ActionForward forward = mapping.findForward(FWD_NEXT);
		String userName = form.getUsername();
		String password1 = form.getPassword();
		String password2 = form.getPassword2();
		CustomerFacade facade = new CustomerFacade();
		if (!password1.equals(password2))
		{
			errors.add("unequal", new ActionError("password.unequal"));
			saveErrors(req, errors);
			return mapping.findForward(FWD_READ);
		}
		try
		{
			Integer id = facade.registerUser(userName, password1, password2);
			Context context = new Context();
			context.setUserId(id);
			context.setUserName(userName);
			context.setRegistered(false);
			setContext(context, req);
			forward = mapping.findForward(FWD_NEXT);
		} catch (CustomerException e)
		{
			errors.add("username", new ActionError("username.exists"));
			saveErrors(req, errors);
			return mapping.findForward(FWD_READ);

		} catch (Exception e)
		{
			errors.add("unknown", new ActionError("error.RuntimeException"));
			saveErrors(req, errors);
			throw new ActionException("unknown");

		} finally
		{
			saveErrors(req, errors);
		}
		log.debug("Exit doSave");
		return forward;
	}
}
