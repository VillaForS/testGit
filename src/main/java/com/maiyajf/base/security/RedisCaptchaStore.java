package com.maiyajf.base.security;

import java.util.Collection;
import java.util.Locale;

import org.apache.commons.collections.CollectionUtils;

import com.maiyajf.base.utils.redis.JedisBisUtil;
import com.octo.captcha.Captcha;
import com.octo.captcha.service.CaptchaServiceException;
import com.octo.captcha.service.captchastore.CaptchaAndLocale;
import com.octo.captcha.service.captchastore.CaptchaStore;

/**
 * @ClassName: MyCaptchaStore
 * @Description: 验证码CaptchaStore
 * @author: yunlei.hua
 * @date: 2015年12月11日 下午6:02:21
 */
public class RedisCaptchaStore implements CaptchaStore {
	
	public final static String REDIS_CAPTCHA_HEADER = "captcha_name:";
	public final static int CAPTCHA_EXPRIE_TIME = 60*10;
	
	private String getKey (String id) {
		return REDIS_CAPTCHA_HEADER+id;
	}

	@Override
	public Captcha getCaptcha(String id) throws CaptchaServiceException {
		CaptchaAndLocale captchaAndLocale = (CaptchaAndLocale) JedisBisUtil.get(getKey(id));
		return captchaAndLocale != null ? (captchaAndLocale.getCaptcha()) : null;
	}

	@Override
	public Locale getLocale(String id) throws CaptchaServiceException {
		CaptchaAndLocale captchaAndLocale = (CaptchaAndLocale) JedisBisUtil.get(getKey(id));
		return captchaAndLocale != null ? (captchaAndLocale.getLocale()) : null;
	}
	
	@Override
	public boolean hasCaptcha(String id) {
		Object captcha = JedisBisUtil.get(getKey(id));
		return captcha == null ? false : true;
	}
	@Override
	public boolean removeCaptcha(String id) {
		Object ob = null;
		try {
			ob = JedisBisUtil.remove(getKey(id));
		} catch (Exception e) {
			throw new CaptchaServiceException(e);
		}
		return ob == null ? false : true;
	}

	@Override
	public void storeCaptcha(String id, Captcha captcha) throws CaptchaServiceException {
		try {
			JedisBisUtil.putExpire(getKey(id), CAPTCHA_EXPRIE_TIME, captcha);
		} catch (Exception e) {
			throw new CaptchaServiceException(e);
		}
	}

	@Override
	public void storeCaptcha(String id, Captcha captcha, Locale locale) throws CaptchaServiceException {
		try {
			JedisBisUtil.putExpire(getKey(id), CAPTCHA_EXPRIE_TIME, new CaptchaAndLocale(captcha,locale));
		} catch (Exception e) {
			throw new CaptchaServiceException(e);
		}
	}
	
	@Override
	public Collection<?> getKeys() {
		return CollectionUtils.EMPTY_COLLECTION;
	}
	
	public void cleanAndShutdown() {
		// TODO Auto-generated method stub
	}
	
	@Override
	public void empty() {
		// TODO Auto-generated method stub
	}
	
	@Override
	public int getSize() {
		// TODO Auto-generated method stub
		return 0;
	}

	public void initAndStart() {
		// TODO Auto-generated method stub
	}

}
