package com.cg.insurance.userServiceImpl;

import java.util.List;
import java.util.regex.Pattern;

import com.cg.insurance.exception.OIQGException;
import com.cg.insurance.model.Accounts;
import com.cg.insurance.model.PolicyModel;
import com.cg.insurance.model.UserRole;
import com.cg.insurance.userDao.UserDao;
import com.cg.insurance.userDaoImpl.UserDaoImpl;
import com.cg.insurance.userService.UserService;

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

	@Override
	public Long addNewAccount(Accounts accounts) throws OIQGException {

		return userDao.addNewAccount(accounts);
	}

	@Override
	public boolean validateBusinessSegment(String businessSegment) throws OIQGException {

		String regex = "BusinessAuto|Apartment|GeneralMerchant|Restaraunt";
		return Pattern.matches(regex, businessSegment);

	}

	@Override
	public boolean checkAccount(Accounts accounts) throws OIQGException {

		return userDao.checkAccount(accounts);
	}

	@Override
	public boolean validateUserName(String userName) throws OIQGException {
		boolean isValidUname = false;
		String unameRegEx = "[A-Za-z]{1,10}";
		isValidUname = Pattern.matches(unameRegEx, userName);
		if (isValidUname == false) {
			throw new OIQGException("Invlid username...Start with caps");
		}
		return isValidUname;
	}

	@Override
	public List<Accounts> getCustomerAccDetails(String userName) throws OIQGException {
		return userDao.getCustomerAccDetails(userName);
	}

	@Override
	public List<PolicyModel> getCustomerPolicyDetails(String userName) throws OIQGException {
		return userDao. getCustomerPolicyDetails(userName);
	}

	

	

}
