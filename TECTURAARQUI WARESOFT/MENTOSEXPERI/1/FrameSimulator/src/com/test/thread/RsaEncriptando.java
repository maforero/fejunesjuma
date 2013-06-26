package com.test.thread;

import java.math.BigInteger;

public class RsaEncriptando {

	private long e;
	private long n;

	public void clave() {
		e = 11;
		n = 239117;

	}

	public byte[] encriptar(byte[] frame) {

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
			imprimir(enc);
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
