package Repartidor;

import java.net.InetAddress;

/**
 * @class Node.java
 * @author Felipe
 * @Date Jul 5, 2013
 * @since 1.0
 */
public class Node {

	private InetAddress ip;
	private int port;
	private int pingPort;
	private int echoPort;
	private boolean active;

	public Node(InetAddress ip, int port, int pingPort, int echoPort) {
		super();
		this.ip = ip;
		this.port = port;
		this.pingPort = pingPort;
		this.echoPort = echoPort;
	}

	/**
	 * @return the active
	 */
	public boolean isActive() {
		return active;
	}

	/**
	 * @param active
	 *            the active to set
	 */
	public void setActive(boolean active) {
		this.active = active;
	}

	/**
	 * @return the ip
	 */
	public InetAddress getIp() {
		return ip;
	}

	/**
	 * @return the port
	 */
	public int getPort() {
		return port;
	}

	/**
	 * @return the pingPort
	 */
	public int getPingPort() {
		return pingPort;
	}

	/**
	 * @return the echoPort
	 */
	public int getEchoPort() {
		return echoPort;
	}

}
