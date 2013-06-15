import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;


public class UDPCallGenerator {

	public void initSocket(String machine, String ...messages) throws SocketException, UnknownHostException{
		//Se crea un socket
		 DatagramSocket socket = new DatagramSocket(); 
		 InetAddress machineAddress = InetAddress.getByName(machine);
		 
		for (String message : messages) {
			 byte [] chain = message.getBytes(); 
			 DatagramPacket frame = new DatagramPacket(chain, message.length(), machineAddress, 9999);
			 try {
				socket.send(frame);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		socket.close();
		
	}
	
	
	public static void main(String args[]){
		try {
			String messages[]={"te amo","copito","ardilla es fea","angarita sempai","procciutto","matrimonio","adilla triste","Prici","San Francisco","Odio a Gladys","Juan Pedro sapo"};
			new UDPCallGenerator().initSocket("192.168.0.13", messages);
		} catch (SocketException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
}
