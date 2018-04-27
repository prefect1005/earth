package com.gravity.api.home.service;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import javax.imageio.ImageIO;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.jfinal.kit.StrKit;
import com.jfinal.weixin.sdk.api.PaymentApi;
import com.jfinal.weixin.sdk.kit.PaymentKit;

public class Test {

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

	public static void main(String[] args) {

		//				Map<String, String> params = new HashMap<String, String>();
		//				params.put("appid", "wxd1415a76940da5e6");
		//				params.put("mch_id", "1396715902");
		//				params.put("nonce_str", System.currentTimeMillis() / 1000 + "");
		//				params.put("body", "地心引力-服务下单");
		//				String out_trade_no=System.currentTimeMillis()+"";
		//				params.put("out_trade_no", out_trade_no);
		//				params.put("total_fee", "100"); //单位为分
		//				
		////				String ip = IpKit.getRealIp(getRequest());
		//				String ip = "";
		//				if (StrKit.isBlank(ip)) {
		//					ip = "127.0.0.1";
		//				}
		//				
		//				params.put("spbill_create_ip", ip);
		//				params.put("notify_url", "http://www.xdixin.com/home/index");
		//				params.put("trade_type", "NATIVE");
		//				
		//				
		////				params.put("openid", openId);
		//
		//				String sign = PaymentKit.createSign(params, "HUJK8973UIJN7888nshj12hu3u4pq0xi");
		//				params.put("sign", sign);
		//				
		//				String xmlResult = PaymentApi.pushOrder(params);
		//				
		//				System.out.println(xmlResult);
		//				
		//				Map<String, String> payResult = PaymentKit.xmlToMap(xmlResult);
		//				
		//				String return_code = payResult.get("return_code");
		//				String result_code = payResult.get("result_code");

		//				TwoDimensionCode handler = new TwoDimensionCode();
		//				handler.encoderQRCode(payResult.get("code_url"), "e:/test.png", "png");

	}

}
