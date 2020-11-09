package com.cg.insurance.adminServiceImpl;

import java.util.List;

import com.cg.insurance.adminDao.AdminDao;
import com.cg.insurance.adminDaoImpl.AdminDaoImpl;
import com.cg.insurance.adminService.AdminService;
import com.cg.insurance.exception.OIQGException;
import com.cg.insurance.model.Accounts;
import com.cg.insurance.model.PolicyDetails;
import com.cg.insurance.model.PolicyModel;
import com.cg.insurance.model.ReportGenerationDetails;
import com.cg.insurance.model.UserRole;

public class AdminServiceImpl implements AdminService{

	
	AdminDao adminDao = new AdminDaoImpl();
	@Override
	public int createUserAccount(UserRole admin) throws OIQGException {
		return adminDao.createUserAccount(admin);
	}
	@Override
	public List<Accounts> getAccountsDetails() throws OIQGException {
		
		return adminDao.getAccountDetails();
	}
	@Override
	public List<ReportGenerationDetails> reportGeneration(Long accountNumber, Long policyNumber) throws OIQGException {
		
		return adminDao.reportGeneration(accountNumber,policyNumber);
	}
	@Override
	public List<PolicyModel> viewAllPolicies() throws OIQGException {
		// TODO Auto-generated method stub
		return adminDao.viewAllPolicies();
	}
	@Override
	public long addNewAccount(Accounts accounts) throws OIQGException {
		// TODO Auto-generated method stub
		return adminDao.addNewAccount(accounts);
	}
	@Override
	public List<String> getAgentNames() throws OIQGException {
		
		return adminDao.getAgentNames();
	}
	@Override
	public boolean checkAgentName(String agentName) throws OIQGException {
		// TODO Auto-generated method stub
		return adminDao.checkAgentName(agentName);
	}
	@Override
	public boolean isAccountExists(long accNumber) throws OIQGException {
		
		return adminDao.isAccountExists(accNumber);
	}
	@Override
	public boolean isPolicyExists(Long policyNumber) throws OIQGException {
		
		return adminDao.isPolicyExists(policyNumber);
	}

}
