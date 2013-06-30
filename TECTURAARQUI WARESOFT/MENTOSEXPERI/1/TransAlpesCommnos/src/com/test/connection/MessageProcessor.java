package com.test.connection;

import com.test.monitoring.Trace;

/**
 * @class MessageExecutor.java
 * @author Felipe
 * @Date Jun 16, 2013
 * @since 1.0
 */
public interface MessageProcessor {

	public void processMessage(Trace monitor);
}
