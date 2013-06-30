package com.test.queue;

import com.test.monitoring.Trace;

/**
 * @class TraceQueueExecutor.java
 * @author Felipe
 * @Date Jun 26, 2013
 * @since 1.0
 */
public class TraceQueueExecutor implements QueueExecutor {
	
	private QueueExecutor executor;
	
	public TraceQueueExecutor(QueueExecutor executor) {
		this.executor = executor;
	}

	/* (non-Javadoc)
	 * @see com.test.queue.QueueExecutor#execute(com.test.monitoring.Trace)
	 */
	@Override
	public void execute(Trace monitor) {
		byte[] data = getData(monitor);
		monitor.setData(data);
		executor.execute(monitor);
	}

	/**
	 * @param monitor
	 * @return
	 */
	private byte[] getData(Trace monitor) {
		byte[] bytesId = monitor.getId().getBytes();
		byte[] monitorData = monitor.getData();
		int dataSize = monitorData.length + bytesId.length + 1;
		byte[] data = new byte[dataSize];
		int i;
		for (i = 0; i < monitorData.length; i++) {
			data[i] = monitorData[i];
		}
		for (int j = 0; j < bytesId.length; j++) {
			data[i+j] = bytesId[j];
		}
		data[data.length - 1] = (byte) bytesId.length;
		return data;
	}
	
}
