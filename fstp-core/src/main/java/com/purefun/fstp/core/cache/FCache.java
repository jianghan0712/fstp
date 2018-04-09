package com.purefun.fstp.core.cache;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.springframework.beans.factory.BeanFactory;
import com.purefun.fstp.core.server.PService;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

public class FCache {	
	private List<String> publicCacheList = null;	//主要存该service可以管理的BO类名
	private List<String> privateCacheList = null;	//存该servic需要存入cache中的数据的key
	private Jedis cache = null;
	static Logger log = null;
	String serviceName = null;
	JedisPool jedisPool = null;
	
	public FCache(PService service) {
		publicCacheList = new ArrayList<String>();
		privateCacheList = new ArrayList<String>();
		log = service.log;
		serviceName = service.getServerName();
		
		initCache(service.getBeanFactory());
	}
	
	private void initCache(BeanFactory factory) {
		// TODO Auto-generated method stub
		if(factory == null) {
			log.error("{} init FCache fail",serviceName);
			return;
		}
		jedisPool = (JedisPool)factory.getBean("jedisPool");
		if(jedisPool != null) {
			cache = jedisPool.getResource();
			log.info("{} init FCache succesful",serviceName);
		}				
	}

	/*************   cache manager start    ***************/
	public Object getObject(String key) {
		Object ret = null;		
		if(cache != null) {
			ret = ObjectTransCoder.deserialize(cache.get(key.getBytes()));
			log.debug("get key={} object!",key);
		}else {
			log.debug("The {} not found in cache:{}",key,cache);
		}
		return ret;
	}
	
	public void setObject(String key, Object value) {
		byte[] bytes = null;
		if(cache != null && value != null) {
			bytes = ObjectTransCoder.serialize(value);
			cache.set(key.getBytes(),bytes);
			log.debug("Set key={} successfull!",key);
		}else {
			log.debug("The object is null");
		}
	}
	
	//存储BO用的LIST
	public void setList(String key, Object value) {
		byte[] bytes = null;
		if(cache != null && value != null) {
			bytes = ObjectTransCoder.serialize(value);
			cache.rpush(key.getBytes(),bytes);
			log.debug("Set List key={} successfull!",key);
		}else {
			log.debug("The object is null");
		}
	}
	
	public List<byte[]> getList(String key) {
		List<byte[]> ret = null;
		
		if(cache != null) {
			ret = cache.lrange(key.getBytes(),0,-1);
			log.debug("get key={} object!",key);
		}else {
			log.debug("The {} not found in cache:{}",key,cache);
		}
		return ret;
	}
	
	//删除对应的cache
	public void delObjct(String key) {
		if(cache != null)
			cache.del(key);
	}	
	
	public void delPublicCache() {
		if(publicCacheList.size()==0)
			return;
		for(String each:publicCacheList) {
			delObjct(each);
		}
	}
	
	public void delPrivateCache() {
		if(privateCacheList.size()==0)
			return;
		for(String each:privateCacheList) {
			delObjct(each);
		}
	}
	
	public void delAllObject() {
		delPublicCache();
		delPrivateCache();
	}
	
	public void stop() {
		delAllObject();
		cache.close();
		jedisPool.close();
	}
	
	/*************   cache manager end    ***************/
	/*************   Map manager start    ***************/

	

	public Jedis getCache() {
		return cache;
	}

	public List<String> getPublicCacheList() {
		return publicCacheList;
	}

	public void setPublicCacheList(List<String> publicCacheList) {
		this.publicCacheList = publicCacheList;
	}

	public List<String> getPrivateCacheList() {
		return privateCacheList;
	}

	public void setPrivateCacheList(List<String> privateCacheList) {
		this.privateCacheList = privateCacheList;
	}

	public void setCache(Jedis cache) {
		this.cache = cache;
	}
}
