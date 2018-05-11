package com.purefun.fstp.core.rpc.sub;

import java.nio.ByteBuffer;

import javax.jms.BytesMessage;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;

import org.apache.qpid.amqp_1_0.jms.TextMessage;

import com.google.protobuf.InvalidProtocolBufferException;
import com.purefun.fstp.core.bo.otw.TestBO2_OTW;
import com.purefun.fstp.core.tool.RPCTool;
import com.purefun.fstp.example.bo.otw.ExampleBO_OTW;

public class PythonMessageListener implements MessageListener{

	@Override
	public void onMessage(Message message) {
		// TODO Auto-generated method stub
		org.apache.qpid.client.message.JMSBytesMessage msg = (org.apache.qpid.client.message.JMSBytesMessage)message;
		BytesMessage byteMsg = (BytesMessage)message;
		try {		
			byte[] byteArray = new byte[1024];
        	int len = -1;
        	len = byteMsg.readBytes(byteArray);
        	if(len == -1){ 
        		return;
        	}
        	TestBO2_OTW receiveBO = new TestBO2_OTW(RPCTool.subBytes(byteArray, 0, len));
        	System.out.println(receiveBO.toString());
//        	ExampleBO_OTW receiveBO = new ExampleBO_OTW();
        	
//			log.info("receive：{}",receiveBO.toString());
		} catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidProtocolBufferException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
