package com.maiyajf.base.po;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**   
 * @Title R.java
 * @Package com.maiyajf.users.common.base
 * @Description 操作返回信息公共类
 * @author zhangww
 * @date 2015年9月25日 上午10:39:05
 * @version V1.0   
 */
@JsonInclude(Include.NON_EMPTY)
public class ResultParams {
	
	/**
	 * 操作返回状态
	 */
	String retcode;

	/**
	 * 操作返回信息
	 */
	String retinfo;
	
	String token;

	/**
	 * 操作返回对象
	 */
	Object doc;
	
	/**
	 * 第三方返回信息
	 */
	String content;

	/**
	 * 第三方返回码
	 */
	String code;
	
	/**
	 * 第三方所需校验的token
	 */
	String resetToken;
	
	/**
	 * 第三方数据源信息
	 */
	String website;

	/**
	 * 第三方所需校验的token
	 */
	private String reqToken;
	
	public static final String SUCCESS_CODE = "000000";
	
	public static final String FAIL_CODE = "000001";
	
	public static final String VALID_CODE = "000002";
	
	public static final String CARD_NOT_MATCH_PID_CODE = "000004";//绑定的信用卡信息与本人身份信息不匹配
	
	public static final String SUCCESS = "成功！";
	
	public static final String FAIL = "失败！";
	
	public static final String VALID = "Token失效！";
	
	public ResultParams() {
		
	}
	
	public ResultParams(String retcode, String retinfo) {
		this.retcode = retcode;
		this.retinfo = retinfo;
	}
	
	public ResultParams(String retcode) {
		this.retcode = retcode;
	}

	public ResultParams(String retcode, String retinfo, Object result) {
		this.retcode = retcode;
		this.retinfo = retinfo;
		this.doc = result;
	}
	
	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public static ResultParams success() {
		return new ResultParams(SUCCESS_CODE, SUCCESS);
	}
	
	public static ResultParams fail() {
		return new ResultParams(FAIL_CODE, FAIL);
	}
	
	public static ResultParams valid() {
		return new ResultParams(VALID_CODE, VALID);
	}

	public static ResultParams successResult(Object result) {
		return new ResultParams(SUCCESS_CODE, SUCCESS, result);
	}
	
	public static ResultParams successInfo(String retinfo) {
		return new ResultParams(SUCCESS_CODE, retinfo);
	}
	
	public static ResultParams failInfo(String retinfo) {
		return new ResultParams(FAIL_CODE, retinfo);
	}
	
	public static ResultParams successCode(String retcode) {
		return new ResultParams(retcode);
	}
	
	public static ResultParams failCode(String retcode) {
		return new ResultParams(retcode);
	}
	
	public static ResultParams failResult(Object result) {
		return new ResultParams(FAIL_CODE, FAIL, result);
	}
	
	public static ResultParams validResult(Object result) {
		return new ResultParams(VALID_CODE, VALID, result);
	}

	public Object getDoc() {
		return doc;
	}

	public void setDoc(Object doc) {
		this.doc = doc;
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

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getResetToken() {
		return resetToken;
	}

	public void setResetToken(String resetToken) {
		this.resetToken = resetToken;
	}

	public String getWebsite() {
		return website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}

	public String getReqToken() {
		return reqToken;
	}

	public void setReqToken(String reqToken) {
		this.reqToken = reqToken;
	}

	@Override
	public String toString() {
		return "ResultParams [retcode=" + retcode + ", retinfo=" + retinfo + ", token=" + token + ", doc=" + doc + "]";
	}
}