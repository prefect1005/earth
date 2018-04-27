package com.gravity.api.provider.service.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.gravity.api.common.view.model.PublishServiceModel;
import com.gravity.api.provider.service.service.PublishServiceServiceIF;
import com.gravity.common.support.mybatis.auto.model.Users;
import com.gravity.common.support.springframework.view.model.AjaxResult;

@Controller
@RequestMapping(value = "providerPublish")
public class PublishServiceController {

	@Value("#{filePath['image.publish.service.screenshot']}")
	private String publishServiceImageReal;

	@Value("#{filePath['image.publish.service.screenshot.url']}")
	private String publishServiceImageUrl;

	@Autowired
	private PublishServiceServiceIF publishServiceService;

	@RequestMapping(value = "test")
	@ResponseBody
	public AjaxResult test() {
		AjaxResult ajaxResult = AjaxResult.success();
		Map<String, String> ll = publishServiceService.getSecondCategoryByFirst(1);
		ajaxResult.addData("data", ll);
		return ajaxResult;
	}

	@RequestMapping(value = "")
	public String initPublishPage(Model model, HttpServletRequest request) {

		Users user = (Users) request.getSession().getAttribute("user");
		String basicInfoUrl = "";
		if (user.getTypeCp() == 1) {
			basicInfoUrl = "/providerBasicInfo/basicInfoCompany";
		} else {
			basicInfoUrl = "/providerBasicInfo/basicInfoPerson";
		}
		model.addAttribute("basicInfoUrl", basicInfoUrl);

		Map<String, String> firstCategoryList = publishServiceService.getFirstCategory();
		int isApprove = publishServiceService.getIsApproveByUserId(user.getId(), user.getTypeCp());
		model.addAttribute("firstCategoryList", firstCategoryList);
		model.addAttribute("isApprove", isApprove);
		return "publishing-service";
	}

	@RequestMapping(value = "success")
	public String initPublishSuccess(Model model, HttpServletRequest request) {
		Users user = (Users) request.getSession().getAttribute("user");
		String basicInfoUrl = "";
		if (user.getTypeCp() == 1) {
			basicInfoUrl = "/providerBasicInfo/basicInfoCompany";
		} else {
			basicInfoUrl = "/providerBasicInfo/basicInfoPerson";
		}
		model.addAttribute("basicInfoUrl", basicInfoUrl);
		return "publish-success";
	}

	@RequestMapping(value = "getSecondCategory")
	@ResponseBody
	public AjaxResult getSecondCategory(@RequestParam("cate") int firstId) {
		AjaxResult ajaxResult = AjaxResult.success();
		Map<String, String> ll = publishServiceService.getSecondCategoryByFirst(firstId);
		ajaxResult.addData("data", ll);
		return ajaxResult;
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
//				imageName.append(FilenameUtils.getBaseName(fullName));
				imageName.append(UUID.randomUUID());
				imageName.append("_");
				imageName.append(RandomStringUtils.randomAlphanumeric(10));
				imageName.append(".");
				imageName.append(FilenameUtils.getExtension(fullName));

				String imageNameString = imageName.toString().replaceAll(",", "");// 去掉文件名称中的逗号,为数据库中存储的照片数组排除错误
				String filePath = publishServiceImageReal + imageNameString;

				System.out.println("filePath:" + filePath);

				try {
					resumeFile.transferTo(new File(filePath));
				} catch (IllegalStateException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
				ajaxResult = AjaxResult.success();
				imageSrc.add(publishServiceImageUrl + imageNameString);
			}
		}
		ajaxResult.addData("imageNumber", resumeFiles.length);
		ajaxResult.addData("imageSrc", imageSrc);

		return ajaxResult;

	}

	@RequestMapping(value = "publishServiceDetail", method = RequestMethod.POST)
	@ResponseBody
	public AjaxResult publishServiceDetail(@RequestBody PublishServiceModel publishServiceModel,
			HttpServletRequest request) {
		AjaxResult ajaxResult = AjaxResult.fail();
		Users user = (Users) request.getSession().getAttribute("user");
		System.out.println("user:" + user);
		if (user.getGroupAuthority() != 1) { // 如果不是1的话，说明不是服务商，不允许发布
			ajaxResult.addData("msg", "权限不符");
			return ajaxResult;
		}
		
		int isApprove = publishServiceService.getIsApproveByUserId(user.getId(), user.getTypeCp());
		if (isApprove == 0) {
			ajaxResult.addData("msg", "还未通过认证,请耐心等候");
			return ajaxResult;
		}
		System.out.println("publishServiceModel:" + publishServiceModel);
		boolean result = false;
		if (user != null) {
			result = publishServiceService.savePublishService(publishServiceModel, user);
		}

		if (result) {
			ajaxResult = AjaxResult.success();
			ajaxResult.addData("location", "/providerPublish/success");
		}
		return ajaxResult;
	}

}
