package com.purefun.fstp.core.server.ignite;

import java.util.List;

import org.apache.commons.logging.impl.Log4JLogger;
import org.apache.ignite.Ignite;
import org.apache.ignite.IgniteCache;
import org.apache.ignite.IgniteLogger;
import org.apache.ignite.Ignition;
import org.apache.ignite.cache.affinity.AffinityKey;
import org.apache.ignite.cache.query.QueryCursor;
import org.apache.ignite.cache.query.SqlFieldsQuery;
import org.apache.ignite.cache.query.SqlQuery;
import org.apache.ignite.configuration.IgniteConfiguration;
import org.apache.ignite.logger.slf4j.Slf4jLogger;
import org.springframework.data.repository.CrudRepository;

import com.purefun.fstp.ace.rds.server.RDSCommon;
import com.purefun.fstp.core.bo.RDSStockBO;
import com.purefun.fstp.core.logging.PLogger;
import com.purefun.fstp.core.model.Person;
import com.purefun.fstp.core.model.repo.PersonRepository;
import com.purefun.fstp.core.server.PService;

public class IgniteService extends PService{
	
	
	public IgniteService(boolean isServer) {	
		super(isServer);
		// TODO Auto-generated constructor stub
		log = PLogger.getLogger(IgniteService.class);
	}
	
	public void init() {
		super.init();		
		
		log.info("{} init successful",serverName);			
	}
	
	public void start() {
		super.start();	
		IgniteConfiguration cfg = new IgniteConfiguration();
		IgniteLogger logger = new Slf4jLogger(log);
		cfg.setGridLogger(logger);
		Ignite ignite = Ignition.start(cfg);
		
//		Ignite ignite = Ignition.start("config\\ignite.xml");
		IgniteCache<String,RDSStockBO> cache = ignite.getOrCreateCache("StockRDSCache");	
		SqlQuery sql = new SqlQuery(RDSStockBO.class, "");
//		Iterable<?> res = cache.query(sql).getAll();
		log.info("{}",cache.get("603993.SH").secu_name_cn);
//		Iterable<?> res = cache.query(new SqlQuery<AffinityKey<Long>, RDSStockBO>(RDSStockBO.class, "").
//                setArgs(0, 1000)).getAll();
//		
//		for (Object next : res) {
//			log.info("{}",next);
//		}
		QueryCursor res = cache.query(sql);
		log.info("{} init successful",serverName);	
				
	}
}
