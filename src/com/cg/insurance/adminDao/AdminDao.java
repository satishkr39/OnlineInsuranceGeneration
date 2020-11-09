package com.cg.insurance.adminDao;

import java.util.List;

import com.cg.insurance.exception.OIQGException;
import com.cg.insurance.model.Accounts;
import com.cg.insurance.model.PolicyDetails;
import com.cg.insurance.model.PolicyModel;
import com.cg.insurance.model.ReportGenerationDetails;
import com.cg.insurance.model.UserRole;

public interface AdminDao {

	int createUserAccount(UserRole admin) throws OIQGException;

	List<Accounts> getAccountDetails()  throws OIQGException;

	List<ReportGenerationDetails> reportGeneration(Long accountNumber, Long policyNumber)throws OIQGException;

	List<PolicyModel> viewAllPolicies() throws OIQGException;

	long addNewAccount(Accounts accounts) throws OIQGException;

	List<String> getAgentNames() throws OIQGException;

	boolean checkAgentName(String agentName) throws OIQGException;

	boolean isAccountExists(long accNumber)throws OIQGException;

	boolean isPolicyExists(Long policyNumber)throws OIQGException;

}
