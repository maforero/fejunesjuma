package IntegrityMonitor;

import java.io.IOException;

import com.test.configuration.ConfigurationManager;
import com.test.configuration.Properties;
import com.test.monitoring.Trace;
import com.test.queue.QueueManager;
import com.test.secutiry.Hash;

public class IntegrityMonitor {
	
    private int TramaSize;
    private int HashSize;
    
    public IntegrityMonitor(){
    	HashSize = Integer.parseInt(ConfigurationManager.getInstance().getProperty(Properties.HASH_SIZE.name()));
    	TramaSize = Integer.parseInt(ConfigurationManager.getInstance().getProperty(Properties.PACKET_SIZE.name()));
    }
    
    public void validarMensaje(Trace trace){
    	desencriptarMensaje(trace);
    	
    }
    
    private void desencriptarMensaje(Trace trace){
    	byte[] datosEncriptados = trace.getData();
    	//AQUI SE DESENCRIPTA EL MENSAJE
    	Rsa rsa=new Rsa();
    	rsa.setValores();
    	byte[] datos=rsa.desencriptar(datosEncriptados);
    	
    	//AQUI SE SEPARA EL HASH Y LOS DATOS DE LA TRAMA
    	// 12 PRIMEROS BYTES DATOS
    	// 16 BYTES RESTANBTES = HASH
    	// TOTAL DE 28 BYTES SIN ENCRIPTAR
    	for (int i = 0; i < datos.length; i++) {
			System.out.print(datos[i]+" ");
		}
    	System.out.println();
    	
    	byte datosTrama[] = new byte[TramaSize];
    	byte hashTrama[] = new byte[HashSize];;
    	
    	datosTrama = java.util.Arrays.copyOfRange(datos, 0, datos.length - HashSize);
    	hashTrama = java.util.Arrays.copyOfRange(datos, datosTrama.length, datos.length);
    	
//    	for(int i=0; i < datos.length; i ++)
//    	{
//    		if(i < TramaSize){
//    			datosTrama[i] = datos[i]; 
//    		}
//    		else
//    		{
//    			hashTrama[i] = datos[i];
//    		}
//    	}
    	
    	//SE ENVIA UNICAMENTE LA TRAMA DE DATOS (SIN HASH)
    	byte hash[] = obtenerHash(datosTrama);
    	boolean valido = validarHash(hashTrama ,hash);
    	if(valido == true)
    	{
    		try {
    			trace.setData(datosTrama);
				sendPacket(trace);
			} catch (IOException e) {
				e.printStackTrace();
			}
    	}
    }
    
    private byte[] obtenerHash(byte[] datosTrama){
    	String trama = "";
    	for(int i=0; i < datosTrama.length; i ++)
    	{
    		trama += datosTrama[i];
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
    			break;
    		}
    	}
    	return valido;
    }
    
    private void sendPacket(Trace trace) throws IOException {
//        DatagramPacket enviarPaquete = new DatagramPacket(datos, datos.length, ip, port);
//        socket.send(enviarPaquete);
    	QueueManager.getInstance().addMessage(trace);
    }

}
