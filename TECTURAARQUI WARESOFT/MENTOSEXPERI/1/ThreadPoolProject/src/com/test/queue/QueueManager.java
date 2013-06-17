package com.test.queue;

import java.util.LinkedList;

import com.test.monitoring.TransAlpesMonitor;

/**
 * @class QueueManager.java
 * @author Felipe
 * @Date Jun 14, 2013
 * @since 1.0
 */
public class QueueManager {

	private static QueueManager instance;
	private LinkedList<TransAlpesMonitor> queue;
	
	private QueueManager() {
		queue = new LinkedList<TransAlpesMonitor>();
	}
	
	public static QueueManager getInstance() {
		if (instance == null) {
			instance = new QueueManager();
		}
		
		return instance;
	}
	
	public boolean hasMessage() {
		return !queue.isEmpty();
	}
	
	public synchronized void addMessage(TransAlpesMonitor message) {
		queue.addLast(message);
	}
	
	public synchronized TransAlpesMonitor pollMessage() {
		return queue.poll();
	}
}
 