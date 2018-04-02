package com.purefun.fstp.core.rpc.qns;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.jms.Connection;
import javax.jms.DeliveryMode;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageProducer;
import javax.jms.ObjectMessage;
import javax.jms.Session;
import javax.jms.TemporaryQueue;
import javax.jms.TextMessage;

import org.slf4j.Logger;

import com.purefun.fstp.core.bo.QNSRequestBO;

import redis.clients.jedis.Jedis;

public class QNSClient{
	static Logger log = null;
	Session session = null;
	Jedis cache = null;
	String msgdef = null;
	
	Destination destination = null;
	TemporaryQueue responseQueue = null;
    MessageConsumer messageConsumer = null;
    MessageProducer messageProducer = null;
    
    String serverName = null;
    
    String[] topics = null;
	
	public QNSClient(Logger log,Session session,Jedis cache,String topic,String servername) {
		this.log = log;
		this.session = session;
		this.cache = cache;
		this.msgdef = topic;
		
		if(session == null) {
			log.error("There is no useful connect to broker");
			System.exit(1);
		}else {
			try {
				destination = session.createTopic(msgdef);
				responseQueue = session.createTemporaryQueue();
				messageConsumer = session.createConsumer(responseQueue);
				messageProducer = session.createProducer(destination);								
			} catch (JMSException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}			
		}
	}
	
	public String[] publish(QNSRequestBO bo) {
		if(session == null) {
			log.error("There is no useful connect to broker");
			return null;
		}	
		String respond = null;
		String ret[] = null;
		try {		
	        ObjectMessage message = session.createObjectMessage();
        	message.setObject((Serializable) bo); 
        	message.setJMSReplyTo(responseQueue);
        	
        	messageProducer.send(message);
        	
        	TextMessage responseMessage = (TextMessage) messageConsumer.receive(5000);
            if (responseMessage != null) {
            	respond = responseMessage.getText();
            	getTopics(respond);
            } else {
            	log.info("query failure");
            }       	        
	        } catch (Exception exp) {
	           System.out.println("[CLIENT] Caught exception, exiting.");
	           exp.printStackTrace(System.out);
	       }
		
		return topics;          
	}
	
	private List[] query() {
		// TODO Auto-generated method stub
		List[] result = new ArrayList[topics.length];
		int i = 0;
		for(String topic:topics) {
			List<byte[]> bocache = cache.lrange(topic.getBytes(),0,-1);
			if(bocache == null || bocache.isEmpty()) {
				log.info("topic {} has no message in cache",topic);
			}else {
				result[i++] = bocache;
			}		
		}
		return result;
	}

	private void getTopics(String respond) {
		while(respond==null) {
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}		
		String temp = respond;
		if(temp.equalsIgnoreCase("1")) {
			log.info("format error");
		}else if(temp.equalsIgnoreCase("2")) {
			log.info("bo-destination init error");
		}else {
			topics = temp.split(",");
		}				
	}
}
