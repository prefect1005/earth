package com.gravity.api.login.service;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.gravity.api.common.view.model.UserModel;
import com.gravity.common.support.mybatis.auto.model.EmployIdentificationCompany;
import com.gravity.common.support.mybatis.auto.model.EmployIdentificationCompanyExample;
import com.gravity.common.support.mybatis.auto.model.EmployIdentificationPerson;
import com.gravity.common.support.mybatis.auto.model.EmployIdentificationPersonExample;
import com.gravity.common.support.mybatis.auto.model.ServiceProviderIdentificationCompany;
import com.gravity.common.support.mybatis.auto.model.ServiceProviderIdentificationCompanyExample;
import com.gravity.common.support.mybatis.auto.model.ServiceProviderIdentificationPerson;
import com.gravity.common.support.mybatis.auto.model.ServiceProviderIdentificationPersonExample;
import com.gravity.common.support.mybatis.auto.model.Users;
import com.gravity.common.support.mybatis.auto.model.UsersExample;
import com.gravity.common.support.mybatis.auto.model.UsersVerifyCode;
import com.gravity.common.support.mybatis.auto.model.UsersVerifyCodeExample;
import com.gravity.common.support.mybatis.auto.repository.EmployIdentificationCompanyMapper;
import com.gravity.common.support.mybatis.auto.repository.EmployIdentificationPersonMapper;
import com.gravity.common.support.mybatis.auto.repository.ServiceProviderIdentificationCompanyMapper;
import com.gravity.common.support.mybatis.auto.repository.ServiceProviderIdentificationPersonMapper;
import com.gravity.common.support.mybatis.auto.repository.UsersMapper;
import com.gravity.common.support.mybatis.auto.repository.UsersVerifyCodeMapper;
import com.gravity.common.util.QEncodeUtil;
import com.gravity.common.util.RSAUtils;
import com.gravity.common.util.YunpianSmsApi;

@Service
public class LoginServiceImpl implements LoginServiceIF {

	private static final Logger LOGGER = LoggerFactory.getLogger(LoginServiceImpl.class);

	@Autowired
	private UsersMapper usersMapper;

	@Autowired
	private UsersVerifyCodeMapper usersVerifyCodeMapper;

	@Value("#{yunpianInfo['yunpian.apikey']}")
	private String apikey;

	@Value("#{yunpianInfo['yunpian.smsText']}")
	private String smsText;

	@Autowired
	ServiceProviderIdentificationPersonMapper serviceProviderIdentificationPersonMapper;

	@Autowired
	ServiceProviderIdentificationCompanyMapper serviceProviderIdentificationCompanyMapper;
	
	@Autowired
	EmployIdentificationCompanyMapper employIdentificationCompanyMapper;

	@Autowired
	EmployIdentificationPersonMapper employIdentificationPersonMapper;
	
	@Override
	public Users login(String account) {
		if (StringUtils.isBlank(account)) {
			return null;
		}
		UsersExample usersExample = new UsersExample();
		usersExample.createCriteria().andPhoneEqualTo(account);
		List<Users> usersList = usersMapper.selectByExample(usersExample);

		if (CollectionUtils.isNotEmpty(usersList)) {
			return usersList.get(0);
		}
		return null;
	}

	@Override
	@Transactional(rollbackFor = Throwable.class)
	public Users register(UserModel userModel) {

		if (userModel == null) {
			return null;
		}

		Users user = new Users();
		user.setPhone(userModel.getPhone());
		user.setPassword(userModel.getPassword());
		user.setTypeCp(Integer.parseInt(userModel.getTypeCp()));
		user.setGroupAuthority(0);
		user.setCreateTime(new Date());
		usersMapper.insert(user);

		UsersExample usersExample = new UsersExample();
		usersExample.createCriteria().andPhoneEqualTo(userModel.getPhone());
		List<Users> usersList = usersMapper.selectByExample(usersExample);
		if (CollectionUtils.isNotEmpty(usersList)) {
			return usersList.get(0);
		}
		return null;
	}

	/**
	 * 验证验证码正确
	 */
	@Override
	public boolean verifyVCode(String phone, String vCode) {
		UsersVerifyCodeExample usersVerifyCodeExample = new UsersVerifyCodeExample();
		Date date = new Date();
		date.setTime(date.getTime() - 3 * 60 * 1000); //验证码三分钟有效
		usersVerifyCodeExample.createCriteria().andPhoneEqualTo(phone).andCreateTimeGreaterThan(date)
				.andVCodeEqualTo(vCode);
		List<UsersVerifyCode> usersVerityCodeList = usersVerifyCodeMapper.selectByExample(usersVerifyCodeExample);
		if (!usersVerityCodeList.isEmpty()) {
			return true;
		}

		return false;
	}

	/**
	 * 获取验证码
	 */
	@Override
	public boolean getVCode(String phone) {
		try {
			// 【地心引力】您的验证码是#code#。验证码三分钟内有效。如非本人操作，请忽略本短信
			int vCode = (int) ((Math.random() * 9 + 1) * 100000);
			String resultJson = YunpianSmsApi.sendSms(apikey, smsText.replaceAll("#code#", vCode + ""), phone);
			System.out.println(apikey);
			System.out.println(phone);
			System.out.println("smstest:" + smsText.replaceAll("#code#", vCode + ""));
			String codeResult = JSON.parseObject(resultJson).getString("code");
			System.out.println("codeResult:" + codeResult);
			if ("0".equals(codeResult)) {
				UsersVerifyCode usersVerifyCode = new UsersVerifyCode();
				usersVerifyCode.setPhone(phone);
				usersVerifyCode.setvCode(vCode + "");
				usersVerifyCode.setCreateTime(new Date());
				int result = usersVerifyCodeMapper.insert(usersVerifyCode);
				if (result == 1) {
					return true;
				}
			} else {
				String msgResult = JSON.parseObject(resultJson).getString("msg");
				LOGGER.info("getVCode error, msg:" + msgResult);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}

	public static void main(String[] args) throws Exception {
		// String passwordHashed = BCrypt.hashpw("111111", BCrypt.gensalt());
		// System.out.println(passwordHashed);
		// System.out.println("===");
		//
		// System.out.println(BCrypt.checkpw("111111",
		// "$2a$10$5Gx0a7PO5SuPxJHMBtpIsuPf.AMyKIJadW6Csgp5v3FQvRFM7rbgG"));
		//		for (int i = 0; i < 100; i++)
		//			System.out.println((int) ((Math.random() * 9 + 1) * 100000));

		//		 Map<String, Object> keyMap = RSAUtils.genKeyPair();
		//         String publicKey = RSAUtils.getPublicKey(keyMap);
		//         String privateKey = RSAUtils.getPrivateKey(keyMap);
		//         
		//         System.out.println(publicKey);
		//         System.out.println("====");
		//         System.out.println(privateKey);

		//		String ss = QEncodeUtil.aesDecrypt("pj6XiaVayzgZb2gVRPQmhmh0xNAJ3rWa6Nf7N8UTYhA=", "9h2eV29Pkt/UC0ARvMs1tKItFp0Qe8SHhQwH2HZ5Xb0+S39QLQB");
		System.out.println(YunpianSmsApi.sendSms("fcd353639d3d2e240befe5cad6ce22c3",
				"【地心引力】您的验证码是1234。验证码三分钟内有效。如非本人操作，请忽略本短信", "18614087661"));

	}

	@Override
	public boolean resetPwd(String phone, String pwd) {

		if (StringUtils.isBlank(phone)) {
			return false;
		}
		UsersExample usersExample = new UsersExample();
		usersExample.createCriteria().andPhoneEqualTo(phone);
		List<Users> usersList = usersMapper.selectByExample(usersExample);

		if (CollectionUtils.isNotEmpty(usersList)) {
			Users user = usersList.get(0);
			String passwordHashed = BCrypt.hashpw(pwd, BCrypt.gensalt());
			user.setPassword(passwordHashed);
			usersMapper.updateByPrimaryKey(user);
			return true;
		}

		return false;
	}

	@Override
	public boolean modifyPwd(int userId, String oldPwd, String newPwd) {

		UsersExample usersExample = new UsersExample();
		usersExample.createCriteria().andIdEqualTo(userId);
		List<Users> usersList = usersMapper.selectByExample(usersExample);

		if (CollectionUtils.isNotEmpty(usersList)) {
			Users user = usersList.get(0);
			if (BCrypt.checkpw(oldPwd, user.getPassword())) {
				String passwordHashed = BCrypt.hashpw(newPwd, BCrypt.gensalt());
				user.setPassword(passwordHashed);
				usersMapper.updateByPrimaryKey(user);
				return true;
			}
		}

		return false;
	}

	@Override
	public boolean verifyIsRegister(String phone) {

		UsersExample example = new UsersExample();
		example.createCriteria().andPhoneEqualTo(phone);
		List<Users> userList = usersMapper.selectByExample(example);
		if (CollectionUtils.isNotEmpty(userList)) {
			return true;
		}
		return false;
	}

	@Override
	public String getUserNameByUserId(int typeCp, int groupAuthority, int userId) {
		
		String showAccount = "";
		
		if (groupAuthority == 1) {
			if (typeCp == 1) {
				ServiceProviderIdentificationCompanyExample example = new ServiceProviderIdentificationCompanyExample();
				example.createCriteria().andUserIdEqualTo(userId);
				List<ServiceProviderIdentificationCompany> companyList = serviceProviderIdentificationCompanyMapper.selectByExample(example);
				if (CollectionUtils.isNotEmpty(companyList)) {
					showAccount = companyList.get(0).getCompanyName();
				}
			} else {
				ServiceProviderIdentificationPersonExample example = new ServiceProviderIdentificationPersonExample();
				example.createCriteria().andUserIdEqualTo(userId);
				List<ServiceProviderIdentificationPerson> personList = serviceProviderIdentificationPersonMapper.selectByExample(example);
				if (CollectionUtils.isNotEmpty(personList)) {
					showAccount = personList.get(0).getName();
				}
			}
		} else {
			if (typeCp == 1) {
				EmployIdentificationCompanyExample example = new EmployIdentificationCompanyExample();
				example.createCriteria().andUserIdEqualTo(userId);
				List<EmployIdentificationCompany> companyList = employIdentificationCompanyMapper.selectByExample(example);
				if (CollectionUtils.isNotEmpty(companyList)) {
					showAccount = companyList.get(0).getCompanyName();
				}
				
			} else {
				EmployIdentificationPersonExample example = new EmployIdentificationPersonExample();
				example.createCriteria().andUserIdEqualTo(userId);
				List<EmployIdentificationPerson> personList = employIdentificationPersonMapper.selectByExample(example);
				if (CollectionUtils.isNotEmpty(personList)) {
					showAccount = personList.get(0).getName();
				}
			}
		}
		
		return showAccount;
	}
}
