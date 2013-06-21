package com.test.thread;

import java.util.concurrent.BlockingQueue;

import com.test.dto.RegularFrameDTO;
import com.test.frame.FrameDemultiplexer;
import com.test.module.GenericModule;
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
	private FrameDemultiplexer demultiplexer;
	private GenericModule genericModule;

	public Worker(BlockingQueue<Trace> threadQueue) {
		this.threadQueue = threadQueue;
		keepRunning = true;
		demultiplexer = new FrameDemultiplexer();
		genericModule = new GenericModule();
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

	/**
	 * @param data
	 */
	private void demultiplexData(Trace monitor) {
		RegularFrameDTO frame = demultiplexer.demultiplexFrame(monitor);
		genericModule.doSomething(frame);
	}

	public synchronized void stopThread() {
		keepRunning = false;
		this.interrupt();
	}

	public boolean isRunning() {
		return keepRunning;
	}
}
