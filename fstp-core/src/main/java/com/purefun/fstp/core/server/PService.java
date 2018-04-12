package com.purefun.fstp.core.server;

import java.util.List;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javax.jms.Session;
import org.apache.xbean.spring.context.ClassPathXmlApplicationContext;
import org.slf4j.Logger;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.ApplicationContext;
import com.purefun.fstp.core.bo.ServerStatsBO;
import com.purefun.fstp.core.bo.copy.otw.ServerStatsBO_OTW;
import com.purefun.fstp.core.cache.FCache;
import com.purefun.fstp.core.constant.RpcConstant;
import com.purefun.fstp.core.qpid.QpidConnect;
import com.purefun.fstp.core.rpc.RpcFactory;
import com.purefun.fstp.core.rpc.query.QueryServerSide;
import com.purefun.fstp.core.server.hb.HBClient;
import com.purefun.fstp.core.tool.ErrorManager;

public class PService {
	protected static PProperty property ;
//	protected Jedis cache = null;
	protected FCache fcache = null;
	protected String serverName = null;
	public static Logger log = null;
	boolean isServer = true;
	boolean connectMonitor = false;
	protected String defaultPath = System.getProperty("user.dir") + "\\src\\resource\\msgdef";
	protected BeanFactory beanFactory = null;
	protected RpcFactory rpcfactory = null;
	protected HBClient hb = null;
	
	protected Session session = null;
	protected QpidConnect connection = null;
		
	public ScheduledExecutorService HBThreadPool = Executors.newScheduledThreadPool(1); 
	public ScheduledExecutorService scheduledQueryThread = Executors.newScheduledThreadPool(1);  //查询请求服务
//	public ErrorManager errManager = null;
	public Map<String,String> ErrMap = null;
	public Map<String,String> managerBOMap = null;
		
	public PService(boolean isServer) {		
		this.serverName = property.fullName;
		this.isServer = isServer;
	}
	
	public void init() {
		log.info("------------------------------------------------------------------------");
		log.info("|                 This is Free & Super Trading Platform                |");
		log.info("------------------------------------------------------------------------");
		log.info("Create a new FSTP Server:{}",serverName);

		fcache = new FCache(this);
		connection = new QpidConnect(log);
		/**********		STEP 1: Load service config file		***************/
		/*	removed to jvm para*/
		
		/**********	     STEP 2: Connet to broker	   	***************/
		if(connection!=null)
			session = connection.connect();
		
		/**********	     STEP 3: create rpc factory	   	***************/
		rpcfactory = new RpcFactory(session, fcache, log);
		
		/**********	     STEP 4: Register to monitor	   	***************/		
		if(!property.serverName.equalsIgnoreCase("MonitorService")) {
//			ServerStatsBO bo = new ServerStatsBO(serverName, RpcConstant.ONLINE_SERVER);
			ServerStatsBO_OTW bo = new ServerStatsBO_OTW();
			bo.setServername(serverName);
			bo.setStatus(RpcConstant.ONLINE_SERVER);
			hb = new HBClient(log, session, "HBTopic",serverName);
			hb.publish(bo);

		}
		/*  monitor service don't need register*/
		/**********	     STEP 5: Get Common info	   	***************/
		ErrMap = ((ErrorManager)beanFactory.getBean("errorManager")).getErrorMap();
	}

	public void start() {
		/*	heart beat */
		HBThreadPool.scheduleAtFixedRate(new HBThread(), 0,10, TimeUnit.SECONDS);
		startQueryService();
	}
	
	public void stop() {
		
	}	
	
	public class HBThread implements Runnable{
//		ServerStatsBO bo = new ServerStatsBO(serverName, RpcConstant.HEART_BEAT);
		ServerStatsBO_OTW bo = new ServerStatsBO_OTW();
		
		@Override		
		public void run() {
			// TODO Auto-generated method stub	
			bo.setServername(serverName);
			bo.setStatus(RpcConstant.HEART_BEAT);
			hb.publish(bo);				
		}		
	}
	
	public void startQueryService() {
		// TODO Auto-generated method stub
		scheduledQueryThread.schedule(new QueryServerSide(log, session, fcache, property.serverName, "QueryTopic",managerBOMap), 0, TimeUnit.SECONDS);
	}

	private static void getProperty() {
		// TODO Auto-generated method stub
		property = new PProperty();
		property.serverName = System.getProperty("ServiceName");
		property.env = System.getProperty("Env");
		property.instance =  System.getProperty("Instance");
		property.fullName = property.serverName + "_" + property.env + "_" + property.instance;
	}
		
	public BeanFactory getBeanFactory() {
		return beanFactory;
	}

	public void setBeanFactory(BeanFactory beanFactory) {
		this.beanFactory = beanFactory;
	}
		
	public String getServerName() {
		return serverName;
	}

	public void setServerName(String serverName) {
		this.serverName = serverName;
	}
	
	public Map<String, String> getManagerBOMap() {
		return managerBOMap;
	}

	public void setManagerBOMap(Map<String, String> managerBOMap) {
		this.managerBOMap = managerBOMap;
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
