package com.esood;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 * The super class of all facades. This will also manage the transactions.
 * 
 * @author Rohit Sood
 */
public abstract class AbstractFacade
{
	protected Log log = LogFactory.getLog(this.getClass());

	Transaction tx;

	/**
	 * Start the transaction
	 * @return
	 */
	protected Session startTx()
	{
		Session s = null;
		s = HibernateUtil.currentSession();
		try
		{
			tx = null;

			tx = s.beginTransaction();

		} catch (HibernateException e)
		{
			log.error("Could not startTx " + e.getMessage());
			throw e;
		}

		return s;
	}
	/**
	 * Commit the transaction 
	 *
	 */
	protected void commitTx()
	{
		Session s = null;
		try
		{
			s = HibernateUtil.currentSession();
			if (tx != null)
			{
				tx.commit();
			}
			if (s != null && s.isOpen())
			{
				s.flush();
				s.close();
			}
		} catch (HibernateException he)
		{
			log.fatal("Could not commit " + he);
			throw he;
		}
	}

	/**
	 * Rollback the transaction
	 *
	 */
	protected void rollbackTx()
	{
		Session s = null;
		try
		{
			s = HibernateUtil.currentSession();
			if (tx != null)
			{
				tx.rollback();
			}
			if (s != null && s.isOpen())
			{
				s.close();
			}
		} catch (HibernateException e)
		{
			log.fatal("Could not rollbackTx " + e.getMessage());
			throw e;
		}
	}

}
