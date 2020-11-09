package com.cg.insurance.agentDaoImpl;

import java.awt.image.AreaAveragingScaleFilter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.cg.insurance.agentDao.AgentDao;
import com.cg.insurance.agentQuery.AgentQueryMapper;
import com.cg.insurance.exception.OIQGException;
import com.cg.insurance.model.Accounts;
import com.cg.insurance.model.PolicyModel;
import com.cg.insurance.utility.JdbcUtility;

public class AgentDaoImpl implements AgentDao {

	static Logger logger = Logger.getLogger(AgentDaoImpl.class);
	Connection connection = null;
	PreparedStatement statement = null;
	ResultSet resultSet = null;
	boolean resFlag = false;

	@Override
	public Long addNewAccount(Accounts accounts) throws OIQGException {
		connection = JdbcUtility.getConnection();

		Long genAccountNumber = 0l;
		try {
			statement = connection.prepareStatement(AgentQueryMapper.createAccountQuery);

			statement.setString(1, accounts.getInsuredName());
			statement.setString(2, accounts.getInsuredStreet());
			statement.setString(3, accounts.getInsuredCity());
			statement.setString(4, accounts.getInsuredState());
			statement.setInt(5, accounts.getInsuredZip());

			statement.setString(6, accounts.getUserName());
			System.out.println(accounts.getUserName());
			statement.executeUpdate();
			connection.commit();
			statement = connection.prepareStatement(AgentQueryMapper.getAcccountIdQuery);

			resultSet = statement.executeQuery();
			resultSet.next();
			genAccountNumber = resultSet.getLong(1);

		} catch (SQLIntegrityConstraintViolationException e) {
			throw new OIQGException("");
		} catch (SQLException e) {
			logger.error("Connection not established");
			System.err.println("connection not established" + e);
		}
		return genAccountNumber;

	}

	@Override
	public int getSegmentId(String businessSegment) throws OIQGException {
		connection = JdbcUtility.getConnection();
		int segmentId = 0;
		try {
			statement = connection.prepareStatement(AgentQueryMapper.getSegmentIdQuery);
			statement.setString(1, businessSegment);
			resultSet = statement.executeQuery();
			resultSet.next();
			segmentId = resultSet.getInt(1);

		} catch (SQLException e) {
			logger.error("Connection not established");
			System.err.println("connection not established" + e);
		}
		return segmentId;

	}

	@Override
	public List<String> getQuestions(int segmentId) throws OIQGException {
		connection = JdbcUtility.getConnection();
		List<String> listQuestion = new ArrayList<>();
		try {
			statement = connection.prepareStatement(AgentQueryMapper.getQuestionsQuery);
			statement.setInt(1, segmentId);
			resultSet = statement.executeQuery();
			while (resultSet.next()) {
				listQuestion.add(resultSet.getString("POL_QUES_DESC"));
			}

		} catch (SQLException e) {
			logger.error("Connection not established");
			System.err.println("connection not established" + e);
		}
		return listQuestion;
	}

	@Override
	public int getQuestionsId(String question, int segmentId) throws OIQGException {
		connection = JdbcUtility.getConnection();
		int questionId = 0;

		try {
			statement = connection.prepareStatement(AgentQueryMapper.getQuestionIdQuery);
			statement.setString(1, question);
			statement.setInt(2, segmentId);
			resultSet = statement.executeQuery();
			resultSet.next();
			questionId = resultSet.getInt("pol_ques_id");

		} catch (SQLException e) {
			logger.error("Connection not established");
			System.err.println("connection not established" + e);
		}
		return questionId;
	}

	@Override
	public Map<String, Integer> getAnswerWeigtage(int questionId) throws OIQGException {
		connection = JdbcUtility.getConnection();
		int answerWeightage = 0;
		Map<String, Integer> map = new LinkedHashMap<>();

		try {
			statement = connection.prepareStatement(AgentQueryMapper.getAnswerWeightage1Query);
			statement.setInt(1, questionId);
			resultSet = statement.executeQuery();
			resultSet.next();
			int weightAge1 = resultSet.getInt("POL_QUES_ANS1_WEIGHTAGE");
			String answer1 = resultSet.getString("POL_QUES_ANS1");
			// System.out.println(weightAge1+answer1);
			map.put(answer1, weightAge1);

			statement = connection.prepareStatement(AgentQueryMapper.getAnswerWeightage2Query);
			statement.setInt(1, questionId);
			resultSet = statement.executeQuery();
			resultSet.next();
			int weightAge2 = resultSet.getInt("POL_QUES_ANS2_WEIGHTAGE");
			String answer2 = resultSet.getString("POL_QUES_ANS2");
			// System.out.println(weightAge2+answer2);
			map.put(answer2, weightAge2);
			statement = connection.prepareStatement(AgentQueryMapper.getAnswerWeightage3Query);
			statement.setInt(1, questionId);
			resultSet = statement.executeQuery();
			resultSet.next();
			int weightAge3 = resultSet.getInt("POL_QUES_ANS3_WEIGHTAGE");
			String answer3 = resultSet.getString("POL_QUES_ANS3");
			// System.out.println(weightAge3+answer3);
			map.put(answer3, weightAge3);

		} catch (SQLException e) {
			logger.error("Connection not established");
			System.err.println("connection not established" + e);
		}
		return map;
	}

	@Override
	public boolean isAccountExists(long accNumber) throws OIQGException {
		connection = JdbcUtility.getConnection();
		boolean accExists = false;
		try {
			statement = connection.prepareStatement(AgentQueryMapper.getAccExistsQuery);
			resultSet = statement.executeQuery();
			while (resultSet.next()) {
				long accountNumber = resultSet.getLong("account_number");
				if (accNumber == accountNumber) {
					accExists = true;
					break;
				}

			}
			if (accExists == false) {
				throw new OIQGException("Account doesn't exits");
			}

		} catch (SQLException e) {
			logger.error("Connection not established");
			System.err.println("connection not established" + e);
		}

		return accExists;
	}

	@Override
	public long addPolicy(PolicyModel policyModel) throws OIQGException {
		connection = JdbcUtility.getConnection();

		Long genPolicyNumber = 0l;
		try {
			statement = connection.prepareStatement(AgentQueryMapper.createPolicyQuery);

			statement.setLong(1, policyModel.getPolicyPremium());
			statement.setString(2, policyModel.getBusinessSegment());
			statement.setLong(3, policyModel.getAccountNumber());

			statement.executeUpdate();
			connection.commit();
			statement = connection.prepareStatement(AgentQueryMapper.getPolicyNumberQuery);

			resultSet = statement.executeQuery();
			resultSet.next();
			genPolicyNumber = resultSet.getLong(1);

		} catch (SQLIntegrityConstraintViolationException e) {
			throw new OIQGException("");
		} catch (SQLException e) {
			logger.error("Connection not established");
			System.err.println("connection not established" + e);
		}
		return genPolicyNumber;

	}

	@Override
	public List<PolicyModel> getCustomerPolicyDetails(String userName) throws OIQGException {
		connection = JdbcUtility.getConnection();
		List<PolicyModel> policyInfo = new ArrayList<>();

		try {
			statement = connection.prepareStatement(AgentQueryMapper.viewCustomerPolicyQuery);
			statement.setString(1, userName);
			resultSet = statement.executeQuery();

			while (resultSet.next()) {

				long accountNo = resultSet.getLong("account_number");
				Long policyNumber = resultSet.getLong("POLICY_NUMBER");
				int policyPremium = resultSet.getInt("POLICY_PREMIUM");
				String businessSegment = resultSet.getString("BUSINESS_SEGMENT");
				PolicyModel model = new PolicyModel(businessSegment, accountNo, policyPremium);
				model.setPolicyNumber(policyNumber);
				policyInfo.add(model);

			}

		} catch (SQLException e) {
			logger.error("Connection not established");
			System.err.println("connection not established" + e);
		}
		return policyInfo;

	}

	@Override
	public List<Accounts> getCustomerAccDetails(String userName) throws OIQGException {
		connection = JdbcUtility.getConnection();
		List<Accounts> policyAccInfo = new ArrayList<>();

		try {
			statement = connection.prepareStatement(AgentQueryMapper.viewCustomerPolicyQuery);

			statement.setString(1, userName);

			resultSet = statement.executeQuery();

			while (resultSet.next()) {
				long accountNo = resultSet.getLong("account_number");
				System.out.println(accountNo);
				String insuredName = resultSet.getString("insured_name");
				String insuredStreet = resultSet.getString("insured_street");
				String insuredCity = resultSet.getString("insured_city");
				String insuredState = resultSet.getString("insured_state");
				int insuredZip = resultSet.getInt("insured_zip");

				Accounts accounts = new Accounts(insuredName, insuredStreet, insuredState, insuredCity, insuredZip);
				policyAccInfo.add(accounts);

			}

		} catch (SQLException e) {
			logger.error("Connection not established");
			System.err.println("connection not established" + e);
		}
		return policyAccInfo;

	}

	@Override
	public boolean setPolicyDetails(Map<Integer, String> policyAnswer, long policyNumber) throws OIQGException {
		connection = JdbcUtility.getConnection();

		Boolean isDetailsAdded = false;
		try {
			statement = connection.prepareStatement(AgentQueryMapper.addPolicyDetailstQuery);

			for (Map.Entry<Integer, String> var : policyAnswer.entrySet()) {
				statement.setLong(1, policyNumber);
				statement.setInt(2, var.getKey());
				statement.setString(3, var.getValue());
				isDetailsAdded = true;
				statement.executeUpdate();
				connection.commit();
			}

		} catch (SQLException e) {
			logger.error("Connection not established");
			System.err.println("connection not established" + e);
		}
		return isDetailsAdded;

	}

	@Override
	public boolean isValidAgent(long accNumber, String userName) throws OIQGException {

		connection = JdbcUtility.getConnection();

		Boolean isValidAgent = false;
		String agentName = "";
		try {
			statement = connection.prepareStatement(AgentQueryMapper.getValidAgentQuery);
			statement.setLong(1, accNumber);
			resultSet = statement.executeQuery();
			while (resultSet.next()) {
				agentName = resultSet.getString("user_name");

			}
			if (userName.equals(agentName)) {
				isValidAgent = true;
			}

			else {
				throw new OIQGException("You are not allowed to create policies for this user");
			}
		} catch (SQLException e) {
			logger.error("Connection not established");
			System.err.println("connection not established" + e);
		}
		return isValidAgent;
	}

}
