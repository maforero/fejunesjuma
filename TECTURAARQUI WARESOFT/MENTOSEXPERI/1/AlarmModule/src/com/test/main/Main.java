package com.test.main;

import com.test.connection.MessageListener;

/**
 * @class Main.java
 * @author Felipe
 * @Date Jun 14, 2013
 * @since 1.0
 */
public class Main {

	public static void main(String[] args) {
				
		Thread messageListener = new Thread(new MessageListener());
		messageListener.start();
	}
}
