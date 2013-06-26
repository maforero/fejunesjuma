package connection;

import java.util.UUID;

import com.test.connection.MessageProcessor;
import com.test.monitoring.Trace;

/**
 * @class TraceMessageProcessor.java
 * @author Felipe
 * @Date Jun 25, 2013
 * @since 1.0
 */
public class StartTraceMessageProcessor implements MessageProcessor {

	private MessageProcessor processor;

	public StartTraceMessageProcessor(MessageProcessor processor) {
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
		monitor.setId(UUID.randomUUID().toString().substring(0, 8));
		processor.processMessage(monitor);
	}
}
