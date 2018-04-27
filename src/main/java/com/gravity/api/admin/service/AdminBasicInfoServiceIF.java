package com.gravity.api.admin.service;

import com.gravity.api.common.view.model.AdminBasicInfoDetailModel;
import com.gravity.common.support.mybatis.auto.model.AdminBasicInfo;

public interface AdminBasicInfoServiceIF {
	
	public AdminBasicInfoDetailModel getAdminBasicInfoByUserId(int userId, String phone);

}
