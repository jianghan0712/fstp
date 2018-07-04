package com.purefun.fstp.core.ipc.msglistener;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

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
	public Map<String, List> queryMap = null;
	
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
		for(Map.Entry<String, List> e:queryMap.entrySet()) {//boName : List<bo>
			if(e.getValue() == null)
				continue;
			doQueryTask(e);
		}
	}
	
	abstract protected void doSubscribe(byte[] objMsg) throws InvalidProtocolBufferException, JMSException;
	
	abstract protected void doQueryTask(Map.Entry<String, List> each);

}
