package com.purefun.fstp.example;

import com.purefun.fstp.ace.rds.loader.ExchRDSLoader;
import com.purefun.fstp.core.logging.PLogger;
import com.purefun.fstp.core.rpc.pub.Publisher;
import com.purefun.fstp.core.server.PService;
import com.purefun.fstp.example.bo.otw.ExampleBO_OTW;

public class FstpPublishExampleService extends PService{

	public FstpPublishExampleService(boolean isServer) {
		super(isServer);
		// TODO Auto-generated constructor stub
		log = PLogger.getLogger(FstpPublishExampleService.class);
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
		Publisher pub = rpcfactory.createPublisher();
		for(int i = 0;i<3;i++) {
			ExampleBO_OTW bo = new ExampleBO_OTW();
			bo.setName("Hans");
			bo.setAge(i);
			pub.publish(bo, 1);
		}
	}
	
}
