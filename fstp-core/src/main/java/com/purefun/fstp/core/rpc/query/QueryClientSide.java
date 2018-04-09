package com.purefun.fstp.core.rpc.query;

import java.util.List;

import javax.jms.Session;

import org.slf4j.Logger;

import com.purefun.fstp.core.bo.TestBO;
import com.purefun.fstp.core.cache.FCache;
import com.purefun.fstp.core.rpc.tunnel.ClientSide;

public class QueryClientSide extends ClientSide<List<TestBO>>{

	public QueryClientSide(Logger log, Session session, FCache fcache, String topic) {
		super(log, session, fcache, topic);
		// TODO Auto-generated constructor stub
	}	
}
