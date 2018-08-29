package com.jd.common;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Arrays;

import com.mysql.cj.protocol.a.result.ByteArrayRow;

public class MD5Encrypt {
	public static byte[] encryptByMD5(String password) {
		byte[] randomBytes = new byte[12];
		SecureRandom random = new SecureRandom();
		random.nextBytes(randomBytes);
		System.out.println("随机值 = " + Arrays.toString(randomBytes));
		MessageDigest md = null;
		try {
			md = MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		md.update(randomBytes);
		md.update(password.getBytes());
		byte[] passwordBytes = md.digest();
		System.out.println("与随机值加密后 = " + Arrays.toString(passwordBytes));
		byte[] resultPassword = new byte[randomBytes.length + passwordBytes.length];
		System.arraycopy(randomBytes, 0, resultPassword, 0, randomBytes.length);
		System.arraycopy(passwordBytes, 0, resultPassword, randomBytes.length, passwordBytes.length);
		System.out.println("存到数据库最终值 = " + Arrays.toString(resultPassword));
		return resultPassword;
	}
	
	public static boolean validatePassword(String password, byte[] resultPassword) {
		byte[] randomBytes = new byte[12];
		System.arraycopy(resultPassword, 0, randomBytes, 0, randomBytes.length);
		System.out.println("从数据库获得的随机值 = " + Arrays.toString(randomBytes));
		MessageDigest md = null;
		try {
			md = MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		md.update(randomBytes);
		md.update(password.getBytes());
		byte[] passwordBytes = md.digest();
		System.out.println("与获得的随机值加密后 = " + Arrays.toString(passwordBytes));
		byte[] passwordBytesInDB = new byte[resultPassword.length - randomBytes.length];
		System.arraycopy(resultPassword, randomBytes.length, passwordBytesInDB, 0, passwordBytesInDB.length);
		System.out.println("从数据库获得的加密后的密码 = " + Arrays.toString(passwordBytesInDB));
		if(Arrays.equals(passwordBytes, passwordBytesInDB)) {
			System.out.println("密码正确");
			return true;
		} else {
			System.out.println("密码错误");
			return false;
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		byte[] resultPassword = MD5Encrypt.encryptByMD5("111aa!!1");
		MD5Encrypt.validatePassword("111aa!!1", resultPassword);
	}

}
