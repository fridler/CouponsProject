package com.yair.coupons.exceptions;

import com.yair.coupons.enums.ErrorTypes;

public class ApplicationException extends RuntimeException {
	
	// --------------------- PROPERTIES ---------------------
	private static final long serialVersionUID = 1L;
	private ErrorTypes errorType;

	// --------------------- CONSTRUCTORS ---------------------
	// This constructor will be used in a case of an exception (goes to DAO)
	public ApplicationException(Exception e, ErrorTypes errorType, String message) {
		super(message, e);
		this.errorType = errorType;
	}

	// This constuctor will be used in a case of an exception we built- Validations (goes to Controller)
	public ApplicationException(ErrorTypes errorType, String message) {
		super(message);
		this.errorType = errorType;
	}
	
	// --------------------- FUNCTIONS ---------------------
	public ErrorTypes getErrorType(){
		return this.errorType;
	}
}
