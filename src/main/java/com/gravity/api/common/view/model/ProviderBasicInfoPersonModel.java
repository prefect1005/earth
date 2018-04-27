package com.gravity.api.common.view.model;

import lombok.Data;

@Data
public class ProviderBasicInfoPersonModel {
	
	private int id;

	private String name;

	private String idCardNumber;
	
	private int locationProvinceId;

	private int locationCityId;

	private int locationTownId;
	
	private String location;

	private String bankCardId;

	private String phoneNumber;

	private String email;
	
	private String[] imgSrcList;
	
	private String storeDisplayImg;

}
