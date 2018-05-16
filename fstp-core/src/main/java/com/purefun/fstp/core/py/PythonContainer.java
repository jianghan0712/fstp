package com.purefun.fstp.core.py;

import com.purefun.fstp.ace.rds.loader.ExchRDSLoader;
import com.purefun.fstp.core.bo.otw.TestBO2_OTW;
import com.purefun.fstp.core.ipc.pub.Publisher;
import com.purefun.fstp.core.ipc.sub.PythonMessageListener;
import com.purefun.fstp.core.ipc.sub.Subscriber;
import com.purefun.fstp.core.logging.PLogger;
import com.purefun.fstp.core.server.PService;

public class PythonContainer extends PService{
	public PythonContainer() {
		super();
		// TODO Auto-generated constructor stub
		log = PLogger.getLogger(PythonContainer.class);
	}
	
	public void init() {
		super.init();		
	}
	
	public void start() {
		super.start();
		Subscriber test = rpcfactory.createSubscriber();
		test.subscribe("python", new PythonMessageListener());
		
//		Publisher pub = rpcfactory.createPublisher();
//		TestBO2_OTW bo = new TestBO2_OTW();
//		bo.setMsg("from java");
//		pub.publish(bo,0);
	}
}
