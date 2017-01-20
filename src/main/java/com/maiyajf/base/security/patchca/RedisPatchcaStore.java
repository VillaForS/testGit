package com.maiyajf.base.security.patchca;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.maiyajf.base.utils.base.Validater;
import com.maiyajf.base.utils.redis.JedisBisUtil;

/**
 * @ClassName: RedisPatchcaStore
 * @Description: 使用redis存储验证码
 * @author: yunlei.hua
 * @date: 2016年1月26日 下午6:42:22
 */
public class RedisPatchcaStore {
	private static Logger logger = LoggerFactory.getLogger(RedisPatchcaStore.class);
	
	/** 图片验证码保存redis中的key的header */
	private final static String REDIS_PATCHCA_CODE_HEADER = "patchca_id:";
	/** 图片验证码有效时长（单位：秒） */
	private final static int PATCHCA_CODE_EXPRIE_SECOND = 60*10;

	/**
	 * @Title: getImgCode
	 * @Description: 验证码保存到redis中
	 * @return: void
	 */
	public static void getImgCode(final HttpServletRequest request,final HttpServletResponse response,final String deviceNo) {
		String imgCode = PatchcaImageCode.getImgCode(request, response, deviceNo);
		// 验证码放在redis中
		JedisBisUtil.putExpire(REDIS_PATCHCA_CODE_HEADER+deviceNo,PATCHCA_CODE_EXPRIE_SECOND,imgCode);
	}
	
	/**
	 * @Title: validateImgCode
	 * @Description: 校验图片验证码
	 * @return: boolean
	 */
	public static boolean validateImgCode(final String deviceNo, final String imgCode) {
		if (Validater.isEmpty(deviceNo) || Validater.isEmpty(imgCode)) {
			logger.error("参数为空，deviceNo："+deviceNo+",imgCode:"+imgCode);
			return false;
		}
		
		String code = (String) JedisBisUtil.get(REDIS_PATCHCA_CODE_HEADER+deviceNo);
		JedisBisUtil.remove(REDIS_PATCHCA_CODE_HEADER+deviceNo);
		return imgCode.equalsIgnoreCase(code);
	}
}
