/*
 * Created on Aug 21, 2005
 */
package com.esood.survey;

import java.lang.reflect.InvocationTargetException;

import org.apache.commons.beanutils.BeanUtils;
import org.hibernate.Session;
import org.hibernate.criterion.Expression;

import com.esood.AbstractFacade;
import com.esood.ContextDTO;
import com.esood.expense.Expense;

/**
 * SurveyFacade Reads and saves the survey.
 * 
 * @author Rohit Sood
 */
public class SurveyFacade extends AbstractFacade
{
	/**
	 * This method will save the DTO
	 * 
	 * @param dto
	 */
	public void saveSurvey(ContextDTO cdto, SurveyDTO dto)
	{
		log.info("Enter SaveSurvey");
		Integer expenseId = cdto.getExpenseId();

		Session s = startTx();
		// query to see if the survey exists
		Expense e = (Expense) s.get(Expense.class, expenseId);
		// if not create a new survey and save it

		Survey survey = (Survey) s.createCriteria(Survey.class).add(Expression.eq("expense", e)).uniqueResult();
		boolean wasNull = (null == survey);
		Integer id = null;
		if (wasNull)
		{
			survey = new Survey();
		}
		survey.setExpense(e);
		try
		{
			if (!wasNull)
			{
				id = survey.getId();
				log.debug("id=" + id);
			}
			BeanUtils.copyProperties(survey, dto);
			log.debug("after copying id=" + survey.getId());

		} catch (IllegalAccessException e1)
		{
			log.warn("Could not copy. " + e1.getMessage());
		} catch (InvocationTargetException e1)
		{
			log.warn("Could not copy. " + e1.getMessage());
		}

		if (wasNull)
		{

			s.save(survey);
		} else
		{
			survey.setId(id);
			s.update(survey);
		}

		commitTx();
		log.info("Exit Save Survey");
	}

	/**
	 * SurveyDTO this is the DTO.
	 * 
	 * @param cdto
	 * @return SurveyDTO
	 */
	public SurveyDTO readSurvey(ContextDTO cdto)
	{
		log.info("Enter readSurvey");
		Integer expenseId = cdto.getExpenseId();
		SurveyDTO dto = new SurveyDTO();
		Session s = startTx();
		// query to see if the survey exists
		Expense e = (Expense) s.get(Expense.class, expenseId);
		// if not create a new survey and save it

		Survey survey = (Survey) s.createCriteria(Survey.class).add(Expression.eq("expense", e)).uniqueResult();

		try
		{
			BeanUtils.copyProperties(dto, survey);
		} catch (IllegalAccessException e1)
		{
			log.warn("Could not copy to the DTO " + e1.getMessage());
		} catch (InvocationTargetException e1)
		{
			log.warn("Could not copy to the DTO " + e1.getMessage());
		}
		log.info("Exit readSurvey");
		return dto;
	}

	/**
	 * Returns the answer for a given question that was asked.
	 * 
	 * @param cdto
	 * @param questionId
	 *            0-7
	 * @return
	 */
	public String getAnswer(ContextDTO cdto, int questionId)
	{
		String returnValue = null;
		log.info("Enter getAnswer");
		Integer expenseId = cdto.getExpenseId();
		Session s = startTx();
		// query to see if the survey exists
		Expense e = (Expense) s.get(Expense.class, expenseId);
		// if not create a new survey and save it

		Survey survey = (Survey) s.createCriteria(Survey.class).add(Expression.eq("expense", e)).uniqueResult();
		commitTx();
		if (survey != null)
		{
			switch (questionId)
			{
				case 0:
				{
					returnValue=survey.getResponse01();
					break;
				}
				case 1:
				{
					returnValue=survey.getResponse02();
					break;
				}
				case 2:
				{
					returnValue=survey.getResponse03();
					break;
				}
				case 3:
				{
					returnValue=survey.getResponse04();
					break;
				}
				case 4:
				{
					returnValue=survey.getResponse05();
					break;
				}
				case 5:
				{
					returnValue=survey.getResponse06();
					break;
				}
				case 6:
				{
					returnValue=survey.getResponse07();
					break;
				}
				case 7:
				{
					returnValue=survey.getResponse08();
					break;
				}
			}//end switch
		}//endif
		return returnValue;
	}

	/**
	 * Saves a single answer into the survey.
	 * 
	 * @param cdto
	 */
	public void saveAnswer(ContextDTO cdto, int questionId, String answer)
	{
		log.info("Enter saveAnswer");
		if(answer==null)
		{
			throw new NullPointerException("Cannot save answer because it is null!");
		}
		Integer expenseId = cdto.getExpenseId();
		Session s = startTx();
		// query to see if the survey exists
		Expense e = (Expense) s.get(Expense.class, expenseId);
		// if not create a new survey and save it

		Survey survey = (Survey) s.createCriteria(Survey.class).add(Expression.eq("expense", e)).uniqueResult();

		boolean surveyWasNull = (null == survey);

		if (surveyWasNull)
		{
			survey = new Survey();
		}
		survey.setExpense(e);
		switch(questionId)
		{
		case 0:
		{
			survey.setResponse01(answer);
			break;
		}
		case 1:
		{
			survey.setResponse02(answer);
			break;
		}
		case 2:
		{
			survey.setResponse03(answer);
			break;
		}
		case 3:
		{
			survey.setResponse04(answer);
			break;
		}
		case 4:
		{
			survey.setResponse05(answer);
			break;
		}
		case 5:
		{
			survey.setResponse06(answer);
			break;
		}
		case 6:
		{
			survey.setResponse07(answer);
			break;
		}
		case 7:
		{
			survey.setResponse08(answer);
			break;
		}
		}
		if (surveyWasNull)
		{
			s.save(survey);
		} else
		{
			s.update(survey);
		}
		commitTx();
		log.info("Exit saveAnswer");

	}
}
