package com.purefun.fstp.core.cache;

import org.apache.ignite.configuration.CacheConfiguration;

public interface ICommonCache {
	public void put(String cacheName, String key, Object value);
	
	public Object get(String cacheName, String... key);
	
	public void addCache(CacheConfiguration cachecfg);
}
