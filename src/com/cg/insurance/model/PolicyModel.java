package com.cg.insurance.model;

import java.io.Serializable;

public class PolicyModel implements Serializable {

	private static final long serialVersionUID = 1L;
	private String businessSegment;
	private Long policyNumber;
	private Integer policyPremium;
	private Long accountNumber;

	public PolicyModel() {

	}

	public PolicyModel(String businessSegment, Long accountNumber, Integer policyPremium) {
		super();
		this.businessSegment = businessSegment;
		this.accountNumber = accountNumber;
		this.policyPremium = policyPremium;

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

	public Long getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(Long accountNumber) {
		this.accountNumber = accountNumber;
	}

	@Override
	public String toString() {
		return "PolicyModel [businessSegment=" + businessSegment + ", policyNumber=" + policyNumber + ", policyPremium="
				+ policyPremium + ", accountNumber=" + accountNumber + "]";
	}

}
