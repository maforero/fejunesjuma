package com.test.queue;

import com.test.monitoring.Trace;

/**
 * @class QueueMonitor.java
 * @author Felipe
 * @Date Jun 14, 2013
 * @since 1.0
 */
public class QueueMonitor implements Runnable {

	private boolean keepRunning;
	private QueueManager queueManager;
	private QueueExecutor executor;

	public QueueMonitor() {
		keepRunning = true;
		queueManager = QueueManager.getInstance();
		executor = QueueExecutorFactory.getInstance().getExecutor();
	}

	public void run() {
		while (keepRunning) {
			Trace monitor = queueManager.pollMessage();
			executor.execute(monitor);
		}
	}
}
