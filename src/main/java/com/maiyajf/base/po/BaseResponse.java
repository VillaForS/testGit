package com.maiyajf.base.po;

import java.util.HashMap;
import java.util.Map;
import com.maiyajf.base.constants.IResponseCode;

/**
 * 接口响应消息基础bean，json格式如下。其中doc中的数据相当于自定义的返回数据（数据类型由调用方和被调方约定好的），
 * 可以调用setDocElement(key,value)设置, value类型为Object，可以为各种数据格式，有使用者自己决定。
 * json格式：{
 * 			 "retcode":"000000",
 * 			 "retinfo":"成功",
 * 			 "doc":{
 * 					  "key1":value1,
 * 					  "key2":value2,
 * 					  ......
 * 				   }
 * 		   }
 * 
 * 
 * @author Administrator
 *
 */
public class BaseResponse {
	private String retcode; // 响应码
	private String retinfo; //响应消息
	private Map<String, Object> doc = new HashMap<>(); // 响应消息体数据集

	public BaseResponse() {
	}

	public BaseResponse(String retcode, String retinfo) {
		this.retcode = retcode;
		this.retinfo = retinfo;
	}

	public static BaseResponse SUCCESS(String info) {
		return new BaseResponse(IResponseCode.SUCCESS, info);
	}

	public static BaseResponse SUCCESS() {
		return SUCCESS("成功");
	}

	public static BaseResponse FAIL(String info) {
		return new BaseResponse(IResponseCode.FAIL, info);
	}

	public static BaseResponse FAIL() {
		return FAIL("失败");
	}

	public String getRetcode() {
		return retcode;
	}

	public BaseResponse setRetcode(String retcode) {
		this.retcode = retcode;
		return this;
	}

	public String getRetinfo() {
		return retinfo;
	}

	public BaseResponse setRetinfo(String retinfo) {
		this.retinfo = retinfo;
		return this;
	}

	/**
	 * 设置响应消息体中的元素。
	 * 
	 * @param name 元素名称
	 * @param value 元素值
	 * @return
	 */
	public BaseResponse setDocElement(String name, Object value) {
		this.doc.put(name, value);
		return this;
	}

	/**
	 * 返回相应消息体中的元素值。
	 * 
	 * @param name 元素名称
	 * @return
	 */
	public Object getDocElement(String name) {
		return this.doc.get(name);
	}

	/**
	 * 建议使用方法：getDocElement(name)
	 * 
	 * @return
	 */
	public Map<String, Object> getDoc() {
		return doc;
	}

	/**
	 * 建议使用方法：setDocElement(name, value)
	 * @param doc
	 * @return
	 */
	public BaseResponse setDoc(Map<String, Object> doc) {
		this.doc = doc;
		return this;
	}

	@Override
	public String toString() {
		return "BaseResponse [retcode=" + retcode + ", retinfo=" + retinfo + ", doc=" + doc + "]";
	}
	
}
