package com.esood.customer;

import java.lang.reflect.InvocationTargetException;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Expression;

import com.esood.AbstractFacade;
import com.esood.HibernateUtil;
import com.esood.ContextDTO;

/**
 * Customer Facade will control everything that is related to the customer.
 * 
 * @author Rohit Sood
 */
public class CustomerFacade extends AbstractFacade
{
	Log log = LogFactory.getLog(CustomerFacade.class);

	public Integer saveCustomer(ContextDTO cdto, CustomerDTO dto) throws CustomerException
	{
		log.info("Enter saveCustomer");
		Customer customer = new Customer();
		
		try
		{
			BeanUtils.copyProperties(customer,dto);
		} catch (IllegalAccessException e1)
		{
			log.warn("Copying failed "+e1.getMessage());
		} catch (InvocationTargetException e1)
		{
			log.warn("Copying failed "+e1.getMessage());
		}

		try
		{
			Session s = startTx();
			
			//load user
			User user=findUserByUserName(s,cdto.getUserName());
			s.save(customer);
			user.setCustomer(customer);
			s.save(user);
			commitTx();
		} catch (HibernateException e)
		{
			log.error(e);
			rollbackTx();
			throw new CustomerException(e.getMessage());
		}
		log.info("Exit Customer "+customer.getId());
		return customer.getId();
	}
/**
 * Registers a user and returns the userid
 * @param userName
 * @param password1
 * @param password2
 * @return
 * @throws CustomerException
 */
	public Integer registerUser(String userName, String password1,
			String password2) throws CustomerException
	{
		Integer returnValue = null;
		if (null != password1 && !password1.equals(password2))
		{
			throw new CustomerException("The passwords did not match.");
		}

		User user = new User();
		user.setUserName(userName);
		user.setPassword(password1);

		try
		{
			Session s = HibernateUtil.currentSession();
			s.save(user);
			returnValue = user.getId();
			commitTx();
		} catch (HibernateException e)
		{
			log.error(e);
			rollbackTx();
			throw new CustomerException(e);
		}

		return returnValue;
	}

	public ContextDTO login(final String userName, final String password)
			throws CustomerException
	{
		log.info("Enter login");
		ContextDTO ucdto=new ContextDTO();

		Session s = startTx();
		User user=(User) s.createCriteria(User.class).add(Expression.eq("userName", userName)).uniqueResult();
		if (user != null && user.getPassword().equals(password))
			{
				log.debug("User was validated");
				ucdto.setUserName(user.getUserName());
				
				Customer customer=user.getCustomer();
				//what if the customer is null ?
				if(customer!=null){
					Integer customerId=customer.getId();				
					ucdto.setCustomerId(customerId);
				}else
				{
					log.warn("Customer is null for user "+user);
				}
				
			} else
			{
				log.debug("User was not validated.");
				
			}
			
		commitTx();

		log.info("Exit login");
		return ucdto;
	}
	
	/**
	 * 
	 * @param cdto
	 * @return
	 */
	public CustomerDTO getCustomerDTO(ContextDTO cdto)
	{
		CustomerDTO customerDTO=null;
		String userName=cdto.getUserName();
		Session s=startTx();
		Customer customer=findCustomerByUserName(s,userName);
		if(customer!=null)
		{
			try
			{
				BeanUtils.copyProperties(customerDTO,customer);
			} catch (IllegalAccessException e)
			{
				log.warn("Could not copy to customerDTO "+e.getMessage());
			} catch (InvocationTargetException e)
			{
				log.warn("Could not copy to customerDTO "+e.getMessage());
			}
		}else
		{
			log.debug("Customer is null");
		}
		commitTx();
		return customerDTO;
	}
	/**
	 * 
	 * @param s
	 * @param userName
	 * @return
	 */
	User findUserByUserName(Session s, String userName)
	{
		log.info("Enter findUserByUserName");
		User user = (User) s.createCriteria(User.class).add(
				Expression.eq("userName", userName)).uniqueResult();
		log.info("Exit findUserByUserName");
		return user;
	}

	/**
	 * 
	 * @param s
	 * @param userName
	 * @return
	 */
	Customer findCustomerByUserName(Session s, String userName)
	{
		log.info("Enter findCustomerByUserName");
		User u = findUserByUserName(s, userName);
		Customer c = u.getCustomer();
		log.info("Exit findCustomerByUserName");
		return c;
	}
}
