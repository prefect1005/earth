package com.gravity.api.provider.service.service;

import java.util.List;

import com.gravity.api.common.view.model.ProviderMyPublishModel;

public interface MyPublishServiceServiceIF {
	
	public List<ProviderMyPublishModel> getProviderPublishedServiceByUserId(int userId, int page);
	
	public int getTotalServicePageByUserId(int userId);
	
	public String deleteMyPublishByServiceId(int serviceId, int userId);

}
