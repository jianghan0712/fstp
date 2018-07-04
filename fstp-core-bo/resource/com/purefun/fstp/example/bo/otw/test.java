package com.purefun.fstp.example.bo.otw;

import java.lang.reflect.Field;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.lucene.index.Fields;

import com.google.protobuf.Any;
import com.google.protobuf.InvalidProtocolBufferException;
import com.purefun.fstp.core.tool.BoFactory;
import com.purefun.fstp.example.bo.ExampleBO;
import com.purefun.fstp.example.bo.ExampleQnsBO;
import com.purefun.fstp.example.bo.otw.ExampleQnsBO_OTW;

public class test {
	public static void main(String[] args) throws InvalidProtocolBufferException {
//		ExampleQnsBO_PRO.ExampleQnsBO.Builder builder = ExampleQnsBO_PRO.ExampleQnsBO.newBuilder();
//		builder.set
		ExampleBO_OTW q1 = (ExampleBO_OTW) BoFactory.createBo(ExampleBO.class);
		q1.setName("jiang han");
		System.out.println(q1.getUuid());
		System.out.println(q1.getBoid());
		System.out.println(q1.getDestination());
		System.out.println(q1.getName());
		
		ExampleBO_OTW q2 = new ExampleBO_OTW(q1.serial());
		System.out.println(q2.getUuid());
		System.out.println(q2.getBoid());
		System.out.println(q2.getDestination());
		System.out.println(q2.getName());
		System.out.println(q2.toString());
	}
}
