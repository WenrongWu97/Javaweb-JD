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
		System.out.println("���ֵ = " + Arrays.toString(randomBytes));
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
		System.out.println("�����ֵ���ܺ� = " + Arrays.toString(passwordBytes));
		byte[] resultPassword = new byte[randomBytes.length + passwordBytes.length];
		System.arraycopy(randomBytes, 0, resultPassword, 0, randomBytes.length);
		System.arraycopy(passwordBytes, 0, resultPassword, randomBytes.length, passwordBytes.length);
		System.out.println("�浽���ݿ�����ֵ = " + Arrays.toString(resultPassword));
		return resultPassword;
	}
	
	public static boolean validatePassword(String password, byte[] resultPassword) {
		byte[] randomBytes = new byte[12];
		System.arraycopy(resultPassword, 0, randomBytes, 0, randomBytes.length);
		System.out.println("�����ݿ��õ����ֵ = " + Arrays.toString(randomBytes));
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
		System.out.println("���õ����ֵ���ܺ� = " + Arrays.toString(passwordBytes));
		byte[] passwordBytesInDB = new byte[resultPassword.length - randomBytes.length];
		System.arraycopy(resultPassword, randomBytes.length, passwordBytesInDB, 0, passwordBytesInDB.length);
		System.out.println("�����ݿ��õļ��ܺ������ = " + Arrays.toString(passwordBytesInDB));
		if(Arrays.equals(passwordBytes, passwordBytesInDB)) {
			System.out.println("������ȷ");
			return true;
		} else {
			System.out.println("�������");
			return false;
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		byte[] resultPassword = MD5Encrypt.encryptByMD5("111aa!!1");
		MD5Encrypt.validatePassword("111aa!!1", resultPassword);
	}

}
