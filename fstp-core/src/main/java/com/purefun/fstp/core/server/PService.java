package com.purefun.fstp.core.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javax.jms.Session;
import org.apache.xbean.spring.context.ClassPathXmlApplicationContext;
import org.slf4j.Logger;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.ApplicationContext;

import com.purefun.fstp.core.bo.ServerStatsBO;
import com.purefun.fstp.core.cache.CacheClient;
import com.purefun.fstp.core.constant.RpcConstant;
import com.purefun.fstp.core.qpid.QpidConnect;
import com.purefun.fstp.core.rpc.RpcFactory;
import com.purefun.fstp.core.server.hb.HBClient;

import redis.clients.jedis.Jedis;

public class PService {
	protected static PProperty property ;
	protected Jedis cache = null;
	protected String serverName = null;
	protected static Logger log = null;
	boolean isServer = true;
	boolean connectMonitor = false;
	protected String defaultPath = System.getProperty("user.dir") + "\\src\\resource\\msgdef";
	protected BeanFactory beanFactory = null;
//	protected String zmqport = null;
//	protected MyTask registTask = null;
	protected RpcFactory rpcfactory = null;
//	protected Publisher pub = null;
//	protected Subscriber sub = null;
//	protected int HBPort = 0;
	protected HBClient hb = null;
	
//	protected Connection connection = null;
	protected Session session = null;
	protected QpidConnect connection = null;
		
	public ScheduledExecutorService HBThreadPool = Executors.newScheduledThreadPool(1); 
//	public ScheduledExecutorService scheduledThreadPool = Executors.newScheduledThreadPool(1); 
		
	public PService(boolean isServer,String zmqport) {		
		this.serverName = property.allTag;
		this.isServer = isServer;
//		this.zmqport = zmqport;
	}
	
	public void init() {
		log.info("-------------------------------------------------");
		log.info("|             This is PILOT                     |");
		log.info("-------------------------------------------------");
		log.info("Create a new PILOT Server:{}",serverName);
		this.cache =  CacheClient.getJedis();
		connection = new QpidConnect(log);
		/**********		STEP 1: Load service config file		***************/
//		if(!loadConfigFile(new File(defaultPath))) {
//			log.error("Step 1: Load Server Config File Failure");
//			return;
//		}
		
//		if(!serverName.equalsIgnoreCase("MonitorService")) {
//			
//			scheduledThreadPool.schedule(new ZMQRequester(zmqport, log ,serverName,registTask), 0, TimeUnit.SECONDS);	
//		}else {//
//			log.info("[MonitorService] start ServerStats Listener");
//			scheduledThreadPool.schedule(new ZMQResponsder(zmqport,serverName,registTask,log), 0, TimeUnit.SECONDS);
//		}	
		/**********	     STEP 2: Connet to broker	   	***************/
		if(connection!=null)
			session = connection.connect();
		/**********	     STEP 3: create rpc factory	   	***************/
		rpcfactory = new RpcFactory(session, cache, log);
		
		/**********	     STEP 4: Register to monitor	   	***************/		
		if(!property.serverName.equalsIgnoreCase("MonitorService")) {
			ServerStatsBO bo = new ServerStatsBO(serverName, RpcConstant.ONLINE_SERVER);
			hb = new HBClient(log, session, cache,"HBTopic",serverName);
			hb.publish(bo);
//			pub = RpcFactory.createPublisher();
//			pub.publish(new ServerStatsBO(serverName, RpcConstant.ONLINE_SERVER));
			
//			scheduledThreadPool.schedule(new ZMQRequester(zmqport, log ,serverName,registTask), 0, TimeUnit.SECONDS);
//			sub.subscribe("pilot.core.manager.serverstatus", new MonitorListener(log));
		}else {//monitorServer 
//			sub = RpcFactory.createSubscriber();
//			pub.publish(new ServerStatsBO(serverName, 1));
//			scheduledThreadPool.schedule(new ZMQRequester(zmqport, log ,serverName,registTask), 0, TimeUnit.SECONDS);
//			sub.subscribe("pilot.core.manager.serverstatusRep", new ServerStatusListener(log));
		}
	}

//	

	public void start() {
		HBThreadPool.scheduleAtFixedRate(new HBThread(), 0,10, TimeUnit.SECONDS);
	}
	
	public void stop() {
		
	}	
	
	public class HBThread implements Runnable{
		ServerStatsBO bo = new ServerStatsBO(serverName, RpcConstant.HEART_BEAT);
		
		@Override		
		public void run() {
			// TODO Auto-generated method stub			
			hb.publish(bo);				
		}
		
	}

	private static void getProperty() {
		// TODO Auto-generated method stub
		property = new PProperty();
		property.serverName = System.getProperty("ServiceName");
		property.env = System.getProperty("Env");
		property.instance =  System.getProperty("Instance");
		property.allTag = property.serverName + "_" + property.env + "_" + property.instance;
	}
	
	
	
	public static void main(String[] args) {		
		getProperty();
		String configPath = "config/" + property.serverName + "/" + property.env + "/" + property.instance + "/config.xml";
				
		ApplicationContext beanFactory=new ClassPathXmlApplicationContext(configPath);
		PService server = (PService)beanFactory.getBean("MainServer");
		server.beanFactory = beanFactory;
		server.init();
		server.start();
		
		
	}


}
