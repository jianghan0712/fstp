package com.purefun.fstp.core.server.hb;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.jms.BytesMessage;
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

import com.purefun.fstp.core.bo.ServerStatsBO;
import com.purefun.fstp.core.bo.model.BOinstance;
import com.purefun.fstp.core.bo.otw.ServerStatsBO_OTW;
import com.purefun.fstp.core.cache.FCache;
import com.purefun.fstp.core.constant.RpcConstant;
import com.purefun.fstp.core.server.monitor.MonitorService;
import com.purefun.fstp.core.tool.RPCTool;

import redis.clients.jedis.Jedis;

public class HBServer{
	Logger log = null;
	Session session = null;
	FCache fcache = null;	
	MonitorService monitor = null;
	String desname = null;
	
	public HBServer(Logger log,Session session,FCache fcache,MonitorService server,String topic) {
		this.log = log;
		this.session = session;
		this.fcache = fcache;
		this.monitor = server;
		this.desname = topic;
	}
	
	public void publish() {
		if(session == null) {
			log.error("There is no useful connect to broker");
			return;
		}
		Map<String,Integer> onlineServerMap = monitor.getOnlineServerMap();//在线服务列表
        Map<String,BOinstance> serviceBOMap = monitor.getServiceBOMap();
		try {
			Destination destination = session.createTopic(desname);
			MessageConsumer messageConsumer = session.createConsumer(destination);
	        MessageProducer messageProducer = session.createProducer(null);
	        			
	        while (true) {
//	        	ServerStatsBO_OTW receiveBO = new ServerStatsBO_OTW();
	        	BytesMessage message = (BytesMessage)messageConsumer.receive();
	        	byte[] byteArray = new byte[1024];
	        	int len = -1;
	        	len=message.readBytes(byteArray);
	        	
	        	if(len == -1){ 
	        		continue;
	        	}
	        	ServerStatsBO_OTW receiveBO = new ServerStatsBO_OTW(RPCTool.subBytes(byteArray, 0, len));
//	        	ServerStatsBO reveivebo = (ServerStatsBO)message.getObject();
				String serverFullName = receiveBO.getServername();
				String serverName = serverFullName.substring(0,serverFullName.indexOf("_"));
				int status = receiveBO.getStatus();				
				
				TextMessage responseMessage = null;
	        	if(status == RpcConstant.ONLINE_SERVER) {
	        		log.info("receive status bo：serverName:{},status:{}",serverName,status);
	    			
	    			if(onlineServerMap.putIfAbsent(serverFullName, status)==null) {
	    				log.info("server {} online ",serverName);
	    				responseMessage = session.createTextMessage("0");		        		
	    			}else {
	    				log.info("server {} online failure",serverName);
	    				responseMessage = session.createTextMessage("-1");
	    			}	        		        		
	        	}else if(status == RpcConstant.HEART_BEAT) {
	        		log.info("[HB] Received HB from service: {}", serverFullName);
	        		responseMessage = session.createTextMessage("2");
//	        		messageProducer.send(message.getJMSReplyTo(), responseMessage, DeliveryMode.NON_PERSISTENT, Message.DEFAULT_PRIORITY, Message.DEFAULT_TIME_TO_LIVE);
	        	}else if(status == RpcConstant.OFFLINE_SERVER) {
	        		onlineServerMap.remove(serverFullName);
	        		boolean deleteFlag = true;
	        		//如果该service的所有实例都已经down了，需要把cache中对应的历史bo删除掉
	        		for(String key : onlineServerMap.keySet()) {
	        			String name = key.substring(0,key.indexOf("_"));
	        			if(name.equalsIgnoreCase(serverName)) {
	        				deleteFlag = false;
	        				continue;
	        			}
	        		}
	        		if(deleteFlag) {
	        			BOinstance bo = serviceBOMap.get(serverName);
	        			if(bo != null) {
	        				log.info("clean cache :{}",bo.getBoEntry());
		        			fcache.delObjct(bo.getBoEntry());
	        			}        			
	        		}
	        		log.info("[HB] service {} status change to offline", serverFullName);
	        		continue;
	        	}           	
	        	messageProducer.send(message.getJMSReplyTo(), responseMessage, DeliveryMode.NON_PERSISTENT, Message.DEFAULT_PRIORITY, Message.DEFAULT_TIME_TO_LIVE);
	       }

	   } catch (Exception exp) {
	           System.out.println("[SERVER] Caught exception, exiting.");
	           exp.printStackTrace(System.out);
//	           System.exit(1);
	   }          
	}
		
}
