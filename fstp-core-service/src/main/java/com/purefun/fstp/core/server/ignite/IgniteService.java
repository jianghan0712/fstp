package com.purefun.fstp.core.server.ignite;

import java.util.List;
import java.util.Map;

import javax.jms.JMSException;

import org.slf4j.Logger;
import com.google.protobuf.InvalidProtocolBufferException;
import com.purefun.fstp.core.bo.TestBO;
import com.purefun.fstp.core.bo.otw.RDSStockBO_OTW;
import com.purefun.fstp.core.bo.otw.TestBO_OTW;
import com.purefun.fstp.core.ipc.msglistener.QnsMessageListener;
import com.purefun.fstp.core.ipc.msglistener.QueryMessageListener;
import com.purefun.fstp.core.logging.PLogger;
import com.purefun.fstp.core.server.PService;

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
//		bo.setQuerytopic("fstp.ace.rds.server.stock");
//		que.query(bo, new TestListener(log));
		
//		ICache test = (ICache) Icache;
//		List<RDSStockBO> a = (List<RDSStockBO>)test.get("com.purefun.fstp.core.bo.RDSStockBO", null);
//		for(RDSStockBO bo: a) {
//			log.info(bo.secu_name_cn);			
//		}
				
//		QNSubscriber qns = rpcfactory.createQNSubscriber();
//		qns.init("fstp.core.rpc.testone", serverName);
//		qns.QNS(new Listener(log));
						
	}
	class TestListener extends QueryMessageListener{

		public TestListener(Logger log) {
			super(log);
			// TODO Auto-generated constructor stub
		}

		@Override
		protected void doTask(byte[] objMsg) {
			// TODO Auto-generated method stub
			RDSStockBO_OTW a;
			try {
				a = new RDSStockBO_OTW(objMsg);
				log.info(a.getSecu_name_cn());
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
		protected void doQueryTask(Map.Entry<String, List> m) {
			// TODO Auto-generated method stub
			String boName = m.getKey();
			List eachList = m.getValue();
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
