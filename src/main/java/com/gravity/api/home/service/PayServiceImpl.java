package com.gravity.api.home.service;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.gravity.api.common.view.model.PayDetailModel;
import com.gravity.common.support.mybatis.auto.model.UserOrder;
import com.gravity.common.support.mybatis.auto.model.UserOrderExample;
import com.gravity.common.support.mybatis.auto.model.UsersVerifyCode;
import com.gravity.common.support.mybatis.auto.repository.UserOrderMapper;
import com.gravity.common.util.YunpianSmsApi;
import com.jfinal.weixin.sdk.api.PaymentApi;

@Service
public class PayServiceImpl implements PayServiceIF {

	@Autowired
	private UserOrderMapper userOrderMapper;

	@Value("#{yunpianInfo['yunpian.apikey']}")
	private String apikey;

	@Value("#{yunpianInfo['yunpian.orderPayText']}")
	private String orderPayText;

	@Override
	public PayDetailModel getPayDetail(String orderNo, String token) {

		PayDetailModel payDetailModel = new PayDetailModel();

		UserOrderExample example = new UserOrderExample();
		example.createCriteria().andIdNoEqualTo(orderNo).andTokenEqualTo(token);
		List<UserOrder> userOrderList = userOrderMapper.selectByExample(example);
		if (CollectionUtils.isNotEmpty(userOrderList)) {
			UserOrder userOrder = userOrderList.get(0);
			payDetailModel.setOrderId(orderNo);
			payDetailModel.setPrice(userOrder.getDealPrice() * 100 + "");
			payDetailModel.setServiceId(userOrder.getServiceId() + "");
		}

		return payDetailModel;
	}

	@Override
	public UserOrder getPayDetail(String orderNo) {

		UserOrderExample example = new UserOrderExample();
		example.createCriteria().andIdNoEqualTo(orderNo);
		List<UserOrder> userOrderList = userOrderMapper.selectByExample(example);
		if (CollectionUtils.isNotEmpty(userOrderList)) {
			UserOrder userOrder = userOrderList.get(0);
			return userOrder;
		}

		return null;
	}

	@Override
	public boolean updateOrderPayStatus(String orderNo) {

		int resultInt = 0;

		UserOrderExample example = new UserOrderExample();
		example.createCriteria().andIdNoEqualTo(orderNo);
		List<UserOrder> userOrderList = userOrderMapper.selectByExample(example);
		if (CollectionUtils.isNotEmpty(userOrderList)) {
			UserOrder userOrder = userOrderList.get(0);
			sendMsgToProvider(userOrder);
			userOrder.setOrderStatus(1);//设置为已支付
			userOrder.setPayTime(new Date());
			resultInt = userOrderMapper.updateByPrimaryKey(userOrder);
		}

		return (resultInt != 0);
	}

	//用户付款到平台,发送短信到服务商手机号
	public boolean sendMsgToProvider(UserOrder userOrder) {

		try {
			String resultJson = YunpianSmsApi.sendSms(apikey, orderPayText.replaceAll("#code#", ""), "");
			String codeResult = JSON.parseObject(resultJson).getString("code");
			System.out.println("codeResult:" + codeResult);

			if ("0".equals(codeResult)) {
				return true;
			} else {
				String msgResult = JSON.parseObject(resultJson).getString("msg");
				System.out.println("getVCode error, msg:" + msgResult);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		return false;
	}

	@Override
	public int selectOrderPayStatus(String orderNo) {

		int resultInt = 0;

		UserOrderExample example = new UserOrderExample();
		example.createCriteria().andIdNoEqualTo(orderNo);
		List<UserOrder> userOrderList = userOrderMapper.selectByExample(example);
		if (CollectionUtils.isNotEmpty(userOrderList)) {
			UserOrder userOrder = userOrderList.get(0);
			if (userOrder.getOrderStatus() == 1) {
				resultInt = 1;
			}
		}

		return resultInt;
	}

	@Override
	public String orderQuery(String orderNo) {
		Map<String, String> resultMap = PaymentApi.queryByOutTradeNo("wxd1415a76940da5e6", "1396715902",
				"HUJK8973UIJN7888nshj12hu3u4pq0xi", orderNo);

		System.out.println("订单查询Id = " + orderNo);
		System.out.println("订单查询结果 = " + resultMap);

		if (resultMap.containsKey("trade_state")) {
			String result = resultMap.get("trade_state");
			switch (result) {
			case "SUCCESS":
				return "支付成功";
			case "REFUND":
				return "转入退款";
			case "NOTPAY":
				return "未支付";
			case "CLOSED":
				return "已关闭";
			case "REVOKED":
				return "已撤销（刷卡支付）";
			case "USERPAYING":
				return "用户支付中";
			case "PAYERROR":
				return "支付失败(其他原因，如银行返回失败)";
			default:
				break;
			}
		}

		return "";
	}

}
