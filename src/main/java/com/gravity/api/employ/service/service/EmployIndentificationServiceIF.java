package com.gravity.api.employ.service.service;

import com.gravity.api.common.view.model.EmployIndentyCompanyModel;
import com.gravity.api.common.view.model.EmployIndentyPersonModel;
import com.gravity.common.support.mybatis.auto.model.Users;

public interface EmployIndentificationServiceIF {
	
	public boolean employIndentificationCompany (EmployIndentyCompanyModel employIndentyCompany, int userId);
	
	public boolean employIndentificationPerson (EmployIndentyPersonModel employIndentyPerson, int userId);
	
	public Users getUserDetail(int userId);

}
