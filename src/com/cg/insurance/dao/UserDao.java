package com.cg.insurance.dao;

import com.cg.insurance.exception.OIQGException;
import com.cg.insurance.model.UserRole;

public interface UserDao {

	Boolean validateUser(UserRole role) throws OIQGException;

	String getRole(UserRole role) throws OIQGException;

	int addUser(UserRole role2) throws OIQGException;

}
