package com.test.frame;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

import com.test.byteutil.ByteUtils;
import com.test.dto.FrameDTO;

/**
 * @class FrameSender.java
 * @author Felipe
 * @Date Jul 9, 2013
 * @since 1.0
 */
public class FrameSender {

	private InetAddress nodeIP;
	private int nodePort;
	private DatagramSocket socket;

	public FrameSender(InetAddress nodeIP, int nodePort) {
		initProperties(nodeIP, nodePort);
		startNode();
	}

	/**
	 * 
	 */
	public void sendFrame(FrameDTO frame) {
		ByteUtils byteUtils = ByteUtils.getInstance();
		byte serializedFrame[] = byteUtils.serialize(frame);
		serializedFrame = byteUtils.getByteWithEnding(serializedFrame);
		sendFrames(serializedFrame);
	}

	/**
	 * @param nodeIP
	 * @param nodePort
	 * @param frame
	 */
	private void initProperties(InetAddress nodeIP, int nodePort) {
		this.nodeIP = nodeIP;
		this.nodePort = nodePort;
	}

	/**
	 * 
	 */
	private void startNode() {
		initSocket();
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

	/**
	 * @param trace
	 */
	private void sendFrames(byte data[]) {
		DatagramPacket packet = new DatagramPacket(data, data.length, nodeIP,
				nodePort);
		try {
			socket.send(packet);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
