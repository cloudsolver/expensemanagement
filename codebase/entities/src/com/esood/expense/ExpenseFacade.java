/*
 * Created on Sep 11, 2005
 */
package com.esood.expense;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.apache.commons.beanutils.BeanUtils;
import org.hibernate.HibernateException;
import org.hibernate.Session;

import com.esood.AbstractFacade;
import com.esood.ContextDTO;
import com.esood.customer.Customer;

/**
 * Handles all processing for the expenses entry.
 * 
 * @author Rohit Sood
 */
public class ExpenseFacade extends AbstractFacade
{
	/**
	 * Creates a new expense the general information.
	 * Adds the expense to the customer.
	 * @param ucdto
	 *            UserContextDTO.
	 * @param dto
	 *            The DTO.
	 * @throws ExpenseException
	 *             The exception.
	 */
	public Integer addExpense(ContextDTO ucdto, ExpenseDTO dto) throws ExpenseException
	{
		log.info("Enter addExpense");
		if (dto == null)
		{
			throw new ExpenseException("The dto is null");
		} else if (dto.getUserContextDTO() == null)
		{
			throw new ExpenseException("Cannot save general information because the user context is missing");
		}
		log.debug(ucdto);
		Expense expense = new Expense();
		expense.setStatus(Expense.STATUS_PROCESSING);
		try
		{
			BeanUtils.copyProperties(expense, dto);
			expense.setApplicationType(ucdto.getApplicationType());
			expense.setApplicationVersion(ucdto.getApplicationVersion());
		} catch (IllegalAccessException e)
		{
			log.error(e);
		} catch (InvocationTargetException e)
		{
			log.error(e);
		}
		Session s = startTx();

		try
		{
			log.debug("Customer id :" + ucdto.getCustomerId());
			if (null == ucdto.getCustomerId())
			{
				throw new ExpenseException("CustomerId is null");
			}
			log.debug("Saving expense");
			Customer c = (Customer) s.load(Customer.class, ucdto.getCustomerId());
			expense.setCustomer(c);
			s.save(expense);
			commitTx();
		} catch (Exception e)
		{
			log.error(e);
			rollbackTx();
			throw new ExpenseException(e);
		}
		
		log.info("Exit addExpense");
		return expense.getId();
	}
	
	/**
	 * Add time.
	 * @param cdto
	 * @throws ExpenseException
	 */
	public void addTime(ContextDTO cdto, Long duration) throws ExpenseException
	{
		log.info("Enter addTime(ContextDTO cdto) :"+duration);
		try
		{
			Integer expenseId=cdto.getExpenseId();
			Session s=startTx();
			Expense expense=(Expense) s. get(Expense.class, expenseId);
			expense.addTaskDuration(duration);
			s.update(expense);
			commitTx();
		} catch (HibernateException e)
		{
			rollbackTx();
			log.error("Could not add time "+e.getMessage());
		}
		log.info("Exit addTime(ContextDTO cdto)");
	}
	
	/**
	 * Changes the status to submitted.
	 * 
	 * @param cdto
	 */
	public void submitExpense(ContextDTO cdto) throws ExpenseException
	{
		log.info("Enter submitExpense(ContextDTO cdto)");
		Integer expenseId=cdto.getExpenseId();
		if(expenseId==null)
		{
			throw new ExpenseException("Cannot submitExpense because Expense id is null");
		}
		Session s=startTx();
		Expense expense=(Expense)s.load(Expense.class, expenseId);
		expense.setStatus(Expense.STATUS_SUBMITTED);
		s.update(expense);
		commitTx();
		log.info("Exit submitExpense(ContextDTO cdto)");
	}
	
	/**
	 * This will change the status of the expense to approved.
	 * This method should be called once the survey has been submitted.
	 * @param cdto
	 */
	public void approveExpense(ContextDTO cdto) throws ExpenseException
	{
		log.info("Enter submitExpense(ContextDTO cdto)");
		Integer expenseId=cdto.getExpenseId();
		if(expenseId==null)
		{
			throw new ExpenseException("Cannot submitExpense because Expense id is null");
		}
		Session s=startTx();
		Expense expense=(Expense)s.load(Expense.class, expenseId);
		expense.setStatus(Expense.STATUS_APPROVED);
		s.update(expense);
		commitTx();
		log.info("Exit submitExpense(ContextDTO cdto)");
	}
	
	/**
	 * Load the expense, update the expense.
	 * 
	 * @param dto
	 */
	public void updateExpense(ContextDTO dto, ExpenseDTO expenseDTO) throws ExpenseException
	{
		log.info("Enter updateExpense");
		Integer expenseId = dto.getExpenseId();
		Session s = startTx();
		Expense expense = (Expense) s.load(Expense.class, expenseId);

		try
		{
			BeanUtils.copyProperties(expense, expenseDTO);
			expense.setApplicationType(dto.getApplicationType());
			expense.setApplicationVersion(dto.getApplicationVersion());
		} catch (IllegalAccessException e)
		{
			log.warn(" " + e.getMessage());
		} catch (InvocationTargetException e)
		{
			log.warn(" " + e.getMessage());
		}
		try
		{
			expense.setStatus(Expense.STATUS_PROCESSING);
			s.update(expense);
		} catch (HibernateException e)
		{
			log.warn("Problems were encountered updating expense " + e.getMessage());
			throw new ExpenseException(e);
		}
		commitTx();

		log.info("Enter updateExpense");
	}

	/**
	 * Updates the expense line item for the expense. Loads the line item and updates the object.
	 * 
	 * @param ucdto
	 *            The context
	 * @param dto
	 *            The most current DTO, will overwrite the existing one.
	 */
	public void updateExpenseLineItem(ContextDTO ucdto, ExpenseLineItemDTO dto) throws ExpenseException
	{
		log.info("Enter updateExpenseLineItem");
		
		Integer expenseLineItemId = dto.getId();
		String lineItemType=dto.getLineItemType();
		
		if (null == expenseLineItemId)
		{
			throw new ExpenseException("Cannot update expenseLineItemId it is null");
		}
		
		Session s = super.startTx();
		
		if(lineItemType.equals(ExpenseLineItemDTO.TYPE_EDU))
		{
			log.debug("Updating EducationExpenseLineItem "+expenseLineItemId);
			EducationExpenseLineItem eeli=(EducationExpenseLineItem)s.get(EducationExpenseLineItem.class,expenseLineItemId);
			s.update(eeli);//register entity for updates
			try
			{
				log.debug("Copying properties");
				BeanUtils.copyProperties(eeli, dto);
				log.debug("EducactionExpenseLineItem:\n" + eeli);
			} catch (IllegalAccessException e)
			{
				log.error(e);
			} catch (InvocationTargetException e)
			{
				log.error(e);
			}
		}
		else if(lineItemType.equals(ExpenseLineItemDTO.TYPE_STD))
		{
			log.debug("Updating StandardExpenseLineItem "+expenseLineItemId);
			StandardExpenseLineItem seli = new StandardExpenseLineItem();
			s.load(seli,expenseLineItemId);
			s.update(seli);//register entity for updates
			try
			{
				log.debug("Copying properties");
				BeanUtils.copyProperties(seli, dto);
				log.debug("StandardExpenseLineItem:\n" + seli);
			} catch (IllegalAccessException e)
			{
				log.error(e);
			} catch (InvocationTargetException e)
			{
				log.error(e);
			}
		}

		log.debug("ExpenseLineItemDTO:\n" + dto);
		


		commitTx();

		log.info("Exit updateExpenseLineItem");
	}

	/**
	 * Adds a new expense line item to the current expense.
	 * 
	 * @param ucdto
	 * @param dto
	 * @throws ExpenseException
	 */
	public Integer addExpenseLineItem(ContextDTO ucdto, ExpenseLineItemDTO dto) throws ExpenseException
	{
		log.info("Enter addExpenseLineItem "+dto);
		Integer expenseId = ucdto.getExpenseId();
		log.debug("Expense id is " + expenseId);
		Integer expenseLineItemId = null;
		Expense expense = new Expense();
		
		
		if (ExpenseLineItemDTO.TYPE_EDU.equals(dto.getLineItemType()))
		{
			log.debug("Creating an EducationExpenseLineItem");
			EducationExpenseLineItem eeli = new EducationExpenseLineItem();
			
			try
			{
				BeanUtils.copyProperties(eeli, dto);
			} catch (IllegalAccessException e)
			{
				log.warn("Could not copy properties into EducationExpenseLineItem " + e.getMessage());
			} catch (InvocationTargetException e)
			{
				log.warn("Could not copy properties into EducationExpenseLineItem " + e.getMessage());
			}
			try
			{
				Session s = startTx();
				s.load(expense, expenseId);
				expense.addEducationExpenseLineItem(eeli);
				s.save(eeli);
				s.save(expense);

				commitTx();
				expenseLineItemId = eeli.getId();
				log.debug("commited " + eeli);
			} catch (Exception e)
			{
				log.error(e);
				rollbackTx();
			}

		} else if (ExpenseLineItemDTO.TYPE_STD.equals(dto.getLineItemType()))
		{
			log.debug("Creating a StandardExpenseLineItem");
			StandardExpenseLineItem eli = new StandardExpenseLineItem();
			try
			{
				log.debug("Copying properties to line item");
				BeanUtils.copyProperties(eli, dto);
				log.debug("Copied all properties, line item is :" + eli);
			} catch (IllegalAccessException e)
			{
				log.error(e);
				throw new ExpenseException(e);
			} catch (InvocationTargetException e)
			{
				log.error(e);
				throw new ExpenseException(e);
			}
			Session s = startTx();
			try
			{

				s.load(expense, expenseId);
				expense.addStandardExpenseLineItem(eli);
				eli.setExpense(expense);
				s.save(eli);
				s.save(expense);

				commitTx();
				expenseLineItemId = eli.getId();
				log.debug("commited " + eli);
			} catch (Exception e)
			{
				log.error(e);
				rollbackTx();
			}
		}

		log.info("Exit addExpenseLineItem");
		return expenseLineItemId;
	}

	/**
	 * Load expense line item from the DB and if it exists, then load the DTO
	 * 
	 * @param id
	 * @return CashExpenseDTO the DTO
	 */
	public ExpenseLineItemDTO getExpenseLineItemDTO(ContextDTO ucdto, Integer id)
	{
		log.info("Enter getExpenseLineItemDTO");
		ExpenseLineItemDTO dto = new ExpenseLineItemDTO();

		Session s = startTx();
		
		log.debug("loading...");
		AbstractExpenseLineItem superClass=(AbstractExpenseLineItem)s.get(AbstractExpenseLineItem.class, id);
		log.debug("loaded: "+superClass.getExpenseType()+" type: "+superClass.getClass().getName());
		
		
		if (superClass.getExpenseType().equals(EducationExpenseLineItem.TYPE))
		{
			log.debug("Object is an education line item...casting");
			EducationExpenseLineItem eeli = (EducationExpenseLineItem) superClass;
			log.debug("casting complete.");
			try
			{
				BeanUtils.copyProperties(dto, eeli);
				log.debug("ExpenseLineItemDTO:" + dto);
			} catch (IllegalAccessException e)
			{
				log.error(e);
			} catch (InvocationTargetException e)
			{
				log.error(e);
			}
		} else if (superClass.getExpenseType().equals(StandardExpenseLineItem.TYPE))
		{
			log.debug("Object is a standard expense line item");
			StandardExpenseLineItem seli=(StandardExpenseLineItem) superClass;
			try
			{
				BeanUtils.copyProperties(dto, seli);
				log.debug("ExpenseLineItemDTO:" + dto);
			} catch (IllegalAccessException e)
			{
				log.error(e);
			} catch (InvocationTargetException e)
			{
				log.error(e);
			}

		}

		commitTx();
		log.debug("Returning ExpenseLineItemDTO:"+dto);
		log.info("Exit getExpenseDetailDTO ");
		return dto;
	}

	/**
	 * Returns the list of  expense line items for the given expense id.
	 * Both education expense line items and standard expense line items will be loaded.
	 * This is a read operation on previously entered data for the expense submission.
	 * 
	 * @param ucdto
	 * @return list of dtos
	 */
	public List getExpenseLineItems(ContextDTO ucdto)
	{
		log.info("Enter getExpenseLineItems(ContextDTO)");
		List dtos = new ArrayList();
		Integer expenseId = ucdto.getExpenseId();
		Expense expense = new Expense();
		expense.setId(expenseId);
		Session s = startTx();
		s.load(expense, expenseId);
		Set expenseLineItems = expense.getExpenseLineItems();

		// convert to DTOs
		Iterator i = expenseLineItems.iterator();
		while (i.hasNext())
		{
			AbstractExpenseLineItem aeli = (AbstractExpenseLineItem) i.next();
			ExpenseLineItemDTO dto = new ExpenseLineItemDTO();
			log.debug("AbstractExpenseLineItem is of type "+aeli.getExpenseType()+" and of class:"+aeli.getClass().getName());
			if(aeli.getExpenseType().equals(StandardExpenseLineItem.TYPE))
			{
				StandardExpenseLineItem seli=(StandardExpenseLineItem)aeli;
				dto.setLineItemType(ExpenseLineItemDTO.TYPE_STD);
				try
				{
					BeanUtils.copyProperties(dto, seli);
				} catch (IllegalAccessException e)
				{
					log.error(e);
				} catch (InvocationTargetException e)
				{
					log.error(e);
				}
			}
			else if(aeli.getExpenseType().equals(EducationExpenseLineItem.TYPE))
			{
				EducationExpenseLineItem eeli=(EducationExpenseLineItem) aeli;
				dto.setLineItemType(ExpenseLineItemDTO.TYPE_EDU);
				try
				{
					BeanUtils.copyProperties(dto, eeli);
					log.debug("Copied from EducationExpensesLineItem "+eeli+" copied dto is = "+dto );
				} catch (IllegalAccessException e)
				{
					log.error(e);
				} catch (InvocationTargetException e)
				{
					log.error(e);
				}
			}
			
			dtos.add(dto);
		}//end while
		commitTx();

		Collections.sort(dtos);
		log.info("Exit getExpenseLineItems(ContextDTO)");
		return dtos;
	}

	/**
	 * Returns a list of expenseDTOs that correspond to the customer.
	 * 
	 * @param customerId
	 * @return
	 * @throws ExpenseException
	 */
	public List getExpenses(Integer customerId) throws ExpenseException
	{
		log.info("Enter getExpenses ");

		List dtos = new ArrayList();
		if (customerId == null)
		{
			log.warn("Customer is null");
			return dtos;
		}
		Customer customer = new Customer();
		Session s = startTx();
		log.debug("Loading customer with id=" + customerId);
		s.load(customer, customerId);
		log.debug("Customer loaded. Loading expenses");
		Set expenses = customer.getExpenses();
		log.debug("Got the set of expenses. getting an interator");
		Iterator i = expenses.iterator();
		log.debug("Expenses is " + expenses);
		while (i.hasNext())
		{
			Expense e = (Expense) i.next();
			Set lineItems = e.getExpenseLineItems();
			Iterator lineItemsIterator = lineItems.iterator();
			double totalExpense = 0.0;

			while (lineItemsIterator.hasNext())
			{
				StandardExpenseLineItem lineItem = (StandardExpenseLineItem) lineItemsIterator.next();
				
				if (lineItem != null)// if lineItem is valid
				{
					Double transactionAmount = lineItem.getTransactionAmount();
					if (transactionAmount != null)// if amount is valid
					{
						double lineItemAmount = transactionAmount.doubleValue();
						totalExpense = totalExpense + lineItemAmount;
					}
				}
			}

			ExpenseDTO dto = new ExpenseDTO();
			//BeanUtils.copyProperties(dto,e);
			
			// manually populate the dto with the entitiy.
			dto.setId(e.getId());
			dto.setApprover(e.getApprover());
			dto.setCostCenter(e.getCostCenter());
			dto.setName(e.getName());
			dto.setPurpose(e.getPurpose());
			dto.setExpenseAmount(new Double(totalExpense));
			dto.setDate(e.getDate());
			dto.setStatus(e.getStatus());
			dto.setApplicationType(e.getApplicationType());
			dto.setApplicationVersion(e.getApplicationVersion());
			// add this dto to the list of dtos
			dtos.add(dto);

		}
		commitTx();
		
		// sort the list of dtos by id.
		Collections.sort(dtos);
		log.info("Exit getExpenses");

		return dtos;
	}

	/**
	 * Deletes a line item
	 * 
	 * @param contextDTO
	 * @param expenseLineItemID
	 * @return
	 * @throws ExpenseException
	 */
	public Boolean deleteExpenseLineItem(ContextDTO contextDTO, Integer expenseLineItemID) throws ExpenseException
	{
		Boolean success = Boolean.FALSE;

		try
		{
			Session s = startTx();
			
			Object obj= s.load(AbstractExpenseLineItem.class, expenseLineItemID);
			s.delete(obj);
			commitTx();
			success = Boolean.TRUE;
		} catch (HibernateException e)
		{
			log.error("Could not delete line item " + e.getMessage());
			throw new ExpenseException("Expense Line Item could not be deleted");
		}

		return success;
	}

	/**
	 * Deletes an expense. And all associated line items.
	 * 
	 * @param contextDTO
	 * @param expenseID
	 * @return
	 * @throws ExpenseException
	 */
	public Boolean deleteExpense(ContextDTO contextDTO) throws ExpenseException
	{
		log.info("Enter deleteExpense");
		Boolean success = Boolean.FALSE;
		Integer expenseId = contextDTO.getExpenseId();
		log.debug("expenseId=" + expenseId);
		try
		{
			Session s = startTx();
			Expense expense = (Expense) s.load(Expense.class, expenseId);
			s.delete(expense);
			commitTx();
			success = Boolean.TRUE;
		} catch (HibernateException e)
		{
			log.error("Could not delete line item " + e.getMessage());
			throw new ExpenseException("Expense Line Item could not be deleted");
		}
		log.info("Exit deleteExpense");
		return success;
	}

	/**
	 * Returns the general expense information.
	 * 
	 * @param dto
	 * @return
	 * @throws ExpenseException
	 */
	public ExpenseDTO getExpense(ContextDTO dto) throws ExpenseException
	{
		log.info("Enter getExpense");
		ExpenseDTO expenseDTO = new ExpenseDTO();
		Integer expenseId = dto.getExpenseId();
		if (expenseId == null)
		{
			throw new ExpenseException("Cannot retrieve expense for expenseId=" + expenseId);
		}
		Session s = startTx();

		Expense expense = (Expense) s.load(Expense.class, expenseId);
		//Adding the expense
		Set lineItems=expense.getExpenseLineItems();
		double total=0.00;
		if(lineItems.size()>0)
		{
			Iterator itemIterator=lineItems.iterator();
			
			while(itemIterator.hasNext())
			{
				AbstractExpenseLineItem eli=(AbstractExpenseLineItem) itemIterator.next();
				Double txAmt=eli.getTransactionAmount();
				if(txAmt!=null)
				{
					total+=txAmt.doubleValue();
				}
			}
		}
		try
		{
			BeanUtils.copyProperties(expenseDTO, expense);
			expenseDTO.setExpenseAmount(new Double(total));
		} catch (IllegalAccessException e)
		{
			log.warn("Could not copy properties " + e.getMessage());
		} catch (InvocationTargetException e)
		{
			log.warn("Could not copy properties " + e.getMessage());
		}
		commitTx();
		log.info("Exit getExpense");
		return expenseDTO;
	}

}
