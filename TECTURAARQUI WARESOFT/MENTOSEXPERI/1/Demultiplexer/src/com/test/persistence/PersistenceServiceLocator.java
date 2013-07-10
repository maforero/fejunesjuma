package com.test.persistence;

import java.util.Properties;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.rmi.PortableRemoteObject;

import com.test.configuration.ConfigurationManager;

/**
 * @class PersistenceProxy.java
 * @author Felipe
 * @Date Jul 7, 2013
 * @since 1.0
 */
public class PersistenceServiceLocator {

	private String persistenceHost;
	private String persistencePort;
	private String persistenceJNDI;
	private FramePersister framePersister;

	public void lookup() {
		try {
			initRemotePersistenceProperties();
		} catch (NamingException e) {
			throw new IllegalStateException("remote persister not found");
		}
	}

	public FramePersister getPersister() {
		return framePersister;
	}

	/**
	 * @return
	 * @throws NamingException
	 */
	private void initRemotePersistenceProperties() throws NamingException {
		loadPersistenceProperties();
		lookupRemoteFramePersister();
	}

	private void loadPersistenceProperties() {
		ConfigurationManager configurationManager = ConfigurationManager
				.getInstance();
		persistenceHost = configurationManager
				.getProperty(com.test.configuration.Properties.PERSISTENCE_HOST
						.name());
		persistencePort = configurationManager
				.getProperty(com.test.configuration.Properties.PERSISTENCE_PORT
						.name());
		persistenceJNDI = configurationManager
				.getProperty(com.test.configuration.Properties.PERSISTENCE_JNDI
						.name());
	}

	/**
	 * @throws NamingException
	 */
	private void lookupRemoteFramePersister() throws NamingException {
		Properties props = new Properties();
		loadInitialContextProperties(props);
		Object prueba = lookupRemote(props);
		castFramePersister(prueba);
	}

	/**
	 * @param props
	 * @return
	 * @throws NamingException
	 */
	private Object lookupRemote(Properties props) throws NamingException {
		InitialContext context = new InitialContext(props);
		Object prueba = context.lookup(persistenceJNDI);
		return prueba;
	}

	/**
	 * @param prueba
	 */
	private void castFramePersister(Object prueba) {
		framePersister = (FramePersister) PortableRemoteObject.narrow(prueba,
				FramePersister.class);
	}

	/**
	 * @param props
	 * @return
	 * @throws NamingException
	 */
	private void loadInitialContextProperties(Properties props)
			throws NamingException {
		props.setProperty("java.naming.factory.initial",
				"com.sun.enterprise.naming.SerialInitContextFactory");
		props.setProperty("org.omg.CORBA.ORBInitialHost", persistenceHost);
		props.setProperty("org.omg.CORBA.ORBInitialPort", persistencePort);
	}

}
