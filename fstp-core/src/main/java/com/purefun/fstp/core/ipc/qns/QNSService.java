package com.purefun.fstp.core.ipc.qns;

import java.util.Map;

import javax.jms.BytesMessage;
import javax.jms.DeliveryMode;
import javax.jms.Destination;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.slf4j.Logger;

import com.purefun.fstp.core.bo.otw.QNSRequestBO_OTW;
import com.purefun.fstp.core.server.monitor.MonitorService;
import com.purefun.fstp.core.tool.RPCTool;

public class QNSService{
	Logger log = null;
	Session session = null;
	MonitorService monitor = null;
	String desname = null;
	
	public QNSService(Logger log,Session session,MonitorService server,String topic) {
		this.log = log;
		this.session = session;
		this.monitor = server;
		this.desname = topic;
	}
	
	public void publish() {
		if(session == null) {
			log.error("There is no useful connect to broker");
			return;
		}			
		try {
			Destination destination = session.createTopic(desname);
			MessageConsumer messageConsumer = session.createConsumer(destination);
	        MessageProducer messageProducer = session.createProducer(null);
			
	        while (true) {
	        	BytesMessage message = (BytesMessage)messageConsumer.receive();
	        	byte[] byteArray = new byte[1024];
	        	int len = -1;
	        	len=message.readBytes(byteArray);
	        	if(len == -1){ 
	        		continue;
	        	}
	        	QNSRequestBO_OTW receiveBO =new QNSRequestBO_OTW(RPCTool.subBytes(byteArray, 0, len));
        	
	        	log.info("receive ServerName:{},QNSTopic:{}",receiveBO.getServername(),receiveBO.getRequest());
	        	String reply = analysis(receiveBO.getRequest());
	        	log.info(receiveBO.getRequest());
	        	TextMessage responseMessage = session.createTextMessage(reply);
	        	messageProducer.send(message.getJMSReplyTo(), responseMessage, DeliveryMode.NON_PERSISTENT, Message.DEFAULT_PRIORITY, Message.DEFAULT_TIME_TO_LIVE);	        
	        }
	   } catch (Exception exp) {
	           System.out.println("[SERVER] Caught exception, exiting.");
	           exp.printStackTrace(System.out);
	   }          
	}
	
	private String analysis(String qns) {
		// TODO Auto-generated method stub
		int len = qns.length();
		int index = qns.indexOf(".*");
		String result = null;
		Map<String,String> boDestinationMap = monitor.getBoDestinationMap();
		
		if(index == -1) {
			log.info("don't need analysis");
			for(Map.Entry<String, String> each:boDestinationMap.entrySet()) {
				String destination = each.getValue();
				if(destination.equalsIgnoreCase(qns)) {
					result = each.getKey();
					return result;
				}				
			}
		}
		
		if(!qns.endsWith(".*")) {
			log.info("format error");
			result = "1";
			return result;
		}
				
		if(boDestinationMap == null) {
			log.info("there is no bo-destination Map");
			result = "2";
			return result;
		}
		String start = qns.substring(0,index);
		for(Map.Entry<String, String> each:boDestinationMap.entrySet()) {
			String destination = each.getValue();
			if(destination.startsWith(start)) {
				String boName = each.getKey();
				if(result==null) {
					result = boName;
				}else {
					result = result + "," + boName;
				}				
			}
		}
		
		return result;
//		log.info(result);		
	}
		
}
