package com.purefun.fstp.core.rpc;

import javax.jms.Session;
import org.slf4j.Logger;

import com.purefun.fstp.core.cache.FCache;
import com.purefun.fstp.core.rpc.pub.Publisher;
import com.purefun.fstp.core.rpc.qns.QNSubscriber;
import com.purefun.fstp.core.rpc.sub.Subscriber;

import redis.clients.jedis.Jedis;

public class RpcFactory {
	static Session session = null;
	static FCache fcache = null;
	static Logger log = null;
	
	public RpcFactory(Session session,FCache fcache,Logger log) {
		this.session = session;
		this.fcache = fcache;
		this.log = log;
	}
	
	public static Publisher createPublisher() {
		Publisher pub = new Publisher(log, session, fcache);		
		return pub;		
	}
	
	public static Subscriber createSubscriber() {
		Subscriber sub = new Subscriber(log, session, fcache);
		return sub;		
	}
	
	public static QNSubscriber createQNSubscriber() {
		QNSubscriber qns = new QNSubscriber(log, session, fcache);
		return qns;		
	}
}
