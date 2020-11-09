package com.cg.insurance.userDaoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.cg.insurance.agentQuery.AgentQueryMapper;
import com.cg.insurance.exception.OIQGException;
import com.cg.insurance.model.Accounts;
import com.cg.insurance.model.PolicyModel;
import com.cg.insurance.model.UserRole;
import com.cg.insurance.userDao.UserDao;
import com.cg.insurance.userQuery.UserQueryMapper;
import com.cg.insurance.utility.JdbcUtility;

public class UserDaoImpl implements UserDao {

	static Logger logger = Logger.getLogger(UserDaoImpl.class);
	Connection connection = null;
	PreparedStatement statement = null;
	ResultSet resultSet = null;
	boolean resFlag;

	@Override
	public Boolean validateUser(UserRole role) throws OIQGException {
		resFlag = false;
		connection = JdbcUtility.getConnection();

		String password = "";
		try {
			statement = connection.prepareStatement(UserQueryMapper.validateQuery);
			statement.setString(1, role.getUserName());
			resultSet = statement.executeQuery();

			while (resultSet.next()) {
				password = resultSet.getString("password");

				if (password.equals(role.getPassword())) {
					resFlag = true;

				}
			}
		} catch (SQLException e) {
			logger.error("Connection not established");
			System.err.println("connection not established" + e);
		} finally {
			try {
				resultSet.close();
			} catch (SQLException e) {
				logger.equals("resultset not closed");
				System.err.println("resultset can not be closed");
			}
		}

		return resFlag;
	}

	@Override
	public String getRole(UserRole role) throws OIQGException {

		connection = JdbcUtility.getConnection();

		String roleCode = "";
		try {
			statement = connection.prepareStatement(UserQueryMapper.getRoleQuery);
			statement.setString(1, role.getUserName());
			statement.setString(2, role.getPassword());
			resultSet = statement.executeQuery();
			while (resultSet.next()) {
				roleCode = resultSet.getString("role_code");

			}

		} catch (SQLException e) {
			logger.error("Connection not established");
			System.err.println("connection not established");
		}

		return roleCode;
	}

	@Override
	public int addUser(UserRole role2) throws OIQGException {
		connection = JdbcUtility.getConnection();

		int genId = 0;
		try {
			statement = connection.prepareStatement(UserQueryMapper.createQuery);

			statement.setString(1, role2.getUserName());
			statement.setString(2, role2.getPassword());
			statement.setString(3, role2.getRoleCode());
			statement.executeUpdate();
			connection.commit();
			statement = connection.prepareStatement(UserQueryMapper.getIdQuery);

			resultSet = statement.executeQuery();
			resultSet.next();
			genId = resultSet.getInt(1);
			System.out.println(genId);

		} catch (SQLIntegrityConstraintViolationException e) {
			logger.error("User defined exveption thrown");
			throw new OIQGException("Account with same user exits");
		} catch (SQLException e) {
			logger.error("Connection not established");
			System.err.println("connection not established" + e);
		}
		return genId;
	}

	@Override
	public Long addNewAccount(Accounts accounts) throws OIQGException {

		connection = JdbcUtility.getConnection();

		Long genAccountNumber = 0l;
		try {
			statement = connection.prepareStatement(UserQueryMapper.createAccountQuery);

			statement.setString(1, accounts.getInsuredName());
			statement.setString(2, accounts.getInsuredStreet());
			statement.setString(3, accounts.getInsuredCity());
			statement.setString(4, accounts.getInsuredState());
			statement.setInt(5, accounts.getInsuredZip());

			statement.setString(6, accounts.getUserName());
			statement.executeUpdate();
			connection.commit();
			statement = connection.prepareStatement(UserQueryMapper.getAcccountIdQuery);

			resultSet = statement.executeQuery();
			resultSet.next();
			genAccountNumber = resultSet.getLong(1);

		} catch (SQLIntegrityConstraintViolationException e) {
			logger.error("User defined exception thrown");
			throw new OIQGException("");
		} catch (SQLException e) {
			logger.error("Connection not established");
			System.err.println("connection not established" + e);
		}
		return genAccountNumber;

	}

	@Override
	public boolean checkAccount(Accounts accounts) throws OIQGException {

		boolean checkFlag = false;
		connection = JdbcUtility.getConnection();

		Long genAccountNumber = 0l;
		try {
			statement = connection.prepareStatement(UserQueryMapper.checkQuery);
			statement.setString(1, accounts.getUserName());
			System.out.println(accounts.getUserName());
			resultSet = statement.executeQuery();
			resultSet.next();
			int count = resultSet.getInt(1);

			if (count == 0) {
				checkFlag = true;
			} else {
				throw new OIQGException("Account Already Exits");
			}

		} catch (Exception e) {
			logger.error("Account already exist Exception");
			System.err.println(e.getMessage());
		}
		return checkFlag;
	}

	@Override
	public List<Accounts> getCustomerAccDetails(String userName) throws OIQGException {
		connection = JdbcUtility.getConnection();
		List<Accounts> policyAccInfo = new ArrayList<>();

		try {
			statement = connection.prepareStatement(UserQueryMapper.viewCustomerPolicyQuery1);

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
	public List<PolicyModel> getCustomerPolicyDetails(String userName) throws OIQGException {
		connection = JdbcUtility.getConnection();
		List<PolicyModel> policyInfo = new ArrayList<>();

		try {
			statement = connection.prepareStatement(UserQueryMapper.viewCustomerPolicyQuery1);
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

}
