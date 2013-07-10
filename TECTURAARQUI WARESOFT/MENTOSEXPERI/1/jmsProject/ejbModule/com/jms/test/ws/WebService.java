package com.jms.test.ws;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.Topic;
import javax.jms.TopicConnectionFactory;
import javax.jws.WebMethod;
import javax.jws.WebParam;

/**
 * Session Bean implementation class WebService
 */
@Stateless
@javax.jws.WebService
public class WebService {

	@Resource(lookup="jms/connectiontest")
	private TopicConnectionFactory connectionFactory;
	@Resource(lookup="jms/Test")
	private Topic topic;
//	
	@WebMethod(operationName = "putMessage")
	public void putMessage(@WebParam(name = "message") String message) {
		try {
			Session session = connectionFactory.createConnection().createSession();
			MessageProducer producer = session.createProducer(topic);
			Message jmsMessage = session.createMessage();
			jmsMessage.setStringProperty("aña", message);
			producer.send(jmsMessage);
			session.close();
		} catch (JMSException e) {
			e.printStackTrace();
		}
	}

}
