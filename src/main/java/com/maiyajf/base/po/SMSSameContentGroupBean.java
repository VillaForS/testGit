package com.maiyajf.base.po;

import java.io.Serializable;

/**
 * @ClassName: SMSSameContentGroupBean
 * @Description: 相同内容实时群发-bean（目前只支持梦网通道）
 * @author: yunlei.hua
 * @date: 2016年2月23日 下午2:57:10
 */
public class SMSSameContentGroupBean implements Serializable{
	private static final long serialVersionUID = 1L;

	/** 短信类型 */
	private String serviceType;
	/** 通道编号 */
	private String channelNo;
	/** 短信内容 */
	private String smsContent;
	/** 手机号 */
	private String[] mobileArray;
	
	public String getServiceType() {
		return serviceType;
	}
	public void setServiceType(String serviceType) {
		this.serviceType = serviceType;
	}
	public String getChannelNo() {
		return channelNo;
	}
	public void setChannelNo(String channelNo) {
		this.channelNo = channelNo;
	}
	public String getSmsContent() {
		return smsContent;
	}
	public void setSmsContent(String smsContent) {
		this.smsContent = smsContent;
	}
	public String[] getMobileArray() {
		return mobileArray;
	}
	public void setMobileArray(String[] mobileArray) {
		this.mobileArray = mobileArray;
	}
	
}
