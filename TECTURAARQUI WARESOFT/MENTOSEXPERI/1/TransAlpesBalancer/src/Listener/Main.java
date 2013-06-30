package Listener;

import java.util.HashMap;

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
		HashMap<String, Long> lastBeatMessage = new HashMap<String, Long>();
		loadConfigurations(args);
		startQueueMonitor(lastBeatMessage);
		startMessageListener();
		startHeartBeatListener(lastBeatMessage);
		// new Main();
	}

	/**
	 * It starts the hearbeatlistener Thread
	 */
	private static void startHeartBeatListener(HashMap<String, Long> lastBeatMessage) {
		String port = ConfigurationManager.getInstance().getProperty(
				Properties.HEARTBEAT_PORT.name());
		Thread heartBeatListener = new Thread(new HeartBeatListener(
				Integer.valueOf(port), lastBeatMessage));
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
	 * @param lastBeatMessage 
	 * 
	 */
	private static void startQueueMonitor(HashMap<String,Long> lastBeatMessage) {
		QueueMonitor queueMonitor = new QueueMonitor(new TraceQueueExecutor(
				new BalancerQueueExecutor(lastBeatMessage)));
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
