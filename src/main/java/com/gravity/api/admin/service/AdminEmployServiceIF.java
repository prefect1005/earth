package com.gravity.api.admin.service;

import java.util.List;

import com.gravity.api.common.view.model.AdminEmployModel;

public interface AdminEmployServiceIF {
	
	public List<AdminEmployModel> getEmployDetail(int page, String row, String queryKey);
	
	public int getTotalEmployCount(String row, String queryKey);

}
