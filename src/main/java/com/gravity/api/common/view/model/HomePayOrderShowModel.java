package com.gravity.api.common.view.model;

import lombok.Data;

@Data
public class HomePayOrderShowModel {
	
	String serviceTitle;
	
	String orderNo;
	
	int totalPirce;
	
	String token;
	
	String serviceIconUrl;
}
