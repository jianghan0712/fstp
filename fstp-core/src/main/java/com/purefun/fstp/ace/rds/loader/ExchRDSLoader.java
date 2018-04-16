package com.purefun.fstp.ace.rds.loader;

import java.util.Map;

import org.zeromq.ZStar.Exit;

import com.purefun.fstp.ace.rds.loader.bean.Cpxxbean;
import com.purefun.fstp.ace.rds.server.StockRDS;
import com.purefun.fstp.core.bo.SourceStockBO;
import com.purefun.fstp.core.bo.otw.SourceStockBO_OTW;
import com.purefun.fstp.core.logging.PLogger;
import com.purefun.fstp.core.rpc.PublishMode;
import com.purefun.fstp.core.rpc.RpcFactory;
import com.purefun.fstp.core.rpc.pub.Publisher;
import com.purefun.fstp.core.server.PService;

public class ExchRDSLoader extends PService{
	Map<String,String> secuMap = null;
	FileLoaderFactory filesFactory = null;
	String exchFilesPath = null;
	
	public ExchRDSLoader(boolean isServer) {
		super(isServer);
		// TODO Auto-generated constructor stub
		log = PLogger.getLogger(ExchRDSLoader.class);
	}
	
	public void init() {
		super.init();		
	}
	
	public void start() {
		super.start();
		filesFactory.init(log);
		loadFiles();
		publish2RDS();
		
		System.exit(0);
	}

	private void loadFiles() {
		// TODO Auto-generated method stub
		filesFactory.loadFiles();

	}
	
	private void publish2RDS() {
		Map<String,Map> allMap = filesFactory.getAllMap();
		Publisher publisher = RpcFactory.createPublisher();
		
		for(Map.Entry<String, Map> eachMap:allMap.entrySet()) {
			String fileNamp = eachMap.getKey();
			Map<String,Map<String,Cpxxbean>> infoMap = eachMap.getValue();
			Map<String,Cpxxbean> beanMap = infoMap.get("STOCK");
			for(Map.Entry<String, Cpxxbean> bean:beanMap.entrySet()) {
				SourceStockBO_OTW bo = new SourceStockBO_OTW();	
				Cpxxbean value = bean.getValue();
				bo.setSecu_id(value.getSecu_id());
				bo.setSecu_chinese_name(value.getSecu_chinese_name());
				publisher.publish(bo, PublishMode.PUBLISH_ONLY);
			}			
		}		
	}

	public Map<String, String> getSecuMap() {
		return secuMap;
	}

	public void setSecuMap(Map<String, String> secuMap) {
		this.secuMap = secuMap;
	}

	public FileLoaderFactory getFilesFactory() {
		return filesFactory;
	}

	public void setFilesFactory(FileLoaderFactory filesFactory) {
		this.filesFactory = filesFactory;
	}
}
