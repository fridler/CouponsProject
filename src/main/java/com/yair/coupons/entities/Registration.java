package com.yair.coupons.entities;

import com.yair.coupons.enums.UserType;

public class Registration {
	
	public Customer customer;
	public User user;
	
	
	public Registration() {
		super();
	}

	public Registration(Customer customer, User user) {
		this.customer = customer;
		this.user = user;
		user.setType(UserType.CUSTOMER);
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
}
