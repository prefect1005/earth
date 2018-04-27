package com.gravity.api.home.service;

import java.util.List;

import com.gravity.api.common.view.model.ShopDetailModel;
import com.gravity.api.common.view.model.ShopDownServiceModel;
import com.gravity.api.common.view.model.ShopDownServiceShowModel;
import com.gravity.api.common.view.model.ShopOtherServiceModel;

public interface ShopInfoServiceIF {
	
	public ShopDetailModel getShopDetailByUserId(int userId);
	
	public void test();
	
	public List<ShopOtherServiceModel> getOtherServiceByShopId(int userId, int page);
	
	public int getOtherServiceCountByShopId(int userId);
	
	public List<ShopDownServiceShowModel> getDownServiceByShopId(int userId, int page);
	
	public int getDownServiceCountByShopId(int userId);

}
