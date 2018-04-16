package com.purefun.fstp.core.rpc.query;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.jms.Session;
import org.slf4j.Logger;

import com.purefun.fstp.core.bo.TestBO;
import com.purefun.fstp.core.bo.otw.QueryRequestBO_OTW;
import com.purefun.fstp.core.cache.FCache;
import com.purefun.fstp.core.cache.ObjectTransCoder;
import com.purefun.fstp.core.rpc.tunnel.ServerSide;

public class QueryServerSide extends ServerSide<QueryRequestBO_OTW, List<TestBO>>{ 
	
	public QueryServerSide(Logger log,Session session,FCache fcache,String servceName,String topic,Map<String,String> desMap) {
		super(log, session, fcache, servceName, topic, desMap);
	}

	@Override
	public List<TestBO> respond(QueryRequestBO_OTW reveiveMsg) {
		// TODO Auto-generated method stub		
		String queryServer = reveiveMsg.getRespondServiceName();	
		String queryClient = reveiveMsg.getRequestServiceName();
		String name = queryClient.substring(0,queryClient.indexOf("_"));
		String boDes = reveiveMsg.getQueryBoDestination();
		//如果发起请求的是同一个service，忽略该请求
		if(servceName.equalsIgnoreCase(name)) {
			return null;
		}
			
		log.info("receive QUERY, ServerName:{}",reveiveMsg.getRequestServiceName());		
		List<TestBO> ret = new ArrayList<TestBO>();
		
		if(!servceName.equalsIgnoreCase(queryServer)) {
			log.info("Ignore this QUERY from {}",reveiveMsg.getRequestServiceName());
		}else {
			if(desMap == null || desMap.size() == 0) {
				return ret;
			}
			for(Map.Entry<String, String> eachMap: desMap.entrySet()) {
				if(eachMap.getKey().equalsIgnoreCase(boDes)) {
					List<byte[]> bocache = fcache.getList(eachMap.getValue());
					if(bocache != null && bocache.size() != 0) {
						for(byte[] each:bocache) {
							TestBO bo = (TestBO)ObjectTransCoder.deserialize(each);
							ret.add(bo);
						}
						log.info("publish to Service {} {} messages ",reveiveMsg.getRequestServiceName(),ret.size());
					}
				}
			}
		}					
		return ret;
	}		
}
