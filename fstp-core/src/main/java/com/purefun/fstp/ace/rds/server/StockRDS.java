package com.purefun.fstp.ace.rds.server;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.List;

import javax.sql.DataSource;

import org.apache.xbean.spring.context.ClassPathXmlApplicationContext;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.data.repository.CrudRepository;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import com.purefun.fstp.core.bo.TestBO;
import com.purefun.fstp.core.logging.PLogger;
import com.purefun.fstp.core.rpc.RpcFactory;
import com.purefun.fstp.core.rpc.pub.Publisher;
import com.purefun.fstp.core.rpc.qns.QNSubscriber;
import com.purefun.fstp.core.tool.ErrorManager;

public class StockRDS extends RDSBase{
	CrudRepository repository = null;
	
	public StockRDS(String serverName, boolean isServer, String zmqport) {
		super(serverName,isServer,zmqport);
		log = PLogger.getLogger(StockRDS.class);
	}
	
	public void init() {
		super.init();
//		HBPort = 5200;
	}
	
	public void start() {
		super.start();	
		Publisher pub = RpcFactory.createPublisher();
		for(int i =0;i<3;i++) {
			TestBO testbo = new TestBO();
			testbo.setServername(serverName);
			pub.publish(new TestBO());
		}
		QNSubscriber qns = RpcFactory.createQNSubscriber();
		qns.setting("fstp.core.rpc.*", serverName);
		qns.QNS(new MyMessageListener(log));
		log.info("{} start successful",serverName);

		
//		CrudRepository repo = beanFactory.getBean(TestBORepository.class);
//    	TestBORepository personDao = ctx.getBean(TestBORepository.class);
    	
//    	TestBO test = new TestBO();
//    	personDao.save(test);
//    	List<TestBO> list = (List<TestBO>)repo.findAll();
//    	for(TestBO person : list) {
//    		System.out.println("查询结果： name=" + person.getMsg() );
//    	}
	}

	public CrudRepository getRepository() {
		return repository;
	}

	public void setRepository(CrudRepository repository) {
		this.repository = repository;
	}
	
	
}
