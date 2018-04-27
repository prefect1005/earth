package com.gravity.api.admin.service;

import java.util.List;

import com.gravity.api.common.view.model.AdminWithDrawShowModel;

public interface AdminWithDrawServiceIF {

	public List<AdminWithDrawShowModel> getAdminWithDrawList(int page, String row, String queryKey);

	public int getCountAdminWithDraw(String row, String queryKey);
	
	public boolean updateWithDrawStatus(int withDrawId);

}
