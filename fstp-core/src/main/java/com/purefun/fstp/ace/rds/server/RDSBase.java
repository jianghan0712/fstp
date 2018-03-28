package com.purefun.fstp.ace.rds.server;

import com.purefun.fstp.core.server.PService;

public abstract class RDSBase extends PService{

	public RDSBase(String serverName, boolean isServer, String zmqport) {
		super(isServer, zmqport);
		// TODO Auto-generated constructor stub
//		registTask = new RegisterMonitor();
	}
	
	public void init() {
		super.init();
		log.info("{} init successful",serverName);	
	}
	
	public void start() {
		super.start();
		
		//First load DB data
//		QNSubscriber publish = new QNSubscriber("pilot.core.rpc.*", serverName);
//		publish.QNS(new MyMessageListener(log));
		log.info("{} start successful",serverName);
		
	}
}
