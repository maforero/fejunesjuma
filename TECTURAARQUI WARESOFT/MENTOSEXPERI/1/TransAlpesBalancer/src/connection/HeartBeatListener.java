package connection;

import Repartidor.DispatcherQueue;
import Repartidor.Node;

public class HeartBeatListener implements Runnable {

	private DispatcherQueue<Node> dispatcherQueue;

	public HeartBeatListener(DispatcherQueue<Node> dispatcherQueue) {
		this.dispatcherQueue = dispatcherQueue;
	}

	@Override
	public void run() {
		for (Node node : dispatcherQueue.getValues()) {
			new Thread(new NodeMonitor(node)).start();
		}
	}

}
