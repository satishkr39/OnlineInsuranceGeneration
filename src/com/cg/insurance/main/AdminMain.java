package com.cg.insurance.main;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import com.cg.insurance.adminService.AdminService;
import com.cg.insurance.adminServiceImpl.AdminServiceImpl;
import com.cg.insurance.agentService.AgentService;
import com.cg.insurance.agentServiceImpl.AgentServiceImpl;
import com.cg.insurance.exception.OIQGException;
import com.cg.insurance.linkService.LinkService;
import com.cg.insurance.model.Accounts;
import com.cg.insurance.model.PolicyModel;
import com.cg.insurance.model.ReportGenerationDetails;
import com.cg.insurance.model.UserRole;
import com.cg.insurance.validationService.ServiceValidation;
import com.cg.insurance.validationService.ServiceValidationImpl;

public class AdminMain {
	AdminService adminService=new AdminServiceImpl();
	LinkService link = new LinkService();
	ServiceValidation validation = new ServiceValidationImpl();
	AgentService agentService=new AgentServiceImpl();
	Scanner scanner=new Scanner(System.in);
	public void adminCase1(UserRole role) throws OIQGException {
		String newUserName;
		String newUserpassword;
		String roleCode;
		long genUserId;
		boolean inputFlag=false;
		String userName;

		System.out.println("Enter the user details1");

		scanner.nextLine();
		do {

			System.out.println("Enter user Name");
			newUserName = scanner.nextLine();
			try {
				inputFlag = validation.validateUserName(newUserName);
				userName = scanner.nextLine();
			} catch (OIQGException e) {
				//logger.error("User Name not validated");
				inputFlag = false;
				System.err.println(e.getMessage());
			}

		} while (inputFlag == false);
		do {

			System.out.println("Enter password");
			newUserpassword = scanner.nextLine();
			try {
				inputFlag = validation.validatePassword(newUserpassword);
			} catch (OIQGException e) {
				//logger.error("Password not validated");
				inputFlag = false;
				System.err.println(e.getMessage());
			}

		} while (inputFlag == false);

		do {

			System.out.println("Enter role");
			roleCode = scanner.nextLine();
			try {
				inputFlag = validation.validateRole(roleCode);

			} catch (OIQGException e) {
				//logger.error("Role not validated");
				inputFlag = false;
				System.err.println(e.getMessage());
			}

		} while (inputFlag == false);

		UserRole admin = new UserRole(newUserName, newUserpassword,
				roleCode);
		System.out.println(admin.getUserName());
		System.out.println(admin.getPassword());
		System.out.println(admin.getRoleCode());

		genUserId = adminService.createUserAccount(admin);

		System.out.println(genUserId);
		
	}
	public void adminCase2(UserRole role) throws OIQGException {
		System.out.println("View Accounts");
		List<Accounts> accounts = new ArrayList<>();
		accounts = adminService.getAccountsDetails();
		if (!accounts.isEmpty()) {
			for (Accounts accounts2 : accounts) {

				System.out.println("*****Account Details for "
						+ accounts2.getInsuredName() + " *******");
				System.out.println("Account Number "
						+ accounts2.getAccountNumber() + "\nInsured Street:"
						+ accounts2.getInsuredStreet() + "\n Insured City: "
						+ accounts2.getInsuredCity() + " "
						+ "\nInsured State: " + accounts2.getInsuredState()
						+ "\n Insured Zip: " + accounts2.getInsuredZip());
			}

		} else {
			System.out.println("No accounts");
		}

		
	}
	public void adminCase3(UserRole role) throws OIQGException {
		boolean inputFlag=false;
		System.out.println("Create Accounts");

		boolean checkUserAccount = false;

		long accountNumber1;
		String insuredName;
		String insuredStreet;
		String insuredState;
		String insuredCity;
		Integer insuredZip = 0;
		String agentName;
		List<String> agentNames;
		String agentArray[] = { "" };
		int i = 0;
		String userName2 = "";
		boolean isAgentExits = false;
		scanner.nextLine();
		boolean isAgentExist=false;

		do {

			System.out.println("Enter Insured Name");

			insuredName = scanner.nextLine();
			try {
				inputFlag = validation.checkInsuredName(insuredName);
			} catch (OIQGException e) {
				inputFlag = false;
				//logger.error("Insured name not validatede");
				System.err.println(e.getMessage());
			}

		} while (inputFlag == false);
		do {

			System.out.println("Enter User Name");

			userName2 = scanner.nextLine();
			try {
				inputFlag = validation.checkInsuredName(insuredName);
			} catch (OIQGException e) {
				//logger.error("User name not validated");
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
				//logger.error("Street not validated");
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
				//logger.error("State not validated");
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
				//logger.error("City not validated");
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
				//logger.error("ZIP not validated");
				inputFlag = false;
				System.err.println(e.getMessage());
			} catch (InputMismatchException e) {
				//logger.error("ZIP not validated");
				inputFlag = false;
				System.err.println("Please enter digits only");

			}

		} while (inputFlag == false);
		System.out.println("Available agents are: Please select any one");
		agentNames = adminService.getAgentNames();

		do {
			for (String agent : agentNames) {
				System.out.println(agent);
			}
			scanner = new Scanner(System.in);
			System.out.println("Enter agent name");
			agentName = scanner.nextLine();

			try {
				isAgentExits = adminService.checkAgentName(agentName);
				Accounts accounts1 = new Accounts(insuredName,
						insuredStreet, insuredState, insuredCity,
						insuredZip, agentName);
				//System.out.println("Hi");
				accountNumber1 = adminService.addNewAccount(accounts1);
				System.out.println(accountNumber1);
				link.addDetails(accountNumber1, userName2);
				//System.out.println("Hello");
				System.out.println("Account created with account no:"
						+ accountNumber1);

			} catch (OIQGException e) {
				//logger.error("Account number not validated");
				isAgentExits = false;
				System.err.println(e.getMessage());
			}

		} while (!isAgentExits);

		
	}
	public void adminCase4(UserRole role) {
		System.out.println("****Create Policy*****");
		Map<String, Integer> weightage;
		List<String> listQuestions = new ArrayList<>();
		List<String> agentNames1 = null;

		String businessSegment;
		boolean segmentFlag;
		int segmentId;
		int questionId;
		long accNumber = 0l;
		boolean accExist;
		long policyNumber1;
		boolean isPolicyDetailsInserted;
		boolean isValidAgent;
		String agentName1;
		int ansChoice = 0;
		boolean ansFlag = false;

		try {
			do {
				scanner = new Scanner(System.in);
				System.out.println("Enter account number");

				try {
					accNumber = scanner.nextLong();
					accExist = agentService.isAccountExists(accNumber);

				} catch (OIQGException e) {
					//logger.error("Account number not validated");
					accExist = false;
					System.err.println(e.getMessage());
				} catch (InputMismatchException e) {
					//logger.error("User entered non digits");
					accExist = false;
					System.err.println("Enter digits");
				}
			} while (!accExist);

			boolean isAgentExits=false;
			do {
				System.out.println(
						"Available agents are: Please select any one");
				agentNames1 = adminService.getAgentNames();
				for (String agent : agentNames1) {
					System.out.println(agent);
				}
				scanner = new Scanner(System.in);
				System.out.println("Enter agent name");
				agentName1 = scanner.nextLine();

				try {
					isAgentExits = adminService.checkAgentName(agentName1);

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
								questionId = agentService.getQuestionsId(
										question, segmentId);

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

									System.out
											.println("Enter your choice:");

									try {
										ansChoice = scanner.nextInt();

										if (ansChoice > 0
												&& ansChoice < 4) {
											ansFlag = true;
										} else {
											System.err.println(
													"Please enter choice between 1 - 3");
											ansFlag = false;
										}
									} catch (InputMismatchException e) {
										ansFlag = false;

										System.err.println(
												"Please enter digtis only");

									}
								} while (ansFlag == false);

								premium = premium
										+ weightArr[ansChoice - 1];
								answer = answerArr[ansChoice - 1];
								policyAnswer.put(questionId, answer);

							}

							for (Map.Entry<Integer, String> var : policyAnswer
									.entrySet()) {
								System.out.println(
										var.getKey() + var.getValue());

							}

							System.out.println(premium);

							PolicyModel policyModel = new PolicyModel(
									businessSegment, accNumber, premium);
							policyNumber1 = agentService
									.addPolicy(policyModel);
							System.out.println(policyNumber1);
							isPolicyDetailsInserted = agentService
									.setPolicyDetails(policyAnswer,
											policyNumber1);

						}

					} while (!segmentFlag);
				} catch (OIQGException e) {
					//logger.error("Segment not validated");
					isAgentExits = false;
					System.err.println(e.getMessage());
				}
			} while (!isAgentExits);

		} catch (Exception e) {
			//logger.error("Exception ");
			System.out.println(e.getMessage());
		}
		
	}
	public void adminCase5(UserRole role) throws OIQGException {
		System.out.println("View policies");
		List<PolicyModel> policyDetails = new ArrayList<>();

		policyDetails = adminService.viewAllPolicies();
		if (!policyDetails.isEmpty()) {
			for (PolicyModel policyModel : policyDetails) {
				System.out.println("*********");
				System.out.println(policyModel.getBusinessSegment());
				System.out.println(policyModel.getAccountNumber());
				System.out.println(policyModel.getPolicyNumber());
				System.out.println(policyModel.getPolicyPremium());
			}

		} else {
			System.out.println("No policy exists");
		}
		
	}
	public void adminCase6(UserRole role) throws OIQGException {
		boolean accExist=false;
		List<ReportGenerationDetails> reportGenList = new ArrayList<>();
		Long accountNumber = 0l;
		Long policyNumber = 0l;
		do {
			scanner = new Scanner(System.in);
			System.out.println("Enter account number");

			try {
				accountNumber = scanner.nextLong();
				accExist = adminService.isAccountExists(accountNumber);

			} catch (OIQGException e) {
				//logger.error("account number not found");
				accExist = false;
				System.err.println(e.getMessage());
			} catch (InputMismatchException e) {
				//logger.error("User entered invalid digits");
				accExist = false;
				System.err.println("Enter digits");
			}
		} while (!accExist);

		do {
			scanner = new Scanner(System.in);
			System.out.println("Enter policy number");

			try {
				policyNumber = scanner.nextLong();
				accExist = adminService.isPolicyExists(policyNumber);

			} catch (OIQGException e) {
				//logger.error("account not found");
				accExist = false;
				System.err.println(e.getMessage());
			} catch (InputMismatchException e) {
				//logger.error("User pressed invalid digits");
				accExist = false;
				System.err.println("Enter digits");
			}
		} while (!accExist);

		reportGenList = adminService.reportGeneration(accountNumber,
				policyNumber);
		if (!reportGenList.isEmpty()) {
			for (ReportGenerationDetails reportGenerationDetails : reportGenList) {
				System.out.println("*********DETAILS  FOR "
						+ reportGenerationDetails.getInsuredName());
				System.out.println("Insured Name:"
						+ reportGenerationDetails.getInsuredName());
				System.out.println("Insured City:"
						+ reportGenerationDetails.getInsuredCity());
				System.out.println("Insured State:"
						+ reportGenerationDetails.getInsuredState());
				System.out.println("Insured Street:"
						+ reportGenerationDetails.getInsuredStreet());
				System.out.println("Insured ZIP:"
						+ reportGenerationDetails.getInsuredZip());
				System.out.println("Account Number:"
						+ reportGenerationDetails.getAccountNumber());
				System.out.println("Business Segment:"
						+ reportGenerationDetails.getBusinessSegment());
				System.out.println("Question Description:"
						+ reportGenerationDetails.getQuesDescription());
				System.out.println(
						"Answer:" + reportGenerationDetails.getAnswer());
				System.out.println("Policy Number.:"
						+ reportGenerationDetails.getPolicyNumber());
				System.out.println("Policy Premium:"
						+ reportGenerationDetails.getPolicyPremium());
				System.out.println("User Name:"
						+ reportGenerationDetails.getUserName());

			}
		} else {
			System.out.println("No reports can be generated");
		}
		
	}
}
