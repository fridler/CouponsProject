package com.yair.coupons.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="Companies")
public class Company implements Serializable{
	
	@Id
	@GeneratedValue
	@Column(name = "id")
	private long companyId;
	
	@Column(name="name", unique = true, nullable = false)
	private String name;
	
	@Column(name="address", nullable = false)
	private String address;
	
//	@JsonIgnore
	@OneToMany(mappedBy = "company",cascade = CascadeType.ALL, fetch=FetchType.EAGER)
	private List<User> users;
	
//	@JsonIgnore
	@OneToMany(mappedBy = "company",cascade = CascadeType.ALL, fetch=FetchType.LAZY)
	private List<Coupon> coupons;
	
//	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
//	@JoinColumn(name = "id")
//	@JsonIgnore
//	private User user;
	
	
	//Default  (means NO parameters)
	public Company(){
	}


	public Company(long companyId, String name, String address, List<User> users, List<Coupon> coupons/*, User user*/) {
		this.companyId = companyId;
		this.name = name;
		this.address = address;
		this.users = users;
		this.coupons = coupons;
//		this.user = user;
	}

	public Company(String name, String address, List<User> users, List<Coupon> coupons/*, User user*/) {
		this.name = name;
		this.address = address;
		this.users = users;
		this.coupons = coupons;
//		this.user = user;
	}

//	public User getUser() {
//		return user;
//	}
//
//
//	public void setUser(User user) {
//		this.user = user;
//	}


	public long getCompanyId() {
		return companyId;
	}


	public void setCompanyId(long companyId) {
		this.companyId = companyId;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getAddress() {
		return address;
	}


	public void setAddress(String address) {
		this.address = address;
	}


	public List<User> getUsers() {
		return users;
	}


	public void setUsers(List<User> users) {
		this.users = users;
	}


	public List<Coupon> getCoupons() {
		return coupons;
	}


	public void setCoupons(List<Coupon> coupons) {
		this.coupons = coupons;
	}


	@Override
	public String toString() {
		return "Company [companyId=" + companyId + ", name=" + name + ", address=" + address + ", users=" + users
				+ ", coupons=" + coupons + "]";
	}



	


}