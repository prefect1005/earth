package com.gravity.api.admin.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gravity.api.common.view.model.AdminCreateUserModel;
import com.gravity.common.support.mybatis.auto.model.AdminBasicInfo;
import com.gravity.common.support.mybatis.auto.model.Users;
import com.gravity.common.support.mybatis.auto.repository.AdminBasicInfoMapper;
import com.gravity.common.support.mybatis.auto.repository.UsersMapper;

@Service
public class AdminAddUserServiceImpl implements AdminAddUserServiceIF {

	@Autowired
	UsersMapper usersMapper;

	@Autowired
	AdminBasicInfoMapper adminBasicInfoMapper;

	@Override
	@Transactional(rollbackFor = Throwable.class)
	public boolean addAdminUser(AdminCreateUserModel adminCreateUser) {

		Users user = new Users();
		user.setPhone(adminCreateUser.getPhoneNumber());
		String passwordHashed = BCrypt.hashpw("000000", BCrypt.gensalt());
		user.setPassword(passwordHashed);
		user.setTypeCp(0);
		user.setGroupAuthority(3);
		user.setCreateTime(new Date());
		int result = usersMapper.insert(user);
		System.out.println("id:" + user.getId());
		if (result != 0) {
			AdminBasicInfo adminBasicInfo = new AdminBasicInfo();
			adminBasicInfo.setUserId(user.getId());
			adminBasicInfo.setPersonName(adminCreateUser.getName());
			adminBasicInfo.setPersonDepartment(adminCreateUser.getDepartment());
			adminBasicInfo.setCreateTime(new Date());
			result = adminBasicInfoMapper.insert(adminBasicInfo);
			if (result != 0) {
				return true;
			}
		}

		return false;
	}

}
