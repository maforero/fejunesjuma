package com.test.queue;

import com.test.configuration.ConfigurationManager;
import com.test.configuration.Properties;
import com.test.monitoring.Trace;
import com.test.thread.ThreadPool;

/**
 * @class ThreadQueueExecutor.java
 * @author Felipe
 * @Date Jun 16, 2013
 * @since 1.0
 */
public class ThreadQueueExecutor implements QueueExecutor {

	private ThreadPool threadPool;

	public ThreadQueueExecutor() {
		String poolSize = ConfigurationManager.getInstance().getProperty(
				Properties.EXECUTOR_POOL_SIZE.name());

		if (poolSize != null) {
			int size = Integer.parseInt(poolSize);
			threadPool = new ThreadPool(size, size);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.test.queue.QueueExecutor#execute(com.test.monitoring.TransAlpesMonitor
	 * )
	 */
	@Override
	public void execute(Trace monitor) {
		threadPool.execute(monitor);
	}

}
