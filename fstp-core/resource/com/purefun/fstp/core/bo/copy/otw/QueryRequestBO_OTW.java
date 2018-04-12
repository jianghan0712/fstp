package com.purefun.fstp.core.bo.copy.otw;

import com.purefun.fstp.core.bo.copy.QueryRequestBO;
import com.purefun.fstp.core.bo.copy.pro.QueryRequestBO_PRO;
import com.google.protobuf.InvalidProtocolBufferException;

public class QueryRequestBO_OTW implements ICommom_OTW {
    QueryRequestBO_PRO.QueryRequestBO.Builder builder = null;
    QueryRequestBO bo = null;

    public QueryRequestBO_OTW() {
        builder = QueryRequestBO_PRO.QueryRequestBO.newBuilder();
        bo= new QueryRequestBO();
        builder.setUuid(bo.uuid);
        builder.setBoid(bo.boid);
        builder.setDestination(bo.destination);
    }

    public QueryRequestBO_OTW(byte[] message) throws InvalidProtocolBufferException {
        builder = QueryRequestBO_PRO.QueryRequestBO.newBuilder();
        bo= new QueryRequestBO();
        QueryRequestBO_PRO.QueryRequestBO receive = QueryRequestBO_PRO.QueryRequestBO.parseFrom(message);
        setUuid(receive.getUuid());
        setBoid(receive.getBoid());
        setDestination(receive.getDestination());
        setRequestServiceName(receive.getRequestServiceName());
        setRespondServiceName(receive.getRespondServiceName());
        setQuerytopic(receive.getQuerytopic());
        setQueryBoDestination(receive.getQueryBoDestination());
    }

    @Override
    public QueryRequestBO_PRO.QueryRequestBO.Builder getBuilder() { 
        return builder;
    }

    @Override
    public QueryRequestBO getBo() { 
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

    public java.lang.String getRequestServiceName() {
        return builder.getRequestServiceName();
    }

    public void setRequestServiceName(java.lang.String requestServiceName) {
        bo.requestServiceName = requestServiceName;
        builder.setRequestServiceName(requestServiceName);
    }

    public java.lang.String getRespondServiceName() {
        return builder.getRespondServiceName();
    }

    public void setRespondServiceName(java.lang.String respondServiceName) {
        bo.respondServiceName = respondServiceName;
        builder.setRespondServiceName(respondServiceName);
    }

    public java.lang.String getQuerytopic() {
        return builder.getQuerytopic();
    }

    public void setQuerytopic(java.lang.String querytopic) {
        bo.querytopic = querytopic;
        builder.setQuerytopic(querytopic);
    }

    public java.lang.String getQueryBoDestination() {
        return builder.getQueryBoDestination();
    }

    public void setQueryBoDestination(java.lang.String queryBoDestination) {
        bo.queryBoDestination = queryBoDestination;
        builder.setQueryBoDestination(queryBoDestination);
    }

    public String toString() {
        return "QueryRequestBO_OTW ["+
            "uuid = " + getUuid() +"," +
            "boid = " + getBoid() +"," +
            "destination = " + getDestination() +"," +
            "requestServiceName = " + getRequestServiceName() +"," +
            "respondServiceName = " + getRespondServiceName() +"," +
            "querytopic = " + getQuerytopic() +"," +
            "queryBoDestination = " + getQueryBoDestination() +"," +
    "]";
    }
}
