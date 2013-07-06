package Listener;

import queue.BalancerQueueExecutor;
import Repartidor.DispatcherBuilder;
import Repartidor.DispatcherQueue;
import Repartidor.Node;

import com.test.configuration.ConfigurationManager;
import com.test.configuration.Properties;
import com.test.connection.MessageListener;
import com.test.connection.QueueMessageProcessor;
import com.test.queue.QueueMonitor;
import com.test.queue.TraceQueueExecutor;

import connection.HeartBeatListener;
import connection.StartTraceMessageProcessor;

public class Main {

	public static void main(String args[]) {
		startApplication(args);
	}

	/**
	 * @param args
	 */
	private static void startApplication(String[] args) {
		loadConfigurations(args);
		DispatcherQueue<Node> dispatcherQueue = loadDispatcherQueue();
		startQueueMonitor(dispatcherQueue);
		startMessageListener();
		startHeartBeatListener(dispatcherQueue);
	}

	/**
	 * @return
	 */
	private static DispatcherQueue<Node> loadDispatcherQueue() {
		DispatcherBuilder dispatcherBuilder = DispatcherBuilder
				.createInstance();
		dispatcherBuilder.buildDispatcherNode();
		DispatcherQueue<Node> dispatcherQueue = dispatcherBuilder
				.getDispatcherQueue();
		return dispatcherQueue;
	}

	/**
	 * It starts the hearbeatlistener Thread
	 */
	private static void startHeartBeatListener(
			DispatcherQueue<Node> dispatcherQueue) {
		Thread heartBeatListener = new Thread(new HeartBeatListener(
				dispatcherQueue));
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
	 * @param dispatcherQueue
	 * 
	 */
	private static void startQueueMonitor(DispatcherQueue<Node> dispatcherQueue) {
		QueueMonitor queueMonitor = new QueueMonitor(new TraceQueueExecutor(
				new BalancerQueueExecutor(dispatcherQueue)));
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
