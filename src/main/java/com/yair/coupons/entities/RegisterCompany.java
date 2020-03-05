package com.yair.coupons.entities;

import com.yair.coupons.enums.UserType;

public class RegisterCompany {

    private Company company;
    private User user;

    public RegisterCompany() {
		super();
	}

	public RegisterCompany(Company company, User user) {
        this.company = company;
        this.user = user;
        user.setType(UserType.COMPANY);
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
