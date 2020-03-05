package com.yair.coupons.entities;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="Purchases")
public class Purchase implements Serializable{

	@Id
	@GeneratedValue
	@Column(name="id") 
	private long purchaseId; 
	
	@Column(name="amount", nullable = false) 
	private int amount;
	
	@ManyToOne
	@JsonIgnore
	@NotNull
	@JoinColumn(name = "couponId")
	private Coupon coupon; 
	
	@ManyToOne
	@JsonIgnore
	@JoinColumn(name = "customerId")
	private Customer customer; 
	
	
	public Purchase() {
		
	}

	public Purchase(long purchaseId, int amount, Coupon coupon, Customer customer) {
		this.purchaseId = purchaseId;
		this.amount = amount;
		this.coupon = coupon;
		this.customer = customer;
	}

	public Purchase(Customer customer, Coupon coupon, int amount) {
		this.customer = customer;
		this.coupon = coupon;
		this.amount = amount;
	}
	
	public long getPurchaseId() {
		return purchaseId;
	}

	public void setPurchaseId(long purchaseId) {
		this.purchaseId = purchaseId;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Coupon getCoupon() {
		return coupon;
	}

	public void setCoupon(Coupon coupon) {
		this.coupon = coupon;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	@Override
	public String toString() {
		return "Purchase [purchaseId=" + purchaseId + ", customer=" + customer + ", coupon=" + coupon + ", amount="
				+ amount + "]";
	}

	
}
