package com.gravity.api.login.service;

import com.gravity.api.common.view.model.UserModel;
import com.gravity.common.support.mybatis.auto.model.Users;

public interface LoginServiceIF {

	public Users login(String account);

	public Users register(UserModel user);
	
	public boolean verifyVCode(String phone, String vCode);
	
	public boolean getVCode(String phone);
	
	public boolean resetPwd(String phone, String pwd);
	
	public boolean modifyPwd(int userId, String oldPwd, String newPwd);
	
	public boolean verifyIsRegister(String phone);
	
	public String getUserNameByUserId(int typeCp, int groupAuthority, int userId);

}
