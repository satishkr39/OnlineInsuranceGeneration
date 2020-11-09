package com.cg.insurance.userDao;

import java.util.List;

import com.cg.insurance.exception.OIQGException;
import com.cg.insurance.model.Accounts;
import com.cg.insurance.model.PolicyModel;
import com.cg.insurance.model.UserRole;

public interface UserDao {

	Boolean validateUser(UserRole role) throws OIQGException;

	String getRole(UserRole role) throws OIQGException;

	int addUser(UserRole role2) throws OIQGException;

	Long addNewAccount(Accounts accounts) throws OIQGException;

	boolean checkAccount(Accounts accounts) throws OIQGException ;

	List<Accounts> getCustomerAccDetails(String userName)throws OIQGException;

	List<PolicyModel> getCustomerPolicyDetails(String userName)throws OIQGException;



	

}
