package com.purefun.fstp.core.bo;

import java.io.Serializable;

import com.purefun.fstp.core.tool.UUID;
import com.purefun.fstp.core.tool.fstpbo;

@fstpbo(boid = 4L, destination = "fstp.core.manager.qnsrespond")
public class QNSRespondBO extends BaseBO  {	
	
	public String servername = "";	
	
	public String respond = "";
}
