package com.purefun.fstp.core.ipc.tunnel;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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

import com.purefun.fstp.core.bo.BaseBO;
import com.purefun.fstp.core.bo.QNSRequestBO;
import com.purefun.fstp.core.bo.QueryRequestBO;
import com.purefun.fstp.core.cache.FCache;

import redis.clients.jedis.Jedis;

public abstract class ClientSide<T>{
	Logger log = null;
	Session session = null;
	FCache fcache = null;
	String msgdef = null;
	
	Destination destination = null;
	TemporaryQueue responseQueue = null;
    MessageConsumer messageConsumer = null;
    MessageProducer messageProducer = null;
    
	public ClientSide(Logger log,Session session,FCache fcache,String topic) {
		this.log = log;
		this.session = session;
		this.fcache = fcache;
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
	
	public T query(BaseBO bo) {
		if(session == null) {
			log.error("There is no useful connect to broker");
			return null;
		}	

		T receive = null;
		try {		
	        ObjectMessage message = session.createObjectMessage();
	       
        	message.setObject((Serializable) bo); 
        	message.setJMSReplyTo(responseQueue);
        	       	
        	messageProducer.send(message);

        	ObjectMessage responseMessage = (ObjectMessage) messageConsumer.receive(5000); 
        	receive = (T) responseMessage.getObject();
	        } catch (Exception exp) {
	           System.out.println("[CLIENT] Caught exception, exiting.");
	           exp.printStackTrace(System.out);
	       }
		
		return receive;          
	}
}
