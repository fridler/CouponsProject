package com.yair.coupons.enums;

public enum ErrorTypes {

	// --------------------- ERROR TYPES ---------------------
	GENERAL_ERROR(601, "GENERAL ERROR", "General error", true),
	CUSTOMER_EMAIL_ALREADY_EXISTS(602, "CUSTOMER EMAIL ALREADY EXISTS","The customer email you have entered already exists \nPlease choose a different email",false),
	CUSTOMER_NAME_ALREADY_EXISTS(603, "CUSTOMER NAME ALREADY EXISTS", "The customer name you've chose already exists",false),
	CUSTOMER_ID_DOES_NOT_EXISTS(604, "CUSTOMER ID DOES NOT EXISTS", "The customer id you have entered does not exists",false),
	COMPANY_NAME_IS_ALREADY_EXISTS(605, "COMPANY NAME IS ALREADY EXISTS", "The company name you've chose is already exist. \nPlease choose a different company name.", false),
	COMPANY_ID_DOES_NOT_EXISTS(606, "COMPANY_ID_DOES_NOT_EXISTS", "The company id you have been searching for does not exist. \n please choose a differrent id",false),
	COUPON_TITLE_IS_ALREADY_EXISTS(607,"COUPON_TITLE_IS_ALREADY_EXISTS", "The coupon title you've chose is already exist. \nPlease choose a different coupon title.", false),
	COUPON_ID_DOES_NOT_EXIST(608, "COUPON_ID_DOES_NOT_EXIST","The coupon id you have been searching for does not exist. \\n please choose a differrent id",false),
	USER_ID_DOES_NOT_EXISTS(609, "USER_ID_DOES_NOT_EXISTS","The user id you have entered does not exists", false),
	PURCHASE_ID_DOES_NOT_EXISTS(610,"PURCHASE_ID_DOES_NOT_EXISTS","The purchase id you have entered does not exists",false),
	ZERO_ERROR(611, "ZERO_ERROR","It must be positive", false),
	USERNAME_IS_ALREADY_EXISTS(612, "USERNAME_IS_ALREADY_EXISTS","The username you've chose is already exist. \nPlease choose a different username.", false),
	INVALID_USERNAME(613, "INVALID_USERNAME","The username you've entered is not exist.", false),
	INVALID_ID(614, "INVALID_ID","The ID you've entered is invalid.", false),
	INVALID_AMOUNT(615, "INVALID_AMOUNT","The amount you've entered is invalid.", false),
	INVALID_PRICE(616, "INVALID_PRICE","The price you've entered is invalid.", false),
	INVALID_EMAIL(617, "INVALID_EMAIL","The email address you've entered is invalid. \nPlease enter a valid email address.", false),
	INVALID_PASSWORD(618, "INVALID_PASSWORD","The password you've entered is invalid. \nPlease enter a valid password.", false),
	INVALID_DATES(619, "INVALID_DATES","The dates you've entered are invalid. \nPlease enter valid dates.", false),
	EMPTY_FIELD(620, "EMPTY_FIELD","This field is required.", false),
	NULL_FIELD(621, "NULL_FIELD","This field cannot be NULL", false),
	FIELD_IS_IRREPLACEABLE(622, "FIELD_IS_IRREPLACEABLE","You cannot change this field!", false),
	NAME_IS_IRREPLACEABLE(623, "NAME_IS_IRREPLACEABLE","You cannot change your name!", false),
	COUPON_IS_OUT_OF_ORDER(624, "COUPON_IS_OUT_OF_ORDER","Coupon is out of order...", false),
	TEXT_EXCEEDS_LIMIT(625, "TEXT_EXCEEDS_LIMIT","The text you have Entered exceeds its limit",false),
	NEGATIVE_NUM(626, "NEGATIVE_NUM","The number you have entered must be positive or zero",false),
	PASSWORD_IS_SHORT(627, "PASSWORD_IS_SHORT","password must be at least 4 characters",false),
	LOGIN_FAILED(628, "LOGIN_FAILED","Login has been failed! \nEmail or password are incorrect, Please try again.", false),
	FAILED_CREATE_USER(629, "FAILED_CREATE_USER","Error in UserDao.createUser" ,false),
	FAILED_CREATE_COMPANY(630, "FAILED_CREATE_COMPANY","Error in CompanyDao.createCompany" ,false),
	NAME_ALREADY_EXISTS(631, "NAME_ALREADY_EXISTS","Name already exists",false), 
	NAME_DOES_NOT_EXIST(632, "NAME_DOES_NOT_EXIST","Name does not exist", false), 
	COUPON_CATEGORY_DOES_NOT_EXIST(633, "COUPON_CATEGORY_DOES_NOT_EXIST","Coupon category does not exist", false);


	// --------------------- PROPERTIES ---------------------
	private int errorNumber;
	private String ErrorName;
	private String errorMessage;
	private boolean isShowStackTrace;

	// --------------------- CONSTRUCTORS ---------------------
	private ErrorTypes(int errorNumber, String errorName, String errorMessage, boolean isShowStackTrace) {
		this.errorNumber = errorNumber;
		ErrorName = errorName;
		this.errorMessage = errorMessage;
		this.isShowStackTrace = isShowStackTrace;
	}
	
	// --------------------- GETTERS&SETTERS ---------------------
	public int getErrorNumber() {
		return errorNumber;
	}

	public void setErrorNumber(int errorNumber) {
		this.errorNumber = errorNumber;
	}

	public String getErrorName() {
		return ErrorName;
	}

	public void setErrorName(String errorName) {
		ErrorName = errorName;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public boolean isShowStackTrace() {
		return isShowStackTrace;
	}

	public void setShowStackTrace(boolean isShowStackTrace) {
		this.isShowStackTrace = isShowStackTrace;
	}

}
