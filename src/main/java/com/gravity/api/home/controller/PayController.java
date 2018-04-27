package com.gravity.api.home.controller;

import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gravity.api.common.view.model.PayDetailModel;
import com.gravity.api.home.service.PayServiceIF;
import com.gravity.common.support.mybatis.auto.model.UserOrder;
import com.gravity.common.support.springframework.view.model.AjaxResult;
import com.gravity.common.util.EarthUtils;
import com.jfinal.kit.HttpKit;
import com.jfinal.kit.StrKit;
import com.jfinal.weixin.sdk.api.PaymentApi;
import com.jfinal.weixin.sdk.kit.IpKit;
import com.jfinal.weixin.sdk.kit.PaymentKit;

@Controller
@RequestMapping(value = "pay")
public class PayController {

	public static final String paternerKey = "HUJK8973UIJN7888nshj12hu3u4pq0xi";

	@Autowired
	PayServiceIF payService;

	@RequestMapping(value = "")
	public String payOrder(Model model) {
		return "pay-order";
	}

	@RequestMapping(value = "payImage")
	public void getWechatPayImage(@RequestParam("orderNo") String orderNo, @RequestParam("token") String token,
			HttpServletRequest request, HttpServletResponse response) {

		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
		response.setContentType("image/jpeg");

		PayDetailModel payDetail = payService.getPayDetail(orderNo, token);

		if (payDetail == null) {
			return;
		}

		Map<String, String> params = new HashMap<String, String>();
		params.put("appid", "wxd1415a76940da5e6");
		params.put("mch_id", "1396715902");
		params.put("nonce_str", System.currentTimeMillis() / 1000 + "");
		params.put("body", "地心引力-服务下单");
		params.put("out_trade_no", orderNo); //商户订单号
		params.put("total_fee", payDetail.getPrice()); //订单价格

		String ip = IpKit.getRealIp(request);
		if (StrKit.isBlank(ip) || "0:0:0:0:0:0:0:1".equals(ip)) {
			ip = "127.0.0.1";
		}
		System.out.println("ip:" + ip);
		params.put("spbill_create_ip", ip);
		params.put("notify_url", "http://www.xdixin.com/pay/wechatPayReturnMsg"); //微信回调url
		params.put("product_id", payDetail.getServiceId());// 二维码中包含的商品ID
		params.put("trade_type", "NATIVE");

		String sign = PaymentKit.createSign(params, paternerKey);
		params.put("sign", sign);

		String xmlResult = PaymentApi.pushOrder(params);

		Map<String, String> payResult = PaymentKit.xmlToMap(xmlResult);

		String return_code = payResult.get("return_code");
		String result_code = payResult.get("result_code");

		if (StrKit.isBlank(return_code) || !"SUCCESS".equals(return_code)) {
			System.out.println("return_code != success");
			System.out.println(xmlResult);
			return;
		}

		if (StrKit.isBlank(result_code) || !"SUCCESS".equals(result_code)) {
			System.out.println("result_code != success");
			System.out.println(xmlResult);
			return;
		}

		try {
			EarthUtils.createQrcode(payResult.get("code_url"), response.getOutputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@RequestMapping(value = "wechatPayReturnMsg")
	public void wechatPayReturnMsg(HttpServletRequest request, HttpServletResponse response) {

		System.out.println("hahhhaahahhsasun");

		String xmlMsg = HttpKit.readData(request);
		System.out.println("支付通知 = " + xmlMsg);
		Map<String, String> params = PaymentKit.xmlToMap(xmlMsg);

		String appid = params.get("appid");
		//商户号
		String mch_id = params.get("mch_id");
		String result_code = params.get("result_code");
		String openId = params.get("openid");
		//交易类型
		String trade_type = params.get("trade_type");
		//付款银行
		String bank_type = params.get("bank_type");
		// 总金额
		String total_fee = params.get("total_fee");
		//现金支付金额
		String cash_fee = params.get("cash_fee");
		// 微信支付订单号
		String transaction_id = params.get("transaction_id");
		// 商户订单号
		String outTradeNo = params.get("out_trade_no");
		// 支付完成时间，格式为yyyyMMddHHmmss
		String time_end = params.get("time_end");

		String attach = params.get("attach");
		String fee_type = params.get("fee_type");
		String is_subscribe = params.get("is_subscribe");
		String err_code = params.get("err_code");
		String err_code_des = params.get("err_code_des");

		// 注意重复通知的情况，同一订单号可能收到多次通知，请注意一定先判断订单状态
		// 避免已经成功、关闭、退款的订单被再次更新
		UserOrder userOrder = payService.getPayDetail(outTradeNo);
		String returnData = "";

		if (userOrder != null && userOrder.getOrderStatus() == 0) {//订单不为空并且状态是未付款
			if (PaymentKit.verifyNotify(params, paternerKey)) {
				if (("SUCCESS").equals(result_code)) {
					//更新订单信息
					boolean result = payService.updateOrderPayStatus(outTradeNo);
					//发送通知等
					if (result) {
						Map<String, String> xml = new HashMap<String, String>();
						xml.put("return_code", "SUCCESS");
						xml.put("return_msg", "OK");
						returnData = PaymentKit.toXml(xml);
					}
				}
			} else {
				System.out.println("paternerKey error=====");
			}
		}
		OutputStream outputStream;
		try {
			outputStream = response.getOutputStream();
			response.setHeader("content-type", "text/html;charset=UTF-8");
			System.out.println("returnData:" + returnData);
			byte[] dataByteArr = returnData.getBytes("UTF-8");
			outputStream.write(dataByteArr);
			outputStream.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return;
	}

	@RequestMapping(value = "checkOrderPayStatus")
	@ResponseBody
	public AjaxResult checkOrderPayStatus(@RequestParam(value = "idNo") String idNo) {
		AjaxResult ajaxResult = AjaxResult.fail();

		int orderStatus = payService.selectOrderPayStatus(idNo);
		ajaxResult = AjaxResult.success();
		ajaxResult.addData("orderStatus", orderStatus);

		return ajaxResult;
	}
	
	@RequestMapping(value = "orderQuery")
	@ResponseBody
	public AjaxResult orderQuery(@RequestParam(value = "idNo") String idNo) {
		AjaxResult ajaxResult = AjaxResult.fail();

		String result = payService.orderQuery(idNo);
		ajaxResult = AjaxResult.success();
		ajaxResult.addData("result", result);

		return ajaxResult;
	}

}
