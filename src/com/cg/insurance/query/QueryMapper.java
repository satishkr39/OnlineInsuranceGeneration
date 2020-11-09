package com.cg.insurance.query;

public interface QueryMapper {

	String validateQuery = "SELECT password FROM user_role WHERE user_name=?";
	String getRoleQuery = "SELECT role_code FROM user_role WHERE user_name=? ";
	String createQuery = "INSERT INTO user_role(user_name, password, role_code, user_id) values(?, ?, ?, user_id_seq.nextval)";
	String getIdQuery = "SELECT user_id_seq.currval FROM dual";

}
