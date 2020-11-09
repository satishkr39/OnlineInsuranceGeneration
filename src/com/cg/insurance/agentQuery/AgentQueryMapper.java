package com.cg.insurance.agentQuery;

public interface AgentQueryMapper {
/*	String validateQuery = "SELECT password FROM user_role WHERE user_name=?";
	String getRoleQuery = "SELECT role_code FROM user_role WHERE user_name=? ";
	String createQuery = "INSERT INTO user_role(user_name, password, role_code, user_id) values(?, ?, ?, user_id_sequence.nextval)";
	String getIdQuery = "SELECT user_id_sequence.currval FROM dual";*/
	String createAccountQuery = "INSERT into accounts(account_number, insured_name, insured_street, insured_city, insured_state, insured_zip, user_name) values(seq_account.nextval, ?, ?, ?, ?, ?, ?)";
	String getAcccountIdQuery="SELECT seq_account.currval FROM dual ";
	String getSegmentIdQuery="SELECT bus_seg_id FROM business_segment WHERE bus_seg_name=?";
/*	String checkQuery = "SELECT count(user_name) FROM accounts WHERE user_name = ?";*/
	String getQuestionsQuery = "SELECT  POL_QUES_DESC FROM policy_questions WHERE bus_seg_id=?";
	String getQuestionIdQuery = "SELECT  pol_ques_id FROM policy_questions WHERE POL_QUES_DESC=? AND bus_seg_id=?";
	String getAnswerWeightage1Query = "SELECT POL_QUES_ANS1_WEIGHTAGE,POL_QUES_ANS1 FROM policy_questions WHERE POL_QUES_ID=?";
	String getAnswerWeightage2Query = "SELECT POL_QUES_ANS2_WEIGHTAGE,POL_QUES_ANS2 FROM policy_questions WHERE POL_QUES_ID=?";
	String getAnswerWeightage3Query = "SELECT POL_QUES_ANS3_WEIGHTAGE,POL_QUES_ANS3 FROM policy_questions WHERE POL_QUES_ID=?";
	String getAccExistsQuery = "SELECT ACCOUNT_NUMBER FROM accounts";
	String createPolicyQuery = "INSERT INTO policy(POLICY_NUMBER,POLICY_PREMIUM,BUSINESS_SEGMENT,ACCOUNT_NUMBER) VALUES(POLICY_NUMBER_SEQ.NEXTVAL,?,?,?)";
	String getPolicyNumberQuery="SELECT POLICY_NUMBER_SEQ.currval FROM dual ";
	String viewCustomerPolicyQuery ="SELECT a.account_number, a.insured_name, a.insured_street, a.insured_city, a.insured_state, a.insured_zip, a.user_name,p.POLICY_NUMBER,p.POLICY_PREMIUM,p.BUSINESS_SEGMENT FROM policy p,accounts a WHERE a.user_name=? AND a.account_number=p.account_number ";
	String addPolicyDetailstQuery ="INSERT INTO policy_details(POLICY_NUMBER,QUESTION_ID,ANSWER) VALUES (?,?,?) ";
	String getValidAgentQuery = "SELECT user_name FROM accounts WHERE account_number=? ";
	String viewCustomerPolicyQuery1 ="SELECT a.account_number, a.insured_name, a.insured_street, a.insured_city, a.insured_state, a.insured_zip, l.user_name,p.POLICY_NUMBER,p.POLICY_PREMIUM,p.BUSINESS_SEGMENT FROM policy p,accounts a,link_table l WHERE l.user_name=? AND l.account_number=a.account_number AND a.account_number=p.account_number ";
	
}

