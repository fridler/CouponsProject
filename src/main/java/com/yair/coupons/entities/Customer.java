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
@Table(name="Customers")
public class Customer implements Serializable{

	@Id
	@GeneratedValue
	@Column(name="id")
	private long customerId; 
	
	@Column(name="name", nullable = false)
	private String name; 

	@Column(name="address", nullable = false)
	private String address; 
	
	@OneToMany(mappedBy = "customer", cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
	private List<Purchase> purchases;  

	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "id")
	@JsonIgnore
	private User user;
	
	
	public Customer() {
		super();
	}


	public Customer(long customerId, String name, String address, List<Purchase> purchases, User user) {
		super();
		this.customerId = customerId;
		this.name = name;
		this.address = address;
		this.purchases = purchases;
		this.user = user;
	}


	public Customer(String name, String address, List<Purchase> purchases, User user) {
		super();
		this.name = name;
		this.address = address;
		this.purchases = purchases;
		this.user = user;
	}


	public long getCustomerId() {
		return customerId;
	}


	public void setCustomerId(long customerId) {
		this.customerId = customerId;
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


	public List<Purchase> getPurchases() {
		return purchases;
	}


	public void setPurchases(List<Purchase> purchases) {
		this.purchases = purchases;
	}


	public User getUser() {
		return user;
	}


	public void setUser(User user) {
		this.user = user;
	}


	@Override
	public String toString() {
		return "Customer [customerId=" + customerId + ", name=" + name + ", address=" + address + ", purchases="
				+ purchases + ", user=" + user + "]";
	}

	
	
	
}
