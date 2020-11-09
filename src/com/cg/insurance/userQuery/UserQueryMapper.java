package com.cg.insurance.userQuery;

public interface UserQueryMapper {

	String validateQuery = "SELECT password FROM user_role WHERE user_name=?";
	String getRoleQuery = "SELECT role_code FROM user_role WHERE user_name=? and password=? ";
	String createQuery = "INSERT INTO user_role(user_name, password, role_code, user_id) values(?, ?, ?, user_id_sequence.nextval)";
	String getIdQuery = "SELECT user_id_sequence.currval FROM dual";
	String createAccountQuery = "INSERT into accounts(account_number, insured_name, insured_street, insured_city, insured_state, insured_zip, user_name) values(seq_account.nextval, ?, ?, ?, ?, ?, ?)";
	String getAcccountIdQuery="SELECT seq_account.currval FROM dual ";
	String checkQuery = "SELECT count(user_name) FROM accounts WHERE user_name = ?";
	String addDetailsQuery = "INSERT INTO LINK_TABLE(USER_NAME,ACCOUNT_NUMBER) VALUES(?,?)";
	String viewCustomerPolicyQuery1 ="SELECT a.account_number, a.insured_name, a.insured_street, a.insured_city, a.insured_state, a.insured_zip, l.user_name,p.POLICY_NUMBER,p.POLICY_PREMIUM,p.BUSINESS_SEGMENT FROM policy p,accounts a,link_table l WHERE l.user_name=? AND l.account_number=a.account_number AND a.account_number=p.account_number ";
    
}
