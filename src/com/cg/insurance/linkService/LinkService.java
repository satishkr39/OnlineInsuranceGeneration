package com.cg.insurance.linkService;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;

import com.cg.insurance.exception.OIQGException;
import com.cg.insurance.userQuery.UserQueryMapper;
import com.cg.insurance.utility.JdbcUtility;

public class LinkService {
	Connection connection=null;
	PreparedStatement statement=null;
	public void addDetails(Long accountNumber, String userName) throws OIQGException {
		System.out.println(accountNumber);
		System.out.println(userName);
		connection = JdbcUtility.getConnection();

		
		try {
			statement = connection.prepareStatement(UserQueryMapper.addDetailsQuery);

			statement.setString(1, userName);
			statement.setLong(2, accountNumber);
		
			statement.executeUpdate();
			connection.commit();
		

			
		} catch (SQLException e) {

			System.err.println("connection not established" + e);
		}
		
		
	}

}
