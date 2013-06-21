package com.test.connection;

import com.test.monitoring.Trace;
import com.test.queue.QueueManager;

/**
 * @class LoadMessageProcessor.java
 * @author Felipe
 * @Date Jun 16, 2013
 * @since 1.0
 */
public class QueueMessageProcessor implements MessageProcessor {

	private QueueManager queueManager;

	public QueueMessageProcessor() {
		queueManager = QueueManager.getInstance();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.test.connection.MessageProcessor#processMessage(com.test.monitoring
	 * .TransAlpesMonitor)
	 */
	@Override
	public void processMessage(Trace monitor) {
		queueManager.addMessage(monitor);
	}
}
