package com.cg.insurance.model;

import java.io.Serializable;

public class Accounts implements Serializable {
	private String insuredName;
	private String insuredStreet;
	private String insuredState;
	private String insuredCity;
	private Integer insuredZip;

	private String userName;
	private Long accountNumber;

	public Accounts() {
		// TODO Auto-generated constructor stub
	}

	public Accounts(String insuredName, String insuredStreet, String insuredState, String insuredCity,
			Integer insuredZip, String userName, Long accountNumber) {
		super();
		this.insuredName = insuredName;
		this.insuredStreet = insuredStreet;
		this.insuredState = insuredState;
		this.insuredCity = insuredCity;
		this.insuredZip = insuredZip;
		
		this.userName = userName;
		this.accountNumber = accountNumber;
	}

	public Accounts(String insuredName, String insuredStreet, String insuredState, String insuredCity,
			Integer insuredZip) {
		super();
		this.insuredName = insuredName;
		this.insuredStreet = insuredStreet;
		this.insuredState = insuredState;
		this.insuredCity = insuredCity;
		this.insuredZip = insuredZip;
		
	}

	public Accounts(String insuredName, String insuredStreet, String insuredState, String insuredCity,
			Integer insuredZip, String userName) {
		super();
		this.insuredName = insuredName;
		this.insuredStreet = insuredStreet;
		this.insuredState = insuredState;
		this.insuredCity = insuredCity;
		this.insuredZip = insuredZip;
		
		this.userName = userName;
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

	@Override
	public String toString() {
		return "Accounts [insuredName=" + insuredName + ", insuredStreet=" + insuredStreet + ", insuredState="
				+ insuredState + ", insuredCity=" + insuredCity + ", insuredZip=" + insuredZip + ", userName="
				+ userName + ", accountNumber=" + accountNumber + "]";
	}

	

}
