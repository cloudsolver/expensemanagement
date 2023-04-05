/*
 * Created on Dec 28, 2005
 */
package com.esood.home.ui;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.esood.ui.AbstractAction;
import com.esood.ui.TimerDelegate;
import com.esood.ui.WebForm;

/*
 * The home action.
 */
public class HomeAction extends AbstractAction
{

	/**
	 * 
	 * 
	 */
	protected ActionForward doAction(ActionMapping mapping, WebForm form, HttpServletRequest req, HttpServletResponse resp)
	{
		ActionForward returnValue = mapping.findForward(FWD_READ);
		String cmd = form.getCmd();
		if (cmd != null && cmd.equals(""))
		{
			if (cmd.equals(CMD_READ))
			{
				TimerDelegate delegate = new TimerDelegate(req);
				delegate.stopTimer();
			}
		}
		return returnValue;
	}

}
