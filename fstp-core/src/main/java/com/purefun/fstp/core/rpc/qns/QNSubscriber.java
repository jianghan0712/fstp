package com.purefun.fstp.core.rpc.qns;

import java.util.ArrayList;
import java.util.List;

import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.Session;

import org.slf4j.Logger;

import com.purefun.fstp.core.bo.QNSRequestBO;
import com.purefun.fstp.core.rpc.msglistener.QnsMessageListener;

import redis.clients.jedis.Jedis;

public class QNSubscriber{
	String[] topics = null;
	String qns = null;
	String serverName = null;
	Logger log = null;
	Session session = null;
	Jedis cache = null;
	
	QNSClient qnsclient = null;
	
	public QNSubscriber(Logger log,Session session,Jedis cache) {
		this.log = log;
		this.session = session;
		this.cache = cache;	
		
	}
	
	public void setting(String des,String serverName) {
		qns = des;
		this.serverName = serverName;
		qnsclient = new QNSClient(log,session,cache,"QNSqueryTopic",serverName);
		
	}
	
	public void QNS(QnsMessageListener listener) {
		QNSRequestBO bo = new QNSRequestBO(qns);
		topics = qnsclient.publish(bo);
		
//		task = new QNSRequestTask(qns);
//		Runnable request = new ZMQRequester("5800", log ,serverName,task);	
//		new Thread(request).start();

		listener.queryList = query();//	1.query
		listener.onQuery();			//	2.deal with history msg
		subscribe(listener);		//	3.subscribe new msg
	}	
	
	private List[] query() {
		// TODO Auto-generated method stub
		List[] result = new ArrayList[topics.length];
		int i = 0;
		for(String topic:topics) {
			List<byte[]> bocache = cache.lrange(topic.getBytes(),0,-1);
			if(bocache == null || bocache.isEmpty()) {
				log.info("topic {} has no message in cache",topic);
			}else {
				result[i++] = bocache;
			}		
		}
		return result;
	}
	
	private void subscribe(MessageListener msglisteneer) {
		try {
			Destination destination = session.createTopic(qns);
			MessageConsumer messageConsumer = session.createConsumer(destination);
			messageConsumer.setMessageListener(msglisteneer);	
		} catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}         
	}	
}
