/*
 * Created on Oct 8, 2005
 */
package com.esood.customer;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Session;
import org.hibernate.criterion.Expression;

public class CustomerHelper
{
	static Log log = LogFactory.getLog(CustomerHelper.class);

	public User findUserByUserName(Session s, String userName)
	{
		log.info("Enter findUserByUserName");
		User user = (User) s.createCriteria(User.class).add(
				Expression.eq("userName", userName)).uniqueResult();
		log.info("Exit findUserByUserName");
		return user;
	}

	public Customer findCustomerByUserName(Session s, String userName)
	{
		log.info("Enter findCustomerByUserName");
		User u = findUserByUserName(s, userName);
		Customer c = u.getCustomer();
		log.info("Exit findCustomerByUserName");
		return c;
	}
}
