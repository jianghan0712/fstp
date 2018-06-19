package com.purefun.fstp.ace.rds.server;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

import javax.jms.BytesMessage;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;

import org.slf4j.Logger;

import com.google.protobuf.InvalidProtocolBufferException;
import com.purefun.fstp.core.bo.TestBO;
import com.purefun.fstp.core.bo.otw.TestBO_OTW;
import com.purefun.fstp.core.bo.pro.TestBO_PRO;
import com.purefun.fstp.core.cache.ObjectTransCoder;
import com.purefun.fstp.core.ipc.msglistener.QnsMessageListener;
import com.purefun.fstp.core.tool.RPCTool;

public class MyMessageListener extends QnsMessageListener {

	public MyMessageListener(Logger log) {
		super(log);
	}

	@Override
	protected void doSubscribe(BytesMessage bytesMsg) throws InvalidProtocolBufferException, JMSException {
		// TODO Auto-generated method stub
    	byte[] byteArray = new byte[1024];
    	int len = -1;
    	len = bytesMsg.readBytes(byteArray);
    	   	
    	if(len == -1){ 
    		return;
    	}
//    	probo = TestBO_PRO.TestBO.parseFrom();
    	TestBO_OTW receiveBO = new TestBO_OTW(RPCTool.subBytes(byteArray, 0, len));
    	log.info("receive：{}",receiveBO.toString());
    	
//		TestBO obj;
//		try {
//			obj = (TestBO)objMsg.getObject();
//			log.info("receive：{}",obj.getMsg());
//		} catch (JMSException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
	}

	@Override
	protected void doQueryTask(List<byte[]> eachList) {
		// TODO Auto-generated method stub
//		for(byte[] each:eachList) {
//			TestBO bo = (TestBO)ObjectTransCoder.deserialize(each);
//			log.info(bo.getDestination());
//		}
		for(byte[] each:eachList) {
//			TestBO_OTW bo = new TestBO_OTW();			
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
