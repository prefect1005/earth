package com.gravity.api.common.view.model;

import java.util.List;

import lombok.Data;

@Data
public class ServiceDetailModel {

	int id;
	
	int shopId;

	String title;

	String keyword;

	String oneIntroduction;

	int originalPrice;

	int groupPrice;
	
	String discount;
	
	List<String> imgSrcList;

	int salesNumber;

	String introductionDetail;

}
