package com.yair.coupons.logic;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;

import com.yair.coupons.dao.ICompanyDao;
import com.yair.coupons.dao.ICouponDao;
import com.yair.coupons.dao.IPurchaseDao;
import com.yair.coupons.entities.Company;
import com.yair.coupons.entities.Coupon;
import com.yair.coupons.enums.CouponCategory;
import com.yair.coupons.enums.ErrorTypes;
import com.yair.coupons.exceptions.ApplicationException;
import com.yair.coupons.utils.DateUtils;

@Controller
public class CouponController {

	@Autowired
	private ICouponDao couponDao;
	@Autowired
	private IPurchaseDao purchaseDao;
	@Autowired
	private ICompanyDao companyDao;

	public long createCoupon(Coupon coupon) throws ApplicationException {
		
		validateCouponInsert(coupon);
		couponDao.save(coupon);
		return coupon.getCouponId();

	}
	
	public void addCoupon(Coupon coupon, long companyId) throws ApplicationException {

		Company company = companyDao.findByCompanyId(companyId);
		coupon.setCompany(company);

		couponDao.save(coupon);
	}

	public void updateCoupon(Coupon coupon, long companyId) throws ApplicationException{
		
		Coupon originCoupon = getCouponById(coupon.getCouponId());
		String couponOriginName = originCoupon.getTitle();
		
		if (!coupon.getTitle().equalsIgnoreCase(couponOriginName)) {
			if (isCouponExistByName(coupon.getTitle())) {
				throw new ApplicationException(ErrorTypes.COUPON_TITLE_IS_ALREADY_EXISTS, DateUtils.getCurrentDateAndTime()
						+ "Error in couponController.updateCoupon(), coupon's name already exists.");
			}
		}
		Company company = companyDao.findByCompanyId(companyId);
		coupon.setCompany(company);
		validateCouponInsert(coupon);
		couponDao.save(coupon);
		
	}
	
	public void addCouponByAdmin(Coupon coupon, String companyName) throws ApplicationException {
	Company company = companyDao.findByCompanyName(companyName);
//		Company company = companyDao.findByCompanyId(companyId);
		coupon.setCompany(company);

		couponDao.save(coupon);
	}

	public Coupon getCouponById(long couponId) throws ApplicationException{
		
		return couponDao.findById(couponId).get();
	}

//	@Transactional
//	public void deleteCoupon(long couponId) throws ApplicationException{
//		
//		if (!couponDao.existsById(couponId)) {
//			throw new ApplicationException(ErrorTypes.COMPANY_ID_DOES_NOT_EXISTS, "Coupon id does not exist");
//		}
//		
//		purchaseDao.deletePurchasesByCouponId(couponId);
//		couponDao.deleteById(couponId);
//	}

	public List <Coupon> getAllCoupons() throws ApplicationException{

		List<Coupon> coupons = (List<Coupon>) couponDao.findAll();
		return coupons;
	}
	
	public void deleteCoupon(long couponId) throws Exception {

		Coupon coupon = getCouponById(couponId);
		couponDao.delete(coupon);
	}
	
	public List<Coupon> getCouponsByCompany(long companyId) throws Exception {
		if(companyId <=0){
			List<Coupon> coupons = (List<Coupon>) couponDao.findAll();
			return coupons;
		} else {
			return couponDao.getCouponsByCompanyId(companyId);
		}
	}
	
	public List <Coupon> getAllCouponsByCategory (CouponCategory category){
		return couponDao.getAllCouponsByCategory(category);
	}
	
	public List<Coupon> getAllCouponsByCompany(long companyId) throws ApplicationException {
		return couponDao.getAllCouponsByCompanyId(companyId);
	}
	
	public boolean isCouponExistByName(String couponName) throws ApplicationException {
		return couponDao.existsByName(couponName);
	}
	
	public void deleteAllExpiredCoupons(Date date) throws ApplicationException {
		couponDao.deleteAllExpiredCoupons(date);
	}

	public void reduceCouponAmountByPurchaseAmont(long couponId, int amount) throws ApplicationException {
		couponDao.updateCouponAmountByPurchaseAmount(couponId, amount);
	}

	public List<Coupon> getCouponsUpToPrice(float couponPrice) throws Exception {
//		return couponDao.findByPriceLessThan(couponPrice);
		List<Coupon> coupons = couponDao.findByPriceLessThan(couponPrice);
		if(coupons.size()==0) {
			throw new Exception("There are no coupons with price less than: " + couponPrice);
		}
		return coupons;
	}

//	public List<Coupon> getCouponByStartDate(Date startDate) throws ApplicationException {
//		return couponDao.findByStartDateGreaterThan(startDate);
//	}
	
	public List<Coupon> getAllCouponsSinceStartDate(Date startDate) throws Exception {
		List<Coupon> coupons = couponDao.getAllCouponsSinceStartDate(startDate);
		if (coupons.size() == 0) {
			throw new Exception("There are no coupons with createDate after " + startDate);
		}
		return coupons;
	}
	
//	public List<Coupon> getCouponByEndDate(Date endDate) throws ApplicationException {
//		return couponDao.findByEndDateLessThan(endDate);
//	}
	
	public List<Coupon> getCouponByEndDate(Date endDate) throws Exception {
		List<Coupon> coupons = couponDao.getCouponByEndDate(endDate);
		if (coupons.size() == 0) {
			throw new Exception("There are no coupons with expiration date after " + endDate);
		}
		return coupons;
	}
	

	public void reduceCouponAmountByPurchaseAmount(long couponId, int amount) throws ApplicationException {
		couponDao.updateCouponAmountByPurchaseAmount(couponId, amount);
	}
	
	
	private void validateCouponInsert(Coupon coupon) throws ApplicationException{
		
		if (coupon.getTitle() == null) {
			throw new ApplicationException(ErrorTypes.NULL_FIELD, "Coupon's title is null");
		}
		if (coupon.getTitle().isEmpty()) {
			throw new ApplicationException(ErrorTypes.EMPTY_FIELD, "Coupon's title is empty");
		}
//		if (couponDao.findByCouponTitle(coupon.getTitle()) != null) {
//			throw new ApplicationException(ErrorTypes.COUPON_TITLE_IS_ALREADY_EXISTS, "Coupon title already exists, aborting");
//		}
		if (coupon.getPrice() <= 0) {
			throw new ApplicationException(ErrorTypes.ZERO_ERROR, "Coupon's price must be positive");
		}
//		if (coupon.getEndDate().equals(null)) {
//			throw new ApplicationException(ErrorTypes.INVALID_DATES, "End date is null");
//		}
//		if(coupon.getEndDate().before(coupon.getStartDate())) {
//			throw new ApplicationException(ErrorTypes.INVALID_DATES, "End time is before start time");
//		}
//		if(coupon.getStartDate().equals(null)) {
//			throw new ApplicationException(ErrorTypes.INVALID_DATES, "Start date is null");
//		}
		if (coupon.getAmount() < 1) {
			throw new ApplicationException(ErrorTypes.ZERO_ERROR, "Amount must be positive");
		}
		if(coupon.getDescription() == null) {
			throw new ApplicationException(ErrorTypes.NULL_FIELD, "Null description");
		}
		if(coupon.getDescription().isEmpty()) {
			throw new ApplicationException(ErrorTypes.EMPTY_FIELD, "Empty description");
		}
		if(coupon.getDescription().length() >= 500) {
			throw new ApplicationException(ErrorTypes.TEXT_EXCEEDS_LIMIT, "Text Limit is 500 characters");
		}
		
	}
}

