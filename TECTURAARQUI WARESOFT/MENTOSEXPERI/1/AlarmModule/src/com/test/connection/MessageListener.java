package com.test.connection;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

import com.test.dto.AlarmFrameDTO;
import com.test.frame.FrameDemultiplexer;
import com.test.module.AlarmModule;


/**
 * @class MessageListener.java
 * @author Felipe
 * @Date Jun 14, 2013
 * @since 1.0
 */
public class MessageListener implements Runnable {

	private DatagramSocket socket;
	private boolean keepRunning;
	private FrameDemultiplexer frmDemultiplexer;
	private AlarmModule alarmModule;

	public MessageListener() {
		keepRunning = true;
		try {
			socket = new DatagramSocket(911);
			socket.setReceiveBufferSize(1000000);
			frmDemultiplexer = new FrameDemultiplexer();
			alarmModule = new AlarmModule();
		} catch (SocketException e) {
			e.printStackTrace();
		}
	}

	public void run() {
		while (keepRunning) {
			byte values[] = new byte[12];
			DatagramPacket packet = new DatagramPacket(values, values.length);
			try {
				socket.receive(packet);
				byte[] data = packet.getData();
				AlarmFrameDTO alarmDTO= frmDemultiplexer.demultiplexAlarmFrame(data);
				alarmModule.doSomething(alarmDTO);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
