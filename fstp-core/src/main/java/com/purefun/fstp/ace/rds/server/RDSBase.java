package com.purefun.fstp.ace.rds.server;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.purefun.fstp.ace.rds.repo.RDSStockBORepository;
import com.purefun.fstp.ace.rds.repo.TestBORepository;
import com.purefun.fstp.core.bo.BaseBO;
import com.purefun.fstp.core.rpc.pub.Publisher;
import com.purefun.fstp.core.rpc.sub.Subscriber;
import com.purefun.fstp.core.server.PService;

public abstract class RDSBase extends PService{
	RDSCommon rdsinfo = null;
	CrudRepository repo = null;
	List sourceBOList = null;
	Publisher pub = null;
	Subscriber sub = null;
	RDSSubMessageListener listener = null;
	
	
	public RDSBase(boolean isServer) {
		super(isServer);
		// TODO Auto-generated constructor stub
//		registTask = new RegisterMonitor();
		
	}
	
	public void init() {
		super.init();
		log.info("{} init successful",serverName);	
//		repo = beanFactory.getBean(RDSStockBORepository.class);
//		repo = beanFactory.getBean(TestBORepository.class);
		
	}
	
	public void start() {
		super.start();
				
		//First load DB data
//		loadDBdata2Cache();
		
	}

	private <T> void loadDBdata2Cache() {
		// TODO Auto-generated method stub
		rdsinfo = beanFactory.getBean(RDSCommon.class);
		try {
			Class rdsCrud = Class.forName(rdsinfo.getRdsCrud());
			repo = (CrudRepository)beanFactory.getBean(rdsCrud);
			log.info("---------load all key from DB table:{}---------",rdsinfo.getTableName());
			List<T> list = (List<T>)repo.findAll();
			int count = 0;
	    	for(T each : list) {
	    		fcache.setList(each.getClass().getName(), each);
//	    		cache.rpush(.getBytes(), ObjectTransCoder.serialize(each));
	    		count++;
	    	}
	    	log.info("load data from DB to cache successful!!");
	    	log.info("Count:{}",count);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	protected void save2DB(BaseBO bo) {
		repo.save(bo);
	} 

	public RDSCommon getRdsinfo() {
		return rdsinfo;
	}

	public void setRdsinfo(RDSCommon rdsinfo) {
		this.rdsinfo = rdsinfo;
	}

	public CrudRepository getRepo() {
		return repo;
	}

	public void setRepo(CrudRepository repo) {
		this.repo = repo;
	}

	public List getSourceBOList() {
		return sourceBOList;
	}

	public void setSourceBOList(List sourceBOList) {
		this.sourceBOList = sourceBOList;
	}
}
