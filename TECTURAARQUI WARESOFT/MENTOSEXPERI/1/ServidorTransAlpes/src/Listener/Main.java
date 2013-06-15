package Listener;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

import Repartidor.Repartidor;


public class Main implements Runnable{

	private DatagramSocket socket;
	private Repartidor repartidor;
	private static int count;
	
	public Main(DatagramSocket s){
		this.socket=s;
		repartidor=new Repartidor();
		
	}
	
	
	public static void main(String args[]){
		try {
			DatagramSocket socket1 = new DatagramSocket(5000);
			socket1.setReceiveBufferSize(100000);

		Main a=	new Main(socket1);
		Thread tMain=new Thread(a,"a");
		
		Main b=	new Main(socket1);
		Thread tbMain=new Thread(b,"b");
		Main c=	new Main(socket1);
		Thread tcMain=new Thread(c,"c");
		Main d=	new Main(socket1);
		Thread tdMain=new Thread(d,"d");
		tMain.start();
//		tbMain.start();
//		tcMain.start();
//		tdMain.start();
		} catch (SocketException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}


	
	public void run() {
		try {

			
			while(true){
				byte datos[]=new byte[10];
				DatagramPacket recibirPaquete=new DatagramPacket(datos,datos.length);
				socket.receive(recibirPaquete);
				System.out.println(new String(recibirPaquete.getData())+Thread.currentThread().getName()+" "+count++);
				String message = new String(recibirPaquete.getData());
				repartidor.repartirMensaje(message);
			}
			
		} catch (SocketException e) {
			
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
	}
}
