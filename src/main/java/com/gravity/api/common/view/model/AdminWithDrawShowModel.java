package com.gravity.api.common.view.model;

import lombok.Data;

@Data
public class AdminWithDrawShowModel {
	
	int withDrawId;
	
	String proviceShopUrl;
	
	String providerName;
	
	String providerNumber;
	
	String providerBankIdCard;
	
	int amount;
	
	String createTime;
	
	/**
	 * 提现状态0为体现，1已提现
	 */
	int status;

}
