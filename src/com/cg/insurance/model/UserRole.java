package com.cg.insurance.model;

import java.io.Serializable;

public class UserRole implements Serializable {
	private String userName;
	private String password;
	private String roleCode;
	private Integer userId;

	public UserRole() {
		// TODO Auto-generated constructor stub
	}

	public UserRole(String userName, String password, String roleCode) {
		super();
		this.userName = userName;
		this.password = password;
		this.roleCode = roleCode;
	}

	public UserRole(String userName, String password, String roleCode, Integer userId) {
		super();
		this.userName = userName;
		this.password = password;
		this.roleCode = roleCode;
		this.userId = userId;
	}

	public UserRole(String userName, String password) {
		super();
		this.userName = userName;
		this.password = password;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRoleCode() {
		return roleCode;
	}

	public void setRoleCode(String roleCode) {
		this.roleCode = roleCode;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	@Override
	public String toString() {
		return "UserRole [userName=" + userName + ", password=" + password + ", roleCode=" + roleCode + ", userId="
				+ userId + "]";
	}

}
