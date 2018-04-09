package com.purefun.fstp.core.rpc.tunnel;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.jms.DeliveryMode;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageProducer;
import javax.jms.ObjectMessage;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.slf4j.Logger;

import com.purefun.fstp.core.bo.BaseBO;
import com.purefun.fstp.core.bo.QNSRequestBO;
import com.purefun.fstp.core.bo.QueryRequestBO;
import com.purefun.fstp.core.cache.FCache;
import com.purefun.fstp.core.server.PService;
import com.purefun.fstp.core.server.monitor.MonitorService;

import redis.clients.jedis.Jedis;

public abstract class ServerSide<T, P> implements Runnable{
	protected Logger log = null;
	Session session = null;
	protected FCache fcache = null;	
	protected String servceName = null;
	String desname = null;
	public Map<String,String> desMap = null;
	
	public ServerSide(Logger log,Session session,FCache fcache,String servceName,String topic,Map<String,String> desMap) {
		this.log = log;
		this.session = session;
		this.fcache = fcache;
		this.servceName = servceName;
		this.desname = topic;
		this.desMap = desMap;
	}
		
	@Override
	public void run() {
		if(session == null) {
			log.error("There is no useful connect to broker");
			return;
		}			
		try {
			Destination destination = session.createTopic(desname);
			MessageConsumer messageConsumer = session.createConsumer(destination);
	        MessageProducer messageProducer = session.createProducer(null);
			
	        while (true) {
	        	ObjectMessage message = (ObjectMessage) messageConsumer.receive();
	        	T reveivebo = (T)message.getObject();
	        		        		        		        	 
//	        	List<byte[]> bocache = cache.lrange("com.cicc.pilot.core.bo.TestBO".getBytes(),0,-1);
	        	P respond = respond(reveivebo);
	        	if(respond == null)
	        		continue;
	        	ObjectMessage responseMessage = session.createObjectMessage();
	        	
	        	responseMessage.setObject((Serializable) respond);
	        	messageProducer.send(message.getJMSReplyTo(), responseMessage, DeliveryMode.NON_PERSISTENT, Message.DEFAULT_PRIORITY, Message.DEFAULT_TIME_TO_LIVE);	        
//	        	log.info("respond {} message.",bocache.size());
	        }
	   } catch (Exception exp) {
	           System.out.println("[SERVER] Caught exception, exiting.");
	           exp.printStackTrace(System.out);
	   }          
	}
	
	public abstract P respond(T reveiveMsg);
		
}
