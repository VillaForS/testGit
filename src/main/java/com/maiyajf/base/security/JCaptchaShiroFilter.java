/**
 * 
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

import com.octo.captcha.service.CaptchaService;
import com.octo.captcha.service.CaptchaServiceException;
import com.maiyajf.base.spring.SpringContextHolder;
import com.maiyajf.base.utils.log.DebugLogger;
import com.maiyajf.base.utils.remote.http.ServletUtils;

/**
 * @author Administrator 验证码过滤器
 */
public class JCaptchaShiroFilter implements Filter {
	private static Logger logger = LoggerFactory
			.getLogger(JCaptchaShiroFilter.class);
	// captchaService
	private CaptchaService captchaService;

	// ------------------------------web.xml中的参数名定义------------------------------//
	public static final String PARAM_CAPTCHA_SERVICE_ID = "captchaServiceId";// captchaService在配置文件中Bean的id
	public static final String DEFAULT_CAPTCHA_SERVICE_ID = "captchaService";
	private String captchaServiceId = DEFAULT_CAPTCHA_SERVICE_ID;


	/**
	 * Filter回调退出函数.
	 */
	public void destroy() {
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
			DebugLogger.debug("生成验证码图片captchaId:"+captchaId);
			try {
				captchaService.validateResponseForID(captchaId, "");
			} catch (Exception e) {
				//ExceptionLogger.error(e);
			}
			DebugLogger.debug("生成验证码图片是captchId" + captchaId);
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

	/**
	 * 重新生成验证码图片.
	 */
	protected void refreshCaptchaImage(final HttpServletRequest request)
			throws IOException {
		try {
			String captchaId = request.getSession(true).getId();
			captchaService.getChallengeForID(captchaId, request.getLocale());
		} catch (CaptchaServiceException e) {
			logger.error(e.getMessage(), e);
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
				String captchaID = request.getSession().getId();
				DebugLogger.debug("当前sessionID" + captchaID);
				String challengeResponse = request.getParameter("imgCode");
				return captchaService.validateResponseForID(captchaID,
						challengeResponse);
		} catch (CaptchaServiceException e) {
			logger.error(e.getMessage(), e);
			return false;
		}
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doFilter(ServletRequest sRequest, ServletResponse sResponse, FilterChain chain)
			throws IOException, ServletException {
		DebugLogger.debug("----- in jcaptchashirofilter -----");
		captchaService = SpringContextHolder.getBean(captchaServiceId);
		HttpServletRequest request = (HttpServletRequest) sRequest;
		HttpServletResponse response = (HttpServletResponse) sResponse;
		String servletPath = request.getServletPath();
		DebugLogger.debug("servletPath---》"+ servletPath);
		
		if (servletPath.indexOf("getCaptchaImage") >= 0) {
			DebugLogger.debug("----- just for captcha -----");
			// 为生成验证图片请求.
			genernateCaptchaImage(request, response);
		} else {
			boolean validated = validateCaptchaChallenge(request);
			System.out.println(validated);
		}
		chain.doFilter(sRequest, sResponse);
	}


}
