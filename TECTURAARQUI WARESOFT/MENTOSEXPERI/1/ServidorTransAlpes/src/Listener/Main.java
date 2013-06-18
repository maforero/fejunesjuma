package Listener;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

import Repartidor.Repartidor;

import com.test.configuration.ConfigurationManager;
import com.test.configuration.Properties;
import com.test.monitoring.Monitor;
import com.test.monitoring.Trace;

public class Main {

    private DatagramSocket socket;
    private Repartidor repartidor;

    public Main() {

        try {
            String port = ConfigurationManager.getInstance().getProperty(Properties.BALANCER_PORT.name());
            socket = new DatagramSocket(Integer.parseInt(port));
            repartidor = new Repartidor();
            while (true) {
                int packetSize = Integer.parseInt(ConfigurationManager.getInstance().getProperty(Properties.PACKET_SIZE.name()));
                byte datos[] = new byte[packetSize];
                DatagramPacket recibirPaquete = new DatagramPacket(datos, datos.length);
                socket.receive(recibirPaquete);
                byte[] data = recibirPaquete.getData();
                Trace trace = Trace.getInstance();
                trace.addInitTrace(System.nanoTime());
                repartidor.repartirMensaje(data);
                trace.addEndTrace(System.nanoTime());
                writeTraces(data[0]);
            }

        } catch (SocketException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * @param recibirPaquete
     */
    private void writeTraces(byte data) {
        if (data == 127) {
            Monitor.getInstance().printTraces();
        }
    }

    public static void main(String args[]) {
        if (args != null && args.length > 0) {
            ConfigurationManager.getInstance().loadProperties(args[0]);
        } else {
            ConfigurationManager.getInstance().loadProperties();
        }
        new Main();
    }
    
}
