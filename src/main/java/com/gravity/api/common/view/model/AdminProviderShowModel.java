package com.gravity.api.common.view.model;

import lombok.Data;

@Data
public class AdminProviderShowModel {
	
	String providerName;
	
	String phoneNumber;
	
	int providerType;
	
	String location;
	
	String shopUrl;
	
	int isApprove;
	
	int providerIdentificationId;
	
	int userId;
}
