package com.cg.insurance.service;

import com.cg.insurance.exception.OIQGException;
import com.cg.insurance.model.UserRole;

public interface UserService {

	Boolean validateUser(UserRole role) throws OIQGException;

	String getRole(UserRole role) throws OIQGException;

	int addUser(UserRole role2) throws OIQGException;

}
