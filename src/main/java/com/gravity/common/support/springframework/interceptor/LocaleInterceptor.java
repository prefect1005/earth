package com.gravity.common.support.springframework.interceptor;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/**
 * Expose current locale to model
 * 
 * @author sunfujin
 */
public class LocaleInterceptor extends HandlerInterceptorAdapter {
	
	protected static final Logger LOGGER = LoggerFactory.getLogger(LocaleInterceptor.class);

	@Autowired
	private LocaleResolver localeResolver;

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		
		if (modelAndView != null && StringUtils.isNotBlank(modelAndView.getViewName())
				&& !StringUtils.startsWithIgnoreCase(modelAndView.getViewName(), "redirect")) {
			
			Locale locale = localeResolver.resolveLocale(request);
			modelAndView.addObject("locale", locale);
		}
	}
}
