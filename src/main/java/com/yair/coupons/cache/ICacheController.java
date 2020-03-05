package com.yair.coupons.cache;

public interface ICacheController {

	
	public Object get(Object key);
	public void put(String key, Object value);
}
