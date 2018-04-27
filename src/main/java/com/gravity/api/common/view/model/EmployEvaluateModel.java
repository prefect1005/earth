package com.gravity.api.common.view.model;

import lombok.Data;

@Data
public class EmployEvaluateModel {
	
	String idNo;
	
	int serviceId;
	
	int proviceId;
	
	int evaluateLevel;
	
	String evaluateContent;
	
	String token;
}
