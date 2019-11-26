package com.blacklist.demo.interceptors;

import java.io.IOException;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.blacklist.demo.service.UserInfoServiceImpl;

/**
 * @author Dawei 2019/3/14
 */
@Component
public class UserLoginInterceptor implements HandlerInterceptor {

	private static final Logger logger = LoggerFactory.getLogger(UserLoginInterceptor.class);

	@Resource
	private UserInfoServiceImpl userInfoService;

//	@PostConstruct
//	public void setUserInfoService(UserInfoServiceImpl userInfoService) {
//		this.userInfoService = userInfoService;
//	}

	/* 前置拦截 */
	@Override public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
			Object handler) {
		String requestURI = request.getRequestURI();
		logger.info("User login check ---- for requestURI={}", requestURI);

		Object userInfoObj = request.getSession().getAttribute("userInfo");
		String userIdStr = request.getParameter("userId");
		if (userIdStr != null) {
			return !userInfoService.checkUserInBlacklist(Long.parseLong(userIdStr));
		}

		try {
			response.sendRedirect("/");
		} catch (IOException e) {
			logger.error("Can`t redirect to login page.");
		}
		return false;
	}

	//方法业务处理之后再调用
	@Override public void postHandle(HttpServletRequest request, HttpServletResponse response,
			Object handler, ModelAndView modelAndView) {

	}

	//页面渲染完成之后 通常用于清除某些资源，类似Finally
	@Override public void afterCompletion(HttpServletRequest request, HttpServletResponse response,
			Object handler, Exception ex) {

	}
}
