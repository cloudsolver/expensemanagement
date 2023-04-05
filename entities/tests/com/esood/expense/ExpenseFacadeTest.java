/*
 * Created on Oct 8, 2005
 */
package com.esood.expense;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.esood.ContextDTO;
import com.esood.customer.CustomerDTO;
import com.esood.customer.CustomerException;
import com.esood.customer.CustomerFacade;

import junit.framework.TestCase;

public class ExpenseFacadeTest extends TestCase
{
	Log log=LogFactory.getLog(NAME);
	private static String NAME = ExpenseFacadeTest.class.getName();
	private int caseId=-1;
	private static final int SAVE_GENERAL_INFORMATION=0;
	private ContextDTO contextDTO;
/**
 * Expense Facade Test
 * @param name
 */
	public ExpenseFacadeTest(String name)
	{
		super(name);
	}
	/**
	 * This is not called automatically.
	 * Sets up this test case
	 */
	protected void setUp()
	{
		switch(caseId){
		case SAVE_GENERAL_INFORMATION:
			log.debug("Setting up SAVE_GENERAL_INFORMATION");
			CustomerFacade cf=new CustomerFacade();
			Integer userId=null;
			try
				{
					userId=cf.registerUser("rohitsood","pwd","pwd");
				} catch (CustomerException e)
				{
					log.error("Could not register user",e);;
				}
			CustomerDTO dto=new CustomerDTO();
			contextDTO=new ContextDTO();
			dto.setUserId(userId);
			dto.setUserContextDTO(contextDTO);
			try
				{
					Integer customerId=cf.saveCustomer(null,dto);
					log.debug("Customer id is"+customerId);
					contextDTO.setCustomerId(customerId);
				} catch (CustomerException e)
				{
					log.error("Could not save demographics",e);
				}
		break;
		default:
			
			break;
		}
	}
	/**
	 * Tears down this test case
	 */
	protected void tearDown()
	{
		switch(caseId){
		case SAVE_GENERAL_INFORMATION:
			
		break;
		default:
			
			break;
		}
	}

	public void testSaveGeneralInformation()
	{
		caseId=SAVE_GENERAL_INFORMATION;
		ExpenseFacade facade = new ExpenseFacade();		
		ExpenseDTO egidto = new ExpenseDTO();
		egidto.setName("Expense name");
		egidto.setApprover("The Approver");
		egidto.setCostCenter("12345");
		egidto.setPurpose("The purpose");
		egidto.setUserContextDTO(contextDTO);
		try
		{
			facade.addExpense(contextDTO, egidto);
		} catch (ExpenseException e)
		{
			log.error(e);
			fail(e.getMessage());
		}
	}
}
