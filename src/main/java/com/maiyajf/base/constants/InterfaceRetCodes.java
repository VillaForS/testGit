package com.maiyajf.base.constants;

/**
 * 模块、接口间的返回码
 * 
 * @author ck
 *
 */
public interface InterfaceRetCodes {
	/**
	 * 接口调用成功
	 */
	String CALL_SUCCESS = "000000";
	/**
	 * 接口调用失败
	 */
	String CALL_FAILURE = "000001";
	/**
	 * token失效
	 */
	String CALL_OVER_EXPIRE = "000002";
	/**
	 * 返回码
	 */
	String RETURN_CODE_STR = "retcode";
	/**
	 * 返回信息
	 */
	String RETURN_INFO_STR = "retinfo";
	/**
	 * 返回结果
	 */
	String RETURN_RESULT = "result";
}
