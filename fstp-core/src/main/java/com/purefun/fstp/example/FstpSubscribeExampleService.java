package com.purefun.fstp.example;

import javax.jms.BytesMessage;
import javax.jms.JMSException;

import org.slf4j.Logger;

import com.google.protobuf.InvalidProtocolBufferException;
import com.purefun.fstp.ace.rds.loader.ExchRDSLoader;
import com.purefun.fstp.core.bo.otw.SourceStockBO_OTW;
import com.purefun.fstp.core.logging.PLogger;
import com.purefun.fstp.core.rpc.msglistener.SubMessageListener;
import com.purefun.fstp.core.rpc.pub.Publisher;
import com.purefun.fstp.core.rpc.sub.Subscriber;
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
		/*	1.create sub	*/
		Subscriber sub =  rpcfactory.createSubscriber();
		
		/*	2.subscribe topic  */
		sub.subscribe("fstp.core.example.publish", new ExampleSubListener(log));

	}
	
	public class ExampleSubListener extends SubMessageListener{

		public ExampleSubListener(Logger log) {
			super(log);
			// TODO Auto-generated constructor stub
			
		}

		@Override
		protected void doSubscribeTask(BytesMessage objMsg) {
			// TODO Auto-generated method stub
			try {
				byte[] byteArray = new byte[1024];
	        	int len = -1;
	        	len = objMsg.readBytes(byteArray);
	        	if(len == -1){ 
	        		return;
	        	}
	        	ExampleBO_OTW receiveBO = new ExampleBO_OTW(RPCTool.subBytes(byteArray, 0, len));
	        	
				log.info("receive：{}",receiveBO.toString());
			} catch (JMSException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InvalidProtocolBufferException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	
}
