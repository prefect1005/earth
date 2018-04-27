package com.gravity.api.employ.service.service;

import java.util.List;

import com.gravity.api.common.view.model.EmployConfimeServiceModel;
import com.gravity.api.common.view.model.EmployEvaluateModel;
import com.gravity.api.common.view.model.EmployOrderShowModel;

public interface EmployMyOrderServiceIF {
	
	public List<EmployOrderShowModel> getEmployOrderDetailByPage(int userId, int typeCp, int page, String row, String queryKey);
	
	public List<EmployOrderShowModel> getEmployOrderDetailByPage(int userId, int page, String row, String queryKey);
	
	public int getEmployOrderCount(int userId, String row, String queryKey);
	
	public String saveEvaluateDetail(EmployEvaluateModel employEvaluate);
	
	public String confimeServiceFinish(EmployConfimeServiceModel employConfimeService, int userId);
	
	public String cancelOrder(String token);

}
