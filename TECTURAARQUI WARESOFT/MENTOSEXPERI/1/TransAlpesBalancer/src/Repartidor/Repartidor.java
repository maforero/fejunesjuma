package Repartidor;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.HashMap;

import com.test.configuration.ConfigurationManager;
import com.test.configuration.Properties;
import com.test.monitoring.Trace;
import com.test.queue.QueueManager;

public class Repartidor {

    private int num;
    private DatagramSocket socket;
    private int port;
    private InetAddress ipNode1;
    private InetAddress ipNode2;
    private HashMap<String,Long> lastBeatMessage;

    public Repartidor(HashMap<String,Long> lastBeatMessage) {
    	this.lastBeatMessage = lastBeatMessage;
        num = 1;
        port = Integer.parseInt(ConfigurationManager.getInstance().getProperty(Properties.NODE_PORT.name()));
        try {
            ipNode1 = InetAddress.getByName(ConfigurationManager.getInstance().getProperty(Properties.NODE_1_IP.name()));
            ipNode2 = InetAddress.getByName(ConfigurationManager.getInstance().getProperty(Properties.NODE_2_IP.name()));
            socket = new DatagramSocket();
        } catch (SocketException e) {
            e.printStackTrace();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }

    public void repartirMensaje(Trace trace) {
    	byte[] datos = trace.getData();
    	InetAddress node1Active = null;
    	InetAddress node2Active = null;
        try {
        	if (lastBeatMessage.get(ipNode1.getHostAddress()) != null && lastBeatMessage.get(ipNode2.getHostAddress()) != null) {
        		node1Active = ipNode1;
        		node2Active = ipNode2;
			} else if (lastBeatMessage.get(ipNode1.getHostAddress()) != null) {
				node1Active = ipNode1;
				node2Active = ipNode1;
			} else if (lastBeatMessage.get(ipNode2.getHostAddress()) != null) {
				node1Active = ipNode2;
				node2Active = ipNode2;
			} else {
				QueueManager.getInstance().addMessage(trace);
				return;
			}
        	
            if (num % 2 == 0) {
                sendPacket(datos, node1Active);
            } else {
                sendPacket(datos, node2Active);
            }
            num++;

        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void sendPacket(byte[] datos, InetAddress ip) throws IOException {
        DatagramPacket enviarPaquete = new DatagramPacket(datos, datos.length, ip, port);
        socket.send(enviarPaquete);
    }

}
