/*
 * Created on Jun 17, 2005
 */
package com.esood.refdata;

import java.util.List;
import java.util.logging.Logger;

import org.hibernate.Session;

import com.esood.AbstractFacade;

/**
 * 
 * @author Rohit Sood
 */

public class ReferenceDataFacade extends AbstractFacade{
	
	transient Logger log=Logger.getLogger(ReferenceDataFacade.class.getName());
	public List getPossibleAnswers(Integer questionId){
		log.info("Enter getPossibleAnswers");
		Session s=startTx();
		Question q=new Question();
		s.load(q,new Integer(1));
		List answers=q.getPossibleAnswers();
		log.info("Exit getPossibleAnswers");
		return answers;
	}
}
