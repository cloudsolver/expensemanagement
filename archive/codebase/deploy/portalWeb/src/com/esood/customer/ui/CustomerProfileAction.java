package com.esood.customer.ui;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.esood.ContextDTO;
import com.esood.customer.CustomerDTO;
import com.esood.customer.CustomerFacade;
import com.esood.customer.CustomerException;
import com.esood.ui.AbstractAction;
import com.esood.ui.Context;
import com.esood.ui.WebForm;

/**
 * Demographics information is entered
 * @author Rohit Sood
 */
public class CustomerProfileAction extends AbstractAction {
	
	
	
	public ActionForward doAction (ActionMapping mapping,WebForm form,HttpServletRequest req,HttpServletResponse resp){
		log.info("Enter doAction");
		
		RegisterForm cf=(RegisterForm) form;
		
		ActionErrors errors=new ActionErrors();
		ActionForward forward=processCommand(mapping,cf,req,errors);
		
		log.info("Exit doAction");
		return forward;
	}
	
	/**
	 * 
	 * @param mapping
	 * @param form
	 * @param req
	 * @param errors
	 * @return
	 */
	private ActionForward processCommand(ActionMapping mapping,
			RegisterForm form, HttpServletRequest req, ActionErrors errors)
	{
		log.info("Enter processCommand ");
		ActionForward forward = null;

		String cmd = form.getCmd();
		log.debug("cmd="+cmd);
		if (cmd != null && cmd.equals(CMD_CREATE))
		{
			forward=doSave(mapping,form,req,errors);
			
		} else if (cmd != null && cmd.equals(CMD_READ))
		{
			forward=doView(mapping,form,req,errors);
		} else
		{
			errors.add("cmd", new ActionError("error.Command.Missing"));
			forward = mapping.findForward(FWD_READ);
			
		}
		log.info("Exit processCommand");
		return forward;
	}

	/**
	 * Populates the demographics screen with drop-downs.
	 * @param mapping
	 * @param form
	 * @param req
	 * @param errors
	 * @return
	 */
	private ActionForward doView(ActionMapping mapping, RegisterForm form,
			HttpServletRequest req,ActionErrors errors)
	{
		log.debug("Enter doView");
		//load up the page
		form.populate(req);
		ContextDTO cdto=null;
		Context ctx=getContext(req);
		if(ctx!=null)
		{
			cdto=ctx.getDTO();
			CustomerFacade facade=new CustomerFacade();
			CustomerDTO dto=facade.getCustomerDTO(cdto);
			if(dto!=null)
				{
				form.populate(dto);
				}
		}
		
		ActionForward forward = mapping.findForward(FWD_READ);
		log.debug("Exit doView");
		return forward;
	}

	private ActionForward doSave(ActionMapping mapping, RegisterForm form,
			HttpServletRequest req, ActionErrors errors)
	{
		log.debug("Enter doSave");
		ActionForward forward=null;
		Integer customerId=null;
		try {
			Context context=super.getContext(req);
			ContextDTO cdto=context.getDTO();
			CustomerFacade facade=new CustomerFacade();
			customerId=facade.saveCustomer(cdto,form.getDTO());
			context.setCustomerId(customerId);
			super.setContext(context,req);
			forward=mapping.findForward(FWD_NEXT);
		} catch (CustomerException e) {
			log.error("Registration Exception ",e);
			errors.add("username",new ActionError("username.exists"));
			saveErrors(req,errors);
			forward= mapping.findForward(FWD_READ);
		}
		
		log.debug("Exit Save");
		return forward;

	}
	
}
