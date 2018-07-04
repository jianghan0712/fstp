package com.purefun.fstp.example.qns;

import java.util.List;
import java.util.Map.Entry;

import javax.jms.BytesMessage;
import javax.jms.JMSException;

import org.slf4j.Logger;

import com.google.protobuf.InvalidProtocolBufferException;
import com.purefun.fstp.core.bo.TestBO;
import com.purefun.fstp.core.bo.commom.ICommom_OTW;
import com.purefun.fstp.core.bo.otw.BaseBO_OTW;
import com.purefun.fstp.core.bo.otw.SourceStockBO_OTW;
import com.purefun.fstp.core.bo.otw.TestBO_OTW;
import com.purefun.fstp.core.ipc.msglistener.QnsMessageListener;
import com.purefun.fstp.core.ipc.msglistener.SubMessageListener;
import com.purefun.fstp.core.ipc.pub.Publisher;
import com.purefun.fstp.core.ipc.qns.QNSubscriber;
import com.purefun.fstp.core.ipc.sub.Subscriber;
import com.purefun.fstp.core.logging.PLogger;
import com.purefun.fstp.core.server.PService;
import com.purefun.fstp.core.tool.RPCTool;
import com.purefun.fstp.example.bo.ExampleBO;
import com.purefun.fstp.example.bo.ExampleQnsBO;
import com.purefun.fstp.example.bo.otw.ExampleBO_OTW;
import com.purefun.fstp.example.bo.otw.ExampleQnsBO_OTW;

public class FstpQNSExampleService extends PService{

	public FstpQNSExampleService(boolean isServer) {
		super(isServer);
		// TODO Auto-generated constructor stub
		log = PLogger.getLogger(FstpQNSExampleService.class);
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
		QNSubscriber sub =  rpcfactory.createQNSubscriber();
		sub.init("fstp.example.bo.*", serverName);
		sub.QNS(new ExampleQNSListener(log));
	}
	
	public class ExampleQNSListener extends QnsMessageListener{

		public ExampleQNSListener(Logger log) {
			super(log);
			// TODO Auto-generated constructor stub
		}

		@Override
		protected void doSubscribe(byte[] objMsg) throws InvalidProtocolBufferException, JMSException {
			// TODO Auto-generated method stub
			try {
				ICommom_OTW r = new BaseBO_OTW(objMsg);
				if(r.getDestination().equalsIgnoreCase("fstp.example.bo.test2")) {
					log.info("this is a ExampleBO");
					ExampleBO_OTW e = new ExampleBO_OTW(objMsg);
					log.info(e.getDestination());
				}else if(r.getDestination().equalsIgnoreCase("fstp.example.bo.test")) {
					ExampleQnsBO_OTW e = new ExampleQnsBO_OTW(objMsg);
					log.info("this is a ExampleQnsBO");
					log.info(e.getCompany());
				}
					
			} catch (InvalidProtocolBufferException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
		}

		@Override
		protected void doQueryTask(Entry<String, List> each) {
			// TODO Auto-generated method stub
			String name = each.getKey();
			if(name.equalsIgnoreCase("com.purefun.fstp.example.bo.ExampleBO")) {
				for(Object e: each.getValue()) {
					log.info(((ExampleBO)e).name);
				}
			}else if(name.equalsIgnoreCase("com.purefun.fstp.example.bo.ExampleQnsBO")) {
				for(Object e: each.getValue()) {
					log.info(((ExampleQnsBO)e).company);
				}
			}
				
		}		
	}
	
}
