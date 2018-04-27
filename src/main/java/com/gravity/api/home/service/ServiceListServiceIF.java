package com.gravity.api.home.service;

import java.util.List;

import com.gravity.api.common.view.model.ProviderMyPublishModel;

public interface ServiceListServiceIF {

	public List<ProviderMyPublishModel> getServiceListByCategoryAndOrderType(int secondCategory, int inOrderType,
			int inOrderTypeUpDown, String location, String searchKeyWord, int page);

	public int getServiceListAllNumber(int secondCategory, String location, String searchKeyWord);
	
	public String getServiceListShowTitle(String category, String searchKy);

}
