package com.test.thread;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import com.test.monitoring.Trace;

/**
 * @class ThreadPool.java
 * @author Felipe
 * @Date Jun 14, 2013
 * @since 1.0
 */
public class ThreadPool {

	/**
	 * The DEFAULT_NUMBER_OF_THREADS 
	 */
	private static final int DEFAULT_NUMBER_OF_THREADS = 30;
	private static final int DEFAULT_POOL_SIZE = 30;
	
	private List<Worker> workers;
	private BlockingQueue<Trace> threadQueue;

	public ThreadPool() {
		this(DEFAULT_NUMBER_OF_THREADS, DEFAULT_POOL_SIZE);
	}

	public ThreadPool(int numberOfThreads, int poolSize) {
		threadQueue = new LinkedBlockingQueue<Trace>(poolSize);
		createThreads(numberOfThreads);
		startThreads();
	}

	public synchronized void execute(Trace monitor) {
		if (threadQueue.remainingCapacity() > 0) {
			threadQueue.add(monitor);
		}
	}

	public synchronized void stop() {
		for (Worker thread : this.workers) {
			thread.stopThread();
		}
	}

	/**
	 * 
	 */
	private void startThreads() {
		for (Worker thread : workers) {
			thread.start();
		}
	}

	/**
	 * 
	 */
	private void createThreads(int numberOfThreads) {
		workers = new LinkedList<Worker>();
		for (int i = 0; i < numberOfThreads; i++) {
			workers.add(new Worker(threadQueue));
		}
	}
}
