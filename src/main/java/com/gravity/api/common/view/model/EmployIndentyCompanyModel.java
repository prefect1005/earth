package com.gravity.api.common.view.model;

import lombok.Data;

@Data
public class EmployIndentyCompanyModel {

	private String companyName;

	private String corporateRepresentative;

	private int companyLocationProvinceId;

	private int companyLocationCityId;

	private int companyLocationTownId;

	private String companyLocation;

	private String companyLocationDetail;

	private String companyEmail;

	private String socialUnifiedCreditCode;

}
