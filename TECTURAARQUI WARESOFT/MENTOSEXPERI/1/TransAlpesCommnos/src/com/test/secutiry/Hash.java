package com.test.secutiry;

public class Hash {
	
	private Hash() {
	}
	
	public static byte[] GenerateHashMD5(String line) {   
		try {
		        java.security.MessageDigest md = java.security.MessageDigest.getInstance("MD5");
		        byte[] array = md.digest(line.getBytes());
		        return array;
		    } catch (java.security.NoSuchAlgorithmException e) {
		    	e.printStackTrace();
		    }
		    return null;
		}
	
}
