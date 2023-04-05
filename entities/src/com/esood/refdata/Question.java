/*
 * Created on Jun 17, 2005
 */
package com.esood.refdata;

import java.util.ArrayList;
import java.util.List;

import com.esood.AbstractEntity;

/**
 * 
 * @author Rohit Sood
 */
public class Question extends AbstractEntity {
	private Integer questionnaireId;
	private String questionText;
	private String questionCode;
	private List possibleAnswers=new ArrayList();
	
	/**
	 * @return Returns the questionnaireId.
	 */
	public Integer getQuestionnaireId() {
		return questionnaireId;
	}
	/**
	 * @param questionnaireId The questionnaireId to set.
	 */
	public void setQuestionnaireId(Integer questionnaireId) {
		this.questionnaireId = questionnaireId;
	}
	/**
	 * @return Returns the questionText.
	 */
	public String getQuestionText() {
		return questionText;
	}
	/**
	 * @param questionText The questionText to set.
	 */
	public void setQuestionText(String questionText) {
		this.questionText = questionText;
	}
	/**
	 * @return Returns the possibleAnswers.
	 */
	public List getPossibleAnswers() {
		return possibleAnswers;
	}
	/**
	 * @param possibleAnswers The possibleAnswers to set.
	 */
	public void setPossibleAnswers(List possibleAnswers) {
		this.possibleAnswers = possibleAnswers;
	}
	/**
	 * @return Returns the questionCode.
	 */
	public String getQuestionCode() {
		return questionCode;
	}
	/**
	 * @param questionCode The questionCode to set.
	 */
	public void setQuestionCode(String questionCode) {
		this.questionCode = questionCode;
	}
}
