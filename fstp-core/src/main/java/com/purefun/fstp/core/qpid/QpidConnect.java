package com.purefun.fstp.core.qpid;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Properties;
import java.util.Set;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.Session;
import javax.naming.Context;
import javax.naming.InitialContext;

import org.apache.qpid.client.AMQConnectionFactory;
import org.slf4j.Logger;

//import com.cicc.pilot.core.bo.BaseBO;
//import com.cicc.pilot.core.cache.CacheClient;
//import com.cicc.pilot.core.cache.ObjectTransCoder;

import redis.clients.jedis.Jedis;

public class QpidConnect {
	Logger log = null;
	Connection connection = null;
	Session session = null;
	
	public QpidConnect(Logger log) {
		this.log = log;
	}
	
	public Session connect() {
		Session session = null;
		try (InputStream resourceAsStream = this.getClass().getResourceAsStream("qpid.properties")){
            Properties properties = new Properties();
            properties.load(resourceAsStream);
            Context context = new InitialContext(properties);
//            ConnectionFactory connectionFactory = new AMQConnectionFactory("amqp://guest:guest@client/?brokerlist='tcp://localhost:5672'");
            ConnectionFactory connectionFactory = (ConnectionFactory) context.lookup("qpidConnectionfactory");
            
            connection = connectionFactory.createConnection();
            connection.start();

            session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
//            session = connection.createSession(false, Session.CLIENT_ACKNOWLEDGE);
        }
        catch (Exception exp){
            exp.printStackTrace();
        }
		return session;
	}
	
	public void disconnect() {
		try {
			connection.close();
			session.close();
		} catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	

	
	
}
