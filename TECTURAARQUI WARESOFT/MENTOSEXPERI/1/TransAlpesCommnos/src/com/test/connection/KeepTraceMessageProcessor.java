package com.test.connection;

import java.util.Arrays;

import com.test.monitoring.Trace;

/**
 * @class KeepTraceMessageProcessor.java
 * @author Felipe
 * @Date Jun 25, 2013
 * @since 1.0
 */
public class KeepTraceMessageProcessor implements MessageProcessor {

	private MessageProcessor processor;
	
	public KeepTraceMessageProcessor(MessageProcessor processor) {
		this.processor = processor;
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.test.connection.MessageProcessor#processMessage(com.test.monitoring
	 * .Trace)
	 */
	@Override
	public void processMessage(Trace monitor) {
		byte data[] = monitor.getData();
		byte idSize = data[data.length - 1];
		byte idBytes[] = Arrays.copyOfRange(data, data.length - 1 - idSize, data.length - 1);
		String id = new String(idBytes);
		monitor.setId(id);
		processor.processMessage(monitor);
	}
}
