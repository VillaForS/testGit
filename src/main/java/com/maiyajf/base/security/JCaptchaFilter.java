/**
 * Copyright (c) 2005-2010 springside.org.cn
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * 
 * $Id: JCaptchaFilter.java 1213 2010-09-11 16:28:22Z calvinxiu $
 */
package com.maiyajf.base.security;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.octo.captcha.service.CaptchaService;
import com.octo.captcha.service.CaptchaServiceException;
import com.maiyajf.base.utils.base.Validater;
import com.maiyajf.base.utils.log.DebugLogger;
import com.maiyajf.base.utils.remote.http.ServletUtils;

/**
 * 集成JCaptcha验证码的Filter.
 * 
 * 可通过配置与SpringSecurity相同的登录表单处理URL与身份验证失败URL,实现与SpringSecurity的集成.
 * 另本filter主要演示与SpringSecurity的集成方式，用户可参考其他验证码方案的集成.
 * 
 * 在web.xml中配置的参数包括：
 * 
 * @see 1.failureUrl
 *      --验证码验证失败后将会跳转的URL路径,需与SpringSecurity(web.xml)中的配置保持一致,无默认值必须配置.
 * @see 2.filterProcessesUrl
 *      --登录表单处理URL,需与SpringSecurity中的配置一致,默认为"/j_spring_security_check".
 * @see 3.captchaServiceId--captchaService在配置文件Spring
 *      ApplicationContext中的bean的id,默认为"captchaService",如果修改了需要保持一致.
 * @see 4.captchaParamter --登录表单中验证码Input框的名称, 默认为"j_captcha".
 * @see 5.autoPassValue
 *      --用于自动化功能测试的自动通过值,默认该值不存在.具体应用参考showcase示例的web.xml与login.jsp.
 * 
 * @author calvin
 */
public class JCaptchaFilter implements Filter {
	// 日志
	private static Logger logger = LoggerFactory
			.getLogger(JCaptchaFilter.class);
	// captchaService
	private CaptchaService captchaService;

	// ------------------------------web.xml中的参数名定义------------------------------//
	public static final String PARAM_CAPTCHA_PARAMTER_NAME = "captchaParamterName";
	public static final String PARAM_CAPTCHA_SERVICE_ID = "captchaServiceId";// captchaService在配置文件中Bean的id

	// ------------------------------默认值定义------------------------------//
	public static final String PARAM_AUTO_PASS_VALUE = "autoPassValue";// 自动化测试默认通过值
	public static final String DEFAULT_CAPTCHA_PARAMTER_NAME = "j_captcha";// 验证码文本框的name默认值
	public static final String DEFAULT_CAPTCHA_SERVICE_ID = "captchaService";

	public static final String DEFAULT_PRODUCT_CAPTCHA_URL = "productCaptchaURL";// 请求生成验证码路劲

	public static final String DEFAULT_GET_CAPTCHA_URL = "/hy/jcaptcha.xjpg";// 请求验证码图片的默认路径

	// ------------------------------外部参数------------------------------//
	private static final String PARAM_LOGIN_FAILURE_VALUE = "loginFailureUrl";

	private String getCaptchaURL = DEFAULT_GET_CAPTCHA_URL;
	private String captchaServiceId = DEFAULT_CAPTCHA_SERVICE_ID;
	private String captchaParamterName = DEFAULT_CAPTCHA_PARAMTER_NAME;
	private String autoPassValue = DEFAULT_GET_CAPTCHA_URL;

	// ------------------------------失败路径集合------------------------------//
	private static String[] failureUrl = null;// 正式登陆验证码失败跳转路劲
	private static String popupfailureUrl = "/hy/popuplogin.action";

	/**
	 * Filter回调初始化函数.
	 */
	public void init(final FilterConfig fConfig) throws ServletException {
		initParameters(fConfig);
		initCaptchaService(fConfig);
	}

	/**
	 * 初始化web.xml中定义的filter init-param.
	 */
	protected void initParameters(final FilterConfig fConfig) {
		if (StringUtils.isNotBlank(fConfig
				.getInitParameter(PARAM_CAPTCHA_SERVICE_ID))) {
			captchaServiceId = fConfig
					.getInitParameter(PARAM_CAPTCHA_SERVICE_ID);
		}
		if (StringUtils.isNotBlank(fConfig
				.getInitParameter(PARAM_CAPTCHA_PARAMTER_NAME))) {
			captchaParamterName = fConfig
					.getInitParameter(PARAM_CAPTCHA_PARAMTER_NAME);
		}
		if (StringUtils.isNotBlank(fConfig
				.getInitParameter(PARAM_AUTO_PASS_VALUE))) {
			autoPassValue = fConfig.getInitParameter(PARAM_AUTO_PASS_VALUE);
		}
		// 生成验证码图片路径
		if (StringUtils.isNotBlank(fConfig
				.getInitParameter(DEFAULT_PRODUCT_CAPTCHA_URL))) {
			getCaptchaURL = fConfig
					.getInitParameter(DEFAULT_PRODUCT_CAPTCHA_URL);
		}
		// 登陆失败跳转路经（考虑到交易平台和交易管理平台共用，所以用传入参数的形式获取）
		if (StringUtils.isNotBlank(fConfig
				.getInitParameter(PARAM_LOGIN_FAILURE_VALUE))) {
			failureUrl = fConfig.getInitParameter(PARAM_LOGIN_FAILURE_VALUE)
					.split(",");
		}
	}

	/**
	 * 从ApplicatonContext获取CaptchaService实例.
	 */
	protected void initCaptchaService(final FilterConfig fConfig) {
		ApplicationContext context = WebApplicationContextUtils
				.getWebApplicationContext(fConfig.getServletContext());
		captchaService = (CaptchaService) context.getBean(captchaServiceId);
	}

	/**
	 * Filter回调退出函数.
	 */
	public void destroy() {
	}

	/**
	 * Filter回调请求处理函数.
	 */
	public void doFilter(final ServletRequest theRequest,
			final ServletResponse theResponse, final FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) theRequest;
		HttpServletResponse response = (HttpServletResponse) theResponse;
		String servletPath = request.getServletPath();
		// 符合filterProcessesUrl为验证处理请求,其余为生成验证图片请求.
		if (servletPath.indexOf(getCaptchaURL) >= 0) {
			// 为生成验证图片请求.
			genernateCaptchaImage(request, response);
		} else {
			boolean validated = validateCaptchaChallenge(request);
			if (validated) {
				// 验证码正确
				chain.doFilter(request, response);
			} else {
				redirectFailureUrl(request, response);
			}
		}
	}

	/**
	 * 生成验证码图片.
	 */
	protected void genernateCaptchaImage(final HttpServletRequest request,
			final HttpServletResponse response) throws IOException {
		ServletUtils.setDisableCacheHeader(response);
		response.setContentType("image/jpeg");
		ServletOutputStream out = response.getOutputStream();
		try {
			String captchaId = request.getSession(true).getId();
			DebugLogger.debug("生成验证码图片是captchId"+captchaId);
			BufferedImage challenge = (BufferedImage) captchaService
					.getChallengeForID(captchaId, request.getLocale());
			ImageIO.write(challenge, "jpg", out);
			out.flush();
		} catch (CaptchaServiceException e) {
			logger.error(e.getMessage(), e);
		} finally {
			out.close();
		}
	}
	protected boolean handerStr(String str) {
		boolean flag = false;
		if (StringUtils.isNotBlank(str)) {
			if (str.contains("<") || str.contains("=") || str.contains(">")
					|| str.contains(")")) {
				flag = true;
			}
		}
		return flag;
	}
	/**
	 * 验证验证码.
	 */
	protected boolean validateCaptchaChallenge(final HttpServletRequest request) {
		try {
			// 是否通过IM单点登录方式
			String j_userName = request.getParameter("j_username");

			if (!Validater.isEmpty(j_userName) && j_userName.length() > 32) {
				// IM登录无需验证码，直接通过
				return true;
			} else {
				String captchaID = request.getSession().getId();
				DebugLogger.debug("当前sessionID"+captchaID);
				String challengeResponse = request
						.getParameter(captchaParamterName);
				String ifalseNum = request.getParameter("failNum");
				if (ifalseNum != null) {
					if (ifalseNum.equals("")) {
						ifalseNum = "0";
					}
				}
				if (StringUtils.isNotBlank(ifalseNum)) {
					if(handerStr(ifalseNum)){
						ifalseNum = "0";
					}
					if (Integer.parseInt(ifalseNum) < 3) {
						return true;
					}
				}
				if (("OtherNoCaptcha").equals(challengeResponse)) {
					return true;
				}
				// 自动通过值存在时,检验输入值是否等于自动通过值
				if (StringUtils.isNotBlank(autoPassValue)
						&& autoPassValue.equals(challengeResponse)) {
					return true;
				}
				return captchaService.validateResponseForID(captchaID,
						challengeResponse);
			}
		} catch (CaptchaServiceException e) {
			logger.error(e.getMessage(), e);
			return false;
		}
	}

	/**
	 * 跳转到失败页面.
	 * 
	 * 可在子类进行扩展, 比如在session中放入SpringSecurity的Exception.
	 * 
	 * @throws ServletException
	 */
	protected void redirectFailureUrl(final HttpServletRequest request,
			final HttpServletResponse response) throws IOException,
			ServletException {
		String _URLType = request.getParameter("urltype");
		String username = request.getParameter("j_username");
		String path = null;
		// 清除用户名或密码错误的消息
		if (StringUtils.isNotEmpty(_URLType)) {
			if ("index_url".equals(_URLType)) {
				if (StringUtils.isNotBlank(username)) {
					username = username.split("\\$")[0];
				}
				path = "/index/index!loginFail.action";
			} else if ("login_url".equals(_URLType)) {
				path = "/hy/login.action";
			} else if ("popuplogin_url".equals(_URLType)) {
				if (StringUtils.isNotBlank(username)) {
					username = username.split("\\^")[0];
				}
				path = popupfailureUrl;
			} else if("znqlogin_url".equals(_URLType)){
				if (StringUtils.isNotBlank(username)) {
					username = username.split("\\^")[0];
				}
				path = "/hy/popuplogin!znqlogin.action";
			} else if ("manage_login_url".equals(_URLType)) {
				path = "/sys/login.action";
			} else {
				path = "/index/index!loginFail.action";
			}

		} else {
			path = "/index/index!loginFail.action";
		}
		request.setAttribute("loginUserName", username);
		request.setAttribute("loginErrorMsg", "验证码错误！");
		String ifalseNum = request.getParameter("failNum");
		if (StringUtils.isNotBlank(ifalseNum)) {
			request.setAttribute("failNum", ifalseNum);
		}
		request.getRequestDispatcher(path).forward(request, response);
	}
}
