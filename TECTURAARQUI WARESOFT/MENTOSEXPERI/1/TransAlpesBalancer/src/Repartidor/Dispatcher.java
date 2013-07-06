package Repartidor;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

import com.test.monitoring.Trace;
import com.test.queue.QueueManager;

public class Dispatcher {

	private DatagramSocket socket;
	private DispatcherQueue<Node> dispatcherNode;
	static int counter;

	public Dispatcher(DispatcherQueue<Node> dispatcherQueue) {
		this.dispatcherNode = dispatcherQueue;
		createSocket();
	}

	public void repartirMensaje(Trace trace) {
		byte[] datos = trace.getData();
		try {
			sendDataToNextNode(datos);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (NoActiveNodeException e) {
			QueueManager.getInstance().addMessage(trace);
		}
	}

	/**
	 * @param datos
	 * @throws IOException
	 */
	private void sendDataToNextNode(byte[] datos) throws IOException {
		Node node = getNextNode();
		sendPacket(datos, node);
	}

	/**
	 * 
	 */
	private void createSocket() {
		try {
			socket = new DatagramSocket();
		} catch (SocketException e) {
			e.printStackTrace();
		}
	}

	/**
	 * @return
	 */
	private Node getNextNode() {
		Node node = dispatcherNode.poll();
		
		if (node == null) {
			throw new NoActiveNodeException();
		}

		dispatcherNode.add(node);
		return node;
	}

	private void sendPacket(byte[] datos, Node node) throws IOException {
		DatagramPacket enviarPaquete = new DatagramPacket(datos, datos.length,
				node.getIp(), node.getPort());
		socket.send(enviarPaquete);
		System.out.println(node.getIp()+" "+node.getPort()+" "+counter++);
	}

	private class NoActiveNodeException extends RuntimeException {

	}
}
