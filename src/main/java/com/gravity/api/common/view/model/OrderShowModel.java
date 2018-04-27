package com.gravity.api.common.view.model;

import lombok.Data;

@Data
public class OrderShowModel {
	
	String iconSrcUrl;
	
	String serviceKeyWord;
	
	String serviceProvider;
	
	int serviceId;
	
	int groupPrice;
	
	//int payType;
	
	int isAfterSales;
}
