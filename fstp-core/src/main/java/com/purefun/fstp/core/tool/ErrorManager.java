package com.purefun.fstp.core.tool;

import java.util.Map;

import org.apache.xbean.spring.context.ClassPathXmlApplicationContext;
import org.springframework.beans.factory.BeanFactory;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

public class ErrorManager {
	private Map<String,String> errorMap = null;
	
	public Map<String, String> getErrorMap() {
		return errorMap;
	}

	public void setErrorMap(Map<String, String> errorMap) {
		this.errorMap = errorMap;
	}
	
}
