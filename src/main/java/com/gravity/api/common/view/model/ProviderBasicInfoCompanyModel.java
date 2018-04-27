package com.gravity.api.common.view.model;

import lombok.Data;

@Data
public class ProviderBasicInfoCompanyModel {
	
	private int id;
	
	private String companyName;

	private String corporateRepresentative;

	private int companyLocationProvinceId;

	private int companyLocationCityId;

	private int companyLocationTownId;

	private String companyLocation;
	
	private String companyLocationDetail;

	private String contactsName;

	private String companyProfile;

	private String companyEmail;

	private String companyBankAccount;

	private String socialUnifiedCreditCode;

	private String[] imgSrcList;
	
	private String storeDisplayImg;

}
