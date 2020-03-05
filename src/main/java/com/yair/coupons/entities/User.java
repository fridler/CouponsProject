package com.yair.coupons.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.yair.coupons.enums.UserType;

@Entity
@Table(name="Users")
public class User implements Serializable{

	@Id
	@GeneratedValue
	@Column(name="id")
	private long userId;
	
	// userName == Email
	@Column(name="email", unique = true, nullable = false)
	private String email;
	
	@Column(name="password", nullable = false)
	private String password;
	
	@Column(name="type", nullable = false)
	@Enumerated(EnumType.STRING)
	private UserType type;
	 
	//@Column(name="CompanyId")
//	@JoinColumn(name = "companyId" ,nullable = true, insertable = false, updatable = false)
	@ManyToOne
	@JsonIgnore
	private Company company;
	 
//	@OneToOne(mappedBy = "user")
	@ManyToOne
//	@JoinColumn(name = "id")
	@JsonIgnore
	private Customer customer;
	
	
	public User() {
	}


	public User(String email, String password, UserType type, Company company, Customer customer) {
		this.email = email;
		this.password = password;
		this.type = type;
		this.company = company;
		this.customer = customer;
	}

	public User(long userId, String email, String password, UserType type, Company company, Customer customer) {
		this.userId = userId;
		this.email = email;
		this.password = password;
		this.type = type;
		this.company = company;
		this.customer = customer;
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

	public UserType getType() {
		return type;
	}

	public void setType(UserType type) {
		this.type = type;
	}

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	public Customer getCustomer() {
		return customer;
	}
	
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	
	@Override
	public String toString() {
		return "User [userId=" + userId + ", email=" + email + ", password=" + password + ", type=" + type
				+ ", company=" + company + ", customer=" + customer + "]";
	}


	
	
	
}
