package com.test.monitoring;

/**
 * @class TransAlpesMonitor.java
 * @author Felipe
 * @Date Jun 16, 2013
 * @since 1.0
 */
public class TransAlpesMonitor {

	private static TransAlpesMonitor instance;
	private long[] traces;
	private byte[] data;
	private static int counter = 0;

	private TransAlpesMonitor() {
		traces = new long[2];
	}

	public static TransAlpesMonitor getInstance() {
		return new TransAlpesMonitor();
	}

	public void addInitTrace(long init) {
		traces[0] = init;
	}

	public void addEndTrace(long end) {
		traces[1] = end;
	}

	public void printTrace() {
//		if (counter == 0 || counter == 2000) {
//		System.out.println("start: " + traces[0]);
//		System.out.println("end: " + traces[1]);
//		System.out.println("Total: " + (traces[1] - traces[0]));
		System.out.println(counter++);
//		}
	}

	/**
	 * @return the data
	 */
	public byte[] getData() {
		return data;
	}

	/**
	 * @param data
	 *            the data to set
	 */
	public void setData(byte[] data) {
		this.data = data;
	}

}
