package com.test.frame;

/**
 * @class AbstractFrameAdapter.java
 * @author Felipe
 * @Date Jul 10, 2013
 * @since 1.0
 */
public abstract class AbstractFrameAdapter {

	private String frame; 
	private int actualIndex;
	
	/**
	 * @param data
	 */
	protected void createBitFrame(byte[] data) {
		StringBuilder frame = new StringBuilder();
		for (byte b : data) {
			frame.append(fillZeroBitValue(b));
		}
		this.frame = frame.toString();
	}
	
	protected int getFrameBitValue(int size) {
		int initIndex = actualIndex;
		actualIndex = actualIndex + size;
		int value = Integer.parseInt(frame.substring(initIndex, actualIndex), 2);
		
		return value;
	}
	
	/**
	 * @param data
	 * @return
	 */
	private String fillZeroBitValue(byte data) {
		String type = Integer.toBinaryString(data);
		int zeroLength = 7 - type.length();
		if (zeroLength > 0) {
			for (int i = 0; i < zeroLength; i++) {
				type = "0" + type;
			}
		}
		return type;
	}
}
