package com.gravity.api.provider.service.controller;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.gravity.api.common.view.model.ProviderIndentyCompanyModel;
import com.gravity.api.common.view.model.ProviderIndentyPersonModel;
import com.gravity.api.login.service.LoginServiceIF;
import com.gravity.api.provider.service.service.ProviderIndentificationServiceIF;
import com.gravity.common.support.mybatis.auto.model.Users;
import com.gravity.common.support.springframework.view.model.AjaxResult;

@Controller
@RequestMapping(value = "providerIdentify")
public class ProviderIndentificationController {

	@Autowired
	private LoginServiceIF loginService;

	@Value("#{filePath['image.provider.indenty']}")
	private String providerIndentificationReal;

	@Value("#{filePath['image.provider.indenty.url']}")
	private String providerIndentyImageUrl;

	@Autowired
	ProviderIndentificationServiceIF providerIndentificationService;

	@RequestMapping(value = "identifyPerson")
	public String initIndentificationPersonPage() {
		return "provider-identification-person";
	}

	@RequestMapping(value = "identifyCompany")
	public String initIndentificationCompanyPage() {
		return "provider-identification-company";
	}

	@RequestMapping(value = "uploadFile", method = RequestMethod.POST)
	@ResponseBody
	public AjaxResult uploadFile(@RequestParam("file") MultipartFile resumeFiles[]) {
		AjaxResult ajaxResult = AjaxResult.fail();
		System.out.println("resumeFilesize:" + resumeFiles.length);
		List<String> imageSrc = new ArrayList<String>();
		for (MultipartFile resumeFile : resumeFiles) {
			if (!resumeFile.isEmpty()) {
				String fullName = resumeFile.getOriginalFilename();
				System.out.println("fullName:" + fullName);

				StringBuffer imageName = new StringBuffer();
				imageName.append(FilenameUtils.getBaseName(fullName));
				imageName.append("_");
				imageName.append(RandomStringUtils.randomAlphanumeric(10));
				imageName.append(".");
				imageName.append(FilenameUtils.getExtension(fullName));

				String imageNameString = imageName.toString().replaceAll(",", "");// 去掉文件名称中的逗号,为数据库中存储的照片数组排除错误
				String filePath = providerIndentificationReal + imageNameString;

				System.out.println("filePath:" + filePath);

				try {
					resumeFile.transferTo(new File(filePath));
				} catch (IllegalStateException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
				ajaxResult = AjaxResult.success();
				imageSrc.add(providerIndentyImageUrl + imageNameString);
			}
		}
		ajaxResult.addData("imageNumber", resumeFiles.length);
		ajaxResult.addData("imageSrc", imageSrc);

		return ajaxResult;

	}

	@RequestMapping(value = "uploadDetailForPerson", method = RequestMethod.POST)
	@ResponseBody
	public AjaxResult saveProviderIndentificationPersonDetail(
			@RequestBody ProviderIndentyPersonModel providerIndentyPersonModel, HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
		AjaxResult ajaxResult = AjaxResult.fail();

		Users user = (Users) request.getSession().getAttribute("user");
		System.out.println("user:" + user);
		if ((user.getTypeCp() != 2)) { // 如果不是1的话，说明不是服务商，不允许发布;如果不是个人也不允许继续
			ajaxResult.addData("message", "级别不符");
			return ajaxResult;
		}

		System.out.println(providerIndentyPersonModel);

		// 先校验验证码是否正确
		if (!loginService.verifyVCode(providerIndentyPersonModel.getPhoneNumber(),
				providerIndentyPersonModel.getVerCode())) {
			ajaxResult.addData("message", "验证码不正确");
			return ajaxResult;
		}

		boolean result = providerIndentificationService.providerIndentificationPerson(providerIndentyPersonModel,
				user.getId());

		if (result) {
			Users updateUser = providerIndentificationService.getUserDetail(user.getId());
			request.getSession().setAttribute("user", updateUser);
			response.addHeader("Set-Cookie", "accountShow=" + URLEncoder.encode(providerIndentyPersonModel.getName(),"utf-8") + "; path=/;");
			ajaxResult = AjaxResult.success();
			ajaxResult.addData("location", "/home/index");

		}
		return ajaxResult;
	}

	@RequestMapping(value = "uploadDetailForCompany", method = RequestMethod.POST)
	@ResponseBody
	public AjaxResult saveProviderIndentificationCompanyDetail(
			@RequestBody ProviderIndentyCompanyModel providerIndentyCompanyModel, HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
		AjaxResult ajaxResult = AjaxResult.fail();

		Users user = (Users) request.getSession().getAttribute("user");
		System.out.println("user:" + user);
		if ((user.getTypeCp() != 1)) { // 如果不是1的话，说明不是服务商，不允许发布;如果不是企业也不允许继续
			ajaxResult.addData("message", "级别不符");
			return ajaxResult;
		}

		System.out.println(providerIndentyCompanyModel);

		// 先校验验证码是否正确
//		if (!loginService.verifyVCode(providerIndentyCompanyModel.getPhoneNumber(),
//				providerIndentyCompanyModel.getVerCode())) {
//			ajaxResult.addData("message", "验证码不正确");
//			return ajaxResult;
//		}

		boolean result = providerIndentificationService.providerIndentificationCompany(providerIndentyCompanyModel,
				user.getId());

		if (result) {
			Users updateUser = providerIndentificationService.getUserDetail(user.getId());
			request.getSession().setAttribute("user", updateUser);
			response.addHeader("Set-Cookie", "accountShow=" + URLEncoder.encode(providerIndentyCompanyModel.getCompanyName(),"utf-8") + "; path=/;");
			ajaxResult = AjaxResult.success();
			ajaxResult.addData("location", "/home/index");
		}
		return ajaxResult;
	}
}
