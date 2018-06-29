package com.purefun.fstp.core.ipc.pub;

import javax.jms.BytesMessage;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Session;
import org.slf4j.Logger;

import com.purefun.fstp.core.bo.commom.ICommom_OTW;
import com.purefun.fstp.core.cache.ICommonCache;
import com.purefun.fstp.core.cache.ignitecache.ICache;
import com.purefun.fstp.core.cache.rediscache.RCache;
import com.purefun.fstp.core.ipc.PublishMode;

public class Publisher{
	Logger log = null;
	Session session = null;
	ICommonCache cache = null;
	
	public Publisher(Logger log,Session session,ICommonCache cache) {
		this.log = log;
		this.session = session;
		this.cache = cache;
	}

	public void publish(ICommom_OTW bo,int mode,String cacheName) {
		if(session == null) {
			log.error("There is no useful connect to broker");
			return;
		}
		
		try {
			Destination destination = session.createTopic(bo.getDestination());
			MessageProducer messageProducer = session.createProducer(destination);
			
        	BytesMessage message = session.createBytesMessage();
        	message.writeBytes(bo.getBuilder().build().toByteArray());
        	      	
            messageProducer.send(message);
            if(mode == PublishMode.PUBLISH_AND_DUR)
            	durableInCache(bo, cacheName);
            log.info("publish BO:[{}]",bo.toString());
            
		} catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
	
	public void durableInCache(ICommom_OTW bo, String cacheName) {
		String mapName = bo.getBo().getClass().getName();
		if(RCache.class.isInstance(cache))
			cache.put(cacheName, bo.getUuid(), bo.getBuilder().build().toByteArray());
		else if(ICache.class.isInstance(cache))
			cache.put(cacheName, bo.getUuid(), bo.getBo());
	}	
}
