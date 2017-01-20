package com.maiyajf.base.po;

import java.io.Serializable;

/**
 * @ClassName: RealTimeSMSBean
 * @Description: 实时短信发送bean
 * @author: yunlei.hua
 * @date: 2015年10月20日 下午5:21:06
 */
public class RealTimeSMSBean implements Serializable {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

	private String smsID;
	private String smsContent;
	private String sMobile;
	private String serviceType;
	private String channelNo;
	private String smsAbstract;

	public String getSmsAbstract() {
		return smsAbstract;
	}

	public void setSmsAbstract(String smsAbstract) {
		this.smsAbstract = smsAbstract;
	}

	public String getSmsID() {
		return smsID;
	}

	public void setSmsID(String smsID) {
		this.smsID = smsID;
	}

	public String getSmsContent() {
		return smsContent;
	}

	public void setSmsContent(String smsContent) {
		this.smsContent = smsContent;
	}

	public String getsMobile() {
		return sMobile;
	}

	public void setsMobile(String sMobile) {
		this.sMobile = sMobile;
	}

	public String getServiceType() {
		return serviceType;
	}

	public String getChannelNo() {
		return channelNo;
	}

	public void setServiceType(String serviceType) {
		this.serviceType = serviceType;
	}

	public void setChannelNo(String channelNo) {
		this.channelNo = channelNo;
	}

	@Override
	public String toString() {
		return "RealTimeSMSBean [smsID=" + smsID + ", smsContent=" + smsContent
				+ ", sMobile=" + sMobile + ", serviceType=" + serviceType
				+ ", channelNo=" + channelNo + ", smsAbstract=" + smsAbstract
				+ "]";
	}
}
