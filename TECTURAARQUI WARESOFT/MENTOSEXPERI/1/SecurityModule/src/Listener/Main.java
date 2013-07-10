package Listener;


import com.test.configuration.ConfigurationManager;
import com.test.configuration.Properties;
import com.test.connection.MessageListener;
import com.test.ping.PingManager;
import com.test.queue.QueueMonitor;
import com.test.queue.SendMessageExecutor;
import com.test.queue.TraceQueueExecutor;

import connection.LeaveRealDataMessageProcessor;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		loadConfigurations(args);
		startQueueMonitor();
		startMessageListener();
		startPingListener();
	}

	private static void startPingListener() {
	    Thread pingListener = new Thread(new PingManager());
	    pingListener.start();
    }

    /**
	 * 
	 */
	private static void startMessageListener() {
		String port = ConfigurationManager.getInstance().getProperty(
				Properties.NODE_PORT.name());
		LeaveRealDataMessageProcessor secutrityMessageProcessor = new LeaveRealDataMessageProcessor();
		Thread messageListener = new Thread(new MessageListener(
				Integer.valueOf(port), secutrityMessageProcessor));
		messageListener.start();
	}

	/**
	 * 
	 */
	private static void startQueueMonitor() {
		SendMessageExecutor executor = new SendMessageExecutor();
		QueueMonitor queueMonitor = new QueueMonitor(executor);
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
