package com.purefun.fstp.core.ipc.qns;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageConsumer;
import javax.jms.Session;

import org.apache.ignite.Ignite;
import org.apache.ignite.IgniteCache;
import org.apache.ignite.lang.IgniteBiTuple;
import org.slf4j.Logger;

import com.purefun.fstp.core.bo.QNSRequestBO;
import com.purefun.fstp.core.bo.otw.QNSRequestBO_OTW;
import com.purefun.fstp.core.bo.otw.QueryRequestBO_OTW;
import com.purefun.fstp.core.cache.ICommonCache;
import com.purefun.fstp.core.cache.ignitecache.ICache;
import com.purefun.fstp.core.cache.rediscache.RCache;
import com.purefun.fstp.core.ipc.msglistener.QnsMessageListener;
import com.purefun.fstp.core.ipc.msglistener.QueryMessageListener;
import com.purefun.fstp.core.ipc.query.Query;
import com.purefun.fstp.core.tool.BoFactory;

public class QNSubscriber{
	String[] topics = null;
	String qns = null;
	String serverName = null;
	Logger log = null;
	Session session = null;
	ICommonCache cache = null;
	List bocache = null;
	
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
		QNSRequestBO_OTW bo = (QNSRequestBO_OTW) BoFactory.createBo(QNSRequestBO.class);
		bo.setRequest(qns);
		topics = qnsclient.publish(bo);

		listener.queryMap = query();//	1.query
		listener.onQuery();			//	2.deal with history msg
		subscribe(listener);		//	3.subscribe new msg
	}	
	
	private Map<String, List> query() {
		// TODO Auto-generated method stub
//		List[] result = new ArrayList[topics.length];
		
		Map<String, List> result = new HashMap<String, List>();
		for(String topic:topics) {// boName|topic
			String[] e = topic.split("\\|");
			if(e.length==2) {
				List bocache = new ArrayList<>();
				if(RCache.class.isInstance(cache))
					bocache = (List)cache.get(e[0], null);
				else if(ICache.class.isInstance(cache)) {
					ICache c = (ICache)cache;
					IgniteCache t = c.ignite.cache(e[0]);
					
					if(t != null) {
						for(Object k:t) {
							bocache.add(((IgniteBiTuple)k).get2());
						}
					}
					
				}				
				result.put(e[0], bocache);
			}					
		}
		return result;
	}
	
	private void subscribe(QnsMessageListener msglisteneer) {
		try {
			for(String topic:topics) {// boName|topic
				String[] e = topic.split("\\|");
				if(e.length==2) {
					Destination destination = session.createTopic(e[1]);
					MessageConsumer messageConsumer = session.createConsumer(destination);
					messageConsumer.setMessageListener(msglisteneer);	
					log.info("QNS topic:{}",e[1]);
				}
			}
			
		} catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}         
	}	
	
}
