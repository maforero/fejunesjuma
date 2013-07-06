package Repartidor;

import java.net.InetAddress;
import java.net.UnknownHostException;

import com.test.configuration.ConfigurationManager;
import com.test.configuration.Properties;

/**
 * @class DispatcherBuilder.java
 * @author Felipe
 * @Date Jul 5, 2013
 * @since 1.0
 */
public class DispatcherBuilder {

	private DispatcherQueue<Node> nodeQueue;

	private DispatcherBuilder() {
		nodeQueue = new NodeQueue();
	}

	public static DispatcherBuilder createInstance() {
		return new DispatcherBuilder();
	}

	public void buildDispatcherNode() {
		try {
			loadDispatcherQueue();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
	}

	public DispatcherQueue<Node> getDispatcherQueue() {
		return nodeQueue;
	}

	/**
	 * @return
	 * @throws UnknownHostException
	 */
	private void loadDispatcherQueue() throws UnknownHostException {
		String[] nodes = getNodes();
		createNodes(nodes);
	}

	/**
	 * @param nodesProperty
	 * @return
	 */
	private String[] getNodes() {
		String nodesProperty = ConfigurationManager.getInstance().getProperty(
				Properties.NODES.name());
		String nodes[] = nodesProperty.split(";");
		return nodes;
	}

	/**
	 * @param nodes
	 * @throws UnknownHostException
	 */
	private void createNodes(String[] nodes) throws UnknownHostException {
		for (String node : nodes) {
			String nodeParts[] = node.split(":");
			nodeQueue.add(createNode(nodeParts[0], nodeParts[1], nodeParts[2],
					nodeParts[3]));
		}
	}

	private Node createNode(String ip, String port, String pingPort,
			String echoPort) throws UnknownHostException {
		InetAddress nodeIp = InetAddress.getByName(ip);
		int nodePort = Integer.parseInt(port);
		int nodePingPort = Integer.parseInt(pingPort);
		int nodeEchoPort = Integer.parseInt(echoPort);
		Node node = new Node(nodeIp, nodePort, nodePingPort, nodeEchoPort);
		return node;
	}

}
