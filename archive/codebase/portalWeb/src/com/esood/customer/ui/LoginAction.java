package com.esood.customer.ui;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.esood.ContextDTO;
import com.esood.customer.CustomerFacade;
import com.esood.customer.CustomerException;
import com.esood.ui.AbstractAction;
import com.esood.ui.Context;
import com.esood.ui.WebForm;

/**
 * This is executed when the user logs on to the site.
 * 
 * @author Rohit Sood
 */
public class LoginAction extends AbstractAction
{

	public ActionForward doAction(ActionMapping mapping, WebForm form, HttpServletRequest req, HttpServletResponse resp)
	{

		log.info("Enter LoginAction");
		String forward = FWD_READ;
		RegisterForm cf = (RegisterForm) form;

		String cmd = cf.getCmd();
		if (CMD_READ.equals(cmd))
		{
			return mapping.findForward(forward);
		}
		String userName = cf.getUsername();
		String password = cf.getPassword();

		// create the facade
		CustomerFacade facade = new CustomerFacade();
		ActionErrors errors = new ActionErrors();
		boolean valid = false;
		try
		{
			log.debug("Calling facade");
			ContextDTO ucdto = facade.login(userName, password);// call facade
			log.debug("ucdto = " + ucdto);
			valid = ucdto.getUserName() != null && !"".equals(ucdto.getUserName());
			if (!valid)
			{
				errors.add("Could not validated", new ActionError("login.failure"));
			} else
			{
				prepareContext(ucdto, req);
				forward = FWD_SUCCESS;
			}
			log.debug("Facade called");
		} catch (CustomerException e)
		{
			log.error("RegistrationException ", e);
			errors.add("username", new ActionError("login.failure"));
			forward = FWD_READ;
		}

		saveErrors(req, errors);
		log.info("Exit LoginAction");

		return mapping.findForward(forward);
	}

	/**
	 * Prepares the context.
	 * 
	 * @param ucdto
	 * @param req
	 */
	private void prepareContext(ContextDTO ucdto, HttpServletRequest req)
	{
		log.info("Enter prepareContext");
		HttpSession session = req.getSession(true);
		Context ctx = (Context) session.getAttribute(Context.NAME);
		if (ctx == null)
		{
			ctx = new Context();
		}
		ctx.setUserName(ucdto.getUserName());
		ctx.setCustomerId(ucdto.getCustomerId());
		super.setContext(ctx, req);
		log.info("Exit prepareContext");
	}
}
