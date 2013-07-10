package com.test.frame.adapter;

/**
 * @class AbstractFrameAdapter.java
 * @author Felipe
 * @Date Jul 10, 2013
 * @since 1.0
 */
public abstract class AbstractFrameCreator {

	protected StringBuilder bitFrame; 
	private String frame;
	
	/**
	 * 
	 */
	public AbstractFrameCreator() {
		bitFrame = new StringBuilder();
	}
	
	public String getFrame() {
		return frame;
	}
	
	protected void createFrame() {
		StringBuilder frameBuilder = new StringBuilder();
		char[] bits = bitFrame.toString().toCharArray();
		for (int i = 0; i < bits.length; ) {
			String byteValue = "";
			if (((bits.length) - i) >= 7) {
				for (int j = 0; j < 7; j++) {
					byteValue += bits[i++];
				}
			} else {
				for (int j = 0; j <= ((bits.length) - i); j++) {
					byteValue += bits[i++];
				}
			}
			frameBuilder.append(Integer.parseInt(byteValue, 2));
			frameBuilder.append(";");
		}
		
		frame = frameBuilder.toString();
	}
	
	/**
	 * @param data
	 */
	protected void createBitFrame(int size, int value) {
		String bitValue = fillZeroBitValue(size, value);
		bitFrame.append(bitValue);
	}
	
	private String fillZeroBitValue(int size, int data) {
		String type = Integer.toBinaryString(data);
		int zeroLength = size - type.length();
		if (zeroLength > 0) {
			for (int i = 0; i < zeroLength; i++) {
				type = "0" + type;
			}
		}
		return type;
	}
}
