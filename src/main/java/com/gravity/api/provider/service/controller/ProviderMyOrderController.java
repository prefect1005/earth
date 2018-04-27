package com.gravity.api.provider.service.controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLConnection;
import java.nio.charset.Charset;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gravity.api.common.view.model.ProviderOrderShowModel;
import com.gravity.api.provider.service.service.ProviderMyOrderServiceIF;
import com.gravity.common.support.mybatis.auto.model.Users;
import com.gravity.common.support.springframework.view.model.AjaxResult;

@Controller
@RequestMapping(value = "providerOrder")
public class ProviderMyOrderController {

	@Autowired
	ProviderMyOrderServiceIF providerMyOrderService;
	
	@Value("#{filePath['file.order.data']}")
	private String orderFileReal;

	@RequestMapping(value = "")
	public String initMyOrderPage(Model model, HttpServletRequest request) {

		Users user = (Users) request.getSession().getAttribute("user");
		String basicInfoUrl = "";
		if (user.getTypeCp() == 1) {
			basicInfoUrl = "/providerBasicInfo/basicInfoCompany";
		} else {
			basicInfoUrl = "/providerBasicInfo/basicInfoPerson";
		}
		model.addAttribute("basicInfoUrl", basicInfoUrl);
		return "provider-my-order";
	}

	@RequestMapping(value = "getProviderOrderDetail")
	@ResponseBody
	public AjaxResult getProviderOrderDetail(@RequestParam(value = "page", defaultValue = "1") int page,
			@RequestParam(value = "row", defaultValue = "") String row,
			@RequestParam(value = "queryKey", defaultValue = "") String queryKey, HttpServletRequest request) {

		// row 有服务名称serviceTitle和交易状态status
		// queryKey 有任意的title和status的固定值（0,1,2,3,4）（取消订单，待付款，服务进行中，带评价，已完成）

		try {
			// TODO 前段传过来的汉字是乱码(局部解决)
			queryKey = new String(queryKey.getBytes("ISO-8859-1"), "UTF-8").trim();
		} catch (UnsupportedEncodingException e3) {
			e3.printStackTrace();
		}
		System.out.println("row:" + row);
		System.out.println("queryKey:" + queryKey);
		AjaxResult ajaxResult = AjaxResult.fail();
		Users user = (Users) request.getSession().getAttribute("user");

		if (user.getGroupAuthority() != 1) {
			ajaxResult.addData("message", "级别不符");
			return ajaxResult;
		}
		List<ProviderOrderShowModel> orderList = providerMyOrderService.getProviderOrderDetailByPage(user.getId(), page,
				row, queryKey);
		int totalSize = providerMyOrderService.getCountProviderOrder(user.getId(), row, queryKey);
		ajaxResult = AjaxResult.success();
		ajaxResult.addData("orderList", orderList);
		ajaxResult.addData("totalSize", totalSize);
		return ajaxResult;
	}
	
	
	@RequestMapping(value = "download", method = RequestMethod.GET)
	public void downloadFile(HttpServletResponse response, @RequestParam("fileName") String fileName) throws FileNotFoundException, UnsupportedEncodingException, IOException{

		try {
			// TODO 前段传过来的汉字是乱码(局部解决)
			fileName = new String(fileName.getBytes("ISO-8859-1"), "UTF-8");
		} catch (UnsupportedEncodingException e3) {
			e3.printStackTrace();
		}
		FileSystemResource fileSystemResource = new FileSystemResource(orderFileReal + fileName);

		if (!fileSystemResource.exists()) {
			String errorMessage = "Sorry. The file you are looking for does not exist";
			System.out.println(errorMessage);
			OutputStream outputStream;
			try {
				outputStream = response.getOutputStream();
				outputStream.write(errorMessage.getBytes(Charset.forName("UTF-8")));
				outputStream.close();
			} catch (IOException e) {
				e.printStackTrace();
			}

			return;
		}

		String mimeType = URLConnection.guessContentTypeFromName(fileSystemResource.getFilename());
		if (mimeType == null) {
			System.out.println("mimetype is not detectable, will take default");
			mimeType = "application/octet-stream";
		}

		response.setContentType(mimeType);

		try {
			response.setHeader("Content-Disposition",
					String.format("attachment; filename=\"%s\"", new String(
							fileSystemResource.getFilename().getBytes("UTF-8"),
							"ISO-8859-1")));
			response.setContentLength((int) fileSystemResource.contentLength());
			FileCopyUtils.copy(fileSystemResource.getInputStream(), response.getOutputStream());
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		} catch (UnsupportedEncodingException e3) {
			e3.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
