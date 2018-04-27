package com.gravity.api.home.service;

import java.util.Map;

import com.gravity.api.common.view.model.PayDetailModel;
import com.gravity.common.support.mybatis.auto.model.UserOrder;

public interface PayServiceIF {
	
	public PayDetailModel getPayDetail(String orderNo, String token);
	
	public UserOrder getPayDetail(String orderNo);
	
	public boolean updateOrderPayStatus(String orderNo);
	
	public int selectOrderPayStatus(String orderNo);
	
	public String orderQuery(String orderNo);

}
