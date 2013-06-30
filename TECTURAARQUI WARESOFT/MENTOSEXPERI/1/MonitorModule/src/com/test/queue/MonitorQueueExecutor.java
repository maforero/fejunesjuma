package com.test.queue;

import java.io.ByteArrayInputStream;
import java.io.ObjectInputStream;

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
		new GenericModule().doSomething(deserialize(monitor.getData()));
	}

	private RegularFrameDTO deserialize(byte[] data) {
		ByteArrayInputStream in = new ByteArrayInputStream(data);
		try {
			ObjectInputStream is = new ObjectInputStream(in);
			return (RegularFrameDTO) is.readObject();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}
}
