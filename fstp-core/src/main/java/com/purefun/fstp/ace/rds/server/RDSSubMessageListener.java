package com.purefun.fstp.ace.rds.server;

import java.util.ArrayList;

import javax.jms.JMSException;
import javax.jms.ObjectMessage;

import org.slf4j.Logger;

import com.purefun.fstp.core.bo.SourceStockBO;
import com.purefun.fstp.core.bo.TestBO;
import com.purefun.fstp.core.rpc.msglistener.SubMessageListener;

public abstract class RDSSubMessageListener<T> extends SubMessageListener{
	
	
	public RDSSubMessageListener(Logger log) {
		super(log);
		// TODO Auto-generated constructor stub
		this.resultList = new ArrayList<T>();
	}
	
	@Override
	protected void doSubscribeTask(ObjectMessage objMsg) {
		// TODO Auto-generated method stub
		T obj;
		try {
			obj = (T)objMsg.getObject();
			log.info("receive：{}",obj.toString());
			doRdsTask(obj);
			resultList.add(obj);
		} catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	abstract protected void doRdsTask(T bo);
	

}
