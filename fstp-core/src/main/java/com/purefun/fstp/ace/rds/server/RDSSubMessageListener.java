package com.purefun.fstp.ace.rds.server;

import java.lang.reflect.Method;
import java.util.ArrayList;

import javax.jms.BytesMessage;
import javax.jms.JMSException;
import javax.jms.ObjectMessage;

import org.slf4j.Logger;

import com.google.protobuf.InvalidProtocolBufferException;
import com.purefun.fstp.core.bo.SourceStockBO;
import com.purefun.fstp.core.bo.TestBO;
import com.purefun.fstp.core.bo.otw.SourceStockBO_OTW;
import com.purefun.fstp.core.ipc.msglistener.SubMessageListener;
import com.purefun.fstp.core.tool.RPCTool;

public abstract class RDSSubMessageListener<T extends com.google.protobuf.GeneratedMessageV3> extends SubMessageListener{
	
	public RDSSubMessageListener(Logger log) {
		super(log);
		// TODO Auto-generated constructor stub
		this.resultList = new ArrayList<T>();
	}
	
	@Override
	protected void doSubscribeTask(BytesMessage objMsg) {
		// TODO Auto-generated method stub
//		SourceStockBO_PRO.SourceStockBO receiveBO;
		try {
			byte[] byteArray = new byte[1024];
        	int len = -1;
        	len = objMsg.readBytes(byteArray);
        	if(len == -1){ 
        		return;
        	}
        	SourceStockBO_OTW receiveBO = new SourceStockBO_OTW(RPCTool.subBytes(byteArray, 0, len));
//        	log.info("receive：{}",receiveBO.getDestination());
//        	S receiveBO = null;
//			bo.
//			receiveBO = (T)objMsg.getObject();
			log.info("receive：{}",receiveBO.toString());
			doRdsTask(receiveBO);
			resultList.add(receiveBO);
		} catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidProtocolBufferException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	abstract protected void doRdsTask(SourceStockBO_OTW bo);
	

}
