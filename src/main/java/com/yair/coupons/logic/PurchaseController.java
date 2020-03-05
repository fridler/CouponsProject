package com.yair.coupons.logic;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.yair.coupons.dao.ICouponDao;
import com.yair.coupons.dao.IPurchaseDao;
import com.yair.coupons.data.LoggedInUserData;
import com.yair.coupons.data.PurchaseData;
import com.yair.coupons.entities.Coupon;
import com.yair.coupons.entities.Customer;
import com.yair.coupons.entities.Purchase;
import com.yair.coupons.entities.User;
import com.yair.coupons.enums.ErrorTypes;
import com.yair.coupons.exceptions.ApplicationException;

@Controller
public class PurchaseController {

	@Autowired
	private IPurchaseDao purchaseDao;
	@Autowired
	private ICouponDao couponDao;
	@Autowired
	private CustomerController customerController;
	@Autowired
	private CouponController couponController;
	

	@Transactional
	public void createPurchase (PurchaseData purchaseData, LoggedInUserData userData) throws Exception {
		
		validatePurchaseInsert(purchaseData);
	
		Coupon coupon = couponController.getCouponById(purchaseData.getCouponId());
		long couponId = coupon.getCouponId();
		
		int updatedCouponAmount = (coupon.getAmount()) - (purchaseData.getAmount());		
		couponController.reduceCouponAmountByPurchaseAmont(couponId, updatedCouponAmount);
		
//		Customer customer = new Customer();
		Customer customer = customerController.getCustomerById(purchaseData.getCustomerId());
		
//		
//		User user = new User();
//		user.setUserId(userData.getUserId());
//		customer.setUser(user);
//		CHECK IF NEW WITH ID GETS ALL DATA
		Purchase userPurchase = new Purchase(customer, coupon, purchaseData.getAmount() );
		purchaseDao.save(userPurchase);
		/*
		Coupon couponBought = couponDao.findById(purchase.getCoupon().getCouponId()).get();
		couponBought.setAmount(couponBought.getAmount() - purchase.getAmount());
		couponDao.save(couponBought);
		
		purchaseDao.save(purchase);
		return purchase.getPurchaseId();

*/
	}
	

//	public void updatePurchase (Purchase purchase) throws ApplicationException {
//
//	}

	public void deletePurchase(long purchaseId) throws ApplicationException{

		if (!purchaseDao.existsById(purchaseId)) {
			throw new ApplicationException(ErrorTypes.PURCHASE_ID_DOES_NOT_EXISTS, "Purchase id does not exist");
		}

		purchaseDao.deleteById(purchaseId);

	}
	
	public Purchase getPurchase (long purchaseId) throws ApplicationException{
		
		return purchaseDao.findById(purchaseId).get();
	}
	
	public List<Purchase> getAllPurchases() throws ApplicationException {

		List<Purchase> purchases =  (List<Purchase>) purchaseDao.findAll();
		return purchases;
	}

//	public List<Purchase> getAllPurchaseByCustomerId(long customerId) throws ApplicationException {
//		
//		return purchaseDao.getAllPurchasesByCustomerId(customerId);
//	}
//
//	public List<Purchase> getAllPurchaseByCouponId(long couponId) throws ApplicationException {
//
//		return purchaseDao.getAllPurchasesByCouponId(couponId);
//	}

	private void validatePurchaseInsert(PurchaseData purchaseData) throws ApplicationException{
		
		if (purchaseData.getAmount() <= 0) {
			throw new ApplicationException(ErrorTypes.ZERO_ERROR,"Purchase amount must be positive");
		}
//		if (couponDao.findById(purchase.getCoupon().getCouponId()).get() == null) {
//			throw new ApplicationException(ErrorTypes.COUPON_ID_DOES_NOT_EXIST, "Coupon was not found");
//		}
//		if (couponDao.findById(purchase.getCoupon().getCouponId()).get().getAmount() - purchase.getAmount() < 0) {
//			throw new ApplicationException(ErrorTypes.INVALID_AMOUNT, "Not enough coupons in stock");
//		}
		
	}

}

