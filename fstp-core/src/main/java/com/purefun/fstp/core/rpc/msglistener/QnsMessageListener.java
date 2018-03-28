package com.purefun.fstp.core.rpc.msglistener;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;

import org.slf4j.Logger;

public abstract class QnsMessageListener implements MessageListener {
	public Logger log = null;
	public List[] queryList = null;
	
	public QnsMessageListener(Logger log) {
		this.log = log;
	}
	
	@Override
	public void onMessage(Message message) {
		// TODO Auto-generated method stub		
		ObjectMessage objMsg = (ObjectMessage) message;

		doSubscribe(objMsg);
		
	}
	
	public void onQuery() {
		for(List<byte[]> each:queryList) {
			if(each==null)
				continue;
			doQueryTask(each);
		}
	}
	
	abstract protected void doSubscribe(ObjectMessage objMsg);
	
	abstract protected void doQueryTask(List<byte[]> each);

}
