package com.purefun.fstp.core.cache.ignitecache;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ignite.Ignite;
import org.apache.ignite.IgniteCache;
import org.apache.ignite.cache.affinity.AffinityKey;
import org.apache.ignite.cache.query.QueryCursor;
import org.apache.ignite.cache.query.SqlQuery;
import org.apache.ignite.configuration.CacheConfiguration;
import org.apache.ignite.internal.processors.cache.CacheEntryImpl;
import org.apache.ignite.lang.IgniteBiTuple;

import com.purefun.fstp.core.bo.TestBO;
import com.purefun.fstp.core.cache.ICommonCache;

public class ICache implements ICommonCache{
	public Ignite ignite;
	public HashMap<String, IgniteCache> cacheMap = new HashMap<String, IgniteCache>();
	
	public ICache(Ignite ignite, List<CacheConfiguration> cacheCfgLst) {
		this.ignite = ignite;
		for(CacheConfiguration each:cacheCfgLst) {
			cacheMap.put(each.getName(), this.ignite.getOrCreateCache(each));
		}
	}

	@Override
	public void put(String cacheName,String key, Object value) {
		// TODO Auto-generated method stub
		if(!cacheMap.containsKey(cacheName)) {
			ignite.log().info("cache {} is not here,will use default cfg create it",cacheName);
			CacheConfiguration temp = new CacheConfiguration(cacheName);
			cacheMap.put(cacheName, ignite.getOrCreateCache(temp));
		}
		cacheMap.get(cacheName).put(key, value);
	}

	@Override
	public Object get(String cacheName, String... key) {
		// TODO Auto-generated method stub
		if(cacheName==null || !cacheMap.containsKey(cacheName)) {
			return null;
		}
		
		List all = new ArrayList();
		if (key!=null) {//key不为空，但可以同时取出多条数据，需要处理
			for(String each:key) {
				all.add(cacheMap.get(cacheName).get(each));
			}
		}else {//key为null时表示取出所有数据
			IgniteCache cache = cacheMap.get(cacheName);
			for(Object c:cache) {
				all.add(((IgniteBiTuple)c).get2());
			}
		}					
		return all;
	}

	@Override
	public void addCache(CacheConfiguration cachecfg) {
		// TODO Auto-generated method stub
		cacheMap.putIfAbsent(cachecfg.getName(), this.ignite.getOrCreateCache(cachecfg));
	}
	
	public <T> List<T> query(String cacheName, String condition) {
		SqlQuery sql = null;
		if(condition==null) {
			sql = new SqlQuery<AffinityKey<String>, T>(TestBO.class, "1=1");
		}else {
			sql = new SqlQuery<AffinityKey<String>, T>(TestBO.class, condition);
		}
		 	
    	QueryCursor<?> res = cacheMap.get(cacheName).query(sql);
    	List<T> ret = new ArrayList<T>();
    	for(Object each:res.getAll()) {
    		T c = (T) ((CacheEntryImpl<String, T>)each).getValue();
    		ret.add(c);
    	}
    	
		return ret;
		
	}
	
}
