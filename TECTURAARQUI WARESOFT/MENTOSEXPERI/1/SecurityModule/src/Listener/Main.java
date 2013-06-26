package Listener;


import queue.SendMessageExecutor;

import com.test.configuration.ConfigurationManager;
import com.test.configuration.Properties;
import com.test.connection.KeepTraceMessageProcessor;
import com.test.connection.MessageListener;
import com.test.queue.QueueMonitor;
import com.test.queue.TraceQueueExecutor;

import connection.SecutrityMessageProcessor;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		loadConfigurations(args);
		startQueueMonitor();
		startMessageListener();
	}

	/**
	 * 
	 */
	private static void startMessageListener() {
		String port = ConfigurationManager.getInstance().getProperty(
				Properties.NODE_PORT.name());
		SecutrityMessageProcessor secutrityMessageProcessor = new SecutrityMessageProcessor();
		KeepTraceMessageProcessor keepTraceMessageProcessor = new KeepTraceMessageProcessor(
				secutrityMessageProcessor);
		Thread messageListener = new Thread(new MessageListener(
				Integer.valueOf(port), keepTraceMessageProcessor));
		messageListener.start();
	}

	/**
	 * 
	 */
	private static void startQueueMonitor() {
		SendMessageExecutor executor = new SendMessageExecutor();
		TraceQueueExecutor traceQueueExecutor = new TraceQueueExecutor(executor);
		QueueMonitor queueMonitor = new QueueMonitor(traceQueueExecutor);
		Thread queueMonitorThread = new Thread(queueMonitor);
		queueMonitorThread.start();
	}

	/**
	 * 
	 */
	private static void loadConfigurations(String[] args) {
		if (args != null && args.length > 0) {
			ConfigurationManager.getInstance().loadProperties(args[0]);
		} else {
			ConfigurationManager.getInstance().loadProperties();
		}
	}

}