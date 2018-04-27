package com.gravity.api.provider.service.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.asm.Handle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gravity.api.common.view.model.ProviderMyAccountIncomeDBModel;
import com.gravity.api.common.view.model.ProviderMyAccountIncomeModel;
import com.gravity.common.support.mybatis.auto.model.ProviderAccount;
import com.gravity.common.support.mybatis.auto.model.ProviderAccountExample;
import com.gravity.common.support.mybatis.auto.model.ProviderIncome;
import com.gravity.common.support.mybatis.auto.model.ProviderIncomeExample;
import com.gravity.common.support.mybatis.auto.model.ProviderWithDraw;
import com.gravity.common.support.mybatis.auto.model.ProviderWithDrawExample;
import com.gravity.common.support.mybatis.auto.model.UserOrder;
import com.gravity.common.support.mybatis.auto.repository.ProviderAccountMapper;
import com.gravity.common.support.mybatis.auto.repository.ProviderIncomeMapper;
import com.gravity.common.support.mybatis.auto.repository.ProviderWithDrawMapper;
import com.gravity.common.support.mybatis.auto.repository.UserOrderMapper;

@Service
public class MyAccountServiceImpl implements MyAccountServiceIF {

	public static final int PAGE_SIZE = 2;

	@Autowired
	ProviderIncomeMapper providerIncomeMapper;

	@Autowired
	ProviderAccountMapper providerAccountMapper;

	@Autowired
	UserOrderMapper userOrderMapper;

	@Autowired
	ProviderWithDrawMapper providerWithDrawMapper;

	@Override
	public List<ProviderMyAccountIncomeModel> getProviderIncomeDetail(int page, int providerId) {

		int beginSize = (page - 1) * PAGE_SIZE;

		List<ProviderMyAccountIncomeModel> incomeList = new ArrayList<ProviderMyAccountIncomeModel>();

		List<ProviderMyAccountIncomeDBModel> providerIncomeList = providerIncomeMapper.selectByPageAndProviderId(
				providerId, beginSize, PAGE_SIZE);
		if (CollectionUtils.isNotEmpty(providerIncomeList)) {
			for (ProviderMyAccountIncomeDBModel incomeDB : providerIncomeList) {
				ProviderMyAccountIncomeModel providerMyAccountIncome = new ProviderMyAccountIncomeModel();

				int inOutcount = incomeDB.getIncomeAmount();
				if (inOutcount > 0) {
					providerMyAccountIncome.setInOutcomeAmount("+" + inOutcount);
				} else {
					providerMyAccountIncome.setInOutcomeAmount("" + inOutcount);
				}

				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				providerMyAccountIncome.setOrderDate(sdf.format(incomeDB.getOrderDate()));
				providerMyAccountIncome.setOrderName(incomeDB.getOrderName());
				providerMyAccountIncome.setOrderNo(incomeDB.getOrderNo());
				incomeList.add(providerMyAccountIncome);
			}
		}

		return incomeList;
	}

	@Override
	public int getProviderIncomeCount(int providerId) {
		return providerIncomeMapper.selectCountByProviderId(providerId);
	}

	@Override
	public List<String> getAllAmountByUserId(int providerId) {

		List<String> resultList = new ArrayList<String>();

		ProviderAccountExample example = new ProviderAccountExample();
		example.createCriteria().andProviderUserIdEqualTo(providerId);
		List<ProviderAccount> accountList = providerAccountMapper.selectByExample(example);
		if (CollectionUtils.isNotEmpty(accountList)) {
			ProviderAccount providerAccount = accountList.get(0);
			resultList.add(providerAccount.getAllAmount() + "");
			resultList.add(providerAccount.getCurrentAmount() + "");
			int level = providerAccount.getAllAmount() / 100000;
			resultList.add(level + "");
		} else {//如果没有账户，则创建账户表
			ProviderAccount providerAccount = new ProviderAccount();
			providerAccount.setProviderUserId(providerId);
			providerAccount.setAllAmount(0);
			providerAccount.setCurrentAmount(0);
			providerAccount.setCreateTime(new Date());
			providerAccount.setUpdateTime(new Date());
			providerAccountMapper.insert(providerAccount);
			resultList.add("0");
			resultList.add("0");
			resultList.add("0");
		}

		return resultList;
	}

	@Override
	@Transactional(rollbackFor = Throwable.class)
	public String providerWithDraw(String amountString, int providerId) {

		int amount = 0;
		try {
			amount = Integer.parseInt(amountString);
		} catch (NumberFormatException e) {
			return "请输入正确的金额";
		}

		//提现，首先检测提现额度是否大于账户可用提现金额，然后扣除用户账户的可用额度，再才插入数据库

		if (amount <= 0) {
			return "请输入正确的金额";
		}

		ProviderAccountExample example = new ProviderAccountExample();
		example.createCriteria().andProviderUserIdEqualTo(providerId);
		List<ProviderAccount> accountList = providerAccountMapper.selectByExample(example);
		if (CollectionUtils.isNotEmpty(accountList)) {
			ProviderAccount providerAccount = accountList.get(0);
			if (amount > providerAccount.getCurrentAmount()) { //如果提现金额大于账户当前额度，拒绝执行并返回提示信息
				return "提现金额大于可用余额";
			}

			int beforeCurrent = providerAccount.getCurrentAmount();
			int afterCurrent = beforeCurrent - amount;
			providerAccount.setCurrentAmount(afterCurrent);
			providerAccount.setUpdateTime(new Date());
			int result = providerAccountMapper.updateByPrimaryKey(providerAccount);

			if (result != 0) { //账户更新成功，插入账户流水表
				ProviderIncome providerIncome = new ProviderIncome();
				providerIncome.setOrderId(0);
				providerIncome.setOrderIdNo("");
				providerIncome.setEmployId(0);
				providerIncome.setProviderId(providerId);
				providerIncome.setAmount(0 - amount);
				providerIncome.setCreateTime(new Date());
				result = providerIncomeMapper.insert(providerIncome);

				if (result != 0) { //更新用户账户当前余额成功
					ProviderWithDraw providerWithDraw = new ProviderWithDraw();
					providerWithDraw.setProviderId(providerId);
					providerWithDraw.setAmount(amount);
					providerWithDraw.setIsSuccess(0);
					providerWithDraw.setCreateTime(new Date());
					result = providerWithDrawMapper.insert(providerWithDraw);
					if (result != 0) {
						return "提现成功，请耐心等待平台处理";
					}
				}
			}
		} else {
			return "还没有该账户";
		}

		return "";
	}
}
