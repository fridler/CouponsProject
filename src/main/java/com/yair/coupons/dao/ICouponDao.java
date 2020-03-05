package com.yair.coupons.dao;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.yair.coupons.entities.Company;
import com.yair.coupons.entities.Coupon;
import com.yair.coupons.enums.CouponCategory;

@Repository
public interface ICouponDao extends CrudRepository<Coupon, Long> {

	/*
	public Coupon findByCouponTitle(String couponTitle);

	public List<Coupon> findByCompany(Company company);

	public List<Coupon> findByCompanyAndCategory(Company company, CouponCategory category);

	public List<Coupon> getAllCoupons();

	public List<Coupon> getAllCouponsActive(boolean active);

	public List<Coupon> getAllCouponsWithImage(String image);

	public List<Coupon> getAllCouponsByTitle(String title);

	 */

	@Query(value = "SELECT * FROM coupons WHERE coupon_category = category", nativeQuery = true)
	public List<Coupon> getAllCouponsByCategory(@Param("category") CouponCategory category);

	@Transactional
	@Modifying
	@Query("UPDATE Coupon c SET c.amount= :amount WHERE ID= :couponId")
	void updateCouponAmountByPurchaseAmount(@Param("couponId") long couponId, @Param("amount") int amount);

	@Query("FROM Coupon c WHERE c.price <= :couponPrice")
	List<Coupon> findByPriceLessThan (@Param("couponPrice") float couponPrice);

	@Query(value = "SELECT * FROM coupons WHERE company_id = companyId", nativeQuery = true)
	public List<Coupon> getAllCouponsByCompanyId(@Param("companyId") Long companyId);

	@Query("SELECT CASE WHEN COUNT(c) > 0 THEN true ELSE false END FROM Coupon c WHERE c.title= :couponName")
	boolean existsByName(@Param("couponName") String couponName);

	@Query("DELETE FROM Coupon c WHERE c.endDate<= :date")
	void deleteAllExpiredCoupons(@Param("date") Date date);

	@Query("SELECT c FROM Coupon c where c.company.id=:cId")
	List<Coupon> getCouponsByCompanyId(@Param("cId") long companyId);

	@Query("from Coupon c where c.startDate > :startDate")
	public List<Coupon> getAllCouponsSinceStartDate(@Param("startDate") Date startDate);

	@Query("from Coupon c where c.endDate > :endDate")
	public List<Coupon> getCouponByEndDate(@Param("endDate") Date endDate);



	//	@Query("FROM Coupon c WHERE c.startDate >= startDate")
	//	List<Coupon> findByStartDateGreaterThan(@Param("startDate") Date startDate);
	//
	//	@Query("FROM Coupon c WHERE c.endDate <= :endDate")
	//	List<Coupon> findByEndDateLessThan(@Param("endDate") Date endDate); 

}
