package com.gravity.api.provider.service.service;

import java.util.Map;

import com.gravity.api.common.view.model.PublishServiceModel;
import com.gravity.common.support.mybatis.auto.model.Users;

public interface PublishServiceServiceIF {
	
	public Map<String, String> getFirstCategory();
	
	public Map<String, String> getSecondCategoryByFirst(int firstId);
	
	public boolean savePublishService(PublishServiceModel publishServiceModel, Users user);
	
	public int getIsApproveByUserId(int userId, int userType);
	
}
