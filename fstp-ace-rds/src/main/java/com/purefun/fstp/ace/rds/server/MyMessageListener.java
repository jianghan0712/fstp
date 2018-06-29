package com.purefun.fstp.ace.rds.server;

import java.util.List;

import javax.jms.JMSException;
import org.slf4j.Logger;

import com.google.protobuf.InvalidProtocolBufferException;
import com.purefun.fstp.core.bo.TestBO;
import com.purefun.fstp.core.bo.otw.TestBO_OTW;
import com.purefun.fstp.core.bo.pro.TestBO_PRO;
import com.purefun.fstp.core.ipc.msglistener.QnsMessageListener;

public class MyMessageListener extends QnsMessageListener {

	public MyMessageListener(Logger log) {
		super(log);
	}

	@Override
	protected void doSubscribe(byte[] bytesMsg) throws InvalidProtocolBufferException, JMSException {
		// TODO Auto-generated method stub
    	TestBO_OTW receiveBO = new TestBO_OTW(bytesMsg);
    	log.info("receive：{}",receiveBO.toString());
    	
	}

	@Override
	protected void doQueryTask(List eachList) {
		// TODO Auto-generated method stub	
		int len = eachList.size();
		for(int i = 0; i<len; i++) {
			try {		
				TestBO_OTW receiveBO;
				if(TestBO.class.isInstance(eachList.get(i))) {
					receiveBO = new TestBO_OTW((TestBO)eachList.get(i));
				}else {
					receiveBO = new TestBO_OTW((byte[])eachList.get(i));
				}					
				log.info(receiveBO.getDestination());
			} catch (InvalidProtocolBufferException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
