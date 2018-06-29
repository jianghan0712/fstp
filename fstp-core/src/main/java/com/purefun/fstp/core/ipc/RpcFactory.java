package com.purefun.fstp.core.ipc;

import javax.jms.Session;

import org.slf4j.Logger;

import com.purefun.fstp.core.cache.ICommonCache;
import com.purefun.fstp.core.cache.rediscache.RCache;
import com.purefun.fstp.core.ipc.pub.Publisher;
import com.purefun.fstp.core.ipc.qns.QNSubscriber;
import com.purefun.fstp.core.ipc.query.Query;
import com.purefun.fstp.core.ipc.sub.Subscriber;

public class RpcFactory {
	static Session session = null;
	static Logger log = null;
	static ICommonCache cache = null;
	
	public RpcFactory(Session session,ICommonCache cache,Logger log) {
		this.session = session;
		this.cache = cache;
		this.log = log;
	}
	
	
	public static Publisher createPublisher() {
		Publisher pub = new Publisher(log, session, cache);		
		return pub;		
	}
	
	public static Subscriber createSubscriber() {
		Subscriber sub = new Subscriber(log, session, cache);
		return sub;		
	}
	
	public static QNSubscriber createQNSubscriber() {
		QNSubscriber qns = new QNSubscriber(log, session, cache);
		return qns;		
	}
	
	public static Query createQuery() {
		Query query = new Query(log, session);
		return query;		
	}
}
