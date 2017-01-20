package com.maiyajf.base.po;

import java.util.HashMap;
import java.util.Map;

import com.maiyajf.base.constants.InterfaceRetCodes;

/**
 * @ClassName: ResponseInfoBase
 * @Description: 接口响应消息bean
 * @author: yunlei.hua
 * @date: 2015年9月25日 上午10:12:28
 */
public class ResponseInfoBase {
	
	private String retcode; // 响应码
	private String retinfo; //响应消息
	private String contactsflag; //上传通讯录、短信标识
	/** token */
	private String token;
	
	public ResponseInfoBase() {
	}
	public ResponseInfoBase(String retcode,String retinfo) {
		this.retcode = retcode;
		this.retinfo = retinfo;
	}
	public ResponseInfoBase(String retcode,String retinfo,String token) {
		this.retcode = retcode;
		this.retinfo = retinfo;
		this.token = token;
	}
	
	
	public String getRetcode() {
		return retcode;
	}
	public void setRetcode(String retcode) {
		this.retcode = retcode;
	}
	public String getRetinfo() {
		return retinfo;
	}
	public void setRetinfo(String retinfo) {
		this.retinfo = retinfo;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	
	public String getContactsflag() {
		return contactsflag;
	}
	public void setContactsflag(String contactsflag) {
		this.contactsflag = contactsflag;
	}

	private static Map<String, String> returnSuccessMap = new HashMap<String, String>();
	{
		returnSuccessMap.put(InterfaceRetCodes.RETURN_CODE_STR, InterfaceRetCodes.CALL_SUCCESS);
		returnSuccessMap.put(InterfaceRetCodes.RETURN_INFO_STR, SUCCESS_MESG);
	}

	private static Map<String, String> returnFailureMap = new HashMap<String, String>();
	{
		returnFailureMap.put(InterfaceRetCodes.RETURN_CODE_STR, InterfaceRetCodes.CALL_FAILURE);
		returnFailureMap.put(InterfaceRetCodes.RETURN_INFO_STR, FAIL_MESG);
	}
	
	public final static String SUCCESS_MESG ="请求成功";
	public final static String FAIL_MESG ="请求失败";
	
	public static Map<String, String> getReturnFailMap() {
		return returnFailureMap;
	}
	
	public static Map<String, String> getReturnSuccessMap() {
		return returnSuccessMap;
	}
	
	@Override
	public String toString() {
		return "ResponseInfoBase [retcode=" + retcode + ", retinfo=" + retinfo + ", contactsflag=" + contactsflag
				+ ", token=" + token + "]";
	}
}
