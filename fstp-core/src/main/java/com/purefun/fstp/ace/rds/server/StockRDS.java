package com.purefun.fstp.ace.rds.server;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.springframework.data.repository.CrudRepository;

import com.google.protobuf.InvalidProtocolBufferException;
import com.purefun.fstp.core.bo.QueryRequestBO;
import com.purefun.fstp.core.bo.RDSStockBO;
import com.purefun.fstp.core.bo.SourceStockBO;
import com.purefun.fstp.core.bo.TestBO;
import com.purefun.fstp.core.bo.TestBO2;
import com.purefun.fstp.core.bo.otw.SourceStockBO_OTW;
import com.purefun.fstp.core.bo.otw.TestBO2_OTW;
import com.purefun.fstp.core.bo.pro.SourceStockBO_PRO;
import com.purefun.fstp.core.logging.PLogger;
import com.purefun.fstp.core.rpc.RpcFactory;
import com.purefun.fstp.core.rpc.qns.QNSubscriber;
import com.purefun.fstp.core.rpc.query.QueryClientSide;

public class StockRDS extends RDSBase{
	CrudRepository repository = null;
	String sourceQns = null;
	Map<String,String> source2rdsMap = null;
	
	public StockRDS(boolean isServer) {
		super(isServer);
		log = PLogger.getLogger(StockRDS.class);
	}
	
	public void init() {
		super.init();
		//receive sourceBO
		sub = RpcFactory.createSubscriber();
		listener = new StockRDSSubListener(log);
		sub.subscribe(sourceQns,  listener);
		
		//publish rdsBO
		pub = RpcFactory.createPublisher();
		
	}
	
	public void start() {
		super.start();			
		sourceBOList = listener.getResultList();

		/*	test query */
//		QueryClientSide query = new QueryClientSide(log, session, fcache, "QueryTopic");
//		List<TestBO> result = query.query(new QueryRequestBO(serverName,"MonitorService","QueryTopic","fstp.core.rpc.testone"));
//		if(result != null && result.size()!=0) {
//			log.info("receive {} messages",result.size());
//		}else {
//			log.info("Query nothing");
//		}
				
		/*	test QNS */
		QNSubscriber qns = RpcFactory.createQNSubscriber();
		qns.setting("fstp.core.rpc.*", serverName);
		qns.QNS(new MyMessageListener(log));
		
		log.info("{} start successful",serverName);
//		RDSStockBO test = new RDSStockBO();
//    	test.setBond_par_value(100.00);

//		for(int i = 0;i<3;i++) {
//			TestBO_OTW test = new TestBO_OTW();
//			pub.publish(test, 0);
//		}
		
//		
//		List<byte[]> list = fcache.getList("com.purefun.fstp.core.bo.TestBO2");
//		log.info("{}",list.size());
//		
//		for(byte[] each:list) {
//			try {
//				com.purefun.fstp.core.bo.proto.TestBO2_PRO.TestBO2 receive = com.purefun.fstp.core.bo.proto.TestBO2_PRO.TestBO2.parseFrom(each);
//				log.info("receive:{}",receive.toString());
//			} catch (InvalidProtocolBufferException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		}
//		test.setMsg("this is otw");
		
//		TestBO2 test = new TestBO2();
//		test.msg = "100000";

//		TestBO2_OTW test = new TestBO2_OTW();
//		log.info(test.getMsg());
//		save2DB(test.getBo());
//		CrudRepository repo =  beanFactory.getBean(RDSStockBORepository.class);
//    	

    	
		
//		CrudRepository repo = (CrudRepository)beanFactory.getBean("curd");
//    	TestBORepository personDao = ctx.getBean(TestBORepository.class);
		
//    	
//    	repo.save(test);
    	
    	
	}

	public CrudRepository getRepository() {
		return repository;
	}

	public void setRepository(CrudRepository repository) {
		this.repository = repository;
	}

	public String getSourceQns() {
		return sourceQns;
	}

	public void setSourceQns(String sourceQns) {
		this.sourceQns = sourceQns;
	}

	public Map<String, String> getSource2rdsMap() {
		return source2rdsMap;
	}

	public void setSource2rdsMap(Map<String, String> source2rdsMap) {
		this.source2rdsMap = source2rdsMap;
	}

	class StockRDSSubListener extends RDSSubMessageListener<SourceStockBO_PRO.SourceStockBO>{

		public StockRDSSubListener(Logger log) {
			super(log);
			// TODO Auto-generated constructor stub
		}

		@Override
		protected void doRdsTask(SourceStockBO_OTW bo) {
			// TODO Auto-generated method stub
			
		}
		
	}
	
	
}
