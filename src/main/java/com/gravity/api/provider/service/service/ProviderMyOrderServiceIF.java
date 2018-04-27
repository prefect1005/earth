package com.gravity.api.provider.service.service;

import java.util.List;

import com.gravity.api.common.view.model.ProviderOrderShowModel;

public interface ProviderMyOrderServiceIF {
	
	public List<ProviderOrderShowModel> getProviderOrderDetailByPage(int userId, int page, String row, String queryKey);
	
	public int getCountProviderOrder(int userId, String row, String queryKey);

}
