package Listener;

import queue.BalancerQueueExecutor;

import com.test.configuration.ConfigurationManager;
import com.test.configuration.Properties;
import com.test.connection.HeartBeatListener;
import com.test.connection.MessageListener;
import com.test.connection.QueueMessageProcessor;
import com.test.queue.QueueMonitor;
import com.test.queue.TraceQueueExecutor;

import connection.StartTraceMessageProcessor;

public class Main {

	public static void main(String args[]) {
		loadConfigurations(args);
		startQueueMonitor();
		startMessageListener();
		startHeartBeatListener();
		// new Main();
	}

	/**
	 * It starts the hearbeatlistener Thread
	 */
	private static void startHeartBeatListener() {
		String port = ConfigurationManager.getInstance().getProperty(
				Properties.HEARTBEAT_PORT.name());
		Thread heartBeatListener = new Thread(new HeartBeatListener(
				Integer.valueOf(port)));
		heartBeatListener.start();
	}

	/**
	 * 
	 */
	private static void startMessageListener() {
		String port = ConfigurationManager.getInstance().getProperty(
				Properties.BALANCER_PORT.name());
		StartTraceMessageProcessor processor = new StartTraceMessageProcessor(
				new QueueMessageProcessor());
		Thread messageListener = new Thread(new MessageListener(
				Integer.valueOf(port), processor));
		messageListener.start();
	}

	/**
	 * 
	 */
	private static void startQueueMonitor() {
		QueueMonitor queueMonitor = new QueueMonitor(new TraceQueueExecutor(
				new BalancerQueueExecutor()));
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
