package com.cg.insurance.validationService;

import java.util.regex.Pattern;

import com.cg.insurance.exception.OIQGException;

public class ServiceValidationImpl implements ServiceValidation {

	@Override
	public boolean checkInsuredName(String insuredName) throws OIQGException {
		boolean isValidInsuredName = false;
		String nameRegEx = "[A-Z]{1}[A-Za-z]{2,30}";
		isValidInsuredName = Pattern.matches(nameRegEx, insuredName);
		if (isValidInsuredName == false) {
			throw new OIQGException("Invlid Insured Name...Start with caps");
		}
		return isValidInsuredName;

	}

	@Override
	public boolean checkInsuredStreet(String insuredStreet) throws OIQGException {
		boolean isValidInsuredStreet= false;
		String streetRegEx = "[A-Za-z0-9 /]{2,30}";
		isValidInsuredStreet = Pattern.matches(streetRegEx, insuredStreet);
		if (isValidInsuredStreet == false) {
			throw new OIQGException("Invalid Street Name");
		}
		return isValidInsuredStreet;
	}

	@Override
	public boolean checkInsuredState(String insuredState) throws OIQGException {
		boolean isValidInsuredState = false;
		String stateRegEx = "[A-Za-z ]{2,30}";
		isValidInsuredState = Pattern.matches(stateRegEx, insuredState);
		if (isValidInsuredState == false) {
			throw new OIQGException("Invalid Insured State, min length is 2 letters");
		}
		return isValidInsuredState;
	}

	@Override
	public boolean checkInsuredCity(String insuredCity) throws OIQGException {
		boolean isValidInsuredCity = false;
		String cityRegEx = "[A-Za-z ]{2,30}";
		isValidInsuredCity = Pattern.matches(cityRegEx, insuredCity);
		if (isValidInsuredCity== false) {
			throw new OIQGException("Invalid Insured City, min length is 2 letters");
		}
		return isValidInsuredCity;
	}

	@Override
	public boolean checkInsuredZip(Integer insuredZip) throws OIQGException {
		boolean isValidInsuredZip = false;
		
		String zipRegEx = "[0-9]{5}";
		
		isValidInsuredZip = Pattern.matches(zipRegEx, insuredZip.toString());
		if (isValidInsuredZip == false) {
			throw new OIQGException("Invalid PIN Code. Please enter 5 digits only");
		}
		return isValidInsuredZip;
	}

	@Override
	public boolean validateUserName(String newUserName) throws OIQGException {
		boolean isValidUserName = false;
		String nameRegEx = "[A-Za-z ]{1,7}";
		isValidUserName = Pattern.matches(nameRegEx,newUserName);
		if (isValidUserName == false) {
			throw new OIQGException("Invalid User Name.  maximum 7 letters");
		}
		return isValidUserName;
	}

	@Override
	public boolean validatePassword(String newUserpassword) throws OIQGException {
		boolean isValidPassword = false;
		String nameRegEx = "[A-Za-z0-9]{2,10}";
		isValidPassword = Pattern.matches(nameRegEx,newUserpassword);
		if (isValidPassword == false) {
			throw new OIQGException("Password invalid");
		}
		return isValidPassword;
	}

	@Override
	public boolean validateRole(String roleCode) throws OIQGException {
		boolean isValidRole = false;
		String roleRegEx = "user|agent|admin";
		isValidRole = Pattern.matches(roleRegEx,roleCode);
		if (isValidRole == false) {
			throw new OIQGException("Enter user or agent or admin only");
		}
		return isValidRole;
	}

}
