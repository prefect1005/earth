package com.gravity.common.util;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Hex;


public class SAES {
	private final static String encoding = "UTF-8";

	public static void main(String[] args) throws Exception {
		String content = "18614087661,111111";
		System.out.println("加密前：" + content);
		String key = "9h2eV29Pkt/UC0ARvMs1tKItFp0Qe8SHhQwH2HZ5Xb0+S39QLQB";
		System.out.println("加密密钥和解密密钥：" + key);

		String encrypt = encryptAES(content, key);
		System.out.println("加密后：" + encrypt);

		String decrypt = decrypt(encrypt, key);
		System.out.println("解密后：" + decrypt);

//		String sss = "9h2eV29Pkt/UC0ARvMs1tKItFp0Qe8SHhQwH2HZ5Xb0+S39QLQB";
//
//		String ss = encryptAES("sZic0yLOEgu5Y4jcqTMJ8dda13Hk5y0bgnZgMa1QA6g=", sss);
//		System.out.println("ss:" + ss);
	}

	/**
	 * AES加密
	 * 
	 * @param content
	 * @param password
	 * @return
	 */
	public static String encryptAES(String content, String password) {
		byte[] encryptResult = encrypt(content, password);
		String encryptResultStr = parseByte2HexStr(encryptResult);
		// BASE64位加密
		//encryptResultStr = ebotongEncrypto(encryptResultStr);
		return encryptResultStr;
	}

	/**
	 * AES解密
	 * 
	 * @param encryptResultStr
	 * @param password
	 * @return
	 */
	public static String decrypt(String encryptResultStr, String password) {
		// BASE64位解密
		//String decrpt = ebotongDecrypto(encryptResultStr);
		byte[] decryptFrom = parseHexStr2Byte(encryptResultStr);
		byte[] decryptResult = decrypt(decryptFrom, password);
		return new String(decryptResult);
	}

	/**
	* 加密字符串
	*/
	public static String ebotongEncrypto(String str) {
		BASE64Encoder base64encoder = new BASE64Encoder();
		String result = str;
		if (str != null && str.length() > 0) {
			try {
				byte[] encodeByte = str.getBytes(encoding);
				result = base64encoder.encode(encodeByte);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		//base64加密超过一定长度会自动换行 需要去除换行符
		return result.replaceAll("\r\n", "").replaceAll("\r", "").replaceAll("\n", "");
	}

	/**
	 * 解密字符串
	 */
	public static String ebotongDecrypto(String str) {
		BASE64Decoder base64decoder = new BASE64Decoder();
		try {
			byte[] encodeByte = base64decoder.decodeBuffer(str);
			return new String(encodeByte);
		} catch (IOException e) {
			e.printStackTrace();
			return str;
		}
	}

	/**  
	 * 加密  
	 *   
	 * @param content 需要加密的内容  
	 * @param password  加密密码  
	 * @return  
	 */
	private static byte[] encrypt(String content, String password) {
		try {
			KeyGenerator kgen = KeyGenerator.getInstance("AES");
			//防止linux下 随机生成key
			SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG");
			secureRandom.setSeed(password.getBytes());
			kgen.init(128, secureRandom);
			//kgen.init(128, new SecureRandom(password.getBytes()));   
			SecretKey secretKey = kgen.generateKey();
			byte[] enCodeFormat = secretKey.getEncoded();
			SecretKeySpec key = new SecretKeySpec(enCodeFormat, "AES");
			Cipher cipher = Cipher.getInstance("AES");// 创建密码器   
			byte[] byteContent = content.getBytes("utf-8");
			cipher.init(Cipher.ENCRYPT_MODE, key);// 初始化   
			byte[] result = cipher.doFinal(byteContent);
			return result; // 加密   
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (NoSuchPaddingException e) {
			e.printStackTrace();
		} catch (InvalidKeyException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IllegalBlockSizeException e) {
			e.printStackTrace();
		} catch (BadPaddingException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**解密  
	 * @param content  待解密内容  
	 * @param password 解密密钥  
	 * @return  
	 */
	private static byte[] decrypt(byte[] content, String password) {
		try {
			KeyGenerator kgen = KeyGenerator.getInstance("AES");
			//防止linux下 随机生成key
			SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG");
			secureRandom.setSeed(password.getBytes());
			kgen.init(128, secureRandom);
			//kgen.init(128, new SecureRandom(password.getBytes()));   
			SecretKey secretKey = kgen.generateKey();
			byte[] enCodeFormat = secretKey.getEncoded();
			SecretKeySpec key = new SecretKeySpec(enCodeFormat, "AES");
			Cipher cipher = Cipher.getInstance("AES");// 创建密码器   
			cipher.init(Cipher.DECRYPT_MODE, key);// 初始化   
			byte[] result = cipher.doFinal(content);
			return result; // 加密   
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (NoSuchPaddingException e) {
			e.printStackTrace();
		} catch (InvalidKeyException e) {
			e.printStackTrace();
		} catch (IllegalBlockSizeException e) {
			e.printStackTrace();
		} catch (BadPaddingException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**将二进制转换成16进制  
	 * @param buf  
	 * @return  
	 */
	public static String parseByte2HexStr(byte buf[]) {
		return new String(Hex.encodeHex(buf));
	}

	public static byte[] parseHexStr2Byte(String hexStr) {
		if (hexStr.length() < 1) {
			return null;
		}
		
		try {
			return Hex.decodeHex(hexStr.toCharArray());
		} catch (DecoderException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
		
	
	}


}
