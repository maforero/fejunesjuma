package com.test.persistence;

import java.util.Properties;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.rmi.PortableRemoteObject;

/**
 * @class PersistenceProxy.java
 * @author Felipe
 * @Date Jul 7, 2013
 * @since 1.0
 */
public class PersistenceServiceLocator {

	public FramePersister getPersister() {
		Object prueba;
		try {
			prueba = initRemotePersistenceProperties();
			FramePersister framePersister = (FramePersister) PortableRemoteObject
					.narrow(prueba, FramePersister.class);

			return framePersister;
		} catch (NamingException e) {
			e.printStackTrace();
		}

		throw new IllegalStateException("remote persister not found");
	}

	/**
	 * @return
	 * @throws NamingException
	 */
	private Object initRemotePersistenceProperties() throws NamingException {
		Properties props = new Properties();
//		props.setProperty("java.naming.factory.initial",
//				"com.sun.enterprise.naming.SerialInitContextFactory");
		props.setProperty("org.omg.CORBA.ORBInitialHost", "localhost");// ur
																		// server
																		// ip
		props.setProperty("org.omg.CORBA.ORBInitialPort", "3700"); // default is
																	// 3700

		InitialContext context = new InitialContext(props);
		Object prueba = context
				.lookup("java:global/TransAlpesIntegrationEAR/TransAlpesPersistence/FramePersisterBean");
		return prueba;
	}

}
