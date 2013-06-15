package com.test.queue;

import java.util.LinkedList;

/**
 * @class QueueManager.java
 * @author Felipe
 * @Date Jun 14, 2013
 * @since 1.0
 */
public class QueueManager {

	private static QueueManager instance;
	private LinkedList<String> queue;
	
	private QueueManager() {
		queue = new LinkedList<String>();
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
	
	public synchronized void addMessage(String message) {
		queue.addLast(message);
	}
	
	public synchronized String pollMessage() {
		return queue.poll();
	}
}
 