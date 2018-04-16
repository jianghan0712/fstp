package com.purefun.fstp.core.rpc.pub;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.jms.BytesMessage;
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
import com.purefun.fstp.core.bo.commom.ICommom_OTW;
import com.purefun.fstp.core.bo.otw.TestBO2_OTW;
import com.purefun.fstp.core.cache.FCache;
import com.purefun.fstp.core.cache.ObjectTransCoder;
import com.purefun.fstp.core.rpc.PublishMode;
import com.purefun.fstp.core.rpc.qns.QNSClient;

import redis.clients.jedis.Jedis;

public class Publisher{
	Logger log = null;
	Session session = null;
	FCache fcache = null;
	
	public Publisher(Logger log,Session session,FCache fcache) {
		this.log = log;
		this.session = session;
		this.fcache = fcache;
	}
	
	public void publish(ICommom_OTW bo,int mode) {
		if(session == null) {
			log.error("There is no useful connect to broker");
			return;
		}
		
		try {
			Destination destination = session.createTopic(bo.getDestination());
			MessageProducer messageProducer = session.createProducer(destination);
			
//			ObjectMessage message = session.createObjectMessage();
//        	message.setObject((Serializable) bo);
        	BytesMessage message = session.createBytesMessage();
        	message.writeBytes(bo.getBuilder().build().toByteArray());
        	      	
            messageProducer.send(message);
            if(mode == PublishMode.PUBLISH_AND_DUR)
            	durableInCache(bo);
            log.info("publish BO:[{}]",bo.toString());
            
		} catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
	
	public void durableInCache(ICommom_OTW bo) {
		String mapName = bo.getBo().getClass().getName();	
		fcache.setList(mapName, bo.getBuilder().build().toByteArray());
//		fcache.setList(mapName, bo);
	}	
}
