package com.gravity.api.admin.service;

import java.text.SimpleDateFormat;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gravity.api.common.view.model.AdminBasicInfoDetailModel;
import com.gravity.common.support.mybatis.auto.model.AdminBasicInfo;
import com.gravity.common.support.mybatis.auto.model.AdminBasicInfoExample;
import com.gravity.common.support.mybatis.auto.repository.AdminBasicInfoMapper;

@Service
public class AdminBasicInfoServiceImpl implements AdminBasicInfoServiceIF {

	@Autowired
	AdminBasicInfoMapper adminBasicInfoMapper;

	@Override
	public AdminBasicInfoDetailModel getAdminBasicInfoByUserId(int userId, String phone) {

		AdminBasicInfoDetailModel adminBasicInfoDetail = new AdminBasicInfoDetailModel();
		AdminBasicInfoExample example = new AdminBasicInfoExample();
		example.createCriteria().andUserIdEqualTo(userId);
		List<AdminBasicInfo> basicInfoList = adminBasicInfoMapper.selectByExample(example);
		if (basicInfoList != null && basicInfoList.size() != 0) {
			AdminBasicInfo adminBasicInfo = basicInfoList.get(0);
			adminBasicInfoDetail.setPhoneNumber(phone);
			adminBasicInfoDetail.setName(adminBasicInfo.getPersonName());
			adminBasicInfoDetail.setDepartment(adminBasicInfo.getPersonDepartment());
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			adminBasicInfoDetail.setCreateTime(sdf.format(adminBasicInfo.getCreateTime()));
		}
		return adminBasicInfoDetail;
	}

}
