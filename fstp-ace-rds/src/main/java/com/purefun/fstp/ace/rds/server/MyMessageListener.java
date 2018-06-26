package com.purefun.fstp.ace.rds.server;

import java.util.List;

import javax.jms.JMSException;
import org.slf4j.Logger;

import com.google.protobuf.InvalidProtocolBufferException;
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
	protected void doQueryTask(List<byte[]> eachList) {
		// TODO Auto-generated method stub
		for(byte[] each:eachList) {
			try {
				com.purefun.fstp.core.bo.pro.TestBO_PRO.TestBO receiveBO = TestBO_PRO.TestBO.parseFrom(each);
				log.info(receiveBO.getDestination());
			} catch (InvalidProtocolBufferException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}
}
