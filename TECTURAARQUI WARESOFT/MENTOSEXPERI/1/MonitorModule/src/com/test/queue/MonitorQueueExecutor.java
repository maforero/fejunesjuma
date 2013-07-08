package com.test.queue;

import com.test.byteutil.ByteUtils;
import com.test.dto.RegularFrameDTO;
import com.test.module.GenericModule;
import com.test.monitoring.Trace;

/**
 * @class LoadMessageProcessor.java
 * @author Felipe
 * @Date Jun 16, 2013
 * @since 1.0
 */
public class MonitorQueueExecutor implements QueueExecutor {

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.test.queue.QueueExecutor#execute(com.test.monitoring.Trace)
	 */
	@Override
	public void execute(Trace monitor) {
		RegularFrameDTO frame = ByteUtils.getInstance().deserialize(
				monitor.getData());
		new GenericModule().doSomething(frame);
	}
}
