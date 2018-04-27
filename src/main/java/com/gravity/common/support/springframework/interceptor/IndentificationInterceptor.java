package com.gravity.common.support.springframework.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.gravity.common.support.mybatis.auto.model.Users;

public class IndentificationInterceptor extends HandlerInterceptorAdapter {

	private static final String[] P_URI = { "/myPublish", "/providerBasicInfo", "/providerModifyPwd", "/providerOrder",
			"/providerPublish", "/providerMyAccount"};

	private static final String[] E_URI = { "/employBasicInfo", "/employModifyPwd", "/employOrder"};

	private static final String PROVIDER_COMPANY_INDENTIFICATION_URL = "/providerIdentify/identifyCompany";

	private static final String PROVIDER_PERSON_INDENTIFICATION_URL = "/providerIdentify/identifyPerson";

	private static final String EMPLOY_COMPANY_INDENTIFICATION_URL = "/employIdentify/identifyCompany";

	private static final String EMPLOY_PERSON_INDENTIFICATION_URL = "/employIdentify/identifyPerson";
	
	private static final String HOME_URL = "/home/index";

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {

		String url = request.getRequestURL().toString();
		System.out.println(">>>: " + url);

		HttpSession session = request.getSession(true);
		// 从session 里面获取用户名的信息
		Users user = (Users) session.getAttribute("user");

		for (String s : P_URI) {
			if (url.contains(s)) { // 需要做判断是否认证过,未认证的话,去做认证
				if (user.getGroupAuthority() == 2 || user.getGroupAuthority() == 3) { // 雇佣方
					response.sendRedirect(HOME_URL);
					return false;
				} else if (user.getGroupAuthority() == 0) {// 未认证
					if (user.getTypeCp() == 1) {
						response.sendRedirect(PROVIDER_COMPANY_INDENTIFICATION_URL);
						return false;
					} else {
						response.sendRedirect(PROVIDER_PERSON_INDENTIFICATION_URL);
						return false;
					}

				}
				return true;
			}
		}

		for (String s : E_URI) {
			if (url.contains(s)) { // 需要做判断是否认证过,未认证的话,去做认证
				if (user.getGroupAuthority() == 1 || user.getGroupAuthority() == 3) { // 雇佣方
					response.sendRedirect(HOME_URL);
					return false;
				} else if (user.getGroupAuthority() == 0) {// 未认证
					if (user.getTypeCp() == 1) {
						response.sendRedirect(EMPLOY_COMPANY_INDENTIFICATION_URL);
						return false;
					} else {
						response.sendRedirect(EMPLOY_PERSON_INDENTIFICATION_URL);
						return false;
					}

				}
				return true;
			}
		}

		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		super.postHandle(request, response, handler, modelAndView);
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		super.afterCompletion(request, response, handler, ex);
	}

}
