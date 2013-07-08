package com.test.byteutil;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * @class ByteUtils.java
 * @author Felipe
 * @Date Jul 8, 2013
 * @since 1.0
 */
public class ByteUtils {

	private static ByteUtils instance = new ByteUtils();

	private ByteUtils() {
	}

	public static ByteUtils getInstance() {
		return instance;
	}

	public <T> T deserialize(byte[] data) {
		ByteArrayInputStream in = new ByteArrayInputStream(data);
		try {
			ObjectInputStream is = new ObjectInputStream(in);
			return (T) is.readObject();
		} catch (Exception e) {
			throw new IllegalStateException(e);
		}
	}

	public byte[] serialize(Object obj) {
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		try {
			ObjectOutputStream os = new ObjectOutputStream(out);
			os.writeObject(obj);

			return out.toByteArray();
		} catch (Exception e) {
			throw new IllegalStateException(e);
		}
	}
}
