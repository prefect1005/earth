package com.gravity.common.util;

import java.io.OutputStream;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;

public class EarthUtils {
	
	public static void createQrcode(String url, OutputStream outputStream) {
		try {
			int qrcodeWidth = 300;
			int qrcodeHeight = 300;
			String qrcodeFormat = "png";
			HashMap<EncodeHintType, String> hints = new HashMap<EncodeHintType, String>();
			hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");
			BitMatrix bitMatrix = new MultiFormatWriter().encode(url, BarcodeFormat.QR_CODE, qrcodeWidth,
					qrcodeHeight, hints);

			MatrixToImageWriter.writeToStream(bitMatrix, qrcodeFormat, outputStream);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static boolean isPhoneNumber(String account) {
		String phoneRegex = "^1[3|4|5|7|8]\\d{9}$";
		Pattern p = Pattern.compile(phoneRegex);
		Matcher matcher = p.matcher(account);
				return matcher.matches();
	}
	
	public static void main(String[] args) {
		System.out.println(isPhoneNumber("10000000000"));

	}

}
