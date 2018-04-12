package com.purefun.fstp.core.bo.otw;

import com.purefun.fstp.core.bo.proto.TestBO2_PRO;
import com.purefun.fstp.core.bo.TestBO2;
import com.purefun.fstp.core.bo.copy.otw.ICommom_OTW;

public class TestBO2_OTW implements ICommom_OTW{	
	TestBO2_PRO.TestBO2.Builder build = null;	
	TestBO2 bo = null;
	
	public TestBO2_OTW() {
		// TODO Auto-generated constructor stub
		build = TestBO2_PRO.TestBO2.newBuilder();
		bo = new TestBO2();
		build.setUuid(bo.uuid);
		build.setBoid(bo.boid);
		build.setDestination(bo.destination);
	}
	
	@Override	
	public TestBO2_PRO.TestBO2.Builder getBuilder() {
		return build;
	}

	public void setBuild(TestBO2_PRO.TestBO2.Builder build) {
		this.build = build;
	}

	public String getServername() {
		return build.getServername();
	}

	public void setServername(String servername) {
		bo.servername = servername;
		build.setServername(servername);
	}

	public String getMsg() {
		return build.getMsg();
	}

	public void setMsg(String msg) {
		bo.msg = msg;
		build.setMsg(msg);
	}

	public void setDestination(String destination) {
		build.setDestination(destination);
	}
	
	@Override
	public TestBO2 getBo() {
		return bo;
	}

	public void setBo(TestBO2 bo) {
		this.bo = bo;
	}

	@Override
	public String getDestination() {
		// TODO Auto-generated method stub
		return build.getDestination();
	}

}
