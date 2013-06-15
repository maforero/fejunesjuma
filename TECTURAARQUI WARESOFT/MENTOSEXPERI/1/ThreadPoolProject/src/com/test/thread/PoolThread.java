package com.test.thread;

import java.util.concurrent.BlockingQueue;

/**
 * @class PoolThread.java
 * @author Felipe
 * @Date Jun 14, 2013
 * @since 1.0
 */
public class PoolThread extends Thread {

	private BlockingQueue<Runnable> threadQueue;
	private boolean keepRunning;
	
	public PoolThread(BlockingQueue<Runnable> threadQueue) {
		this.threadQueue = threadQueue;
		keepRunning = true;
	}
	
	@Override
	public void run() {
		while (keepRunning) {
			Runnable runnable;
			try {
				runnable = threadQueue.take();
				runnable.run();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	public synchronized void stopThread() {
		keepRunning = false;
		this.interrupt();
	}
	
	public boolean isRunning() {
		return !keepRunning;
	}
}
