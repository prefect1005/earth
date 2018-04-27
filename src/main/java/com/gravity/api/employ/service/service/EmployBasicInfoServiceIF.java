package com.gravity.api.employ.service.service;

import com.gravity.api.common.view.model.EmployBasicInfoCompanyModel;
import com.gravity.api.common.view.model.EmployBasicInfoPersonModel;
import com.gravity.common.support.mybatis.auto.model.Users;

public interface EmployBasicInfoServiceIF {
	
	public EmployBasicInfoPersonModel getBasicInfoPersonByUser (Users user);
	
	public boolean updateBasicInfoPersonByInfoId (EmployBasicInfoPersonModel employBasicInfoPerson, int userId);
	
	public EmployBasicInfoCompanyModel getBasicInfoCompanyByUser (Users user);
	
	public boolean updateBasicInfoCompanyByInfoId (EmployBasicInfoCompanyModel employBasicInfoCompany, int userId);

}
