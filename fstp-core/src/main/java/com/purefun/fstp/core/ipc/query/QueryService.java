package com.purefun.fstp.core.ipc.query;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.jms.BytesMessage;
import javax.jms.DeliveryMode;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageProducer;
import javax.jms.Session;
import org.slf4j.Logger;

import com.purefun.fstp.core.bo.otw.QueryRequestBO_OTW;
import com.purefun.fstp.core.cache.ICommonCache;
import com.purefun.fstp.core.cache.ignitecache.ICache;
import com.purefun.fstp.core.cache.rediscache.RCache;
import com.purefun.fstp.core.tool.BoFactory;
import com.purefun.fstp.core.tool.RPCTool;

public class QueryService implements Runnable{
	protected Logger log = null;
	Session session = null;
	protected ICommonCache cache = null;	
	protected String serviceName = null;
	public Map<String, String> selfTopic = null;//<topic,boname>  config-->managerBOMap
	final String QUERY_TOPIC = "QueryTopic"; 
	
	public QueryService(Logger log, Session session, ICommonCache cache, String serviceName, Map<String, String> selfTopic) {
		this.log = log;
		this.session = session;
		this.serviceName = serviceName;
		this.cache = cache;
		this.selfTopic = selfTopic;
	}
		
	@Override
	public void run() {
		if(session == null) {
			log.error("There is no useful connect to broker");
			return;
		}			
		try {
			Destination destination = session.createTopic(QUERY_TOPIC);
			MessageConsumer messageConsumer = session.createConsumer(destination);
	        MessageProducer messageProducer = session.createProducer(null);
			
	        while (true) {
	        	BytesMessage message = (BytesMessage) messageConsumer.receive();
	        	try {
	    			byte[] byteArray = new byte[1024];
	            	int len = -1;
	            	len = message.readBytes(byteArray);
	            	if(len == -1){ 
	            		return;
	            	}
					QueryRequestBO_OTW queryBO = new QueryRequestBO_OTW(RPCTool.subBytes(byteArray, 0, len));
					List<byte[]> queResult = queryFromCache(queryBO);
	            	if(queResult != null) {
	            		log.info("receive QueryBO topic: {}.",queryBO.getQuerytopic());
	            		for(byte[] eachbo:queResult) {
		            		BytesMessage responseMessage = session.createBytesMessage();
			            	responseMessage.writeBytes(eachbo);
				        	messageProducer.send(message.getJMSReplyTo(), responseMessage, DeliveryMode.NON_PERSISTENT, Message.DEFAULT_PRIORITY, Message.DEFAULT_TIME_TO_LIVE);	        
		            	}
	            		log.info("publish {} bo.",queResult.size());
	            	}	            	            	
	    		} catch (JMSException e) {
	    			// TODO Auto-generated catch block
	    			e.printStackTrace();
	    		}	        		        		        		        	 
	        }
	   } catch (Exception exp) {
	           System.out.println("[SERVER] Caught exception, exiting.");
	           exp.printStackTrace(System.out);
	   }          
	}
	
	private List<byte[]> queryFromCache(QueryRequestBO_OTW queryBO) {
		// TODO Auto-generated method stub
		String request = queryBO.getQuerytopic();
		List<byte[]> ret = null;
		if(selfTopic==null || selfTopic.isEmpty())
			return null;
		for(Map.Entry<String, String> e:selfTopic.entrySet()) {
			if (e.getKey().equalsIgnoreCase(request)) {
				if(RCache.class.isInstance(cache)) {
					ret = (List<byte[]>)cache.get(e.getValue(), null);
				}else if(ICache.class.isInstance(cache)) {					
					ret = queryFromIgniteCache(e.getValue());
				}
			}		
		}
		return ret;
	}

	private List<byte[]> queryFromIgniteCache(String boname) {//com.purefun.fstp.core.bo.TestBO
		// TODO Auto-generated method stub
		String boName = boname;
		String[] spiltTemp = boname.split("\\.");
		int len = spiltTemp.length;
		StringBuffer otwBoName = new StringBuffer();
		List<byte[]> ret = new ArrayList<byte[]>(); 
		List tempList = (List) cache.get(boname, null);
		if(tempList == null) {
			return ret;
		}
		
		for(int i = 0;i<len-1;i++)
			otwBoName.append(spiltTemp[i]).append(".");
		
		otwBoName.append("otw.").append(spiltTemp[len-1]).append("_OTW");
		try {
			Class c1 = Class.forName(boname);		
//			Object t = BoFactory.createBo(c1);
			Class c2 = Class.forName(otwBoName.toString());
			Constructor c = c2.getDeclaredConstructor(c1);
			Method m = c2.getMethod("serial");
			
			for(int i = 0;i<tempList.size();i++) {
				if(c1.isInstance(tempList.get(i))) {			
					byte[] k = (byte[]) m.invoke(c.newInstance(tempList.get(i)));
					ret.add(k);
				}
			}		
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		return ret;
	}
		
}
