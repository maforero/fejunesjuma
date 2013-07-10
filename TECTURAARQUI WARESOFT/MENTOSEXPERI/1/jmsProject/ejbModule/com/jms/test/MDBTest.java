package com.jms.test;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;

/**
 * Message-Driven Bean implementation class for: MDBTest
 *
 */
@MessageDriven(
		activationConfig = { @ActivationConfigProperty(
				propertyName = "destinationType", propertyValue = "javax.jms.Topic"
		) }, 
		mappedName = "jms/Test")
public class MDBTest implements MessageListener {

    /**
     * Default constructor. 
     */
    public MDBTest() {
        // TODO Auto-generated constructor stub
    }
	
	/**
     * @see MessageListener#onMessage(Message)
     */
    public void onMessage(Message message) {
    	try {
			System.out.println(message.getStringProperty("aña")+" -----------------------");
		} catch (JMSException e) {
			e.printStackTrace();
		}
    }

}
