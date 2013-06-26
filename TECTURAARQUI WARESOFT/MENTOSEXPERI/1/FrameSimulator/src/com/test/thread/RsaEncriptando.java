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

		byte[] enc = new byte[frame.length];

		BigInteger c, c1, l, en;
		en = new BigInteger(n + "");

		for (int i = 0; i < frame.length; i++) {

			c1 = new BigInteger(frame[i] + "");
			c = c1.pow((int) e);
			l = c.mod(en);
			enc[i] = (Byte.parseByte(l.toString()));

		}

		return enc;
	}

}
