package com.gravity.api.home.service;

import java.util.List;
import java.util.Map;

import com.gravity.api.common.view.model.ServiceDetailModel;
import com.gravity.api.common.view.model.ServiceEvaluateDetailModel;

public interface ServiceDetailServiceIF {

	public ServiceDetailModel getServiceDetailById (int id);
	
	public Map<String, String> getServiceEvaluateScoreByServiceId(int id);
	
	public List<ServiceEvaluateDetailModel> getServiceEvaluateDetailByServiceId(int id);
}
