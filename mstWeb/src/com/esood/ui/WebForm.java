/*
 * Created on Aug 8, 2005
 */
package com.esood.ui;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.validator.ValidatorForm;


/**
 * This is the super class for all Web-Forms.
 * 
 * @author Rohit
 */
public class WebForm extends ValidatorForm
{
	protected Log log=LogFactory.getLog(this.getClass());
	static final long serialVersionUID=23425L;
	private String cmd;
	private String type;
	private Context context;
	
	/**
	 * 
	 *
	 */
	public WebForm()
	{
		super();
	}
	/**
	 * The reset of the ActionForm will populate the form
	 */
	public void reset(ActionMapping mapping, HttpServletRequest request)
	{
		super.reset(mapping, request);
		
	}
	/**
	 * Checks for command before actually performing a validation.
	 * @param mapping
	 * @param request
	 * @return errors - ActionErrors. Null cmd is null, read, process or back.
	 */
	public ActionErrors validate(ActionMapping mapping, HttpServletRequest request)
	{
		log.info("Enter public ActionErrors validate(ActionMapping mapping, HttpServletRequest request)");
		ActionErrors errors=null;
		if(null==cmd || AbstractAction.CMD_READ.equals(cmd) || AbstractAction.CMD_PROCESS.equals(cmd) || AbstractAction.CMD_BACK.equals(cmd) )			
		{
			log.debug("Will not validate because cmd is "+cmd+" returning ActionErrors="+errors);			
			return errors;
		}
		else{
			errors=super.validate(mapping,request);
			if(errors==null)
			{
				errors=new ActionErrors();
			}
		}
		log.info("Exit public ActionErrors validate(ActionMapping mapping, HttpServletRequest request)");
		return errors;
	}
	
	/**
	 * @return Returns the cmd.
	 */
	public String getCmd()
	{
		return cmd;
	}
	/**
	 * @param cmd The cmd to set.
	 */
	public void setCmd(String cmd)
	{
		this.cmd = cmd;
	}
	/**
	 * @return Returns the context.
	 */
	public Context getContext()
	{
		return context;
	}
	/**
	 * @param context The context to set.
	 */
	public void setContext(Context context)
	{
		this.context = context;
	}
	/**
	 * @return Returns the type.
	 */
	public String getType()
	{
		return type;
	}
	/**
	 * @param type The type to set.
	 */
	public void setType(String type)
	{
		this.type = type;
	}

}
