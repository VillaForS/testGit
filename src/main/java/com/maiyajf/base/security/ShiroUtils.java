/**
 * 
 */
package com.maiyajf.base.security;

import java.util.Date;

import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.session.Session;

import com.maiyajf.base.utils.log.DebugLogger;
import com.maiyajf.base.utils.log.ExceptionLogger;

/**
 * @author Administrator
 * 
 */
public class ShiroUtils {
	public static final String AUTHORIZATION_KEY = "_authorization_key";
	public static final String AUTHORIZATION_OPERATORID_KEY = "_authorization_operatorID_key";
	public static final String AUTHORIZATION_EXCEPTION_KEY = "_authorization_error";
	public static final String ORIGIN_NAME_KEY = "origin_name_key";
	public static final String ORIGIN_IP_KEY = "origin_ip_key";

	/**
	 * 取得当前用户
	 */
	@SuppressWarnings("unchecked")
	public static <T extends SimpleAuthenticationInfo> T getCurrentUser() {
		try {
			return (T) ShiroUtils.getSessionAttribute(AUTHORIZATION_KEY);
		} catch (Exception e) {
			ExceptionLogger.error(e);
		}
		return null;
	}

	/**
	 * 获取当前用户shiro Session
	 * 
	 * @return
	 */
	public static Session getSession() {
		try {
			return SecurityUtils.getSubject().getSession();
		} catch (Exception e) {
			ExceptionLogger.error(e);
			return null;
		}
	}

	/**
	 * 获取会话中的值
	 * 
	 * @return
	 */
	public static Object getSessionAttribute(String key) {
		try {
			return SecurityUtils.getSubject().getSession().getAttribute(key);
		} catch (Exception e) {
			ExceptionLogger.error(e);
		}
		return null;
	}

	/**
	 * 是否认证
	 * 
	 * @return
	 */
	public static boolean isAuthenticated() {
		try {
			return SecurityUtils.getSubject().isAuthenticated();
		} catch (Exception e) {
			ExceptionLogger.error(e);
			return false;
		}
	}

	/**
	 * 删除会话中的值
	 * 
	 * @param key
	 */
	public static void removeSessionAttribute(String key) {
		try {
			SecurityUtils.getSubject().getSession().removeAttribute(key);
		} catch (Exception e) {
			ExceptionLogger.error(e);
		}
	}

	/**
	 * 取得当前用户的登录名, 如果当前用户未登录则返回空字符串.
	 */
	public static String getCurrentUserName() {
		String username = "";
		try {
			username = ShiroUtils.getRealUserName((String) ShiroUtils
					.getSessionAttribute(ShiroUtils.ORIGIN_NAME_KEY));
		} catch (Exception e) {
			ExceptionLogger.error(e);
		}
		if (StringUtils.isBlank(username)) {
			username = "anonymousUser";
		}
		return username;

	}

	/**
	 * 取得当前用户登录IP, 如果当前用户未登录则返回空字符串.
	 */
	public static String getCurrentUserIp() {
		String ip = "";
		try {
			ip = (String) ShiroUtils
					.getSessionAttribute(ShiroUtils.ORIGIN_IP_KEY);
		} catch (Exception e) {
			ExceptionLogger.error(e);
		}
		return ip;
	}

	/**
	 * 判断用户是否拥有角色, 如果用户拥有参数中的任意一个角色则返回true.
	 */
	public static boolean hasAnyRole(String... roles) {
		return false;
	}

	/**
	 * 获取市场ID
	 * 
	 * @return
	 */
	public static String getMarketID() {
		return null;
	}

	/**
	 * 获取用户名
	 * 
	 * @param username
	 * @return
	 */
	public static String getRealUserName(String username) {
		if (StringUtils.isBlank(username)) {
			return "";
		}
		if (username.indexOf("$") != -1) {
			String[] strs = username.split("\\$");
			if (strs.length > 1) {
				username = strs[0];
			}
		}
		if (username.indexOf("^") != -1) {
			String[] strs = username.split("\\^");
			if (strs.length > 1) {
				username = strs[0];
			}
		}
		// 如果是第三方登录，设置登录类型和匹配编码
		if (username.indexOf("~") != -1) {
			String[] strs = username.split("\\~");
			if (strs.length > 1) {
				username = strs[0];
			}
		}
		return username;
	}

	/**
	 * 将用户相关信息放到SESSION中
	 * 
	 * @param key
	 * @param value
	 */
	public static void putToSession(Object key, Object value) {
		Subject currentUser = SecurityUtils.getSubject();
		if (null != currentUser) {
			Session session = currentUser.getSession();
			if (null != session) {
				session.setAttribute(key, value);
			} else {
				DebugLogger.debug("user session is null!==>" + new Date());
			}
		}
	}
}
