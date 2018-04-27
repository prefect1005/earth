package com.gravity.api.common.view.model;

import java.util.Date;

import lombok.Data;

@Data
public class EmployOrderShowDBModel {
	
	Date createTime;
	
	String orderNo;
	
	int providerUserId;
	
	String serviceTitle;
	
	String[] serviceLogoUrls;
	
	int dealPrice;
	
	/**
	 * 订单是否取消（0没，1取消）
	 */
	int isCancel;
	
	/**
	 * 是否售后（0不，1是）
	 */
	int isAfterSale;
	
	/**
	 * 付款状态（0未付款，1付款，2确认收货，3评价完成）
	 */
	int orderStatus;
	
	String token;

}
