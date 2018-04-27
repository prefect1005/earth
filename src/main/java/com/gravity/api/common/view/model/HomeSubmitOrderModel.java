package com.gravity.api.common.view.model;

import lombok.Data;

@Data
public class HomeSubmitOrderModel {
	
	int serviceId;
	
	int groupPrice;
	
	//int payType;
	
	int isAfterSales;
	
	String employOrderPhone;
	
	String verCode;
	
	String orderRemark;
	
	String orderRemarkFileUrl;
	
	String privilegeId;//优惠码id
	
	//int privilegeSum;//优惠码金额
	
	int totalPrice;
	
}
