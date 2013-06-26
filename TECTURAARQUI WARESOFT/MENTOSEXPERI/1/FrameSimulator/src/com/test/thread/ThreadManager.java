package com.test.thread;

import java.io.File;
import java.io.FileNotFoundException;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

import com.test.configuration.ConfigurationManager;
import com.test.configuration.Properties;
import com.test.secutiry.Hash;

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
			RsaEncriptando rsa=new RsaEncriptando();
			rsa.clave();
			Scanner scanner = new Scanner(new File(framesPath));
			scanner.useDelimiter("\n");
			while (scanner.hasNext()) {
				String line = scanner.next();
				
				// GENERA EL HASH DE LA TRAMA EN MD5
				// EL HASH ESTA EN TERMINOS DE BYTES PARA ADICIONARLO AL FRAME
				// LA TRAMA QUEDA DE 28 BYTES SIN ENCRIPTAR
				byte hash[] = Hash.GenerateHashMD5(line);
				
										
				String values[] = line.split(";");
				int longitud = values.length + hash.length;				
				byte frame[] = new byte[longitud]; // -->  byte frame[] = new byte[values.length];
				for (int i = 0; i < values.length; i++) {
					frame[i] = Byte.parseByte(values[i].trim());
				}
				
				//SE AGREGAN LOS BYTES DEL HASH AL FINAL DE LA TRAMA
				for (int i = 0; i < hash.length; i++)
				{
					int pos = values.length + i;
					frame[pos] = hash[i];
				}
				
				//SE AGREGA LA TRAMA ->  AUN FALTA ENCRIPTARLA
				frames.add(rsa.encriptar(frame));
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
