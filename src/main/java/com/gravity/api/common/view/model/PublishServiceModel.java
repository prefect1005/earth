package com.gravity.api.common.view.model;

import lombok.Data;

@Data
public class PublishServiceModel {
	
	private String title;
	
	private String firstlevel;
	
	private String secondlevel;
	
	private String keyword;
	
	private String oneintroduction;
	
	private String introductiondetail;
	
	private int oprice;
	
	private int gprice;
	
	private String[] imgSrcList;

}
