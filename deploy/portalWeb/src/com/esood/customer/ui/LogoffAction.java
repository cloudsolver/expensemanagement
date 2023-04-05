package com.esood.customer.ui;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.commons.logging.LogFactory;
import org.apache.commons.logging.Log;


/**
 * This will log-off the user.
 * @author Rohit Sood
 */
public class LogoffAction extends Action {
	
	private static String SUCCESS="success";
	private Log log=LogFactory.getLog(this.getClass());
	
	public ActionForward perform (ActionMapping mapping,ActionForm form,HttpServletRequest req,HttpServletResponse resp){
		log.info("Enter Logoff Action");
		HttpSession session=req.getSession();
		if(null!=session)
			{
			
			session.invalidate();
			}
		log.info("Exit Logoff Action");
		return mapping.findForward(SUCCESS);
	}
}
