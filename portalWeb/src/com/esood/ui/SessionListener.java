/*
 * Created on Nov 15, 2005
 */
package com.esood.ui;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Session Listener
 * 
 * @author Rohit Sood
 */
public class SessionListener implements HttpSessionListener
{
	Log log=LogFactory.getLog(SessionListener.class);
	/* (non-Javadoc)
	 * @see javax.servlet.http.HttpSessionListener#sessionCreated(javax.servlet.http.HttpSessionEvent)
	 */
	public void sessionCreated(HttpSessionEvent arg0)
	{
		log.info("Enter public void sessionCreated(HttpSessionEvent arg0)");
		
		
		log.info("Exit public void sessionCreated(HttpSessionEvent arg0)");
	}

	/* (non-Javadoc)
	 * @see javax.servlet.http.HttpSessionListener#sessionDestroyed(javax.servlet.http.HttpSessionEvent)
	 */
	public void sessionDestroyed(HttpSessionEvent arg0)
	{
		log.info("Enter public void sessionDestroyed(HttpSessionEvent arg0)");
		TimerDelegate delegate=new TimerDelegate(arg0.getSession());
		delegate.stopTimer();
		log.info("Exit public void sessionDestroyed(HttpSessionEvent arg0)");
		
	}


}
