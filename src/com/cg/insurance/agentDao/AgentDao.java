package com.cg.insurance.agentDao;

import java.util.List;
import java.util.Map;

import com.cg.insurance.exception.OIQGException;
import com.cg.insurance.model.Accounts;
import com.cg.insurance.model.PolicyModel;

public interface AgentDao {

	Long addNewAccount(Accounts accounts) throws OIQGException;

	int getSegmentId(String businessSegment)throws OIQGException;

	List<String> getQuestions(int segmentId)throws OIQGException;

	int getQuestionsId(String question,int segmentId)throws OIQGException;

	Map<String, Integer> getAnswerWeigtage(int questionId)throws OIQGException;

	boolean isAccountExists(long accNumber) throws OIQGException;

	long addPolicy(PolicyModel policyModel)throws OIQGException;

	List<PolicyModel> getCustomerPolicyDetails(String userName)throws OIQGException;

	List<Accounts> getCustomerAccDetails(String userName)throws OIQGException;

	boolean setPolicyDetails(Map<Integer, String> policyAnswer, long policyNumber)throws OIQGException;

	boolean isValidAgent(long accNumber, String userName)throws OIQGException;

	

}
