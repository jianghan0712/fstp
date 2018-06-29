package com.purefun.fstp.core.ipc.qns;

import java.util.ArrayList;
import java.util.List;

import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageConsumer;
import javax.jms.Session;

import org.slf4j.Logger;

import com.purefun.fstp.core.bo.otw.QNSRequestBO_OTW;
import com.purefun.fstp.core.cache.ICommonCache;
import com.purefun.fstp.core.ipc.msglistener.QnsMessageListener;

public class QNSubscriber{
	String[] topics = null;
	String qns = null;
	String serverName = null;
	Logger log = null;
	Session session = null;
	ICommonCache cache = null;
	
	QNSClient qnsclient = null;
	
	public QNSubscriber(Logger log,Session session,ICommonCache cache) {
		this.log = log;
		this.session = session;
		this.cache = cache;
	}

	public void init(String des,String serverName) {
		qns = des;
		this.serverName = serverName;
		qnsclient = new QNSClient(log, session, "QNSqueryTopic", serverName);
	}
	
	public void QNS(QnsMessageListener listener) {
		QNSRequestBO_OTW bo = new QNSRequestBO_OTW();
		bo.setRequest(qns);
		topics = qnsclient.publish(bo);

		listener.queryList = query();//	1.query
		listener.onQuery();			//	2.deal with history msg
		subscribe(listener);		//	3.subscribe new msg
	}	
	
	private List[] query() {
		// TODO Auto-generated method stub
		List[] result = new ArrayList[topics.length];
		int i = 0;
		for(String topic:topics) {
			List bocache = (List)cache.get(topic, null);
			if(bocache != null && !bocache.isEmpty()) {
				result[i++] = bocache;
			}			
		}
		return result;
	}
	
	private void subscribe(QnsMessageListener msglisteneer) {
		try {
			Destination destination = session.createTopic(qns);
			MessageConsumer messageConsumer = session.createConsumer(destination);
			messageConsumer.setMessageListener(msglisteneer);	
			log.info("QNS topic:{}",qns);
		} catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}         
	}	
}
