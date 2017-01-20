/**
 * 
 */
package com.maiyajf.base.security;

import java.util.Collection;
import java.util.Locale;

import com.octo.captcha.Captcha;
import com.octo.captcha.service.CaptchaServiceException;
import com.octo.captcha.service.captchastore.CaptchaAndLocale;
import com.octo.captcha.service.captchastore.CaptchaStore;
import org.apache.commons.collections.CollectionUtils;
/**
 * @author Administrator
 * 
 */
public class SessionCaptchaStore implements CaptchaStore {

	private Object getCaptchaById(String id) {
		try {
			return ShiroUtils.getSessionAttribute(id);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public boolean hasCaptcha(String id) {
		try {
			if (getCaptchaById(id) != null) {
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public void storeCaptcha(String id, Captcha captcha)
			throws CaptchaServiceException {
		try {
			ShiroUtils.putToSession(id, captcha);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void storeCaptcha(String id, Captcha captcha, Locale locale)
			throws CaptchaServiceException {
		try {
			ShiroUtils.putToSession(id, new CaptchaAndLocale(captcha, locale));
		} catch (Exception e) {
			throw new CaptchaServiceException(e);
		}
	}

	public Captcha getCaptcha(String id) throws CaptchaServiceException {
		try {
			Object captchaAndLocale = getCaptchaById(id);
			return captchaAndLocale == null ? null
					: ((CaptchaAndLocale) captchaAndLocale).getCaptcha();
		} catch (Exception e) {
			throw new CaptchaServiceException(e);
		}
	}

	public Locale getLocale(String id) throws CaptchaServiceException {
		try {
			Object captchaAndLocale = getCaptchaById(id);
			return captchaAndLocale == null ? null
					: ((CaptchaAndLocale) captchaAndLocale).getLocale();
		} catch (Exception e) {
			throw new CaptchaServiceException(e);
		}
	}

	public boolean removeCaptcha(String id) {
		try {
			if (getCaptcha(id) != null) {
				ShiroUtils.removeSessionAttribute(id);
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public int getSize() {
		return 0;
	}

	public Collection<?> getKeys() {
		return CollectionUtils.EMPTY_COLLECTION;
	}

	public void empty() {

	}

	public void initAndStart() {
	}

	public void cleanAndShutdown() {

	}

}
