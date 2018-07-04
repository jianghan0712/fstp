package com.purefun.fstp.example.query;

import java.util.List;

import javax.jms.BytesMessage;
import javax.jms.JMSException;

import org.apache.ignite.cache.affinity.AffinityKey;
import org.apache.ignite.cache.query.QueryCursor;
import org.apache.ignite.cache.query.SqlQuery;
import org.apache.ignite.internal.processors.cache.CacheEntryImpl;
import org.slf4j.Logger;

import com.google.protobuf.InvalidProtocolBufferException;
import com.purefun.fstp.core.bo.QueryRequestBO;
import com.purefun.fstp.core.bo.RDSStockBO;
import com.purefun.fstp.core.bo.otw.QueryRequestBO_OTW;
import com.purefun.fstp.core.bo.otw.RDSStockBO_OTW;
import com.purefun.fstp.core.bo.otw.SourceStockBO_OTW;
import com.purefun.fstp.core.bo.otw.TestBO_OTW;
import com.purefun.fstp.core.cache.ignitecache.ICache;
import com.purefun.fstp.core.ipc.msglistener.QueryMessageListener;
import com.purefun.fstp.core.ipc.msglistener.SubMessageListener;
import com.purefun.fstp.core.ipc.pub.Publisher;
import com.purefun.fstp.core.ipc.query.Query;
import com.purefun.fstp.core.ipc.sub.Subscriber;
import com.purefun.fstp.core.logging.PLogger;
import com.purefun.fstp.core.server.PService;
import com.purefun.fstp.core.tool.BoFactory;
import com.purefun.fstp.core.tool.RPCTool;
import com.purefun.fstp.example.bo.ExampleBO;
import com.purefun.fstp.example.bo.otw.ExampleBO_OTW;

public class FstpQueryExampleService extends PService{

	public FstpQueryExampleService(boolean isServer) {
		super(isServer);
		// TODO Auto-generated constructor stub
		log = PLogger.getLogger(FstpQueryExampleService.class);
	}
	
	public void init() {
		super.init();		
	}
	
	public void start() {
		super.start();
		doservice();		
	}

	private void doservice() {
		// TODO Auto-generated method stub
//		Query query =  rpcfactory.createQuery();//创建query
//		QueryRequestBO_OTW bo = (QueryRequestBO_OTW)BoFactory.createBo(QueryRequestBO.class);//新建QueryRequestBO
//		bo.setQuerytopic("fstp.example.bo.test2");//设置要查询的topic
//		query.query(bo, new TestExample(log));
		
		ICache cache = (ICache)Icache;
		List<ExampleBO> t = cache.query( "age = 2", ExampleBO.class);
		for(ExampleBO e:t) {
			log.info("name = {}, age = {} ",e.name, e.age);
		}

		
	}
	
	class TestExample extends QueryMessageListener{

		public TestExample(Logger log) {
			super(log);
			// TODO Auto-generated constructor stub
		}

		@Override
		protected void doTask(byte[] objMsg) {
			// TODO Auto-generated method stub
			try {
				ExampleBO_OTW receiveBO = new ExampleBO_OTW(objMsg);
				log.info("name = {}, age = {} ",receiveBO.getName(), receiveBO.getAge());
			} catch (InvalidProtocolBufferException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
	}
	
}
