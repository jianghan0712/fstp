package com.purefun.fstp.ace.rds.server;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import org.slf4j.Logger;

import com.purefun.fstp.core.ipc.msglistener.SubMessageListener;

public abstract class RDSSubMessageListener extends SubMessageListener{
	public ScheduledExecutorService rdsThread = Executors.newScheduledThreadPool(5);
	
	public RDSSubMessageListener(Logger log) {
		super(log);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	protected void doSubscribeTask(byte[] objMsg) {
		// TODO Auto-generated method stub
		rdsThread.execute(new RDSThead(objMsg));			
	}
	
	abstract protected void doRdsTask(byte[] bo);
	
	class RDSThead implements Runnable{
		byte[] objMsg = null;
		RDSThead(byte[] objMsg){
			this.objMsg = objMsg;
		}
		@Override
		public void run() {
			// TODO Auto-generated method stub
			log.info("recive a new SourceBO.");
			doRdsTask(objMsg);
		}		
	}	
}
