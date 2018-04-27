package com.gravity.api.common.view.model;

import lombok.Data;

@Data
public class ProviderOrderShowModel {

	String createTime;

	String orderNo;

	String serviceEmployName;

	/**
	 * 联系方式
	 */
	String contants;

	String serviceTitle;

	String serviceLogoUrl;

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

	/**
	 * 附件链接
	 */
	String fileUrl;

	/**
	 * 订单备注
	 */
	String remark;

	String token;

}
