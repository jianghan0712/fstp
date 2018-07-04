package com.purefun.fstp.core.server;

import java.util.List;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javax.jms.Session;

import org.apache.ignite.Ignite;
import org.apache.ignite.IgniteCache;
import org.apache.ignite.Ignition;
import org.apache.ignite.configuration.CacheConfiguration;
import org.apache.ignite.springdata.repository.config.EnableIgniteRepositories;
import org.slf4j.Logger;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.purefun.fstp.core.bo.ServerStatsBO;
import com.purefun.fstp.core.bo.otw.ServerStatsBO_OTW;
import com.purefun.fstp.core.cache.ICommonCache;
import com.purefun.fstp.core.cache.ignitecache.ICache;
import com.purefun.fstp.core.cache.rediscache.RCache;
import com.purefun.fstp.core.constant.RpcConstant;
import com.purefun.fstp.core.ignite.cfg.IgniteCfg;
import com.purefun.fstp.core.ipc.RpcFactory;
import com.purefun.fstp.core.ipc.query.QueryService;
import com.purefun.fstp.core.logging.PLogger;
import com.purefun.fstp.core.qpid.QpidConnect;
import com.purefun.fstp.core.server.hb.HBClient;
import com.purefun.fstp.core.tool.BoFactory;

@EnableIgniteRepositories
public class PService {
	protected static PProperty property ;
	protected String serverName = null;
	public static Logger log = null;
	boolean isServer = true;
//	boolean connectMonitor = false;
//	protected String defaultPath = System.getProperty("user.dir") + "\\src\\resource\\msgdef";
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
	
	public IgniteCfg cfg ;	
	public Ignite ignite = null;
	
	public ICommonCache Icache = null;
	public String cacheType = null;
		
	public PService(boolean isServer) {		
		this.serverName = property.fullName;
		this.isServer = isServer;
	}
	
	public PService() {		
		getProperty();
//		log = PLogger.getLogger(TestPython.class);
		log.info(property.toString());
		this.serverName = property.fullName;	
	}
	
	public void init() {		
		log.info("------------------------------------------------------------------------");
		log.info("|                 This is Free & Super Trading Platform                |");
		log.info("------------------------------------------------------------------------");
		log.info("Create a new FSTP Server:{}",serverName);

		connection = new QpidConnect(log);
		/**********		STEP 1: Load service config file		***************/
		if(cacheType.equalsIgnoreCase("ignite")) {
			cfg = beanFactory.getBean(IgniteCfg.class);
			cfg.init(log);
			ignite = Ignition.start(cfg.getCfg());
			Icache = new ICache(ignite, (List<CacheConfiguration>)beanFactory.getBean("cacheConfigurationList"));
		}else if(cacheType.equalsIgnoreCase("redis")) {
			Icache = new RCache(log, serverName, beanFactory);
		}		
		
		/**********	     STEP 2: Connet to broker	   	***************/
		if(connection!=null)
			session = connection.connect();
		
		/**********	     STEP 3: create rpc factory	   	***************/
		rpcfactory = new RpcFactory(session, Icache, log);
		
		/**********	     STEP 4: Register to monitor	   	***************/		
		if(!property.serverName.equalsIgnoreCase("MonitorService")) {
			ServerStatsBO_OTW hbbo = (ServerStatsBO_OTW)BoFactory.createBo(ServerStatsBO.class);
			hbbo.setServername(serverName);
			hbbo.setStatus(RpcConstant.ONLINE_SERVER);
			hb = new HBClient(log, session, "HBTopic",serverName);
			hb.publish(hbbo);
		}

		/**********	     STEP 5: Get Common info	   	***************/
//		ErrMap = ((ErrorManager)beanFactory.getBean("errorManager")).getErrorMap();		
	}

	public void start() {
		/*	heart beat */
		/**********	     STEP 6: HB Thread	   	***************/
		if(!property.develop.equalsIgnoreCase("python")) {
			HBThreadPool.scheduleAtFixedRate(new HBThread((ServerStatsBO_OTW)BoFactory.createBo(ServerStatsBO.class)), 0, 60, TimeUnit.SECONDS);
		}
		
		/**********	     STEP 7: Query Thread	   	***************/
		scheduledQueryThread.schedule(new QueryService(log, session, Icache, property.serverName, managerBOMap), 0, TimeUnit.SECONDS);
		
		log.info("<====================   active");
	}
	
	public void stop() {
		
	}	
	
	public class HBThread implements Runnable{
		ServerStatsBO_OTW bo = null;
		public HBThread(ServerStatsBO_OTW bo) {
			this.bo = bo;
		}
		
		@Override		
		public void run() {
			// TODO Auto-generated method stub	
			bo.setServername(serverName);
			bo.setStatus(RpcConstant.HEART_BEAT);
			hb.publish(bo);				
		}		
	}

	private static void getProperty() {
		// TODO Auto-generated method stub
		property = new PProperty();
		property.serverName = System.getProperty("ServiceName");
		property.env = System.getProperty("Env");
		property.instance =  System.getProperty("Instance");
		property.fullName = property.serverName + "_" + property.env + "_" + property.instance;
		property.develop = System.getProperty("Develop");
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

	public boolean isServer() {
		return isServer;
	}

	public void setServer(boolean isServer) {
		this.isServer = isServer;
	}

	public RpcFactory getRpcfactory() {
		return rpcfactory;
	}

	public void setRpcfactory(RpcFactory rpcfactory) {
		this.rpcfactory = rpcfactory;
	}

	public Map<String, String> getErrMap() {
		return ErrMap;
	}

	public void setErrMap(Map<String, String> errMap) {
		ErrMap = errMap;
	}
	
	public String getCacheType() {
		return cacheType;
	}

	public void setCacheType(String cacheType) {
		this.cacheType = cacheType;
	}

	public static void main(String[] args) {		
		getProperty();
		String configPath = null;
		PService server = null;
		if(property.develop.equalsIgnoreCase("python")) {			
			configPath = "config/PythonService/" +  property.env + "/" + property.instance + "/config.xml";	
		}else if(property.develop.equalsIgnoreCase("java")){
			configPath = "config/" + property.serverName + "/" + property.env + "/" + property.instance + "/config.xml";				
		}

		ApplicationContext beanFactory=new ClassPathXmlApplicationContext(configPath);
		server = (PService)beanFactory.getBean("MainServer");
		server.beanFactory = beanFactory;
		
		server.init();
		server.start();	
		
	}
}
