package com.purefun.fstp.core.bo.otw;

import com.purefun.fstp.core.bo.TestBO;
import com.purefun.fstp.core.bo.pro.TestBO_PRO;
import com.google.protobuf.InvalidProtocolBufferException;
import com.purefun.fstp.core.bo.commom.ICommom_OTW;

public class TestBO_OTW implements ICommom_OTW {
    TestBO_PRO.TestBO.Builder builder = null;
    TestBO bo = null;

    public TestBO_OTW() {
        builder = TestBO_PRO.TestBO.newBuilder();
        bo= new TestBO();
        builder.setUuid(bo.uuid);
        builder.setBoid(bo.boid);
        builder.setDestination(bo.destination);
    }

    public TestBO_OTW(byte[] message) throws InvalidProtocolBufferException {
        builder = TestBO_PRO.TestBO.newBuilder();
        bo= new TestBO();
        TestBO_PRO.TestBO receive = TestBO_PRO.TestBO.parseFrom(message);
        setUuid(receive.getUuid());
        setBoid(receive.getBoid());
        setDestination(receive.getDestination());
        setServername(receive.getServername());
        setMsg(receive.getMsg());
    }

    @Override
    public TestBO_PRO.TestBO.Builder getBuilder() { 
        return builder;
    }

    @Override
    public TestBO getBo() { 
        return bo;
    }

    public java.lang.String getUuid() {
        return builder.getUuid();
    }

    public void setUuid(java.lang.String uuid) {
        bo.uuid = uuid;
        builder.setUuid(uuid);
    }

    public long getBoid() {
        return builder.getBoid();
    }

    public void setBoid(long boid) {
        bo.boid = boid;
        builder.setBoid(boid);
    }

    public java.lang.String getDestination() {
        return builder.getDestination();
    }

    public void setDestination(java.lang.String destination) {
        bo.destination = destination;
        builder.setDestination(destination);
    }

    public java.lang.String getServername() {
        return builder.getServername();
    }

    public void setServername(java.lang.String servername) {
        bo.servername = servername;
        builder.setServername(servername);
    }

    public java.lang.String getMsg() {
        return builder.getMsg();
    }

    public void setMsg(java.lang.String msg) {
        bo.msg = msg;
        builder.setMsg(msg);
    }

    public String toString() {
        return "TestBO_OTW ["+
            "uuid = " + getUuid() +"," +
            "boid = " + getBoid() +"," +
            "destination = " + getDestination() +"," +
            "servername = " + getServername() +"," +
            "msg = " + getMsg() +"," +
    "]";
    }
}
