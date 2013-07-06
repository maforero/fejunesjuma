package connection;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.net.SocketTimeoutException;

import Repartidor.Node;

class NodeMonitor implements Runnable {

	/**
	 * The NodeMonitor
	 */
	private DatagramPacket packet;
	private Node node;
	private DatagramSocket socket;

	public NodeMonitor(Node node) {
		this.node = node;
		startSocket();
		createPacket(node);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {
		byte[] pingData = new byte[] { 1 };
		DatagramPacket packet = new DatagramPacket(pingData, pingData.length);
		while (true) {
			sendPing(packet);
		}
	}

	/**
	 * @param port
	 */
	private void startSocket() {
		try {
			socket = new DatagramSocket(node.getEchoPort());
			socket.setSoTimeout(5);
		} catch (SocketException e) {
			e.printStackTrace();
		}
	}

	/**
	 * @param node
	 */
	private void createPacket(Node node) {
		byte pingData[] = { 1 };
		this.packet = new DatagramPacket(pingData, pingData.length,
				node.getIp(), node.getPingPort());
	}

	/**
	 * @param pingPacket
	 * 
	 */
	private void sendPing(DatagramPacket pingPacket) {
		try {
			socket.send(packet);
			socket.receive(pingPacket);
			node.setActive(true);
		} catch (SocketTimeoutException e) {
			node.setActive(false);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}