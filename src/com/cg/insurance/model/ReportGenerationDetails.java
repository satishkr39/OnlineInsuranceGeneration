package com.cg.insurance.model;

import java.io.Serializable;

public class ReportGenerationDetails implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7938807269845941967L;

	private String insuredName;
	private String insuredStreet;
	private String insuredState;
	private String insuredCity;
	private Integer insuredZip;

	private String userName;
	private Long accountNumber;
	private String businessSegment;
	private Long policyNumber;
	private Integer policyPremium;

	/*private Integer questionId;*/
	private String answer;

	private String quesDescription;

	public ReportGenerationDetails() {

	}

	public ReportGenerationDetails(String insuredName, String insuredStreet, String insuredState, String insuredCity,
			Integer insuredZip, String userName, Long accountNumber, String businessSegment, Long policyNumber,
			Integer policyPremium, String answer, String quesDescription) {
		super();
		this.insuredName = insuredName;
		this.insuredStreet = insuredStreet;
		this.insuredState = insuredState;
		this.insuredCity = insuredCity;
		this.insuredZip = insuredZip;
		this.userName = userName;
		this.accountNumber = accountNumber;
		this.businessSegment = businessSegment;
		this.policyNumber = policyNumber;
		this.policyPremium = policyPremium;
		this.answer = answer;
		this.quesDescription = quesDescription;
	}

	public ReportGenerationDetails(Long accountNumber,String insuredName, String insuredStreet, String insuredState, String insuredCity,
			Integer insuredZip, String userName, String businessSegment, Long policyNumber, Integer policyPremium,
			String quesDescription, String answer) {
		super();
		this.accountNumber=accountNumber;
		this.insuredName = insuredName;
		this.insuredStreet = insuredStreet;
		this.insuredState = insuredState;
		this.insuredCity = insuredCity;
		this.insuredZip = insuredZip;
		this.userName = userName;

		this.businessSegment = businessSegment;
		this.policyNumber = policyNumber;
		this.policyPremium = policyPremium;
		this.quesDescription = quesDescription;
		this.answer = answer;
	}

	public String getInsuredName() {
		return insuredName;
	}

	public void setInsuredName(String insuredName) {
		this.insuredName = insuredName;
	}

	public String getInsuredStreet() {
		return insuredStreet;
	}

	public void setInsuredStreet(String insuredStreet) {
		this.insuredStreet = insuredStreet;
	}

	public String getInsuredState() {
		return insuredState;
	}

	public void setInsuredState(String insuredState) {
		this.insuredState = insuredState;
	}

	public String getInsuredCity() {
		return insuredCity;
	}

	public void setInsuredCity(String insuredCity) {
		this.insuredCity = insuredCity;
	}

	public Integer getInsuredZip() {
		return insuredZip;
	}

	public void setInsuredZip(Integer insuredZip) {
		this.insuredZip = insuredZip;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Long getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(Long accountNumber) {
		this.accountNumber = accountNumber;
	}

	public String getBusinessSegment() {
		return businessSegment;
	}

	public void setBusinessSegment(String businessSegment) {
		this.businessSegment = businessSegment;
	}

	public Long getPolicyNumber() {
		return policyNumber;
	}

	public void setPolicyNumber(Long policyNumber) {
		this.policyNumber = policyNumber;
	}

	public Integer getPolicyPremium() {
		return policyPremium;
	}

	public void setPolicyPremium(Integer policyPremium) {
		this.policyPremium = policyPremium;
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

	public String getQuesDescription() {
		return quesDescription;
	}

	public void setQuesDescription(String quesDescription) {
		this.quesDescription = quesDescription;
	}

	@Override
	public String toString() {
		return "ReportGenerationDetails [insuredName=" + insuredName + ", insuredStreet=" + insuredStreet
				+ ", insuredState=" + insuredState + ", insuredCity=" + insuredCity + ", insuredZip=" + insuredZip
				+ ", userName=" + userName + ", accountNumber=" + accountNumber + ", businessSegment=" + businessSegment
				+ ", policyNumber=" + policyNumber + ", policyPremium=" + policyPremium + ", answer=" + answer
				+ ", quesDescription=" + quesDescription + "]";
	}

}
