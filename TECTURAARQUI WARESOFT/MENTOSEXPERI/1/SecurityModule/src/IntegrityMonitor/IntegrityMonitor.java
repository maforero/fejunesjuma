package IntegrityMonitor;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

import com.test.configuration.ConfigurationManager;
import com.test.configuration.Properties;
import com.test.secutiry.Hash;

public class IntegrityMonitor {
	
    private DatagramSocket socket;
    private int port;
    private InetAddress ipNode1;
    private int TramaSize;
    private int HashSize;
    
    public IntegrityMonitor(){
    	HashSize = Integer.parseInt(ConfigurationManager.getInstance().getProperty(Properties.HASH_SIZE.name()));
    	TramaSize = Integer.parseInt(ConfigurationManager.getInstance().getProperty(Properties.PACKET_SIZE.name()));
    	port = Integer.parseInt(ConfigurationManager.getInstance().getProperty(Properties.NODE_PORT2.name()));
        try {
            ipNode1 = InetAddress.getByName(ConfigurationManager.getInstance().getProperty(Properties.NODE_1_IP.name()));
            socket = new DatagramSocket();
        } catch (SocketException e) {
            e.printStackTrace();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }
    
    public void validarMensaje(byte[] datos){
    	desencriptarMensaje(datos);
    	
    }
    
    private void desencriptarMensaje(byte[] datos){
    	//AQUI SE DESENCRIPTA EL MENSAJE
    	
    	
    	//AQUI SE SEPARA EL HASH Y LOS DATOS DE LA TRAMA
    	// 12 PRIMEROS BYTES DATOS
    	// 16 BYTES RESTANBTES = HASH
    	// TOTAL DE 28 BYTES SIN ENCRIPTAR
    	
    	
    	byte datosTrama[] = new byte[TramaSize];
    	byte hashTrama[] = new byte[HashSize];;
    	
    	for(int i=0; i < datos.length; i ++)
    	{
    		if(i < TramaSize){
    			datosTrama[i] = datos[i]; 
    		}
    		else
    		{
    			hashTrama[i] = datos[i];
    		}
    	}
    	
    	//SE ENVIA UNICAMENTE LA TRAMA DE DATOS (SIN HASH)
    	byte hash[] = obtenerHash(datosTrama);
    	boolean valido = validarHash(hashTrama ,hash);
    	if(valido == true)
    	{
    		try {
				sendPacket(datosTrama,ipNode1);
			} catch (IOException e) {
				e.printStackTrace();
			}
    	}
    }
    
    private byte[] obtenerHash(byte[] datosTrama){
    	String trama = "";
    	for(int i=0; i < datosTrama.length; i ++)
    	{
    		trama += (char)datosTrama[i];
    		if(i != datosTrama.length -1){
    			trama += ";";
    		}
    	}
    	
    	byte hash[] = Hash.GenerateHashMD5(trama); 	
    	return hash;
    }
    
    private boolean validarHash(byte[] hashTrama, byte[] hash){
    	boolean valido = true;
    	for(int i=0; i < hash.length; i ++)
    	{
    		if(hashTrama[i] != hash[i]){
    			valido = false;
    		}
    	}
    	return valido;
    }
    
    private void sendPacket(byte[] datos, InetAddress ip) throws IOException {
        DatagramPacket enviarPaquete = new DatagramPacket(datos, datos.length, ip, port);
        socket.send(enviarPaquete);
    }

}
