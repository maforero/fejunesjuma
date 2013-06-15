package com.test.thread;

import java.util.concurrent.BlockingQueue;

import com.test.dto.RegularFrameDTO;
import com.test.frame.FrameDemultiplexer;

/**
 * @class PoolThread.java
 * @author Felipe
 * @Date Jun 14, 2013
 * @since 1.0
 */
public class PoolThread extends Thread {

	private BlockingQueue<byte[]> threadQueue;
	private boolean keepRunning;
	private FrameDemultiplexer demultiplexer;
	private static int count;
	
	public PoolThread(BlockingQueue<byte[]> threadQueue) {
		this.threadQueue = threadQueue;
		keepRunning = true;
		demultiplexer = new FrameDemultiplexer();
	}
	
	@Override
	public void run() {
		while (keepRunning) {
			byte data[];
			try {
				data = threadQueue.take();
				demultiplexData(data);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * @param data
	 */
	private void demultiplexData(byte[] data) {
		RegularFrameDTO frame = demultiplexer.demultiplexFrame(data);
//		System.out.println(new String(data) + " " + count++);
	}

	public synchronized void stopThread() {
		keepRunning = false;
		this.interrupt();
	}
	
	public boolean isRunning() {
		return !keepRunning;
	}
}
