package com.test.queue;

import com.test.monitoring.Trace;

/**
 * @class QueueExecutor.java
 * @author Felipe
 * @Date Jun 16, 2013
 * @since 1.0
 */
public interface QueueExecutor {

	public void execute(Trace monitor);
}
