package com.test.thread;

import java.util.Arrays;
import java.util.concurrent.BlockingQueue;

import com.test.frame.FrameDemultiplexer;
import com.test.frame.FrameDemultiplexerFactory;
import com.test.monitoring.Trace;

/**
 * @class PoolThread.java
 * @author Felipe
 * @Date Jun 14, 2013
 * @since 1.0
 */
public class Worker extends Thread {

	private BlockingQueue<Trace> threadQueue;
	private boolean keepRunning;

	public Worker(BlockingQueue<Trace> threadQueue) {
		this.threadQueue = threadQueue;
		keepRunning = true;
	}

	@Override
	public void run() {
		while (keepRunning) {
			Trace monitor;
			try {
				monitor = threadQueue.take();
				demultiplexData(monitor);
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
		return keepRunning;
	}

	/**
	 * @param data
	 */
	private void demultiplexData(Trace monitor) {
		FrameDemultiplexerFactory factory = FrameDemultiplexerFactory
				.getInstance();
		byte[] data = monitor.getData();
		byte[] realData = getDataWithoutFrameType(data);
		FrameDemultiplexer demultiplexer = factory.getFrameDemultiplexer(realData);
		demultiplexer.demultiplex(data);
	}

	/**
	 * @param data
	 * @return
	 */
	private byte[] getDataWithoutFrameType(byte[] data) {
		byte realData[] = Arrays.copyOfRange(data, 1, data.length);
		return realData;
	}

}
