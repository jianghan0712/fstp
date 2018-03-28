package com.purefun.fstp.core.server;

import java.util.concurrent.TimeUnit;

import com.purefun.fstp.core.logging.PLogger;

public class TestService extends PService{		
	public TestService(String serverName,boolean isServer, String zmqport) {	
		super( isServer,zmqport);
		// TODO Auto-generated constructor stub
		commonInit();
	}
	
	public void commonInit() {
		log = PLogger.getLogger(TestService.class);
//		registTask = new RegisterMonitor();
	}
	
	public void init() {
		super.init();
		log.info("{} init successful",serverName);	
	}
	
	public void start() {
		super.start();
			

		
	}
	
	

}
