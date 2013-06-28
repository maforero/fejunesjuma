package IntegrityMonitor;

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
import java.security.PrivateKey;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class Rsa {

	private long p;
	private long q;
	private long n;
	private long fi;
	private long e;
	private long d;
	public static final String ALGORITHM = "RSA";
	public static final String PRIVATE_KEY_FILE = "private.key";
	private static PrivateKey key;
	private static String IV = "AAAAAAAAAAAAAAAA";
	private static String encryptionKey = "0123456789abcdef";
	  
	public void setValores() {
		p = 487;
		q = 491;
		n = 239117;
		fi = 238140;
		e = 11;
		d = 216491;
//		ObjectInputStream inputStream = null;
//		try {
//			inputStream = new ObjectInputStream(new FileInputStream(PRIVATE_KEY_FILE));
//			key = (PrivateKey) inputStream.readObject();
//			
//		} catch (FileNotFoundException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		} catch (IOException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		} catch (ClassNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
	      
	}
	  public static byte[] desencriptar(byte[] cipherText){
		    try {
				Cipher cipher = Cipher.getInstance("AES/CBC/NoPadding", "SunJCE");
				SecretKeySpec key = new SecretKeySpec(encryptionKey.getBytes("UTF-8"), "AES");
				cipher.init(Cipher.DECRYPT_MODE, key,new IvParameterSpec(IV.getBytes("UTF-8")));
				//imprimir(cipherText);
				return  cipherText;
			} catch (InvalidKeyException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
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
			} catch (InvalidAlgorithmParameterException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return cipherText;
		  }
	  
	  public static byte[] desencriptar3(byte[] text ) {
		    byte[] dectyptedText = null;
		    try {
		      System.out.println(text.length);
		      final Cipher cipher = Cipher.getInstance(ALGORITHM);

		      
		      cipher.init(Cipher.DECRYPT_MODE, key);
		      dectyptedText = cipher.doFinal(text);

		    } catch (Exception ex) {
		      ex.printStackTrace();
		    }
		    imprimir(dectyptedText);
		    return dectyptedText;
		  }
	private static void imprimir(byte[] dectyptedText) {
		for (int i = 0; i < dectyptedText.length; i++) {
			System.out.println(dectyptedText[i]);
		}
		
	}

	public long obtenerp(long num) {
		for (long i = num + 1; i <= 999999; i++)
			if (primo(i) == true) {
				p = i;
				return i;
			}
		p = 17;
		return 17;
	}

	public boolean primo(long num) {
		boolean p = false;

		if (num < 4)
			p = true;
		else {
			if (num % 2 == 0)
				p = false;
			else {
				long contador = 0;
				long i = 1;
				long limite = (num - 1) / 2;
				if (limite % 2 == 0)
					limite--;

				while (i <= limite) {
					if (num % i == 0)
						contador++;
					i += 2;
					if (contador == 2)
						i = limite + 1;
				}

				if (contador == 1)
					p = true;
			}
		}

		return p;
	}

	public String obtenerq(long num) {
		for (long i = num + 1; i <= 999999; i++)
			if (primo(i) == true) {
				q = i;
				return i + "";
			}
		q = 11;
		return q + "";
	}

	public String obtenern() {
		n = p * q;
		return n + "";
	}

	public String obtenerfi() {
		fi = (p - 1) * (q - 1);
		return fi + "";
	}

	public long mcd(long x, long y) {
		long a, b;

		a = x;
		b = y;
		while (a != b) {
			if (a < b) {
				b = b - a;
			} else {
				a = a - b;
			}
		}
		return (a);
	}

	public String obtenere() {
		BigInteger aux, aux1;
		aux = new BigInteger(fi + "");

		for (long i = 2; i < fi; i++) {
			aux1 = new BigInteger(i + "");
			if (aux.gcd(aux1).equals(BigInteger.ONE)) {
				e = i;
				return i + "";
			}

		}
		return null;
	}

	public String obtenerd() {
		BigInteger aux, aux1;
		aux = new BigInteger(e + "");
		aux1 = new BigInteger(fi + "");
		System.out.print(aux.modInverse(aux1));
		d = Long.parseLong(aux.modInverse(aux1).toString());
		return d + "";
	}

	public byte[] desencriptar2(byte[] enc) {

		BigInteger c, en, l, c1;
		en = new BigInteger(n + "");
		byte[] frame = new byte[enc.length/4];

		try {
			for (int i = 0; i < enc.length - 3; i += 4) {
				int entero = (enc[i + 3] & 0xFF) | ((enc[i + 2] & 0xFF) << 8)
						| ((enc[i + 1] & 0xFF) << 16) | ((enc[i] & 0xFF) << 24);
				c1 = new BigInteger(entero + "");
				c = c1.pow((int) (d));
				l = c.mod(en);
				frame[i] = (Byte.parseByte(l.toString()));

				System.out.println(l);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return frame;

	}

	public long fastexp1(long a, long b) {
		long s = 1;
		long t = 0;
		long s1 = 0;
		long t1 = 1;
		long qu;
		long r;
		long temps, tempt;
		while (b != 0) {
			qu = (a / b);
			r = a % b;
			temps = s1;
			tempt = t1;
			a = b;
			b = r;
			s1 = s - (s1 * qu);
			t1 = t - (t1 * qu);
			s = temps;
			t = tempt;

		}
		return (long) s;
	}

}
