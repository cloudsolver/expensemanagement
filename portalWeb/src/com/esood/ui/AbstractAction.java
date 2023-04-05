/*
 * Created on Jul 23, 2005
 */
package com.esood.ui;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;



/**
 * This is the super action for all actions within the application.
 * 
 * @author Rohit Sood
 */
public abstract class AbstractAction extends Action { 
	public static final String FWD_STAY = "stay";
	public static final String FWD_FAILURE="failure";
	public static final String FWD_SUCCESS="success";
	public static final String FWD_READ="read";
	public static final String FWD_NEXT="next";
	
	
	public static final String CMD_CREATE="create";
	public static final String CMD_READ="read";
	public static final String CMD_UPDATE = "update";
	public static final String CMD_DELETE="delete";
	public static final String CMD_PROCESS="process";
	public static final String CMD_BACK="back";
	
	protected Log log=LogFactory.getLog(this.getClass());
	
	/**
	 * 
	 */
	public ActionForward execute(ActionMapping mapping,ActionForm form,HttpServletRequest req,HttpServletResponse resp)
	{
		ActionForward forward=null;
	
		ActionErrors errors=new ActionErrors();
		try{
			
			WebForm wf=(WebForm) form;
			
			Context ctx=getContext(req);
			//If the web-form is not null then set the context.
			if(wf!=null)
			{
				wf.setContext(ctx);//every web-form will have the context available.
			}
			

			forward=doAction(mapping,wf,req,resp);
		}
		
		catch(Exception e){
			
			log.error("Exception caught at AbstractAction",e);
				
			if(e instanceof NullPointerException)
			{
				log.error("Exception is a NPE");
				errors.add("timeOut",new ActionError("error.TimeOut"));
				saveErrors(req,errors);
				throw new TimeoutException (e);
			}
			else
			{
				errors.add("Unexpected Error has occurred",new ActionError("error.RuntimeException"));
				saveErrors(req,errors);
				throw new ActionException();
			}
						
		}
		
		return forward;
	}
	/**
	 * 
	 * @param req
	 * @return
	 */
	protected Context getContext(HttpServletRequest req)
	{
		Context returnValue=null;
		HttpSession session=req.getSession(false);
		if(null!=session)
		{
			returnValue=(Context) session.getAttribute(Context.NAME);
		}
		
		return returnValue;
	}
	
	protected void setContext(Context context, HttpServletRequest req)
	{
		HttpSession session=req.getSession(false);
		if(null!=session)
		{
			session.setAttribute(Context.NAME, context);
		}
		else
		{
			log.warn("Session does not exist, cannot store context. Please ensure session exists.");
		}

	}
	
	public void startTimer(HttpServletRequest req)
	{
		TimerDelegate helper=new TimerDelegate(req);
		helper.startTimer();
	}
	public void stopTimer(HttpServletRequest req)
	{
		TimerDelegate helper=new TimerDelegate(req);
		helper.stopTimer();
	}
	
	protected abstract ActionForward doAction(ActionMapping mapping,WebForm form,HttpServletRequest req,HttpServletResponse resp);

}
