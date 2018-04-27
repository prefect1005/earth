package com.gravity.api.common.view.model;

import lombok.Data;

@Data
public class PayDetailModel {

	String orderId;
	
	String serviceId;
	
	//单位为分
	String price;
}
