package com.yair.coupons.threads;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Timer;
import java.util.TimerTask;

import com.yair.coupons.enums.ErrorTypes;
import com.yair.coupons.exceptions.ApplicationException;
import com.yair.coupons.logic.CouponController;
import com.yair.coupons.logic.PurchaseController;

public class TimerTaskThread extends TimerTask implements Runnable {

	private CouponController couponController;
	
	@Override
	public void run() {
//		try {
//			CouponController couponController = new CouponController();
//			PurchaseController purchaseController = new PurchaseController();
//			Date date = new Date();
//			//Set all expired coupons as inActive
//			couponController.inActiveExpiredCoupons(date);
//			//Set all out of stock coupons as inActive
//			couponController.inActiveOutOfStockCoupons();
//
//			//Set all purchases of expired coupons as inActive
//			purchaseController.inActivePurchaseOfExpiredCoupons();
//			//Set all purchases of out of stock coupons as inActive
//			purchaseController.inActivePurchaseOfOutOfStockCoupons();
//
//		} catch (ApplicationException e) {
//			e.printStackTrace();
//		} finally {
//			System.out.println("\n Task finished at: " + new java.util.Date() + "\n");
//		}
		
		try {
			System.out.println("TimerTask is running");
			couponController.deleteAllExpiredCoupons(new java.util.Date());

			/* All the rows in all the tables that hold these coupons have been deleted with: Cascade delete */

			System.out.println("Coupons deleted successfully");

		} catch (ApplicationException e) {
			e.printStackTrace();
			throw new ApplicationException(ErrorTypes.GENERAL_ERROR, "ff");
		}
		
		finally {
			System.out.println("\n Task finished at: " + new java.util.Date() + "\n");
		}	

	}

	public void startDeletingCoupons() {
		GregorianCalendar gc = new GregorianCalendar();

		gc.set(Calendar.HOUR, 00);
		gc.set(Calendar.MINUTE, 00);
		gc.set(Calendar.SECOND, 00);

		gc.add(Calendar.DAY_OF_MONTH, 1);

		Timer timer = new Timer();

		timer.schedule(new TimerTaskThread(), gc.getTime(), 1000 * 60 * 60 * 24);

	}
}
