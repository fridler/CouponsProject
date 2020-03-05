package com.yair.coupons.entities;

import java.io.Serializable;
import java.sql.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.yair.coupons.enums.CouponCategory;

@Entity
@Table(name="Coupons")
public class Coupon implements Serializable{

	@Id
	@GeneratedValue
	@Column(name="id")
	private long couponId;  
	
	@Column(name= "title" , nullable = false)  
	private String title;
	
	@Column(name= "startDate", nullable = false)
	private Date startDate;
	
	@Column(name= "endDate", nullable = false)
	private Date endDate;
	
	@Column(name= "amount", nullable = false)  
	private int amount;
	
	@Column(name= "category", nullable = false)   
	@Enumerated(EnumType.STRING)
	private CouponCategory category;
	
	@Column(name= "price", nullable = false)  
	private float price;
	
	@Column(name= "image") 
	private String image;
	
	@Column(name= "description", nullable = false)  
	private String description;
	
	@ManyToOne
	@JsonIgnore
	private Company company;   
	
	@OneToMany(mappedBy = "coupon", cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
	private List<Purchase> purchases;
	
	@Column(name= "active", nullable = false)
	private boolean active;
	
	
	// Default constrcutor (means NO parameters)
	public Coupon() {

		}


	public Coupon(long couponId, String title, Date startDate, Date endDate, int amount, CouponCategory category,
			float price, String image, String description, Company company, boolean active) {
		super();
		this.couponId = couponId;
		this.title = title;
		this.startDate = startDate;
		this.endDate = endDate;
		this.amount = amount;
		this.category = category;
		this.price = price;
		this.image = image;
		this.description = description;
		this.company = company;
		this.active = active;
	}


	public Coupon(String title, Date startDate, Date endDate, int amount, CouponCategory category, float price,
			String image, String description, Company company, boolean active) {
		super();
		this.title = title;
		this.startDate = startDate;
		this.endDate = endDate;
		this.amount = amount;
		this.category = category;
		this.price = price;
		this.image = image;
		this.description = description;
		this.company = company;
		this.active = active;
	}


	public long getCouponId() {
		return couponId;
	}


	public void setCouponId(long couponId) {
		this.couponId = couponId;
	}


	public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title = title;
	}


	public Date getStartDate() {
		return startDate;
	}


	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}


	public Date getEndDate() {
		return endDate;
	}


	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}


	public int getAmount() {
		return amount;
	}


	public void setAmount(int amount) {
		this.amount = amount;
	}


	public CouponCategory getCategory() {
		return category;
	}


	public void setCategory(CouponCategory category) {
		this.category = category;
	}


	public float getPrice() {
		return price;
	}


	public void setPrice(float price) {
		this.price = price;
	}


	public String getImage() {
		return image;
	}


	public void setImage(String image) {
		this.image = image;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public Company getCompany() {
		return company;
	}


	public void setCompany(Company company) {
		this.company = company;
	}


	public boolean isActive() {
		return active;
	}


	public void setActive(boolean active) {
		this.active = active;
	}


	@Override
	public String toString() {
		return "Coupon [couponId=" + couponId + ", title=" + title + ", startDate=" + startDate + ", endDate=" + endDate
				+ ", amount=" + amount + ", category=" + category + ", price=" + price + ", image=" + image
				+ ", description=" + description + ", company=" + company + ", active=" + active + "]";
	}
	
	

	
}
