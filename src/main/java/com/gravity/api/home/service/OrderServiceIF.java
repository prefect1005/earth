package com.gravity.api.home.service;

import com.gravity.api.common.view.model.HomePayOrderShowModel;
import com.gravity.api.common.view.model.HomePlaceOrderModel;
import com.gravity.api.common.view.model.HomeSubmitOrderModel;
import com.gravity.api.common.view.model.OrderShowModel;

public interface OrderServiceIF {
	
	public HomePayOrderShowModel submitOrder (HomeSubmitOrderModel homeSubmitOrder, int userId);
	
	public OrderShowModel getOrderShowDetail (HomePlaceOrderModel homePlaceOrder);
	
	public HomePayOrderShowModel payOrderPage(String idNo, String token, int userId);

}
