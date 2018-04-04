package com.purefun.fstp.ace.rds.server;

import org.slf4j.Logger;
import org.springframework.data.repository.CrudRepository;
import com.purefun.fstp.core.bo.RDSStockBO;
import com.purefun.fstp.core.bo.SourceStockBO;
import com.purefun.fstp.core.logging.PLogger;
import com.purefun.fstp.core.rpc.RpcFactory;
import com.purefun.fstp.core.rpc.pub.Publisher;
import com.purefun.fstp.core.rpc.sub.Subscriber;

public class StockRDS extends RDSBase{
	CrudRepository repository = null;
	String sourceQns = null;
	
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
		log.info("{} start successful",serverName);
			
		RDSStockBO test = new RDSStockBO();
    	test.setBond_par_value(100.00);
		save2DB(test);
//		CrudRepository repo =  beanFactory.getBean(RDSStockBORepository.class);
//    	

    	
//		CrudRepository repo = beanFactory.getBean(RDSStockBORepository.class);
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
	
	class StockRDSSubListener extends RDSSubMessageListener<SourceStockBO>{

		public StockRDSSubListener(Logger log) {
			super(log);
			// TODO Auto-generated constructor stub
		}

		@Override
		protected void doRdsTask(SourceStockBO bo) {
			// TODO Auto-generated method stub
			
		}
		
	}
	
	
}
