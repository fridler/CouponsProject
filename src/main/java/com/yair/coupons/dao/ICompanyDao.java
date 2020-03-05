package com.yair.coupons.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.yair.coupons.entities.Company;
import com.yair.coupons.entities.Coupon;
import com.yair.coupons.enums.CouponCategory;

@Repository
public interface ICompanyDao extends CrudRepository<Company, Long>{

	
	@Query(value = "SELECT name FROM companies c WHERE c.name = :name", nativeQuery = true)
	public Company findByCompanyName(String name);
	
	@Query(value = "SELECT address FROM companies c WHERE c.address = :address", nativeQuery = true)
	public Company findByCompanyAddress(String address);
	
	
	@Query("SELECT c FROM Company c where c.id=:id")
    public Company findByCompanyId(@Param("id") Long id);
	
	public List<Company> getCompanyByName(String name);
	
	
	@Query(value = "SELECT * FROM companies WHERE address", nativeQuery = true)
	public List<Company> getAllCompaniesByAddress(String address);
	
	// good
	@Query(value = "SELECT * FROM companies", nativeQuery = true)
	public List<Company> getAllCompanies();
}
