package com.purefun.fstp.ace.rds.server;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;

import org.slf4j.Logger;

import com.purefun.fstp.core.bo.TestBO;
import com.purefun.fstp.core.cache.ObjectTransCoder;
import com.purefun.fstp.core.rpc.msglistener.QnsMessageListener;

public class MyMessageListener extends QnsMessageListener {

	public MyMessageListener(Logger log) {
		super(log);
	}

	@Override
	protected void doSubscribe(ObjectMessage objMsg) {
		// TODO Auto-generated method stub
		TestBO obj;
		try {
			obj = (TestBO)objMsg.getObject();
			log.info("receive：{}",obj.getMsg());
		} catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	protected void doQueryTask(List<byte[]> eachList) {
		// TODO Auto-generated method stub
		for(byte[] each:eachList) {
			TestBO bo = (TestBO)ObjectTransCoder.deserialize(each);
			log.info(bo.getDestination());
		}
	}
}
