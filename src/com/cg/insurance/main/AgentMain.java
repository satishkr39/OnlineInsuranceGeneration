package com.cg.insurance.main;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import com.cg.insurance.agentService.AgentService;
import com.cg.insurance.agentServiceImpl.AgentServiceImpl;
import com.cg.insurance.exception.OIQGException;
import com.cg.insurance.linkService.LinkService;
import com.cg.insurance.model.Accounts;
import com.cg.insurance.model.PolicyModel;
import com.cg.insurance.model.UserRole;
import com.cg.insurance.validationService.ServiceValidation;
import com.cg.insurance.validationService.ServiceValidationImpl;

public class AgentMain {
	AdminMain adminMain=new AdminMain();
	Scanner scanner=new Scanner(System.in);
	ServiceValidation validation = new ServiceValidationImpl();
	AgentService agentService=new AgentServiceImpl();
	LinkService link = new LinkService();
	public void agentCase1(UserRole role) throws OIQGException {
		boolean checkUserAccount = false;

		boolean inputFlag = false;
		String insuredName;
		String insuredStreet;
		String insuredState;
		String insuredCity;
		Integer insuredZip = 0;
		String userName1 = "";

		Long accountNumber = 0l;

		do {

			System.out.println("Enter Insured Name");
			insuredName = scanner.nextLine();
			try {
				inputFlag = validation.checkInsuredName(insuredName);
			} catch (OIQGException e) {

				inputFlag = false;
				//logger.error("Insured name not validated");
				System.err.println(e.getMessage());
			}

		} while (inputFlag == false);
		do {

			System.out.println("Enter User Name");
			userName1 = scanner.nextLine();
			try {
				inputFlag = validation.checkInsuredName(userName1);
			} catch (OIQGException e) {
				inputFlag = false;
				//logger.info("Insured username not validated");
				System.err.println(e.getMessage());
			}

		} while (inputFlag == false);

		do {

			System.out.println("Enter Insured Street");
			insuredStreet = scanner.nextLine();
			try {
				inputFlag = validation.checkInsuredStreet(insuredStreet);
			} catch (OIQGException e) {
				inputFlag = false;
				//logger.error("Insured street not validated");
				System.err.println(e.getMessage());
			}

		} while (inputFlag == false);

		do {

			System.out.println("Enter Insured State");
			insuredState = scanner.nextLine();
			try {
				inputFlag = validation.checkInsuredState(insuredState);
			} catch (OIQGException e) {
				inputFlag = false;
				//logger.info("Insured state not validated");
				System.err.println(e.getMessage());
			}

		} while (inputFlag == false);

		do {

			System.out.println("Enter Insured City");
			insuredCity = scanner.nextLine();
			try {
				inputFlag = validation.checkInsuredCity(insuredCity);
			} catch (OIQGException e) {
				inputFlag = false;
				//logger.error("Insured city not validated");
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
				inputFlag = false;
				//logger.error("ZIP not validated");
				System.err.println(e.getMessage());
			} catch (InputMismatchException e) {
				inputFlag = false;
				//logger.error("User pressed invalid digits");
				System.err.println("Please enter digits only");

			}

		} while (inputFlag == false);

		Accounts accounts = new Accounts(insuredName, insuredStreet,
				insuredState, insuredCity, insuredZip, role.getUserName());

		accountNumber = agentService.addNewAccount(accounts);
		link.addDetails(accountNumber, userName1);
		System.out.println(accountNumber);
		
	}
	public void agentCase2(UserRole role) {
		Map<String, Integer> weightage;
		List<String> listQuestions = new ArrayList<>();

		String businessSegment;
		boolean segmentFlag;
		int segmentId;
		int questionId;
		long accNumber = 0l;
		boolean accExist = false;
		boolean ansFlag = false;
		long policyNumber;
		boolean isPolicyDetailsInserted;
		boolean isValidAgent;
		int ansChoice = 0;
		do {
			scanner = new Scanner(System.in);
			System.out.println("Enter account number");

			try {
				accNumber = scanner.nextLong();
				accExist = agentService.isAccountExists(accNumber);

			} catch (OIQGException e) {
				accExist = false;
				//logger.error("Account Number not found");
				System.err.println(e.getMessage());
			} catch (InputMismatchException e) {
				accExist = false;
				//logger.error("User pressed invalid inputs");
				System.err.println("Enter digits");
			}
		} while (!accExist);

		try {
			isValidAgent = agentService.isValidAgent(accNumber,
					role.getUserName());
			if (isValidAgent) {

				scanner.nextLine();

				do {

					System.out.println("Enter Business Segment");
					System.out.println("Business Auto");
					System.out.println("Apartment");
					System.out.println("Restaurant");
					System.out.println("General Merchant");
					businessSegment = scanner.nextLine();
					segmentFlag = agentService
							.validateBusinessSegment(businessSegment);

					if (segmentFlag == false) {
						System.err.println("Please enter valid choice");
					} else {
						int premium = 0;
						segmentId = agentService
								.getSegmentId(businessSegment);
						listQuestions = agentService
								.getQuestions(segmentId);
						Map<Integer, String> policyAnswer = new LinkedHashMap<>();
						for (String question : listQuestions) {
							int weightArr[] = new int[3];
							String answerArr[] = new String[3];
							String answer;

							int count = 1;
							System.out.println(question);
							questionId = agentService
									.getQuestionsId(question, segmentId);

							weightage = agentService
									.getAnswerWeigtage(questionId);
							for (Map.Entry<String, Integer> var : weightage
									.entrySet()) {

								System.out.println("Select" + count
										+ " for: " + var.getKey());

								weightArr[count - 1] = var.getValue();
								answerArr[count - 1] = var.getKey();
								count++;

							}
							count = 1;
							do {
								scanner = new Scanner(System.in);

								System.out.println("Enter your choice:");

								try {
									ansChoice = scanner.nextInt();

									if (ansChoice > 0 && ansChoice < 4) {
										ansFlag = true;
									} else {
										System.err.println(
												"Please enter choice between 1 - 3");
										ansFlag = false;
									}
								} catch (InputMismatchException e) {
									ansFlag = false;
									//logger.info(
											//"User pressed invalid digits");
									System.err.println(
											"Please enter digtis only");

								}
							} while (ansFlag == false);

							premium = premium + weightArr[ansChoice - 1];
							answer = answerArr[ansChoice - 1];
							policyAnswer.put(questionId, answer);

						}

						System.out.println(premium);

						PolicyModel policyModel = new PolicyModel(
								businessSegment, accNumber, premium);
						policyNumber = agentService.addPolicy(policyModel);
						System.out.println(policyNumber);
						isPolicyDetailsInserted = agentService
								.setPolicyDetails(policyAnswer,
										policyNumber);
						System.out.println(isPolicyDetailsInserted);
					}

				} while (!segmentFlag);
			}

		} catch (OIQGException e) {
			//logger.error("Agent is not valid");
			System.err.println(e.getMessage());
		}
		
	}
	public void agentCase3(UserRole role) {
		System.out.println("***** Welcome to Policy Details ******");
		List<Accounts> policyAccInfo = new ArrayList<>();
		List<PolicyModel> policyInfo = new ArrayList<>();

		try {

			policyAccInfo = agentService
					.getCustomerAccDetails(role.getUserName());

			String insuredName1 = "";
			if (!policyAccInfo.isEmpty()) {
				for (Accounts accounts2 : policyAccInfo) {

					if (accounts2.getInsuredName().equals(insuredName1)) {
						continue;
					}
					System.out.println("*******Policy Details For "
							+ accounts2.getInsuredName() + "*******");
					System.out.println(
							"Insured Name: " + accounts2.getInsuredName());
					System.out.println(
							"Insured City: " + accounts2.getInsuredCity());
					System.out.println("Insured Street:  "
							+ accounts2.getInsuredStreet());
					System.out.println(
							"Insured Zip: " + accounts2.getInsuredZip());
					System.out.println("Insured State: "
							+ accounts2.getInsuredState());
					insuredName1 = accounts2.getInsuredName();

					policyInfo = agentService
							.getCustomerPolicyDetails(role.getUserName());

					for (PolicyModel model : policyInfo) {

						System.out.println(
								"Account Number:" + model.getAccountNumber()
										+ "\n Business Segment :"
										+ model.getBusinessSegment()
										+ "\nPolicy premium :"
										+ model.getPolicyPremium());
					}

				}

			} else {
				System.out.println("No Policies Found");
			}

		} catch (OIQGException e) {
			//logger.error("Policy not found");
			System.err.println(e.getMessage());
		}

		
	}

}
