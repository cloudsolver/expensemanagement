/*
 * Created on Jun 17, 2005
 */
package com.esood.refdata;

import com.esood.AbstractEntity;

/**
 * 
 * @author Rohit Sood
 */
public class Answer extends AbstractEntity {
	private Integer questionId;
	private String answerText;
	private String answerCode; 
	/**
	 * @return Returns the answerCode.
	 */
	public String getAnswerCode() {
		return answerCode;
	}
	/**
	 * @param answerCode The answerCode to set.
	 */
	public void setAnswerCode(String answerCode) {
		this.answerCode = answerCode;
	}
	/**
	 * @return Returns the answerText.
	 */
	public String getAnswerText() {
		return answerText;
	}
	/**
	 * @param answerText The answerText to set.
	 */
	public void setAnswerText(String answerText) {
		this.answerText = answerText;
	}
	/**
	 * @return Returns the questionId.
	 */
	public Integer getQuestionId() {
		return questionId;
	}
	/**
	 * @param questionId The questionId to set.
	 */
	public void setQuestionId(Integer questionId) {
		this.questionId = questionId;
	}
}
