package com.test.connection;

import com.test.configuration.ConfigurationManager;
import com.test.configuration.Properties;

/**
 * @class MessageProcessorFactory.java
 * @author Felipe
 * @Date Jun 16, 2013
 * @since 1.0
 */
class MessageProcessorFactory {

	private static MessageProcessorFactory instance;
	private MessageProcessor processor;

	private MessageProcessorFactory() {
	}

	public static MessageProcessorFactory getInstance() {
		if (instance == null) {
			instance = new MessageProcessorFactory();
		}

		return instance;
	}

	public MessageProcessor getMessageProcessor() {
		if (processor == null) {
			processor = getProcessorInstance();
		}

		return processor;
	}

	/**
	 * @return
	 */
	private MessageProcessor getProcessorInstance() {

		String className = ConfigurationManager.getInstance().getProperty(
				Properties.MESSAGE_PROCESSOR_IMPL.name());
		try {
			Class<?> clazz = Class.forName(className);
			MessageProcessor processor = (MessageProcessor) clazz.newInstance();
			return new KeepTraceMessageProcessor(processor);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}

}
