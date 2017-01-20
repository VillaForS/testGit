package com.maiyajf.base.utils.remote.http;

import java.io.IOException;

import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;

import com.maiyajf.base.po.ResponseInfoBase;
import com.maiyajf.base.utils.base.JsonUtil;

/**
 * @ClassName: ResponseUtils
 * @Description: response工具类
 * @author: yunlei.hua
 * @date: 2015年9月30日 下午4:46:55
 */
public class ResponseUtils {
	
	/**
	 * @Title: toJson
	 * @Description: 将返回值转换为json输出
	 * @return: void
	 */
	public static void toJson(ServletResponse response,String retcode,String retinfo) {
		ResponseInfoBase responseInfoBase = new ResponseInfoBase();
		responseInfoBase.setRetcode(retcode);
		responseInfoBase.setRetinfo(retinfo);
		try {
			String fullContentType = "application/json;charset=UTF-8";
			response.setContentType(fullContentType);
			response.getWriter().write(JsonUtil.objectToJson(responseInfoBase));
			response.getWriter().flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * @Title: toJsonP
	 * @Description: 将返回值转换为jsonp输出
	 * @return: void
	 */
	public static void toJsonP(HttpServletRequest request, ServletResponse response,String retcode,String retinfo) {
		ResponseInfoBase responseInfoBase = new ResponseInfoBase();
		responseInfoBase.setRetcode(retcode);
		responseInfoBase.setRetinfo(retinfo);
		String url = null;
		if(null != request){
			url = request.getRequestURI();
			// 获取RequestMapping值
			url = url.substring(url.lastIndexOf("/")+1, url.length());
		}
		String fullContentType = null;
		try {
			fullContentType = "application/javascript;charset=UTF-8";
			response.setContentType(fullContentType);
			response.getWriter().write(
				(StringUtils.isNotBlank(request.getParameter("callback")) ? request.getParameter("callback") : "")
				+ "(" +JsonUtil.objectToJson(responseInfoBase) + ")");
			response.getWriter().flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * @Title: toJsonOrJsonP
	 * @Description: 将返回值转换为json或者jsonp输出
	 * @return: void
	 */
	public static void toJsonOrJsonP(HttpServletRequest request, ServletResponse response,String retcode,String retinfo) {
		ResponseInfoBase responseInfoBase = new ResponseInfoBase();
		responseInfoBase.setRetcode(retcode);
		responseInfoBase.setRetinfo(retinfo);
		String callback = request.getParameter("callback");
		String fullContentType = null;
		try {
			if(StringUtils.isNotBlank(callback)) {
				// 返回JsonP格式
				fullContentType = "application/javascript;charset=UTF-8";
				response.setContentType(fullContentType);
				response.getWriter().write(callback + "(" +JsonUtil.objectToJson(responseInfoBase) + ")");
			} else {
				// 返回Json格式
				fullContentType = "application/json;charset=UTF-8";
				response.setContentType(fullContentType);
				response.getWriter().write(JsonUtil.objectToJson(responseInfoBase));
			}
			response.getWriter().flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * @Title: toJsonOrJsonP
	 * @Description: 将返回值转换为json或者jsonp输出
	 * @return: void
	 */
	public static void toJsonP(HttpServletRequest request, ServletResponse response,Object result) {
		String callback = request.getParameter("callback");
		String fullContentType = null;
		try {
			if(StringUtils.isNotBlank(callback)) {
				// 返回JsonP格式
				fullContentType = "application/javascript;charset=UTF-8";
				response.setContentType(fullContentType);
				response.getWriter().write(callback + "(" +JsonUtil.objectToJson(result) + ")");
			}
			response.getWriter().flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
