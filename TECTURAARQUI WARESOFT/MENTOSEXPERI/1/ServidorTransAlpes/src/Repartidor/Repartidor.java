package Repartidor;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

public class Repartidor {

	private int num;
	private DatagramSocket socket;
	
	public Repartidor(){
		num=1;
		try {
			socket=new DatagramSocket();
		} catch (SocketException e) {
			e.printStackTrace();
		}
	}
	
	
	public void repartirMensaje(String message) throws SocketException {
		
		byte datos[]=message.getBytes();
		socket.setSendBufferSize(100000);
		
		try {
			if(num%2==0){
			DatagramPacket enviarPaquete=new DatagramPacket(datos,datos.length,InetAddress.getByName("157.253.226.55"),9999);
			socket.send(enviarPaquete);
			}
			else{
				
			DatagramPacket enviarPaquete=new DatagramPacket(datos,datos.length,InetAddress.getByName("157.253.227.40"),9999);
			socket.send(enviarPaquete);
			}
			num++;
			
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
	}

}
