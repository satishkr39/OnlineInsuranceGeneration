package com.cg.insurance.ui;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import com.cg.insurance.adminService.AdminService;
import com.cg.insurance.adminServiceImpl.AdminServiceImpl;
import com.cg.insurance.agentService.AgentService;
import com.cg.insurance.agentServiceImpl.AgentServiceImpl;
import com.cg.insurance.exception.OIQGException;
import com.cg.insurance.linkService.LinkService;
import com.cg.insurance.main.AdminMain;
import com.cg.insurance.main.AgentMain;
import com.cg.insurance.main.UserMain;
import com.cg.insurance.model.Accounts;
import com.cg.insurance.model.PolicyModel;
import com.cg.insurance.model.ReportGenerationDetails;
import com.cg.insurance.model.UserRole;
import com.cg.insurance.userService.UserService;
import com.cg.insurance.userServiceImpl.UserServiceImpl;
import com.cg.insurance.validationService.ServiceValidation;
import com.cg.insurance.validationService.ServiceValidationImpl;

public class InsuranceMain {
	static Logger logger = Logger.getLogger(InsuranceMain.class);

	@SuppressWarnings({ "unused", "resource" })
	public static void main(String[] args) {

		PropertyConfigurator.configure("resources/log4j.properties");
		logger.info("log4j file configured");

		UserService userService = new UserServiceImpl();
		AgentService agentService = new AgentServiceImpl();
		ServiceValidation validation = new ServiceValidationImpl();
		AgentMain agentMain = new AgentMain();
		AdminMain adminMain = new AdminMain();
		UserMain userMain = new UserMain();
		LinkService link = new LinkService();
		Scanner scanner = null;
		boolean choiceFlag = false;
		int choice = 0;
		String userName;
		String password = "";
		Boolean userFlag = false;
		int option;
		String roleSwitch = "";
		String userRole = "";
		boolean isValidUserName = false;
		boolean outerFlag = false;
		int outerChoice = 0;
		do {
			scanner = new Scanner(System.in);
			System.out.println("Enter 1 for login\nEnter 2 for exit ");
			try {
				outerChoice = scanner.nextInt();
				scanner.nextLine();
				switch (outerChoice) {
				case 1:
					do {
						logger.info("Inside the login");
						scanner = new Scanner(System.in);
						System.out.println("Enter your details");

						System.out.println("Enter username");
						userName = scanner.nextLine();

						System.out.println("Enter password");
						password = scanner.nextLine();

						UserRole role = new UserRole(userName, password);

						try {

							userFlag = userService.validateUser(role);

							if (userFlag == true) {

								roleSwitch = userService.getRole(role);

								boolean userChoiceFlag = false;

								switch (roleSwitch) {

								case "user":
									logger.info("inside user case");
									boolean userExitFlag = false;
									do {
										System.out.println("*** Welcome " + role.getUserName() + " ***");
										System.out.println("1. Add new Account ");
										System.out.println("2. View Policy");
										System.out.println("3. Logout");
										System.out.println("4. Exit Application ");
										do {
											scanner = new Scanner(System.in);
											try {
												int userOption = scanner.nextInt();
												scanner.nextLine();
												userChoiceFlag = true;
												switch (userOption) {
												case 1:
													userMain.userCase1(role);

													break;
												case 2:
													logger.info("Inside case 2 of user");
													userMain.userCase2(role);

													break;

												case 3:
													userFlag = false;
													userExitFlag = true;
													System.out.println("**** Successfully Logged Out ****");
													logger.info("Logging Out");
													break;

												case 4:
													logger.info("Application Closed");
													System.out.println("**** Application Closed *****");
													System.exit(0);

													break;

												default:
													userChoiceFlag = false;
													System.out.println("Please enter choice : 1 - 4 only");
													logger.info("User pressed invalid digits");
													break;
												}
											} catch (Exception e) {
												logger.error("Entered invalid digits");
												userChoiceFlag = false;
												System.err.println("Please enter valid digits");
											}

										} while (!userChoiceFlag);

									} while (!userExitFlag);
									break;

								case "agent":

									logger.info("Inside Agent Module");

									boolean agentExitFlag = false;

									System.out.println("**** Welcome " + role.getUserName() + " *****");
									do {
										boolean agentChoiceFlag = false;
										System.out.println(
												"1. Create User Account  \n2.Create policies   \n3.View Policy Details \n4.Logout \n5.Exit");
										System.out.println("Enter your choice:");

										do {
											scanner = new Scanner(System.in);
											try {
												int agentOption = scanner.nextInt();
												scanner.nextLine();
												agentChoiceFlag = true;
												switch (agentOption) {
												case 1:
													agentMain.agentCase1(role);

													break;

												case 2:

													logger.info("In case 2 of agent module");
													agentMain.agentCase2(role);

													break;

												case 3:
													logger.info("In case 3 of agent module");
													agentMain.agentCase3(role);

													break;

												case 4:
													logger.info("In case 4 of agent module");
													userFlag = false;
													agentExitFlag = true;
													System.out.println("**** Successfully Logged Out ****");
													break;

												case 5:
													logger.info("Closing application");
													System.out.println("**** Application Closed *****");
													System.exit(0);
													break;

												default:
													logger.error("User pressed invalid digits");
													agentChoiceFlag = false;
													System.out.println("Please enter choice between 1 & 5 only");
													break;

												}
											}

											catch (InputMismatchException e) {
												logger.error("User entered invalid inputs");
												agentChoiceFlag = false;
												System.err.println("Please enter  digits only");
											}

										} while (!agentChoiceFlag);

									} while (!agentExitFlag);
									break;

								case "admin":

									logger.info("In admin module");
									boolean adminChoiceFlag = false;
									boolean adminExitFlag = false;
									boolean inputFlag = false;
									String userName1 = "";

									AdminService adminService = new AdminServiceImpl();

									System.out.println("Welcome Admin");
									do {
										System.out.println("1. Create Profile");
										System.out.println("2.View accounts");
										System.out.println("3. Create Accounts");
										System.out.println("4.Create Policies");
										System.out.println("5. View Policies");
										System.out.println("6.Generate Reports");
										System.out.println("7.Logout");
										System.out.println("6.Exit Application");
										int adminChoice = 0;
										do {

											scanner = new Scanner(System.in);
											System.out.println("Enter your choice");
											try {
												adminChoice = scanner.nextInt();
												adminChoiceFlag = true;

												switch (adminChoice) {
												case 1:
													logger.info("In case 1 of admin module");
													adminMain.adminCase1(role);

													break;
												case 2:
													logger.info("In case 2 of admin module");
													adminMain.adminCase2(role);

													break;
												case 3:
													logger.info("In case 3 of admin module");
													adminMain.adminCase3(role);

													break;

												case 4:

													logger.info("in case 4 of admin module");
													adminMain.adminCase4(role);

													break;

												case 5:
													logger.info("In case 5 of admin  module");
													adminMain.adminCase5(role);

													break;

												case 6:
													logger.info("In case 6 of admin module");
													adminMain.adminCase6(role);

													break;

												case 7:
													logger.info("in case 7 of admin module");
													userFlag = false;
													adminExitFlag = true;
													System.out.println("**** Successfully Logged Out ****");
													break;

												case 8:
													logger.info("in case 8 of admin module");
													System.out.println("**** Application Closed *****");
													System.exit(0);
													break;

												default:
													logger.info("user pressed invalid digits");
													adminChoiceFlag = false;
													System.out.println("Please enter choice between 1 to 8 only");
													break;
												}
											} catch (InputMismatchException e) {
												logger.error("User pressed invalid digits");
												adminChoiceFlag = false;
												System.err.println("Enter only digits");
											}
										} while (!adminChoiceFlag);

									} while (!adminExitFlag);

									break;
								default:
									break;
								}

							} else {
								System.err.println("Invalid Username and Password");
								userFlag = false;

							}

						} catch (Exception e) {
							System.err.println(e.getMessage());
						}

					} while (userFlag == false);

					break;
				case 2:
					logger.info("In case2 : closing application");
					System.out.println("Application closing");
					System.exit(0);
					break;

				default:
					logger.info("user pressed invalid digits");
					System.out.println("Enter 1 or 2 only");
					outerFlag = false;
					break;
				}
			} catch (InputMismatchException e) {
				logger.error("InputMisMatch exception met");
				outerFlag = false;
				System.err.println("Digits only");
			}

		} while (!outerFlag);

	}
}
