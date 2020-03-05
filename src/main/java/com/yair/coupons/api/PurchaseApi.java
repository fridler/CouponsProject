package com.yair.coupons.api;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yair.coupons.consts.Constants;
import com.yair.coupons.data.LoggedInUserData;
import com.yair.coupons.data.PurchaseData;
import com.yair.coupons.entities.Purchase;
import com.yair.coupons.exceptions.ApplicationException;
import com.yair.coupons.logic.PurchaseController;
import com.yair.coupons.logic.UserController;

@RestController
@RequestMapping("/purchase")
public class PurchaseApi {
	
	@Autowired
	private PurchaseController purchaseController;

	@GetMapping("/{purchaseId}")
	public Purchase getPurchase(@PathVariable("purchase") long purchaseId) throws ApplicationException {
		System.out.println(purchaseId);
		return this.purchaseController.getPurchase(purchaseId);
	}
	
//	http://localhost:8080/purchase/2
	@DeleteMapping
	@RequestMapping("/{purchaseId}")
	public void deletePurchase(@PathVariable("purchaseId") long purchaseId) throws ApplicationException {
		purchaseController.deletePurchase(purchaseId);
		System.out.println("Delete : " + purchaseId);
	}
	
	
	/*
	http://localhost:8080/purchase
	{
  "amount":3,
  "couponId":1,
  "customerId":2
}
	 */
	@PostMapping
	public void createPurchase(@RequestBody PurchaseData purchase, HttpServletRequest request) throws Exception {
		LoggedInUserData userData = (LoggedInUserData) request.getAttribute(Constants.USER_DATA_KEY);
		purchaseController.createPurchase(purchase, userData);
		System.out.println("Create: " + purchase);
	}
	
//	@PutMapping
//	public void updatePurchase(@RequestBody Purchase purchase) throws ApplicationException {
//		this.purchaseController.updatePurchase(purchase);
//		System.out.println("Update: " + purchase);
//	}
	
//	http://localhost:8080/purchase
	@GetMapping
	public List<Purchase> getAllPurchases() throws ApplicationException{
		return purchaseController.getAllPurchases();
	}
}
