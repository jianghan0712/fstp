package com.purefun.fstp.core.ipc.msglistener;

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
import com.purefun.fstp.core.tool.RPCTool;

public abstract class QnsMessageListener implements MessageListener {
	public Logger log = null;
	public List[] queryList = null;
	
	public QnsMessageListener(Logger log) {
		this.log = log;
	}
	
	@Override
	public void onMessage(Message message) {
		// TODO Auto-generated method stub		
		BytesMessage objMsg = (BytesMessage) message;
		try {
			byte[] byteArray = new byte[1024];
        	int len = -1;
        	len = objMsg.readBytes(byteArray);
        	if(len == -1){ 
        		return;
        	}
        	doSubscribe(RPCTool.subBytes(byteArray, 0, len));
		} catch (InvalidProtocolBufferException | JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
	
	public void onQuery() {
		for(List<byte[]> each:queryList) {
			if(each==null)
				continue;
			log.info("{}",each.size());
			doQueryTask(each);
		}
	}
	
	abstract protected void doSubscribe(byte[] objMsg) throws InvalidProtocolBufferException, JMSException;
	
	abstract protected void doQueryTask(List<byte[]> each);

}
