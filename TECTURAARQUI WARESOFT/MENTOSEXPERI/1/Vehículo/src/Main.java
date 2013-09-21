import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;


public class Main{
	private DatagramSocket socket;
	
	
	public Main(){
		
		BufferedReader buff;
		String line;
		try {
			buff=new BufferedReader(new FileReader("datos.txt"));
			socket=new DatagramSocket();
			int i = 0;
			long init = System.nanoTime();
//			while(i < 500){
				i++;
//				byte datos[]= {20, 41, 18, 35, 2, 36, 72, 64, 72, 36, 9, 2};
				byte datos[]= {127, 41, 18, 35, 2, 36, 72, 64, 72, 36, 9, 2};
//				DatagramPacket enviarPaquete=new DatagramPacket(datos,datos.length,InetAddress.getByName("192.168.0.19"),9999);
				DatagramPacket enviarPaquete=new DatagramPacket(datos,datos.length,InetAddress.getByName("192.168.0.12"),9999);
//				DatagramPacket enviarPaquete=new DatagramPacket(datos,datos.length,InetAddress.getByName("192.168.0.17"),5000);
//				DatagramPacket enviarPaquete=new DatagramPacket(datos,datos.length,InetAddress.getByName("192.168.0.15"),911);
				socket.send(enviarPaquete);
//				try {
//					Thread.currentThread().sleep(100);
//				} catch (InterruptedException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//			}
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	public static void main(String arg[]){
		new Main();
	}
}