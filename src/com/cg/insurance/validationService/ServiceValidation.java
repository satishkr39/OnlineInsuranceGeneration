package com.cg.insurance.validationService;

import com.cg.insurance.exception.OIQGException;

public interface ServiceValidation {

	boolean checkInsuredName(String insuredName) throws OIQGException;

	boolean checkInsuredStreet(String insuredStreet) throws OIQGException;

	boolean checkInsuredState(String insuredState) throws OIQGException;

	boolean checkInsuredCity(String insuredCity) throws OIQGException;

	boolean checkInsuredZip(Integer insuredZip) throws OIQGException;

	boolean validateUserName(String newUserName)throws OIQGException;

	boolean validatePassword(String newUserpassword)throws OIQGException;

	boolean validateRole(String roleCode)throws OIQGException;

}
