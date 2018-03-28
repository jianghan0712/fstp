package com.purefun.fstp.core.rpc;

import javax.jms.Session;
import org.slf4j.Logger;

import com.purefun.fstp.core.rpc.pub.Publisher;
import com.purefun.fstp.core.rpc.qns.QNSubscriber;
import com.purefun.fstp.core.rpc.sub.Subscriber;

import redis.clients.jedis.Jedis;

public class RpcFactory {
	static Session session = null;
	static Jedis cache = null;
	static Logger log = null;
	
	public RpcFactory(Session session,Jedis cache,Logger log) {
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
}
