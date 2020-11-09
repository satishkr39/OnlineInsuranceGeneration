package com.cg.insurance.main;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import com.cg.insurance.exception.OIQGException;
import com.cg.insurance.linkService.LinkService;
import com.cg.insurance.model.Accounts;
import com.cg.insurance.model.PolicyModel;
import com.cg.insurance.model.UserRole;
import com.cg.insurance.userService.UserService;
import com.cg.insurance.userServiceImpl.UserServiceImpl;
import com.cg.insurance.validationService.ServiceValidation;
import com.cg.insurance.validationService.ServiceValidationImpl;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

public class UserMain {
	//PropertyConfigurator.configure("resources/log4j.properties");
	UserService userService = new UserServiceImpl();
	ServiceValidation validation = new ServiceValidationImpl();
	LinkService link = new LinkService();
	public void userCase1(UserRole role) {
		//logger.info("Inside add new account");
		boolean segmentFlag = false;
		boolean checkUserAccount = false;

		String insuredName;
		String insuredStreet;
		String insuredState;
		String insuredCity;
		Integer insuredZip = 0;
		boolean inputFlag = false;

		Long accountNumber = 0l;
		Scanner scanner=new Scanner(System.in);

		do {

			System.out.println("Enter Insured Name");
			insuredName = scanner.nextLine();
			try {
				inputFlag = validation.checkInsuredName(insuredName);
			} catch (OIQGException e) {
				//logger.error("Insured name not validate");
				inputFlag = false;
				System.err.println(e.getMessage());
			}

		} while (inputFlag == false);

		do {

			System.out.println("Enter Insured Street");
			insuredStreet = scanner.nextLine();
			try {
				inputFlag = validation.checkInsuredStreet(insuredStreet);
			} catch (OIQGException e) {
				//logger.error("Insured street not validated");
				inputFlag = false;
				System.err.println(e.getMessage());
			}

		} while (inputFlag == false);

		do {

			System.out.println("Enter Insured State");
			insuredState = scanner.nextLine();
			try {
				inputFlag = validation.checkInsuredState(insuredState);
			} catch (OIQGException e) {
				//logger.error("Insured state not validated");
				inputFlag = false;
				System.err.println(e.getMessage());
			}

		} while (inputFlag == false);

		do {

			System.out.println("Enter Insured City");
			insuredCity = scanner.nextLine();
			try {
				inputFlag = validation.checkInsuredCity(insuredCity);
			} catch (OIQGException e) {
				//logger.error("Insured city not validated");
				inputFlag = false;
				System.err.println(e.getMessage());
			}

		} while (inputFlag == false);

		do {
			scanner = new Scanner(System.in);

			System.out.println("Enter Insured ZIP");

			try {
				insuredZip = scanner.nextInt();
				inputFlag = validation.checkInsuredZip(insuredZip);
			} catch (OIQGException e) {
				//logger.info("ZIP not validated");
				inputFlag = false;
				System.err.println(e.getMessage());
			} catch (InputMismatchException e) {
				//logger.error("User entered invalid digits");
				inputFlag = false;
				System.err.println("Please enter digits only");

			}
		} while (inputFlag == false);

		Accounts accounts = new Accounts(insuredName, insuredStreet,
				insuredState, insuredCity, insuredZip, role.getUserName());

		try {
			checkUserAccount = userService.checkAccount(accounts);

			if (checkUserAccount) {
				accountNumber = userService.addNewAccount(accounts);
				link.addDetails(accountNumber, accounts.getUserName());
				System.out.println(accountNumber);
			}

		} catch (OIQGException e) {
			//logger.error("Account already exist");
			System.err.println("Account Exist");
		}
	}
	public void userCase2(UserRole role) {
		
		List<Accounts> policyAccInfo = new ArrayList<>();
		List<PolicyModel> policyInfo = new ArrayList<>();
		long accNumber = 0l;
		boolean accExist = false;

		try {
			String insuredName1 = "";

			policyAccInfo = userService
					.getCustomerAccDetails(role.getUserName());
			policyInfo = userService
					.getCustomerPolicyDetails(role.getUserName());
			System.out.println("*******Policy Details For "
					+ role.getUserName() + "*******");
			System.out.println(String.format("%-10s %-20s %-20s %-20s ","Insured Name","City","ZIP"," Insured State"));
			if (!policyAccInfo.isEmpty())

			{
				for (Accounts accounts2 : policyAccInfo) {
					if (accounts2.getInsuredName().equals(insuredName1)) {
						continue;
					}

					
					
					System.out.println(String.format("%-10s %-20s %-20s %-20s ",accounts2.getInsuredName(),accounts2.getInsuredCity(),accounts2.getInsuredZip(),accounts2.getInsuredState()));

				}
			
				for (PolicyModel model : policyInfo) {
					System.out.println("Account Number               Business Segment                       PolicyPremium");
					
					System.out.println(model.getAccountNumber()+ "                      " + model.getBusinessSegment()+ "                                  "+ model.getPolicyPremium());
					
				
				}
			} else {
				System.out.println("No Policies Found");
			}
		} catch (OIQGException e) {
			//logger.error("Exception in getting policy");
			System.err.println(e.getMessage());
		}
		
	}
}
