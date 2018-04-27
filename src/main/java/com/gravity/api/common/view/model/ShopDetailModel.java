package com.gravity.api.common.view.model;

import java.util.List;

import lombok.Data;

@Data
public class ShopDetailModel {
	
	int shopId;
	
	String storeDisplayImgSrc;
	
	String name; //company or name
	
	/*
	 * 完成项目数
	 */
	int completeServiceNumber;
	
	/*
	 * 好评数量(四颗星以上)
	 */
	int goodReputationNumber;
	
	/*
	 * 服务范围(该用户发布的所有服务的二级分类)
	 */
	String serviceScope;
	
	String registerDate;
	
	String phoneNumber;
	
	String userLocation;
	
	/*
	 * 服务商简介
	 */
	String servicerProfile;
	
	/*
	 * 资质证书
	 */
	List<String> proveImgUrl; 

}
