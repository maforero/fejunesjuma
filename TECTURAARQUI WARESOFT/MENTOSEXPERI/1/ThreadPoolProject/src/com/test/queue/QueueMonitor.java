package com.test.queue;

import com.test.thread.ThreadPool;

/**
 * @class QueueMonitor.java
 * @author Felipe
 * @Date Jun 14, 2013
 * @since 1.0
 */
public class QueueMonitor implements Runnable {

	private boolean keepRunning;
	private QueueManager queueManager;
	private ThreadPool threadPool;

	public QueueMonitor() {
		keepRunning = true;
		queueManager = QueueManager.getInstance();
		threadPool = new ThreadPool();
	}

	public void run() {
		while (keepRunning) {
			if (queueManager.hasMessage()) {
				final String message = queueManager.pollMessage();
				threadPool.execute(new Runnable() {
					@Override
					public void run() {
						System.out.println(message);
					}
				});
			}
		}
	}
}
