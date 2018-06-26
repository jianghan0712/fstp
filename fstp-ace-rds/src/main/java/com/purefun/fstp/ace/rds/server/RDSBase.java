package com.purefun.fstp.ace.rds.server;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.purefun.fstp.core.bo.BaseBO;
import com.purefun.fstp.core.ipc.pub.Publisher;
import com.purefun.fstp.core.ipc.sub.Subscriber;
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
	
	}
	
	public void init() {
		super.init();
		log.info("{} init successful",serverName);			
	}
	
	public void start() {
		super.start();		
		//First load DB data
		loadDBdata2Cache();	
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	private <T> void loadDBdata2Cache() {
		// TODO Auto-generated method stub
		rdsinfo = beanFactory.getBean(RDSCommon.class);

		try {
			Class rdsCrud = Class.forName(rdsinfo.getRdsCrud());
			repo = (CrudRepository)beanFactory.getBean(rdsCrud);
			log.info("---------load all key from DB table:{}---------",rdsinfo.getTableName());
			List list = (List) repo.findAll();

			loadDBdata2CacheImp(list);	    	
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
		
	abstract protected void loadDBdata2CacheImp(List list);
	
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
