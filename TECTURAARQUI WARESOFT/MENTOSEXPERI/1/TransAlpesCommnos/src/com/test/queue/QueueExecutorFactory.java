package com.test.queue;

import com.test.configuration.ConfigurationManager;
import com.test.configuration.Properties;

/**
 * @class QueueExecutorFactory.java
 * @author Felipe
 * @Date Jun 16, 2013
 * @since 1.0
 */
class QueueExecutorFactory {

	private static QueueExecutorFactory instance;
	private QueueExecutor executor;

	private QueueExecutorFactory() {
	}

	public static QueueExecutorFactory getInstance() {
		if (instance == null) {
			instance = new QueueExecutorFactory();
		}

		return instance;
	}

	/**
	 * @return
	 */
	public QueueExecutor getExecutor() {
		if (executor == null) {
			executor = getQueueExecutorInstance();
		}

		return executor;
	}

	/**
	 * 
	 */
	private static QueueExecutor getQueueExecutorInstance() {
		String instanceName = ConfigurationManager.getInstance().getProperty(
				Properties.QUEUE_EXECUTOR_IMPL.name());

		try {
			Class<?> clazz = Class.forName(instanceName);
			Object instance = clazz.newInstance();
			return (QueueExecutor) instance;
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
