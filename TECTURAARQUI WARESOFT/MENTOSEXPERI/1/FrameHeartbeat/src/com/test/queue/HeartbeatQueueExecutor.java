package com.test.queue;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

import com.test.configuration.ConfigurationManager;
import com.test.configuration.Properties;
import com.test.monitoring.Monitor;
import com.test.monitoring.Trace;

/**
 * @class LoadMessageProcessor.java
 * @author Felipe
 * @Date Jun 16, 2013
 * @since 1.0
 */
public class HeartbeatQueueExecutor implements QueueExecutor {

    private DatagramSocket socket;
    private InetAddress nodeIP;
    private int nodePort;
    private InetAddress balancerIP;
    private int balancerPort;
    private List<Object[]> pingModules;

    public HeartbeatQueueExecutor() {
        initBalancerNode();
        initNode();
        initSocket();
        initPingModules();
        sendHeartbeat(new byte[] { 1 });
    }

    private void initPingModules() {
        try {
            String modulesConfig = ConfigurationManager.getInstance().getProperty(Properties.MODULES_IP.name());
            String modules[] = modulesConfig.split(";");
            pingModules = new ArrayList<Object[]>(modules.length);
            for (String module : modules) {
                String values[] = module.split("-");
                Object moduleValues[] = new Object[2];
                moduleValues[0] = InetAddress.getByName(values[0]);
                moduleValues[1] = Integer.valueOf(values[1]);
                pingModules.add(moduleValues);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
	 * 
	 */
    private void initNode() {
        try {
            nodeIP = InetAddress.getByName(ConfigurationManager.getInstance().getProperty(Properties.NODE_1_IP.name()));
            nodePort = Integer.parseInt(ConfigurationManager.getInstance().getProperty(Properties.NODE_1_PORT.name()));
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }

    /**
	 * 
	 */
    private void initBalancerNode() {
        try {
            balancerIP = InetAddress.getByName(ConfigurationManager.getInstance().getProperty(Properties.BALANCER_IP.name()));
            balancerPort = Integer.parseInt(ConfigurationManager.getInstance().getProperty(Properties.BALANCER_PORT.name()));
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }

    /**
	 * 
	 */
    private void initSocket() {
        try {
            int pingPortListener = Integer.parseInt(ConfigurationManager.getInstance().getProperty(Properties.PING_PORT_LISTENER.name()));
            socket = new DatagramSocket(pingPortListener);
        } catch (SocketException e) {
            e.printStackTrace();
        }
    }

    /*
     * (non-Javadoc)
     * @see com.test.queue.QueueExecutor#execute(com.test.monitoring.Trace)
     */
    @Override
    public void execute(Trace trace) {
        byte data[] = { 1 };
        sendFrames(trace);
        trace.addTime(System.nanoTime());
        sendHeartbeat(data);
        writeTraces(trace.getData()[0]);
    }

    private void sendPing(byte[] data) throws IOException {
        for (Object module[] : pingModules) {
            boolean reSend;
            do {
                reSend = false;
                try {
                    DatagramPacket packet = new DatagramPacket(data, data.length, (InetAddress) module[0], (Integer) module[1]);
                    socket.send(packet);
                    socket.setSoTimeout(50);
                    socket.receive(packet);
                } catch (SocketTimeoutException e) {
                    reSend = true;
                }
            } while (reSend);
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

    /**
     * @param trace
     */
    private void sendFrames(Trace trace) {
        byte data[] = trace.getData();
        DatagramPacket packet = new DatagramPacket(data, data.length, nodeIP, nodePort);
        try {
            socket.send(packet);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * @param data
     * @throws IOException
     */
    private void sendHeartbeat(byte[] data) {
        DatagramPacket packet = new DatagramPacket(data, data.length, balancerIP, balancerPort);
        try {
            sendPing(data);
            socket.send(packet);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
