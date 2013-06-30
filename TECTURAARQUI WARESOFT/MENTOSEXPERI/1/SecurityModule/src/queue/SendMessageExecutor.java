package queue;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

import com.test.configuration.ConfigurationManager;
import com.test.configuration.Properties;
import com.test.monitoring.Trace;
import com.test.queue.QueueExecutor;

/**
 * @class LoadMessageProcessor.java
 * @author Felipe
 * @Date Jun 16, 2013
 * @since 1.0
 */
public class SendMessageExecutor implements QueueExecutor {

	private DatagramSocket socket;
	private InetAddress nodeIP;
	private int nodePort;

	public SendMessageExecutor() {
		initNode();
		initSocket();
	}

	/**
	 * 
	 */
	private void initNode() {
		try {
			nodeIP = InetAddress.getByName(ConfigurationManager.getInstance()
					.getProperty(Properties.NODE_1_IP.name()));
			nodePort = Integer.parseInt(ConfigurationManager.getInstance()
					.getProperty(Properties.NODE_1_PORT.name()));
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
	}


	/**
	 * 
	 */
	private void initSocket() {
		try {
			socket = new DatagramSocket();
		} catch (SocketException e) {
			e.printStackTrace();
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.test.queue.QueueExecutor#execute(com.test.monitoring.Trace)
	 */
	@Override
	public void execute(Trace trace) {
		sendFrames(trace);
	}

	/**
	 * @param trace
	 */
	private void sendFrames(Trace trace) {
		byte data[] = trace.getData();
		DatagramPacket packet = new DatagramPacket(data, data.length, nodeIP,
				nodePort);
		try {
			socket.send(packet);
			trace.addTime(System.nanoTime());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
