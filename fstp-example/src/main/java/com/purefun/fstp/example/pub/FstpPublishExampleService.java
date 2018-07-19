package com.purefun.fstp.example.pub;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.purefun.fstp.core.bo.TestBO;
import com.purefun.fstp.core.bo.otw.TestBO_OTW;
import com.purefun.fstp.core.cache.ignitecache.ICache;
import com.purefun.fstp.core.dur.ctl.CtlCommon;
import com.purefun.fstp.core.ipc.PublishMode;
import com.purefun.fstp.core.ipc.pub.Publisher;
import com.purefun.fstp.core.logging.PLogger;
import com.purefun.fstp.core.server.PService;
import com.purefun.fstp.core.tool.BoFactory;
import com.purefun.fstp.example.bo.ExampleBO;
import com.purefun.fstp.example.bo.ExampleQnsBO;
import com.purefun.fstp.example.bo.otw.ExampleBO_OTW;
import com.purefun.fstp.example.bo.otw.ExampleQnsBO_OTW;
import com.purefun.fstp.example.repo.ExampleQnsBORepository;

public class FstpPublishExampleService extends PService{

	public FstpPublishExampleService(boolean isServer) {
		super(isServer);
		// TODO Auto-generated constructor stub
		log = PLogger.getLogger(FstpPublishExampleService.class);
	}
	
	public void init() {
		super.init();	
	}
	
	public void start() {
		super.start();
		doservice();		
	}

	private void doservice() {
		// TODO Auto-generated method stub
		Publisher pub = rpcfactory.createPublisher();
		ExampleQnsBORepository repo;
		CtlCommon rdsinfo = beanFactory.getBean(CtlCommon.class);
		Class rdsCrud;
		try {
			rdsCrud = Class.forName(rdsinfo.getRdsCrud());
			repo = (ExampleQnsBORepository)beanFactory.getBean(rdsCrud);
			
			for(int i = 0;i<3;i++) {
				ExampleBO_OTW bo = (ExampleBO_OTW) BoFactory.createBo(ExampleBO.class);
				bo.setName("Hans");
				bo.setAge(i);
//				bo.setCompany("CICC");
//				pub.publish(bo, PublishMode.PUBLISH_ONLY);
				pub.publish(bo, PublishMode.PUBLISH_AND_DUR);
//				repo.save(bo.getBo());
				log.info("{}",bo.getBoid());
			}
//			for(int i = 0;i<3;i++) {
//				ExampleQnsBO_OTW bo = (ExampleQnsBO_OTW) BoFactory.createBo(ExampleQnsBO.class);
//				bo.setName("Hans");
//				bo.setAge(i);
//				bo.setCompany("CICC");
////				pub.publish(bo, PublishMode.PUBLISH_ONLY);
//				pub.publish(bo, PublishMode.PUBLISH_AND_DUR);
//				repo.save(bo.getBo());
//				log.info("{}",bo.getBoid());
//			}
//			for(int i = 0;i<3;i++) {
//				TestBO_OTW bo = (TestBO_OTW) BoFactory.createBo(TestBO.class);
//				bo.setMsg(String.valueOf(i));
//				bo.setServername("Publisher");
//				pub.publish(bo, PublishMode.PUBLISH_AND_DUR);
//				log.info("{}",bo.getBoid());
//			}
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
				
//		ICache cache = (ICache)Icache;
//		List<ExampleQnsBO> t = cache.<ExampleQnsBO>query("fstp.example.bo.test", null, ExampleQnsBO.class);
//		for(ExampleQnsBO e:t) {
//			log.info("name = {}, age = {} ",e.name, e.age);
//		}
	}
	
}
