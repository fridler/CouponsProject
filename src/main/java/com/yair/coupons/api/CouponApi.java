package com.yair.coupons.api;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.yair.coupons.consts.Constants;
import com.yair.coupons.data.LoggedInUserData;
import com.yair.coupons.entities.Coupon;
import com.yair.coupons.exceptions.ApplicationException;
import com.yair.coupons.logic.CouponController;
import com.yair.coupons.logic.UserController;

@RestController
@RequestMapping("/coupon")
public class CouponApi {
	
	@Autowired
	private CouponController couponController;

	//http://localhost:8080/coupon/2
	@GetMapping("/{couponId}")
	public Coupon getCouponById(@PathVariable("couponId") long couponId) throws ApplicationException {
		System.out.println("Success to get coupon");
		return this.couponController.getCouponById(couponId);
	}
	
//	@DeleteMapping
//	@RequestMapping("/{couponId}")
//	public void deleteCoupon(@PathVariable("couponId") long couponId) throws ApplicationException {
//		this.couponController.deleteCoupon(couponId);
//		System.out.println("Delete : " + couponId);
//	}
	
//	http://localhost:8080/coupon/
	@PostMapping("/create")
	public void createCoupon(@RequestBody Coupon coupon, HttpServletRequest request) throws ApplicationException {
        LoggedInUserData userData = (LoggedInUserData) request.getAttribute(Constants.USER_DATA_KEY);
        couponController.addCoupon(coupon, userData.getCompanyId());
		
//		couponController.createCoupon(coupon);
//		System.out.println("Create: " + coupon);
	}
	
    @PostMapping("/byComName")
    public void createCouponByAdmin(@RequestBody Coupon coupon, String companyName) throws ApplicationException {

        couponController.addCouponByAdmin(coupon, companyName);
    }
	
	@PutMapping
	public void updateCoupon(@RequestBody Coupon coupon, HttpServletRequest request) throws ApplicationException {
//		couponController.updateCoupon(coupon);
		LoggedInUserData userData = (LoggedInUserData) request.getAttribute(Constants.USER_DATA_KEY);
        couponController.updateCoupon(coupon, userData.getCompanyId());
		System.out.println("Update: " + coupon);
	}
	
    //http://localhost:8080/coupon/2
    @DeleteMapping
    @RequestMapping("/{couponId}")
    public void deleteCoupon(@PathVariable("couponId") long couponId) throws Exception {
        couponController.deleteCoupon(couponId);
    }
	
//	http://localhost:8080/coupon
	@GetMapping
	@RequestMapping
	public List<Coupon> getAllCoupons() throws ApplicationException{
		return couponController.getAllCoupons();
	}
	
	//http://localhost:8080/coupon/upToPrice?couponPrice=200
	@GetMapping
	@RequestMapping("/upToPrice")
	public List<Coupon> getCouponsUpToPrice(@RequestParam("couponPrice") float couponPrice) throws Exception{
		return couponController.getCouponsUpToPrice(couponPrice);
	}
	
//	@GetMapping
//	@RequestMapping("/byStartDate")
//	public List<Coupon> getCouponByStartDate(@RequestParam("startDate") Date startDate) throws ApplicationException{
//		return couponController.getCouponByStartDate(startDate);
//	}
	
    @GetMapping
    @RequestMapping("/cId")
    public List<Coupon> getCouponsByCompany(HttpServletRequest request) throws Exception {

        LoggedInUserData userData = (LoggedInUserData) request.getAttribute(Constants.USER_DATA_KEY);

        return couponController.getCouponsByCompany(userData.getCompanyId());
    }
	
    //http://localhost:8080/coupon/byStartDate?startDate=2019-12-09
    @GetMapping("/byStartDate")
    public List<Coupon> getCouponByCreateDate(@RequestParam("startDate") String strStartDate)
            throws Exception {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date startDate = dateFormat.parse(strStartDate);
        return couponController.getAllCouponsSinceStartDate(startDate);
    }

//	@GetMapping
//	@RequestMapping("/byEndDate")
//	public List<Coupon> getCouponByEndDate(@RequestParam("endDate") Date endDate) throws ApplicationException{
//		return couponController.getCouponByEndDate(endDate);
//	}
	
    //	http://localhost:8080/coupon/byEndDate?endDate=2019-12-29
    @GetMapping("/byEndDate")
    public List<Coupon> getCouponByEndDate(@RequestParam("endDate") String strEndDate) throws Exception {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date endDate = dateFormat.parse(strEndDate);
        return couponController.getCouponByEndDate(endDate);
    }

}
