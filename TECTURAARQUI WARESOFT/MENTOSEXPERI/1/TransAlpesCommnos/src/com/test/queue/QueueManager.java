package com.test.queue;

import java.util.LinkedList;

import com.test.monitoring.Trace;

/**
 * @class QueueManager.java
 * @author Felipe
 * @Date Jun 14, 2013
 * @since 1.0
 */
public class QueueManager {

	private static QueueManager instance;
	private LinkedList<Trace> queue;
	
	private QueueManager() {
		queue = new LinkedList<Trace>();
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
	
	public synchronized void addMessage(Trace message) {
		queue.addLast(message);
	}
	
	public synchronized Trace pollMessage() {
		return queue.poll();
	}
}
 