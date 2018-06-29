package com.purefun.fstp.core.server.ignite;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.jms.JMSException;

import org.apache.commons.logging.impl.Log4JLogger;
import org.apache.ignite.Ignite;
import org.apache.ignite.IgniteCache;
import org.apache.ignite.IgniteLogger;
import org.apache.ignite.Ignition;
import org.apache.ignite.cache.affinity.AffinityKey;
import org.apache.ignite.cache.query.QueryCursor;
import org.apache.ignite.cache.query.SqlFieldsQuery;
import org.apache.ignite.cache.query.SqlQuery;
import org.apache.ignite.configuration.IgniteConfiguration;
import org.apache.ignite.internal.processors.cache.CacheEntryImpl;
import org.apache.ignite.logger.slf4j.Slf4jLogger;
import org.slf4j.Logger;
import org.springframework.data.repository.CrudRepository;

import com.google.protobuf.InvalidProtocolBufferException;
import com.purefun.fstp.ace.rds.server.RDSCommon;
import com.purefun.fstp.core.bo.RDSStockBO;
import com.purefun.fstp.core.bo.TestBO;
import com.purefun.fstp.core.bo.otw.QueryRequestBO_OTW;
import com.purefun.fstp.core.bo.otw.TestBO_OTW;
import com.purefun.fstp.core.bo.pro.TestBO_PRO;
import com.purefun.fstp.core.cache.ignitecache.ICache;
import com.purefun.fstp.core.cache.rediscache.RCache;
import com.purefun.fstp.core.ipc.PublishMode;
import com.purefun.fstp.core.ipc.msglistener.QnsMessageListener;
import com.purefun.fstp.core.ipc.msglistener.QueryMessageListener;
import com.purefun.fstp.core.ipc.pub.Publisher;
import com.purefun.fstp.core.ipc.qns.QNSubscriber;
import com.purefun.fstp.core.ipc.query.Query;
import com.purefun.fstp.core.logging.PLogger;
import com.purefun.fstp.core.model.Person;
import com.purefun.fstp.core.model.repo.PersonRepository;
import com.purefun.fstp.core.server.PService;

import redis.clients.jedis.Jedis;

public class IgniteService extends PService{
	
	public IgniteService(boolean isServer) {	
		super(isServer);
		// TODO Auto-generated constructor stub
		log = PLogger.getLogger(IgniteService.class);
	}
	
	public void init() {
		super.init();		
		
		log.info("{} init successful",serverName);			
	}
	
	public void start() {
		super.start();	
//		Publisher pub = rpcfactory.createPublisher();
//		String key = null;
//		for(int i = 0;i<3;i++) {
//			TestBO_OTW bo = new TestBO_OTW();
//			bo.setMsg("Hans");
//			pub.publish(bo, PublishMode.PUBLISH_AND_DUR, "com.purefun.fstp.core.bo.TestBO");
//			log.info(bo.getUuid());
//		}
		
//		Query que = rpcfactory.createQuery();
//		QueryRequestBO_OTW bo = new QueryRequestBO_OTW();
//		bo.setQuerytopic("fstp.core.rpc.testone");
//		que.query(bo, new TestListener(log));
		
//		ICache test = (ICache) Icache;
//		List<TestBO> a = (List<TestBO>)test.get("com.purefun.fstp.core.bo.TestBO", null);
//		for(TestBO bo: a) {
//			log.info("{}",a.size());
//			log.info(bo.uuid);			
//		}
				
		QNSubscriber qns = rpcfactory.createQNSubscriber();
		qns.init("fstp.core.rpc.testone", serverName);
		qns.QNS(new Listener(log));
						
	}
	class TestListener extends QueryMessageListener{

		public TestListener(Logger log) {
			super(log);
			// TODO Auto-generated constructor stub
		}

		@Override
		protected void doTask(byte[] objMsg) {
			// TODO Auto-generated method stub
			TestBO_OTW a;
			try {
				a = new TestBO_OTW(objMsg);
				log.info(a.getUuid());
			} catch (InvalidProtocolBufferException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}		
		}
		
	}
	
	class Listener extends QnsMessageListener{

		public Listener(Logger log) {
			super(log);
			// TODO Auto-generated constructor stub
		}

		@Override
		protected void doSubscribe(byte[] bytesMsg) throws InvalidProtocolBufferException, JMSException {
			// TODO Auto-generated method stub
	    	TestBO_OTW receiveBO = new TestBO_OTW(bytesMsg);
	    	log.info("receive：{}",receiveBO.toString());
	    	
		}

		@Override
		protected void doQueryTask(List eachList) {
			// TODO Auto-generated method stub
			int len = eachList.size();
			for(int i = 0; i<len; i++) {
				try {		
					TestBO_OTW receiveBO;
					if(TestBO.class.isInstance(eachList.get(i))) {
						receiveBO = new TestBO_OTW((TestBO)eachList.get(i));
					}else {
						receiveBO = new TestBO_OTW((byte[])eachList.get(i));
					}					
					log.info(receiveBO.getDestination());
				} catch (InvalidProtocolBufferException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
	}
	
}
