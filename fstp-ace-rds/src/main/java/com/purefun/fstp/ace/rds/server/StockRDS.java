package com.purefun.fstp.ace.rds.server;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.ignite.configuration.CacheConfiguration;
import org.slf4j.Logger;
import com.google.protobuf.InvalidProtocolBufferException;
import com.purefun.fstp.core.bo.RDSStockBO;
import com.purefun.fstp.core.bo.otw.RDSStockBO_OTW;
import com.purefun.fstp.core.bo.otw.SourceStockBO_OTW;
import com.purefun.fstp.core.cache.ignitecache.ICache;
import com.purefun.fstp.core.cache.rediscache.RCache;
import com.purefun.fstp.core.ipc.RpcFactory;
import com.purefun.fstp.core.logging.PLogger;

public class StockRDS extends RDSBase{
	String sourceQns = null;
	Map<String,String> source2rdsMap = null;
	HashMap<String,RDSStockBO_OTW> rdsBoList = new HashMap<String,RDSStockBO_OTW>();
	
	public StockRDS(boolean isServer) {
		super(isServer);
		log = PLogger.getLogger(StockRDS.class);
	}
	
	public void init() {
		super.init();
		//receive sourceBO
		sub = RpcFactory.createSubscriber();
		listener = new StockRDSSubListener(log);
		sub.subscribe(sourceQns,  listener);
		
		//publish rdsBO
		pub = RpcFactory.createPublisher();
	
		CacheConfiguration<String, RDSStockBO> selfcache = beanFactory.getBean(CacheConfiguration.class);
		cache = ignite.getOrCreateCache(selfcache);
	}
	
	public void start() {
		super.start();	
		
		log.info("{} start successful",serverName);	
	}

	public String getSourceQns() {
		return sourceQns;
	}

	public void setSourceQns(String sourceQns) {
		this.sourceQns = sourceQns;
	}

	public Map<String, String> getSource2rdsMap() {
		return source2rdsMap;
	}

	public void setSource2rdsMap(Map<String, String> source2rdsMap) {
		this.source2rdsMap = source2rdsMap;
	}

//	@Override
//	protected void loadDBdata2CacheImp(List list) {
//		// TODO Auto-generated method stub
//		int count = 0;
//    	for(RDSStockBO each : (List<RDSStockBO>)list) {
//    		RDSStockBO_OTW bo = new RDSStockBO_OTW(each);
//    		fcache.setList(each.getClass().getName(), bo.getBuilder().build().toByteArray());
//    		rdsBoList.put(bo.getProduct_id(), bo);
//    		log.info("load bo :product_id={}",bo.getProduct_id());
//    		count++;
//    	}
//    	log.info("load data from DB to cache successful!!");
//    	log.info("Count:{}",count);
//	}
	@Override
	protected void loadDBdata2CacheImp(List list) {
		// TODO Auto-generated method stub
		int count = 0;
    	for(RDSStockBO each : (List<RDSStockBO>)list) {
    		if(ICache.class.isInstance(Icache)) {
    			Icache.put("com.purefun.fstp.core.bo.RDSStockBO", each.product_id, each);
    		}else if(RCache.class.isInstance(Icache)) {
    			RDSStockBO_OTW bo = new RDSStockBO_OTW(each);
    			Icache.put(null, each.getClass().getName(), bo.getBuilder().build().toByteArray());
    		}
    			
    		log.info("load bo :product_id={}",each.product_id);
    		count++;
    	}
    	log.info("load data from DB to cache successful!!");
    	log.info("Count:{}",count);

		ICache cache = (ICache)Icache;
		List<RDSStockBO> t = cache.query("product_id LIKE '900934.SH'", RDSStockBO.class);
		for(RDSStockBO e:t) {
			log.info("product_id = {}, sec_name_cn = {} ",e.product_id, e.secu_name_cn);
		}
		log.info("{}",t.size());
	}
	
	class StockRDSSubListener extends RDSSubMessageListener{

		public StockRDSSubListener(Logger log) {
			super(log);
			// TODO Auto-generated constructor stub
		}

		@Override
		protected void doRdsTask(byte[] bobyte) {
			// TODO Auto-generated method stub
			try {
				SourceStockBO_OTW sourcebo = new SourceStockBO_OTW(bobyte);
				RDSStockBO_OTW rdsbo = null;
				
				rdsbo = rdsBoList.get(sourcebo.getSecu_id());
				if(rdsbo == null) {//insert directly
					log.info("There is no product_id={} in cache, we will insert it.",sourcebo.getBo().secu_id);
					rdsbo = new RDSStockBO_OTW();
					insertOrUpdateCacheAndDB(null, sourcebo, false, rdsbo);
					rdsBoList.put(rdsbo.getProduct_id(), rdsbo);
				}else {
					String[] ret = ifDupBO(rdsbo, sourcebo);
					if(ret[0]==null) {
						log.info("This is a dup BO:product_id={}, we will ignore it!",sourcebo.getSecu_id());
					}else {
						log.info("Need update,product_id={},fild={},source value={},rds value={}",rdsbo.getProduct_id(), ret[0],ret[1],ret[2]);					
						RDSStockBO_OTW rdsboNew = new RDSStockBO_OTW();
						insertOrUpdateCacheAndDB(rdsbo, sourcebo, true, rdsboNew);
						rdsBoList.replace(rdsbo.getProduct_id(), rdsbo);
					}					
				}				
			} catch (InvalidProtocolBufferException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}						
		}

		private void insertOrUpdateCacheAndDB(RDSStockBO_OTW rdsboOld, SourceStockBO_OTW sourcebo,boolean isUpdate, RDSStockBO_OTW rdsboNew) {
			// TODO Auto-generated method stub
			for(Map.Entry<String, String> eachMap:source2rdsMap.entrySet()) {
				setMethod(rdsboNew, sourcebo, eachMap);
			}
			if(isUpdate) {				
				repo.deleteById(rdsboOld.getUuid());
				repo.save(rdsboNew.getBo());
				log.info("update successful,product_id={}",rdsboNew.getProduct_id());
			}else {
				repo.save(rdsboNew.getBo());
				log.info("insert successful,product_id={}",rdsboNew.getProduct_id());
			}		
		}

		private String[] ifDupBO(RDSStockBO_OTW rdsbo, SourceStockBO_OTW sourcebo) {
			// TODO Auto-generated method stub
			String[] ret = {null,null,null};
			for(Map.Entry<String, String> eachMap:source2rdsMap.entrySet()) {				
				String getSouceMethodName = new StringBuffer("get").append(eachMap.getValue().substring(0, 1).toUpperCase()).append(eachMap.getValue().substring(1)).toString();
				String getRdsMethodName = new StringBuffer("get").append(eachMap.getKey().substring(0, 1).toUpperCase()).append(eachMap.getKey().substring(1)).toString();
				try {
					Method getSourceMethod = sourcebo.getClass().getMethod(getSouceMethodName);
					Class returnType =  getSourceMethod.getReturnType();
					Method getRdsMethod = rdsbo.getClass().getMethod(getRdsMethodName);

					if(!getSourceMethod.invoke(sourcebo).equals(getRdsMethod.invoke(rdsbo))) {
						ret[0] = eachMap.getKey();
						ret[1] = String.valueOf(getSourceMethod.invoke(sourcebo));
						ret[2] = String.valueOf(getRdsMethod.invoke(rdsbo));						
						break;
					}										
				} catch (NoSuchMethodException | SecurityException |IllegalAccessException|IllegalArgumentException|InvocationTargetException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			return ret;		
		}
		
		public void setMethod(RDSStockBO_OTW rdsbo, SourceStockBO_OTW sourcebo, Map.Entry<String, String>eachMap) {
			String getMethodName = new StringBuffer("get").append(eachMap.getValue().substring(0, 1).toUpperCase()).append(eachMap.getValue().substring(1)).toString();
			String setMethodName = new StringBuffer("set").append(eachMap.getKey().substring(0, 1).toUpperCase()).append(eachMap.getKey().substring(1)).toString();

			try {
				Method getSourceMethod = sourcebo.getClass().getMethod(getMethodName);
				Class returnType =  getSourceMethod.getReturnType();
				Method setRDSMethod = rdsbo.getClass().getMethod(setMethodName, returnType);
				setRDSMethod.invoke(rdsbo, getSourceMethod.invoke(sourcebo));
			} catch (NoSuchMethodException | SecurityException |IllegalAccessException|IllegalArgumentException|InvocationTargetException e) {
				e.printStackTrace();
			}		

		}
	}
	
}
