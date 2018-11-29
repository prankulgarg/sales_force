package com.rc.service;

import java.util.HashMap;
import java.util.Map;


import org.infinispan.Cache;
import org.infinispan.manager.DefaultCacheManager;




public class Cachemanagerimpl {/*
	
	private static final String CACHE_NAME ="WOM";
	private  static Object cacheManager;
	
	private Cachemanagerimpl(){}
	
	static {
		cacheManager = new CacheManagerService();
	}

	*//**
	 * 
	 * @param key
	 * @return
	 *//*
	public static Object getCacheValueById(String key){
		Map<Object, Object>  cache = cacheManager.getCache(CACHE_NAME);
		if(cache != null && cache.containsKey(key)){
			return cache.get(key);
		}
		return null;
	}
	public static Cache<Object, Object> getCacheObject(){
		return cacheManager.getCache(CACHE_NAME);
	}

	*//**
	 * 
	 * @param key
	 * @param value
	 *//*
	public static void putCacheValue(Object key,Object value){
		Map<Object, Object>  cache = new HashMap();
		if(cache != null){
			cache.put(key, value);
		}
	}
	*//**
	 * @param
	 *//*
	public static void clearCache(){
		Cache<Object, Object>  cache = cacheManager.getCache(CACHE_NAME);
		if(cache != null){
			cache.clear();
		}
	}
	
	
	
*/}
