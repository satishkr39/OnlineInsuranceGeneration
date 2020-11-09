package com.cg.insurance.adminQuery;

public interface AdminQueryMapper {

	String createNewUserQuery = "INSERT INTO user_role(user_name, password, role_code, user_id) values(?,?,?,user_id_sequence.nextval)";
	String getIdQuery = "SELECT user_id_sequence.currval FROM dual";
	String getAccountsQuery = "SELECT * FROM ACCOUNTS";

	String getAccountPolicyQuery = "select a.account_number,a.insured_name,a.insured_street,a.insured_city,a.insured_state,a.insured_zip,a.user_name,p.policy_number,p.business_segment,p.policy_premium,pd.answer,pq.pol_ques_desc from accounts a,policy p,policy_details pd,policy_questions pq where a.account_number=p.account_number and p.policy_number=pd.policy_number and pq.pol_ques_id=pd.question_id AND a.account_number=? AND p.policy_number=?";
	String getPolicyQueryDetails = "SELECT * FROM policy";

	String creatAccountQuery = "INSERT into accounts(account_number, insured_name, insured_street, insured_city, insured_state, insured_zip, user_name) values(seq_account.nextval, ?, ?, ?, ?, ?, ?)";
	String getAcccountIdQuery = "SELECT seq_account.currval FROM dual ";

	String agentNameQuery = "SELECT user_name FROM user_role WHERE role_code =  ?";
	String checkAgentQuery = "SELECT role_code FROM user_role WHERE user_name = ?";
	String getAccExistsQuery = "SELECT ACCOUNT_NUMBER FROM accounts";
	String getPolicyExistsQuery ="SELECT POLICY_NUMBER FROM policy";
}
