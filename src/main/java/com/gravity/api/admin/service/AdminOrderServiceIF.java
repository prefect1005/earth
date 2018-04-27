package com.gravity.api.admin.service;

import java.util.List;

import com.gravity.api.common.view.model.AdminrOrderShowModel;

public interface AdminOrderServiceIF {
	
	public List<AdminrOrderShowModel> getAllOrderDetailByPage (int page, String row, String queryKey);
	
	public int getCountOrder (String row, String queryKey);

}
