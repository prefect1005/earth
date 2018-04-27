package com.gravity.api.common.view.model;

import lombok.Data;

@Data
public class ProviderMyPublishModel {
	
	private int serviceId;
	
	private String imageSrc;
	
	private String title;
	
	private String oneIntroduction;
	
	private int originalPrice;
	
	private int groupPrice;
}
