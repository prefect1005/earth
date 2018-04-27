package com.gravity.api.common.view.model;

import java.util.Date;

import lombok.Data;

@Data
public class ProviderMyAccountIncomeDBModel {

	String orderNo;

	String orderName;

	Date orderDate;

	int incomeAmount;

}
