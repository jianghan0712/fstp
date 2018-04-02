package com.purefun.fstp.core.server.monitor;

import java.io.File;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javax.jms.JMSException;
import javax.jms.ObjectMessage;

import org.apache.xbean.spring.context.ClassPathXmlApplicationContext;
import org.slf4j.Logger;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import com.purefun.fstp.core.bo.model.BOinstance;
import com.purefun.fstp.core.logging.PLogger;
import com.purefun.fstp.core.rpc.qns.QNSService;
import com.purefun.fstp.core.server.PService;
import com.purefun.fstp.core.server.hb.HBServer;

public class MonitorService extends PService{
	
	public ScheduledExecutorService scheduledQNSThread = Executors.newScheduledThreadPool(1); //QNS解析服务
	public ScheduledExecutorService scheduledHBThread = Executors.newScheduledThreadPool(1);  //心跳服务

	private Map<String,String> boDestinationMap = new HashMap<String,String>();//bo与destination的map，从config文件中读取
	public Map<String,Integer> onlineServerMap = new HashMap<String,Integer>();    //在线service列表
	public Map<String,BOinstance> serviceBOMap = new HashMap<String,BOinstance>(); //<serviceName,BOinstance>
	public Map<String,BOinstance> boMap = new HashMap<String,BOinstance>();        //<boName,BOinstance>
	
	public MonitorService(boolean isServer, String zmqport) {	
		super(isServer,zmqport);
		// TODO Auto-generated constructor stub
		log = PLogger.getLogger(MonitorService.class);
	}
	
	public void init() {
		super.init();		
		loadBOmsgdef(new File("resource/msgdef/"));
		log.info("{} init successful",serverName);			
	}
	
	public void start() {
		super.start();
		startHBService();
		startQNSService();	
		log.info("{} start successful",serverName);	
		log.info(ErrMap.get("00000000"));
	}

	private void startHBService() {
		// TODO Auto-generated method stub
		scheduledHBThread.schedule(new HBThread(), 0, TimeUnit.SECONDS);
		
	}
	
	private void startQNSService() {
		// TODO Auto-generated method stub
		scheduledQNSThread.schedule(new QNSThread(), 0, TimeUnit.SECONDS);
	}
	
	public class HBThread implements Runnable{		
		@Override
		public void run() {
			HBServer hb = new HBServer(log, session, cache,MonitorService.this,"HBTopic");
			hb.publish();

		}	
	}
	
	public class QNSThread implements Runnable{		
		@Override
		public void run() {
//			HBServer hb = new HBServer(log, session, cache,MonitorService.this,"HBTopic");
//			hb.publish();
			QNSService qns = new QNSService(log, session, cache,MonitorService.this,"QNSqueryTopic");
			qns.publish();
		}	
	}
	
	private boolean loadBOmsgdef(File directory) {
		// TODO Auto-generated method stub
		boolean ret = false;
		
		File flist[] = directory.listFiles();
		BeanFactory bean = null;
		
		if (flist == null || flist.length == 0) {
		    log.warn("The Server config file is not FOUND");
		}else {
			for (File f : flist) {
			    if (f.isDirectory()) {
			    	loadBOmsgdef(f);
			    } else {
//			    	"config/" + property.serverName + "/" + property.env + "/" + property.instance + "/config.xml";
			    	String filename = f.getName();
			    	bean  = new ClassPathXmlApplicationContext(f.getPath());	
			    	BOinstance bobean = (BOinstance)bean.getBean("BOinstance");
			    	boMap.put(bobean.getBoEntry(), bobean);
			    	
			    	List<String> serverList = bobean.getServerList();
			    	for(String serverName :serverList) {
			    		serviceBOMap.put(serverName, bobean);
			    	}
			    	boDestinationMap.put(bobean.getBoEntry(), bobean.getDestination());
			    	log.info("Load BO {} msgdef with path:{}",bobean.name,f.getPath());
			    }		    		    	
			}
		}
		return ret;
	}
	
	public Map<String, String> getBoDestinationMap() {
		return boDestinationMap;
	}

	public void setBoDestinationMap(Map<String, String> boDestinationMap) {
		this.boDestinationMap = boDestinationMap;
	}

	public Map<String, Integer> getOnlineServerMap() {
		return onlineServerMap;
	}

	public void setOnlineServerMap(Map<String, Integer> onlineServerMap) {
		this.onlineServerMap = onlineServerMap;
	}

	public Map<String, BOinstance> getServiceBOMap() {
		return serviceBOMap;
	}

	public void setServiceBOMap(Map<String, BOinstance> serviceBOMap) {
		this.serviceBOMap = serviceBOMap;
	}

	public Map<String, BOinstance> getBoMap() {
		return boMap;
	}

	public void setBoMap(Map<String, BOinstance> boMap) {
		this.boMap = boMap;
	}
	
	

}
