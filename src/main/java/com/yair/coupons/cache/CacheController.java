package com.yair.coupons.cache;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;


// This class serves as a basis for a clustered cache mechanism
@Controller
public class CacheController implements ICacheController{

	private Map<String, Object> cacheMap;
	
	public CacheController() {
		this.cacheMap = new HashMap<String, Object>();
	}

	@Override
	public Object get(Object key) {
		return this.cacheMap.get(key);
	}

	@Override
	public void put(String key, Object value) {
		this.cacheMap.put(key, value);
		
	}

}
