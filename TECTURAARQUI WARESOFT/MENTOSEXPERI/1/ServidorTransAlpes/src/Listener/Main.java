package Listener;

import com.test.configuration.ConfigurationManager;
import com.test.configuration.Properties;
import com.test.connection.HeartBeatListener;
import com.test.connection.MessageListener;
import com.test.queue.QueueMonitor;

public class Main {
//
//    private DatagramSocket socket;
//    private Repartidor repartidor;
//
//    public Main() {
//
//        try {
//            String port = ConfigurationManager.getInstance().getProperty(Properties.BALANCER_PORT.name());
//            socket = new DatagramSocket(Integer.parseInt(port));
//            repartidor = new Repartidor();
//            while (true) {
//                int packetSize = Integer.parseInt(ConfigurationManager.getInstance().getProperty(Properties.PACKET_SIZE.name()));
//                byte datos[] = new byte[packetSize];
//                DatagramPacket recibirPaquete = new DatagramPacket(datos, datos.length);
//                socket.receive(recibirPaquete);
//                byte[] data = recibirPaquete.getData();
//                Trace trace = Trace.getInstance();
//                trace.addInitTrace(System.nanoTime());
//                repartidor.repartirMensaje(data);
//                trace.addEndTrace(System.nanoTime());
//                writeTraces(data[0]);
//            }
//
//        } catch (SocketException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//    }
    
//    /**
//     * @param recibirPaquete
//     */
//    private void writeTraces(byte data) {
//        if (data == 127) {
//            Monitor.getInstance().printTraces();
//        }
//    }

    public static void main(String args[]) {
    	loadConfigurations(args);
		startQueueMonitor();
		startMessageListener();
		startHeartBeatListener();
//        new Main();
    }
    
    /**
	 * It starts the hearbeatlistener Thread
	 */
	private static void startHeartBeatListener() {
		String port = ConfigurationManager.getInstance().getProperty(
				Properties.HEARTBEAT_PORT.name());
		Thread heartBeatListener = new Thread(new HeartBeatListener(
				Integer.valueOf(port)));
		heartBeatListener.start();
	}
    
    /**
	 * 
	 */
	private static void startMessageListener() {
		String port = ConfigurationManager.getInstance().getProperty(
				Properties.BALANCER_PORT.name());
		Thread messageListener = new Thread(new MessageListener(
				Integer.valueOf(port)));
		messageListener.start();
	}

	/**
	 * 
	 */
	private static void startQueueMonitor() {
		QueueMonitor queueMonitor = new QueueMonitor();
		Thread queueMonitorThread = new Thread(queueMonitor);
		queueMonitorThread.start();
	}

	/**
	 * 
	 */
	private static void loadConfigurations(String[] args) {
	    if (args != null && args.length > 0) {
            ConfigurationManager.getInstance().loadProperties(args[0]);
        } else {
            ConfigurationManager.getInstance().loadProperties();
        }
	}
    
}
