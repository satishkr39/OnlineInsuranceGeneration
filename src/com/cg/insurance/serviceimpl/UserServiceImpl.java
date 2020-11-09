package com.cg.insurance.serviceimpl;

import com.cg.insurance.dao.UserDao;
import com.cg.insurance.daoimpl.UserDaoImpl;
import com.cg.insurance.exception.OIQGException;
import com.cg.insurance.model.UserRole;
import com.cg.insurance.service.UserService;

public class UserServiceImpl implements UserService {

	UserDao userDao = new UserDaoImpl();
	
	@Override
	public Boolean validateUser(UserRole role) throws OIQGException {
		return userDao.validateUser(role);
		
	}

	@Override
	public String getRole(UserRole role) throws OIQGException {
		
		return userDao.getRole(role);
	}

	@Override
	public int addUser(UserRole role2) throws OIQGException {
		
		return userDao.addUser(role2);
		
	}

}
