package com.purefun.fstp.core.ipc.sub;

import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageConsumer;
import javax.jms.Session;
import org.slf4j.Logger;

import com.purefun.fstp.core.cache.FCache;
import com.purefun.fstp.core.ipc.msglistener.SubMessageListener;

public class Subscriber{
	Logger log = null;
	Session session = null;
	FCache fcache = null;
	
	public Subscriber(Logger log,Session session,FCache fcache) {
		this.log = log;
		this.session = session;
		this.fcache = fcache;
	}
	
	public void subscribe(String topic,SubMessageListener msglisteneer) {
		try {
			Destination destination = session.createTopic(topic);
			MessageConsumer messageConsumer = session.createConsumer(destination);
			log.info("subscribe topic : {}",topic);
		
		    messageConsumer.setMessageListener(msglisteneer);	
		} catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}         
	}
	
	public void subscribe(String topic,PythonMessageListener msglisteneer) {
		try {
			Destination destination = session.createTopic(topic);
			MessageConsumer messageConsumer = session.createConsumer(destination);
			log.info("subscribe topic : {}",topic);
		
		    messageConsumer.setMessageListener(msglisteneer);	
		} catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}         
	}
	

}
