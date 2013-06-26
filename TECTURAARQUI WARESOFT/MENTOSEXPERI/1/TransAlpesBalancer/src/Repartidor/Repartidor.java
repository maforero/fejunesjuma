package Repartidor;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

import com.test.configuration.ConfigurationManager;
import com.test.configuration.Properties;

public class Repartidor {

    private int num;
    private DatagramSocket socket;
    private int port;
    private InetAddress ipNode1;
    private InetAddress ipNode2;

    public Repartidor() {
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

    public void repartirMensaje(byte[] datos) {

        try {
            if (num % 2 == 0) {
                sendPacket(datos, ipNode1);
            } else {
                sendPacket(datos, ipNode2);
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
