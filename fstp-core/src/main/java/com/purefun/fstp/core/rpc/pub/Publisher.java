package com.purefun.fstp.core.rpc.pub;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.jms.DeliveryMode;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageConsumer;
import javax.jms.MessageProducer;
import javax.jms.ObjectMessage;
import javax.jms.Session;
import javax.jms.TemporaryQueue;
import javax.jms.TextMessage;

import org.slf4j.Logger;

import com.purefun.fstp.core.bo.BaseBO;
import com.purefun.fstp.core.bo.QNSRequestBO;
import com.purefun.fstp.core.cache.ObjectTransCoder;
import com.purefun.fstp.core.rpc.qns.QNSClient;

import redis.clients.jedis.Jedis;

public class Publisher{
	Logger log = null;
	Session session = null;
	Jedis cache = null;
	
	public Publisher(Logger log,Session session,Jedis cache) {
		this.log = log;
		this.session = session;
		this.cache = cache;
	}
	
	public void publish(BaseBO bo) {
		if(session == null) {
			log.error("There is no useful connect to broker");
			return;
		}			
		try {
			Destination destination = session.createTopic(bo.getDestination());
			MessageProducer messageProducer = session.createProducer(destination);
			
			ObjectMessage message = session.createObjectMessage();
        	message.setObject((Serializable) bo); 
        	      	
            messageProducer.send(message);
            durableInCache(bo);
            log.info("publish BO:[{}]",bo.toString());
            
		} catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
	
	public void durableInCache(BaseBO bo) {
		String mapName = bo.getClass().getName();
		cache.rpush(mapName.getBytes(), ObjectTransCoder.serialize(bo));
		
//		log.info(mapName);
	}	
}
