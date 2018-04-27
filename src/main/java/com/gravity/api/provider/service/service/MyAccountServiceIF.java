package com.gravity.api.provider.service.service;

import java.util.List;
import java.util.Map;

import com.gravity.api.common.view.model.ProviderMyAccountIncomeModel;

public interface MyAccountServiceIF {
	
	public List<ProviderMyAccountIncomeModel> getProviderIncomeDetail(int page, int providerId);
	
	public int getProviderIncomeCount(int providerId);
	
	public List<String> getAllAmountByUserId(int providerId);
	
	public String providerWithDraw(String amount, int providerId);

}
