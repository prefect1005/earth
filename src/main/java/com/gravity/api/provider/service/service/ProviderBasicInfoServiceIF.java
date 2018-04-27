package com.gravity.api.provider.service.service;

import com.gravity.api.common.view.model.ProviderBasicInfoCompanyModel;
import com.gravity.api.common.view.model.ProviderBasicInfoPersonModel;

public interface ProviderBasicInfoServiceIF {
	
	public ProviderBasicInfoCompanyModel getBasicInfoCompanyByUserId(int userId);
	
	public boolean updateBasicInfoCompanyByIdAndUserId(ProviderBasicInfoCompanyModel providerBasicInfoCompany, int userId);
	
	public ProviderBasicInfoPersonModel getBasicInfoPersonByUserId(int userId);
	
	public boolean updateBasicInfoPersonByIdAndUserId(ProviderBasicInfoPersonModel providerBasicInfoPersonModel, int userId);

}
