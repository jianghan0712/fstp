package com.purefun.fstp.core.rpc.sub;

import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.QueueBrowser;
import javax.jms.Session;
import javax.jms.Topic;
import javax.jms.TopicSubscriber;

import org.slf4j.Logger;

import com.purefun.fstp.core.rpc.msglistener.SubMessageListener;

import redis.clients.jedis.Jedis;

public class Subscriber{
	Logger log = null;
	Session session = null;
	Jedis cache = null;
	
	public Subscriber(Logger log,Session session,Jedis cache) {
		this.log = log;
		this.session = session;
		this.cache = cache;
	}
	
	public void subscribe(String topic,SubMessageListener msglisteneer) {
		try {
			Destination destination = session.createTopic(topic);
			MessageConsumer messageConsumer = session.createConsumer(destination);
			log.info("topic : {}",topic);
//			connection.setClientID("123");
//			MessageConsumer messageConsumer = session.createDurableSubscriber(session.createTopic(topic),connection.getClientID()); 
			
		    messageConsumer.setMessageListener(msglisteneer);	
		} catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}         
	}
	

}
