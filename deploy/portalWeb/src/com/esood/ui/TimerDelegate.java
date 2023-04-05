/*
 * Created on Jul 31, 2005
 */
package com.esood.ui;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.esood.ContextDTO;
import com.esood.expense.ExpenseException;
import com.esood.expense.ExpenseFacade;
/**
 * Interacts with the context to ensure that the time is kept.
 * 
 * @author Rohit Sood
 */
public class TimerDelegate extends Object{
	Log log=LogFactory.getLog(TimerDelegate.class);
	
	private Context ctx;
	private HttpSession session;
	
	public TimerDelegate(HttpServletRequest req)
	{
		session=req.getSession();
		ctx=(Context) session.getAttribute(Context.NAME);
	}
	
	public TimerDelegate(HttpSession session)
	{
		this.session=session;
		ctx=(Context) session.getAttribute(Context.NAME);
	}
	
	private void saveContext()
	{
		session.setAttribute(Context.NAME,ctx);
	}
	
	public void startTimer()
	{
		ctx.setStartTime(System.currentTimeMillis());
		saveContext();
	}
	
	private void resetTime()
	{
		ctx.setStartTime(0L);
		saveContext();
	}
	
	/**
	 * Returns the number of seconds elapsed.
	 * The timer will reset if called without an expenseId
	 * @return
	 */
	public long stopTimer()
	{
		log.info("Enter stopTimer()");
		long startTime=0L;
		long returnValue=0L;
		long now=System.currentTimeMillis();
	
		if(ctx!=null && null!=ctx.getExpenseId())
		{
			startTime=ctx.getStartTime();
			if(startTime>0)
				{
				returnValue=(now-startTime)/1000;
				}
			ExpenseFacade facade=new ExpenseFacade();
			ContextDTO dto=ctx.getDTO();
			Long duration=new Long(returnValue);
			log.debug("Stopping timer at "+returnValue+" sec(s)");
			try
			{
				if(returnValue>0){
					facade.addTime(dto,duration);
				}
				
			} catch (ExpenseException e)
			{
				log.error("Failure to measure."+e.getMessage());
			}finally
			{
			resetTime();
			saveContext();
			finalize();
			}
		}
		log.info("Exit stopTimer()");
		return returnValue;
		
	}
	
	protected void finalize()
	{
		log.debug("Enter protected void finalize()");
		ctx=null;
		session=null;
		log.debug("Exit protected void finalize()");
	}
}
