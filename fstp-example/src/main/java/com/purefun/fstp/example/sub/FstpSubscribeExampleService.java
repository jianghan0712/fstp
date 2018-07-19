package com.purefun.fstp.example.sub;

import javax.jms.BytesMessage;
import javax.jms.JMSException;

import org.slf4j.Logger;

import com.google.protobuf.InvalidProtocolBufferException;
import com.purefun.fstp.core.bo.otw.SourceStockBO_OTW;
import com.purefun.fstp.core.bo.otw.TestBO_OTW;
import com.purefun.fstp.core.ipc.msglistener.SubMessageListener;
import com.purefun.fstp.core.ipc.pub.Publisher;
import com.purefun.fstp.core.ipc.sub.Subscriber;
import com.purefun.fstp.core.logging.PLogger;
import com.purefun.fstp.core.server.PService;
import com.purefun.fstp.core.tool.RPCTool;
import com.purefun.fstp.example.bo.otw.ExampleBO_OTW;

public class FstpSubscribeExampleService extends PService{

	public FstpSubscribeExampleService(boolean isServer) {
		super(isServer);
		// TODO Auto-generated constructor stub
		log = PLogger.getLogger(FstpSubscribeExampleService.class);
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
		Subscriber sub =  rpcfactory.createSubscriber();
		sub.subscribe("fstp.core.rpc.testone", new ExampleSubListener(log));
	}
	
	public class ExampleSubListener extends SubMessageListener{

		public ExampleSubListener(Logger log) {
			super(log);
			// TODO Auto-generated constructor stub		
		}

		@Override
		protected void doSubscribeTask(byte[] objMsg) {
			// TODO Auto-generated method stub
			try {
//				ExampleBO_OTW receiveBO = new ExampleBO_OTW(objMsg);
//				log.info("name = {}, age = {} ",receiveBO.getName(), receiveBO.getAge());
				TestBO_OTW receiveBO = new TestBO_OTW(objMsg);
				log.info("servicename = {}, msg = {} ",receiveBO.getServername(), receiveBO.getMsg());
			} catch (InvalidProtocolBufferException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}			
		}		
	}
	
}
