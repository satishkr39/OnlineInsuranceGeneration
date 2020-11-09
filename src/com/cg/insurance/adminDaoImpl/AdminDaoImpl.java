package com.cg.insurance.adminDaoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.cg.insurance.adminDao.AdminDao;
import com.cg.insurance.adminQuery.AdminQueryMapper;
import com.cg.insurance.agentQuery.AgentQueryMapper;
import com.cg.insurance.exception.OIQGException;
import com.cg.insurance.model.Accounts;
import com.cg.insurance.model.PolicyDetails;
import com.cg.insurance.model.PolicyModel;
import com.cg.insurance.model.ReportGenerationDetails;
import com.cg.insurance.model.UserRole;
import com.cg.insurance.utility.JdbcUtility;

public class AdminDaoImpl implements AdminDao {

	static Logger logger = Logger.getLogger(AdminDaoImpl.class);
	Connection connection = null;
	PreparedStatement statement = null;
	ResultSet resultSet = null;
	boolean resFlag = false;

	@Override
	public int createUserAccount(UserRole admin) throws OIQGException {

		logger.info("Create user called");

		connection = JdbcUtility.getConnection();
		int genUserId = 0;

		try {
			statement = connection.prepareStatement(AdminQueryMapper.createNewUserQuery);
			statement.setString(1, admin.getUserName());
			statement.setString(2, admin.getPassword());
			statement.setString(3, admin.getRoleCode());

			statement.executeUpdate();
			connection.commit();
			statement = connection.prepareStatement(AdminQueryMapper.getIdQuery);
			resultSet = statement.executeQuery();
			resultSet.next();
			genUserId = resultSet.getInt(1);

		} catch (SQLIntegrityConstraintViolationException e) {
			logger.error("Constraint violation met");
			throw new OIQGException("Account with same user exits");
		} catch (SQLException e) {
			logger.error("Connection not established");
			System.err.println("connection not established" + e);
		}
		return genUserId;

	}

	@Override
	public List<Accounts> getAccountDetails() throws OIQGException {

		connection = JdbcUtility.getConnection();

		List<Accounts> accounts = new ArrayList<Accounts>();

		try {
			statement = connection.prepareStatement(AdminQueryMapper.getAccountsQuery);
			resultSet = statement.executeQuery();

			while (resultSet.next()) {

				long accountNo = resultSet.getLong("account_number");
				String insuredName = resultSet.getString("insured_name");
				String insuredStreet = resultSet.getString("insured_street");
				String insuredCity = resultSet.getString("insured_city");
				String insuredState = resultSet.getString("insured_state");
				int insuredZip = resultSet.getInt("insured_zip");
				String userName = resultSet.getString("user_name");

				Accounts accounts2 = new Accounts(insuredName, insuredStreet, insuredState, insuredCity, insuredZip);
				accounts2.setAccountNumber(accountNo);
				accounts2.setUserName(userName);
				accounts.add(accounts2);

			}

		} catch (SQLException e) {
			logger.error("Connection not established");
			System.err.println("Connection not established");
		}

		return accounts;

	}

	@Override
	public List<ReportGenerationDetails> reportGeneration(Long accNumber, Long polNumber) throws OIQGException {
		connection = JdbcUtility.getConnection();
		String insuredName;
		String insuredStreet;
		String insuredState;
		String insuredCity;
		Integer insuredZip;

		String userName;
		Long accountNumber;
		String businessSegment;
		Long policyNumber;
		Integer policyPremium;

		String answer;

		String quesDescription;
		ReportGenerationDetails details = null;
		List<ReportGenerationDetails> accountsReport = new ArrayList<>();

		try {
			statement = connection.prepareStatement(AdminQueryMapper.getAccountPolicyQuery);
			statement.setLong(1, accNumber);
			statement.setLong(2, polNumber);
			resultSet = statement.executeQuery();

			while (resultSet.next()) {

				insuredName = resultSet.getString("insured_name");

				insuredStreet = resultSet.getString("insured_street");

				insuredState = resultSet.getString("insured_state");

				insuredCity = resultSet.getString("insured_city");

				insuredZip = resultSet.getInt("insured_zip");

				userName = resultSet.getString("user_name");

				accountNumber = resultSet.getLong("account_number");

				businessSegment = resultSet.getString("business_segment");

				policyNumber = resultSet.getLong("policy_number");

				policyPremium = resultSet.getInt("policy_premium");

				quesDescription = resultSet.getString("pol_ques_desc");
				answer = resultSet.getString("answer");

				details = new ReportGenerationDetails(accountNumber, insuredName, insuredStreet, insuredState,
						insuredCity, insuredZip, userName, businessSegment, policyNumber, policyPremium,
						quesDescription, answer);

				accountsReport.add(details);

			}
		} catch (SQLException e) {
			logger.error("SQL Exception");
			System.out.println(e.getMessage());
		}
		return accountsReport;
	}

	@Override
	public List<PolicyModel> viewAllPolicies() throws OIQGException {
		List<PolicyModel> details = new ArrayList<>();
		connection = JdbcUtility.getConnection();

		String businessSegment;
		Long policyNumber;
		Integer policyPremium;
		Long accountNumber;

		try {
			statement = connection.prepareStatement(AdminQueryMapper.getPolicyQueryDetails);
			resultSet = statement.executeQuery();
			while (resultSet.next()) {

				businessSegment = resultSet.getString("business_segment");
				policyNumber = resultSet.getLong("policy_number");
				policyPremium = resultSet.getInt("policy_premium");
				accountNumber = resultSet.getLong("account_number");

				PolicyModel model = new PolicyModel(businessSegment, accountNumber, policyPremium);
				model.setPolicyNumber(policyNumber);

				details.add(model);

			}

		} catch (SQLException e) {
			logger.error("Connection not established");
			System.err.println("Connection Not Established");
		}

		return details;
	}

	@Override
	public long addNewAccount(Accounts accounts) throws OIQGException {

		connection = JdbcUtility.getConnection();

		Long genAccountNumber = 0l;
		try {
			statement = connection.prepareStatement(AdminQueryMapper.creatAccountQuery);

			statement.setString(1, accounts.getInsuredName());
			statement.setString(2, accounts.getInsuredStreet());
			statement.setString(3, accounts.getInsuredCity());
			statement.setString(4, accounts.getInsuredState());
			statement.setInt(5, accounts.getInsuredZip());
			statement.setString(6, accounts.getUserName());
			System.out.println(accounts.getUserName());
			statement.executeUpdate();
			connection.commit();
			statement = connection.prepareStatement(AdminQueryMapper.getAcccountIdQuery);

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
	public List<String> getAgentNames() throws OIQGException {
		connection = JdbcUtility.getConnection();

		List<String> agentName = new ArrayList<>();
		String agentNames;
		try {

			statement = connection.prepareStatement(AdminQueryMapper.agentNameQuery);
			statement.setString(1, "agent");

			resultSet = statement.executeQuery();

			while (resultSet.next()) {
				agentNames = resultSet.getString("user_name");
				agentName.add(agentNames);
			}

		} catch (SQLException e) {
			logger.error("Connection not established");
			System.err.println("connection not established");
		}

		return agentName;
	}

	@Override
	public boolean checkAgentName(String agentName) throws OIQGException {
		connection = JdbcUtility.getConnection();
		String roleCode;
		boolean isAgentExits = false;
		try {
			statement = connection.prepareStatement(AdminQueryMapper.checkAgentQuery);
			statement.setString(1, agentName);
			resultSet = statement.executeQuery();
			while (resultSet.next()) {
				roleCode = resultSet.getString("role_code");
				if (roleCode.equals("agent")) {
					isAgentExits = true;
					break;
				}

			}
			if (isAgentExits == false) {
				throw new OIQGException("Agend not found");
			}

		} catch (SQLException e) {
			logger.error("Connection not established");
			System.err.println("Connection not established" + e);
		}

		return isAgentExits;

	}

	@Override
	public boolean isAccountExists(long accNumber) throws OIQGException {
		connection = JdbcUtility.getConnection();
		boolean accExists = false;
		try {
			statement = connection.prepareStatement(AdminQueryMapper.getAccExistsQuery);
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
	public boolean isPolicyExists(Long policyNumber) throws OIQGException {
		connection = JdbcUtility.getConnection();
		boolean policyExists = false;
		try {
			statement = connection.prepareStatement(AdminQueryMapper.getPolicyExistsQuery);
			resultSet = statement.executeQuery();
			while (resultSet.next()) {
				long policyNumber1 = resultSet.getLong("policy_number");
				if (policyNumber1 == policyNumber) {
					policyExists = true;
					break;
				}

			}
			if (policyExists == false) {
				throw new OIQGException("Account doesn't exits");
			}

		} catch (SQLException e) {
			logger.error("Connection not established");
			System.err.println("connection not established" + e);
		}

		return policyExists;

	}

}
