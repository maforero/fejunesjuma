package com.test.thread;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.PublicKey;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class RsaEncriptando {

	private long e;
	private long n;
	public static final String ALGORITHM = "RSA";
	public static final String PUBLIC_KEY_FILE = "public.key";
	private static PublicKey key;
	static String encryptionKey = "0123456789abcdef";
	 static String IV = "AAAAAAAAAAAAAAAA";
	
	public void clave() {
		e = 11;
		n = 239117;
		ObjectInputStream inputStream = null;
		try {
			inputStream = new ObjectInputStream(new FileInputStream(PUBLIC_KEY_FILE));
			key = (PublicKey) inputStream.readObject();
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	      
	}
	  public static byte[] encriptar(byte[] plainText)  {
		    Cipher cipher;
			try {
				cipher = Cipher.getInstance("AES/CBC/NoPadding", "SunJCE");
			    SecretKeySpec key = new SecretKeySpec(encryptionKey.getBytes("UTF-8"), "AES");
			    cipher.init(Cipher.ENCRYPT_MODE, key,new IvParameterSpec(IV.getBytes("UTF-8")));
			    return cipher.doFinal(plainText);
			} catch (NoSuchAlgorithmException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (NoSuchProviderException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (NoSuchPaddingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InvalidKeyException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InvalidAlgorithmParameterException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalBlockSizeException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (BadPaddingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return plainText;

		   
		  }
	  
	  public static byte[] encriptar3(byte[] frame) {
		    byte[] cipherText = null;
		    try {
		      // get an RSA cipher object and print the provider
		      final Cipher cipher = Cipher.getInstance(ALGORITHM);
		      // encrypt the plain text using the public key
		      cipher.init(Cipher.ENCRYPT_MODE, key);
		      cipherText = cipher.doFinal(frame);
		    } catch (Exception e) {
		      e.printStackTrace();
		    }
		    return cipherText;
		  }
	public byte[] encriptar2(byte[] frame) {

		byte[] enc = new byte[frame.length*4];

		BigInteger c, c1, l, en;
		en = new BigInteger(n + "");

		for (int i = 0,j=0; i < frame.length; i++,j+=4) {
			byte[] aux=new byte[4];
			c1 = new BigInteger(frame[i] + "");
			c = c1.pow((int) e);
			l = c.mod(en);
			//System.out.println(l);
			aux=toBytes(Integer.parseInt(l.toString()));
			enc[j] =aux[0] ;
			enc[j+1] =aux[1] ;
			enc[j+2] =aux[2] ;
			enc[j+3] =aux[3] ;

		}
//			imprimir(enc);
		return enc;
	}
	public void imprimir(byte[] enc){
		for (int i = 0; i < enc.length-3; i+=4) {
			System.out.print((enc[i+3]& 0xFF)|((enc[i+2]& 0xFF)<<8)|((enc[i+1]& 0xFF)<<16)|((enc[i]& 0xFF)<<24));
			System.out.println();
		}
	}
	public byte[] toBytes(int i)
	{
		  byte[] resultado = new byte[4];

		  resultado[0] = (byte) ((i >> 24) & 0xFF);
		  resultado[1] = (byte) ((i >> 16)& 0xFF);
		  resultado[2] = (byte) ((i >> 8)& 0xFF);
		  resultado[3] = (byte) (i & 0xFF/*>> 0*/);

		  return resultado;
		}
	
	public  int toInt(byte[] bytes)
	{
		int resultado=(bytes[0]<<24)+(bytes[1]<<16)+(bytes[2]<<8)+(bytes[3]);

		  return resultado;
		}

}
