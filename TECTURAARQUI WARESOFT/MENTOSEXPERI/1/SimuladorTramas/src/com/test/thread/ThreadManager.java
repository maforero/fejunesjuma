package com.test.thread;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

import com.test.configuration.ConfigurationManager;
import com.test.configuration.Properties;

/**
 * @class ThreadManager.java
 * @author Felipe
 * @Date Jun 18, 2013
 * @since 1.0
 */
public class ThreadManager {

	private static ThreadManager instance = new ThreadManager();
	private int poolSize;
	
	private ThreadManager(){
		loadConfigurations();
	}

	/**
	 * 
	 */
	private void loadConfigurations() {
		ConfigurationManager configurationManager = ConfigurationManager.getInstance();
		poolSize = Integer.parseInt(configurationManager.getProperty(Properties.POOL_SIZE.name()));
	}

	public static ThreadManager getInstance() {
		return instance;
	}
	
	public void loadFrames() {
		List<byte[]> frames = new LinkedList<byte[]>();
		loadFrames(frames);
		List<Thread> threads = new ArrayList<Thread>(poolSize);
		createThreads(frames, threads);
		startThreads(threads);
	}
	
	/**
	 * @param configurationManager
	 */
	private static void loadFrames(List<byte[]> frames) {
		String framesPath = ConfigurationManager.getInstance().getProperty(Properties.FRAMES_PATH.name());
		try {
			Scanner scanner = new Scanner(new File(framesPath));
			scanner.useDelimiter("\n");
			while (scanner.hasNext()) {
				String line = scanner.next();
				String values[] = line.split(";");
				byte frame[] = new byte[values.length];
				for (int i = 0; i < values.length; i++) {
					frame[i] = Byte.parseByte(values[i].trim());
				}
				frames.add(frame);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	/**
	 * @param runnable
	 * @param threads
	 */
	private void createThreads(List<byte[]> frames, List<Thread> threads) {
		for (int i = 0; i < poolSize; i++) {
			threads.add(new Thread(new ThreadExecutor(frames)));
		}
	}

	/**
	 * @param threads
	 */
	private void startThreads(List<Thread> threads) {
		for (Thread thread : threads) {
			thread.start();
		}
	}
}
