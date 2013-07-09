package com.test.frame;

/**
 * @class FrameDemultiplexerFactory.java
 * @author Felipe
 * @Date Jul 7, 2013
 * @since 1.0
 */
public class FrameDemultiplexerFactory {

	private static FrameDemultiplexerFactory instance = new FrameDemultiplexerFactory();

	private FrameDemultiplexerFactory() {
	}

	public static FrameDemultiplexerFactory getInstance() {
		return instance;
	}

	public FrameDemultiplexer getFrameDemultiplexer(byte data[]) {

		if (isAlarmFrame(data)) {
			return new AlarmFrameDemultiplexer();
		}
		if (isRegularFrame(data)) {
			return new RegularFrameDemultiplexer();
		}

		throw new DemultiplexerTypeNotFound();
	}

	/**
	 * @param data
	 * @return
	 */
	private boolean isRegularFrame(byte[] data) {
		return data[0] == 0;
	}

	/**
	 * @param data
	 * @return
	 */
	private boolean isAlarmFrame(byte[] data) {
		return data[0] == 1;
	}

	public static class DemultiplexerTypeNotFound extends RuntimeException {

		/**
		 * The serialVersionUID
		 */
		private static final long serialVersionUID = 375186584088298445L;

	}
}
