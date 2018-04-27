package com.gravity.api.provider.service.service;

import com.gravity.api.common.view.model.ProviderIndentyCompanyModel;
import com.gravity.api.common.view.model.ProviderIndentyPersonModel;
import com.gravity.common.support.mybatis.auto.model.Users;

public interface ProviderIndentificationServiceIF {
	
	public boolean providerIndentificationPerson(ProviderIndentyPersonModel providerIndentyPersonModel, int userId);
	
	public boolean providerIndentificationCompany(ProviderIndentyCompanyModel providerIndentyCompanyModel, int userId);
	
	public Users getUserDetail(int userId);

}
