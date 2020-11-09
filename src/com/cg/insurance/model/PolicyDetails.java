package com.cg.insurance.model;

import java.io.Serializable;

public class PolicyDetails implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7689147775037463738L;
	private Long policyNumber;
	private Integer questionId;
	private String answer;
	
	public PolicyDetails() {
		// TODO Auto-generated constructor stub
	}

	public PolicyDetails(Long policyNumber, Integer questionId, String answer) {
		super();
		this.policyNumber = policyNumber;
		this.questionId = questionId;
		this.answer = answer;
	}

	public Long getPolicyNumber() {
		return policyNumber;
	}

	public void setPolicyNumber(Long policyNumber) {
		this.policyNumber = policyNumber;
	}

	public Integer getQuestionId() {
		return questionId;
	}

	public void setQuestionId(Integer questionId) {
		this.questionId = questionId;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "PolicyDetails [policyNumber=" + policyNumber + ", questionId=" + questionId + ", answer=" + answer
				+ "]";
	}
	
	

}
