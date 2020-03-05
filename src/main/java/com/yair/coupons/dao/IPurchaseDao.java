package com.yair.coupons.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.yair.coupons.entities.Purchase;

@Repository
public interface IPurchaseDao extends CrudRepository<Purchase, Long>{

//	public List<Purchase> getAllPurchasesByCouponId(Long couponId);
//	
//	public List<Purchase> getAllPurchasesByCustomerId(Long customerId);
//
//	@Modifying
//	@Query(value = "DELETE FROM purchases WHERE coupon_id IN ( SELECT coupon_id FROM coupons WHERE company_id = companyId", nativeQuery = true)
//	public void deletePurchasesByCouponId(long couponId);
	
}
