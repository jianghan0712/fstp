package com.purefun.fstp.core.cache;

import java.util.HashMap;
import java.util.Map;

import org.apache.xbean.spring.context.ClassPathXmlApplicationContext;
import org.apache.xbean.spring.context.FileSystemXmlApplicationContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.ApplicationContext;

import com.purefun.fstp.core.logging.PLogger;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

public class CacheClient {	
	private static JedisPool jedisPool = null;
	private Jedis cache = null;
	Logger log = null;
	
	
	public CacheClient(String path) {
		 ApplicationContext context = new FileSystemXmlApplicationContext(path);
		 Object obj = context.getBean("jedisPool");
		 if (obj!=null) {
			 jedisPool = (JedisPool)obj;
			 cache = jedisPool.getResource();
		 }else {
			 log.error("get redis failure!");
		}
		
	}
	
	public CacheClient(Logger log) {
		this.log = log;
		BeanFactory factory  = new ClassPathXmlApplicationContext("classpath:resource/comConf/CacheClient.xml");
		Object obj = factory.getBean("jedisPool");
		if (obj!=null) {
			jedisPool = (JedisPool)obj;
			cache = jedisPool.getResource();
			log.info("get cache successful!");
		}else {
			log.error("get cache failure!");
		}
		
	}
	
	public static Jedis getJedis() {
		if(jedisPool==null) {
			BeanFactory factory  = new ClassPathXmlApplicationContext("classpath:resource/comConf/CacheClient.xml");
			Object obj = factory.getBean("jedisPool");
			if (obj!=null) {
				jedisPool = (JedisPool)obj;
			}
		}
		return jedisPool.getResource();
	}
	
	public Object getObject(String key) {
		Object ret = null;
		
		if(cache != null) {
			ret = ObjectTransCoder.deserialize(cache.get(key.getBytes()));
			log.info("get key={} object!",key);
		}else {
			log.info("The {} not found in cache:{}",key,cache);
		}
		return ret;
	}
	
	public void setObject(String key, Object value) {
		byte[] bytes = null;
		if(cache != null && value != null) {
			bytes = ObjectTransCoder.serialize(cache.get(key.getBytes()));
			cache.set(key.getBytes(),bytes);
			log.info("Set key={} successfull!",key);
		}else {
			log.info("The object {} is null",value);
		}
	}
	
	
    public static void main(String[] args) {
    	Logger log = PLogger.getLogger(CacheClient.class);
    	CacheClient a = new CacheClient(log);
//    	Jedis connect = a.getJedis();

    	Jedis connect = CacheClient.getJedis();
    	Map<String,Person> map = new HashMap<String, Person>();
    	map.put("1", new Person("caspar",25));
    	connect.set("map".getBytes(), ObjectTransCoder.serialize(map));
    	HashMap result = (HashMap)ObjectTransCoder.deserialize(connect.get("map".getBytes()));
    	System.out.println(((Person)result.get("1")).getName());
 	
	}
}
