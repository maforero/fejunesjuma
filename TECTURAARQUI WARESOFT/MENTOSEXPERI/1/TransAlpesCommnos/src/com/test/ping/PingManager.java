package com.test.ping;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

import com.test.configuration.ConfigurationManager;
import com.test.configuration.Properties;

public class PingManager implements Runnable {

    private DatagramSocket socket;
    private boolean keepRunning;
    private InetAddress pingIp;
    private int pingPort;
    private int pingPortListener;

    public PingManager() {
        keepRunning = true;
        initPingProperties();
        startSocket();
    }

    private void initPingProperties() {
        ConfigurationManager configurationManager = ConfigurationManager.getInstance();
        String port = configurationManager.getProperty(Properties.PING_PORT.name());
        pingPort = Integer.parseInt(port);
        String pingIpConfig = configurationManager.getProperty(Properties.PING_IP.name());
        String pingPortListenerConfig = configurationManager.getProperty(Properties.PING_PORT_LISTENER.name());
        pingPortListener = Integer.parseInt(pingPortListenerConfig);
        try {
            pingIp = InetAddress.getByName(pingIpConfig);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }

    /**
     * @param port
     */
    private void startSocket() {
        try {
            socket = new DatagramSocket(pingPortListener);
        } catch (SocketException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        while (keepRunning) {
            byte values[] = new byte[1];
            DatagramPacket packet = new DatagramPacket(values, values.length);
            try {
                socket.receive(packet);
                sendPing();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void sendPing() throws IOException {
        byte values[] = new byte[1];
        DatagramPacket packet = new DatagramPacket(values, values.length, pingIp, pingPort);
        socket.send(packet);
    }

}
