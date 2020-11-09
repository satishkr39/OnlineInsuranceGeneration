package com.cg.insurance.agentServiceImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import com.cg.insurance.agentDao.AgentDao;
import com.cg.insurance.agentDaoImpl.AgentDaoImpl;
import com.cg.insurance.agentService.AgentService;
import com.cg.insurance.exception.OIQGException;
import com.cg.insurance.model.Accounts;
import com.cg.insurance.model.PolicyModel;
import com.cg.insurance.userQuery.UserQueryMapper;
import com.cg.insurance.utility.JdbcUtility;

public class AgentServiceImpl implements AgentService {
	AgentDao agentDao=new AgentDaoImpl();
	@Override
	public Long addNewAccount(Accounts accounts) throws OIQGException {
		
		return agentDao.addNewAccount(accounts);
	}
	@Override
	public boolean validateBusinessSegment(String businessSegment) throws OIQGException {
		

		String segmentRegex="Business Auto|Apartment|General Merchant|Restaurant";
		return Pattern.matches(segmentRegex,businessSegment);
	}
	@Override
	public int getSegmentId(String businessSegment) throws OIQGException {
		return agentDao.getSegmentId(businessSegment);
	}
	@Override
	public List<String> getQuestions(int segmentId) throws OIQGException {
		
		return agentDao.getQuestions(segmentId);
	}
	@Override
	public int getQuestionsId(String question,int segmentId) throws OIQGException {
		return agentDao.getQuestionsId(question,segmentId);
	}
	@Override
	public Map<String,Integer> getAnswerWeigtage(int questionId) throws OIQGException {
		
		return agentDao.getAnswerWeigtage(questionId);
	}
	@Override
	public boolean isAccountExists(long accNumber) throws OIQGException {
	
		return agentDao.isAccountExists(accNumber);
	}
	@Override
	public long addPolicy(PolicyModel policyModel) throws OIQGException {
		
		return agentDao.addPolicy(policyModel);
	}
	@Override
	public List<PolicyModel> getCustomerPolicyDetails(String userName) throws OIQGException {
		return agentDao.getCustomerPolicyDetails(userName);
	}
	@Override
	public List<Accounts> getCustomerAccDetails(String userName) throws OIQGException {
		
		return agentDao.getCustomerAccDetails(userName);
	}
	@Override
	public boolean setPolicyDetails(Map<Integer, String> policyAnswer,long policyNumber) throws OIQGException {
		
		return agentDao.setPolicyDetails(policyAnswer, policyNumber);
	}
	
	@Override
	public boolean isValidAgent(long accNumber, String userName) throws OIQGException {
		// TODO Auto-generated method stub
		return agentDao.isValidAgent(accNumber,userName);
	}
	@Override
	public boolean isPolicyExists(Long policyNumber) throws OIQGException {
		// TODO Auto-generated method stub
		return false;
	}

}
