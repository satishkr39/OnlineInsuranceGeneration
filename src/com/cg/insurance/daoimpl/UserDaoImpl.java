package com.cg.insurance.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.cg.insurance.dao.UserDao;
import com.cg.insurance.exception.OIQGException;
import com.cg.insurance.model.UserRole;
import com.cg.insurance.query.QueryMapper;
import com.cg.insurance.utility.JdbcUtility;

public class UserDaoImpl implements UserDao {
	Connection connection = null;
	PreparedStatement statement = null;
	ResultSet resultSet = null;
	boolean resFlag = false;

	@Override
	public Boolean validateUser(UserRole role) throws OIQGException {
		
		connection = JdbcUtility.getConnection();

		String password="";
		try {
			statement = connection.prepareStatement(QueryMapper.validateQuery);
			statement.setString(1, role.getUserName());
			resultSet = statement.executeQuery();
			
			while (resultSet.next()) {
				password = resultSet.getString("password");
				

			}
		
			if (password.equals(role.getPassword())) {
				resFlag = true;
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.err.println("connection not established");
		}
		
		return resFlag;
	}

	@Override
	public String getRole(UserRole role) throws OIQGException {

		connection = JdbcUtility.getConnection();

		String roleCode = "";
		try {
			statement = connection.prepareStatement(QueryMapper.getRoleQuery);
			statement.setString(1, role.getUserName());
			resultSet = statement.executeQuery();
			while (resultSet.next()) {
				roleCode = resultSet.getString("role_code");

			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.err.println("connection not established");
		}

		return roleCode;
	}

	@Override
	public int addUser(UserRole role2) throws OIQGException {
		connection = JdbcUtility.getConnection();

		
		int genId = 0;
		try {
			statement = connection.prepareStatement(QueryMapper.createQuery);
			statement.setString(1, role2.getUserName());
			statement.setString(2, role2.getPassword());
			statement.setString(3, role2.getRoleCode());
			statement.executeUpdate();
			connection.commit();
			statement = connection.prepareStatement(QueryMapper.getIdQuery);
			resultSet = statement.executeQuery();		
			while(resultSet.next()) {
				genId = resultSet.getInt("user_id");
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.err.println("connection not established"+e);
		}
		return genId;
	}

}
