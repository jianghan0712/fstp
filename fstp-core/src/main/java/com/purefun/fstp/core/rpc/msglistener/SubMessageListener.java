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

public abstract class SubMessageListener implements MessageListener {
	public Logger log = null;
	protected List resultList = null;
	
	public SubMessageListener(Logger log) {
		this.log = log;
	}
	
	@Override
	public void onMessage(Message message) {
		// TODO Auto-generated method stub		
		ObjectMessage objMsg = (ObjectMessage) message;

		doSubscribeTask(objMsg);
		
	}
	
	public List getResultList() {
		return resultList;
	}

	public void setResultList(List resultList) {
		this.resultList = resultList;
	}

	abstract protected void doSubscribeTask(ObjectMessage objMsg);
}
