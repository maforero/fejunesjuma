package Repartidor;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * @class NodeQueue.java
 * @author Felipe
 * @Date Jul 5, 2013
 * @since 1.0
 */
public class NodeQueue implements DispatcherQueue<Node> {

	private LinkedList<Node> queue;

	public NodeQueue() {
		queue = new LinkedList<Node>();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see Repartidor.DispatcherQueue#add()
	 */
	@Override
	public void add(Node node) {
		queue.add(node);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see Repartidor.DispatcherQueue#poll()
	 */
	@Override
	public Node poll() {
		return getActiveNode();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see Repartidor.DispatcherQueue#isEmtpy()
	 */
	@Override
	public boolean isEmtpy() {
		return queue.isEmpty();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see Repartidor.DispatcherQueue#getSize()
	 */
	@Override
	public int getSize() {
		return queue.size();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see Repartidor.DispatcherQueue#hasActiveNodes()
	 */
	@Override
	public boolean hasActiveNodes() {
		return getActiveNode() != null;
	}

	/**
	 * @return
	 */
	private Node getActiveNode() {
		for (Node node : queue) {
			if (node.isActive()) {
				return node;
			}
		}

		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see Repartidor.DispatcherQueue#getValues()
	 */
	@Override
	public List<Node> getValues() {
		return Collections.unmodifiableList(queue);
	}

}
