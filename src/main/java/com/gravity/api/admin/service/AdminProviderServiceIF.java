package com.gravity.api.admin.service;

import java.util.List;

import com.gravity.api.common.view.model.AdminProviderShowModel;

public interface AdminProviderServiceIF {
	
	public List<AdminProviderShowModel> getProviderDetail(int page, String row, String queryKey);
	
	public int getCountProvider(String row, String queryKey);
	
	public boolean setApproveById(int priId, int userId);

}
