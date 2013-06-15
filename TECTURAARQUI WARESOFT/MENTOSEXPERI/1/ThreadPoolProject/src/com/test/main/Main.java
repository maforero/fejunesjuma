package com.test.main;

import com.test.connection.MessageListener;
import com.test.queue.QueueMonitor;

/**
 * @class Main.java
 * @author Felipe
 * @Date Jun 14, 2013
 * @since 1.0
 */
public class Main {

	public static void main(String[] args) {
		
		QueueMonitor queueMonitor = new QueueMonitor();
		Thread queueMonitorThread = new Thread(queueMonitor);
		queueMonitorThread.start();
		
		Thread messageListener = new Thread(new MessageListener());
		messageListener.start();
	}
}
