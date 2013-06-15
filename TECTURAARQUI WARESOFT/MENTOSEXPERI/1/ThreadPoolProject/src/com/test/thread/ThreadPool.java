package com.test.thread;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

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
	private static final int DEFAULT_NUMBER_OF_THREADS = 20;
	private static final int DEFAULT_POOL_SIZE = 20;
	
	private List<PoolThread> threads;
	private BlockingQueue<Runnable> threadQueue;

	public ThreadPool() {
		this(DEFAULT_NUMBER_OF_THREADS, DEFAULT_POOL_SIZE);
	}

	public ThreadPool(int numberOfThreads, int poolSize) {
		threadQueue = new LinkedBlockingQueue<Runnable>(poolSize);
		createThreads(numberOfThreads);
		startThreads();
	}

	public synchronized void execute(Runnable runnable) {
		threadQueue.add(runnable);
	}

	public synchronized void stop() {
		for (PoolThread thread : this.threads) {
			thread.stopThread();
		}
	}

	/**
	 * 
	 */
	private void startThreads() {
		for (PoolThread thread : threads) {
			thread.start();
		}
	}

	/**
	 * 
	 */
	private void createThreads(int numberOfThreads) {
		threads = new LinkedList<PoolThread>();
		for (int i = 0; i < numberOfThreads; i++) {
			threads.add(new PoolThread(threadQueue));
		}
	}
}
