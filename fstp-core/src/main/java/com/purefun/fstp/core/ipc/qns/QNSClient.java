package com.purefun.fstp.core.ipc.qns;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.jms.BytesMessage;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageConsumer;
import javax.jms.MessageProducer;
import javax.jms.ObjectMessage;
import javax.jms.Session;
import javax.jms.TemporaryQueue;
import javax.jms.TextMessage;

import org.slf4j.Logger;

import com.purefun.fstp.core.bo.QNSRequestBO;
import com.purefun.fstp.core.bo.commom.ICommom_OTW;
import com.purefun.fstp.core.cache.FCache;

public class QNSClient{
	static Logger log = null;
	Session session = null;
	FCache fcache = null;
	String msgdef = null;
	
	Destination destination = null;
	TemporaryQueue responseQueue = null;
    MessageConsumer messageConsumer = null;
    MessageProducer messageProducer = null;
    
    String serverName = null;
    
    String[] topics = null;
	
	public QNSClient(Logger log,Session session,FCache fcache,String topic,String servername) {
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
	
	public String[] publish(ICommom_OTW bo) {
		if(session == null) {
			log.error("There is no useful connect to broker");
			return null;
		}	
		String respond = null;
		String ret[] = null;
		try {		
//	        ObjectMessage message = session.createObjectMessage();
//        	message.setObject((Serializable) bo); 
//        	message.setJMSReplyTo(responseQueue);
//        	
//        	messageProducer.send(message);
        	BytesMessage message = session.createBytesMessage();
        	message.writeBytes(bo.getBuilder().build().toByteArray());
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
