package com.maiyajf.base.utils.sms;

import java.util.Date;
import java.util.Map;
import java.util.Properties;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.maiyajf.base.spring.SpringContextHolder;
import com.maiyajf.base.utils.base.PropertiesUtils;
import com.maiyajf.base.utils.log.ExceptionLogger;
import com.maiyajf.base.utils.log.OutInterfaceLogger;

/**
 * @ClassName: SMSSendServiceUtil
 * @Description: 请求用户消息中心发送短信
 * @author: yunlei.hua
 * @date: 2015年12月29日 下午2:24:33
 */
@Component
public class SMSSendServiceUtil {
	
	private static String sendSmsUrl = "";
	private static RestTemplate smsRest = null; 
	static {
		try {
			Properties p = PropertiesUtils.loadProperties("classpath:application.properties");
			sendSmsUrl =  p.getProperty("mobileMsg.url");
		} catch (Exception e) {
			ExceptionLogger.error("加载文件application.properties中的mobileMsg.url异常","",e);
		}
	}

	/**
	 * @Title: asycSend
	 * @Description: 异步请求用户消息中心发送短信
	 * @param: params 请求参数
	 * @param: pathInfo 请求url映射（例：/sendMsg4Register）
	 * @return: void
	 */
	@Async
	public void asycSend (Map<String, String> params, String pathInfo) {
		String result = "";
		Date beginDate = new Date();
		String url = SMSSendServiceUtil.sendSmsUrl+pathInfo;
		try {
			if (null == smsRest) {
				smsRest = SpringContextHolder.getBean(RestTemplate.class);
			}
			result = SMSSendServiceUtil.smsRest.postForObject(url, params, String.class);
		} catch (Exception e) {
			ExceptionLogger.error("异步请求用户消息中心发送手机短信失败","",e);
		} finally {
			OutInterfaceLogger.info("调用用户消息中心发送短信", url, beginDate, params.toString(), new Date(), result);
		}
	}
}
