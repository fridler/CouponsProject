package com.yair.coupons.enums;

public enum UserType {

	// All kinds of user types in the program
	CUSTOMER("CUSTOMER"),
	COMPANY("COMPANY"),
	ADMIN("ADMIN");
	
	private final String userType;

	private UserType(String userType) {
		this.userType = userType;
	}

	public String getUserType() {
		return userType;
	}
	

}
