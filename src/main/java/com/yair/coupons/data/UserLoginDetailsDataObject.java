package com.yair.coupons.data;

public class UserLoginDetailsDataObject {

	private String email;
	private String password;
    private long companyId;
    private long userId;
	
	public UserLoginDetailsDataObject() {
	}
	
	public UserLoginDetailsDataObject(String email, String password, long companyId, long userId) {
		this.email = email;
		this.password = password;
		this.companyId = companyId;
		this.userId = userId;
	}

	public UserLoginDetailsDataObject(String email, String password) {
		this.email = email;
		this.password = password;
	}

	public long getCompanyId() {
		return companyId;
	}

	public void setCompanyId(long companyId) {
		this.companyId = companyId;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	
}
