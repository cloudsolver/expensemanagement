/*
 * Created on May 22, 2005
 */
package com.esood.ui;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.log4j.NDC;

/**
 * This filter will intercept all *.do & .jsp requests and then forward it on to the ActionServlet in another thread.
 * 
 * @author Rohit Sood
 */
public final class ProcessFilter implements Filter
{
	private static Log log = LogFactory.getLog(ProcessFilter.class);

	/**
	 * 0.001 seconds
	 */
	static final long DELAY_SHORT = 1;

	/**
	 * 2.9 seconds 
	 */
	static final long DELAY_MEDIUM = 2900;
	
	/**
	 * 6.9 seconds
	 */
	static final long DELAY_LONG = 6900;

	/**
	 * Initialization
	 * 
	 * @param FilterConfig
	 */
	public void init(FilterConfig config) throws ServletException
	{
		// TODO Auto-generated method stub
	}

	/**
	 * This filter simulates a delay for the form submissions
	 */
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException
	{
		log.info("Enter doFilter");
		pushLogContext((HttpServletRequest) req);
		try
		{

			Thread.sleep(getDelay((HttpServletRequest) req));
			
		} catch (InterruptedException e)
		{
			log.error(e);
		} catch (Throwable t)
		{
			log.error(t);
		}finally
		{
			chain.doFilter(req, resp);
		}

		log.info("Exit doFilter");
	}
/**
 * Push the log context with the user name.
 * @param req
 */
	private void pushLogContext(HttpServletRequest req)
	{
		HttpSession session = req.getSession(false);
		if (null == session)
		{
			return;
		}
		Context ctx = (Context) session.getAttribute(Context.NAME);

		if (null != ctx)// push to ndc if context is not null
		{
			NDC.clear();
			NDC.push(ctx.getUserName());

		} else
		{
			NDC.clear();
			NDC.push("anonymous");
		}

	}

	public void destroy()
	{
		NDC.clear();
	}

	/**
	 * Check the URL for delay=none, delay=medium, delay=long. If that exists push the delay value onto the session and
	 * apply it to every filter chain.
	 * 
	 * @param req
	 * @return
	 */
	private long getDelay(HttpServletRequest req)
	{
		long returnValue = ProcessFilter.DELAY_SHORT;
		HttpSession session = req.getSession(false);
		Context ctx = null;

		if (session != null)
		{
			ctx = (Context) session.getAttribute(Context.NAME);
		}

		if (session != null && ctx != null)
		{
			int delay = ctx.getDelay();
			// decide
			if (delay == Context.NO_DELAY)
			{
				returnValue = ProcessFilter.DELAY_SHORT;
			} 
			else if (delay == Context.MEDIUM_DELAY)
			{
				returnValue = ProcessFilter.DELAY_MEDIUM;
			} 
			else if (delay == Context.LONG_DELAY)
			{
				returnValue = ProcessFilter.DELAY_LONG;
			}
		}
		return returnValue;

	}//()

}
