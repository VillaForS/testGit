package com.maiyajf.base.po;

import java.io.Serializable;
import java.util.Arrays;

/**
 * @ClassName: SMSTime
 * @Description: 定时短信批量实体
 * @author: yunlei.hua
 * @date: 2016年2月16日 下午3:28:59
 */
public class SMSTime implements Serializable {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

	private String batchID;
	private String serviceType;
	private String channelNo;
	private SMSBean[] smsBeans;

	public String getBatchID() {
		return batchID;
	}

	public void setBatchID(String batchID) {
		this.batchID = batchID;
	}

	public SMSBean[] getSmsBeans() {
		return smsBeans;
	}

	public void setSmsBeans(SMSBean[] smsBeans) {
		this.smsBeans = smsBeans;
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
		return "SMSTime [batchID=" + batchID + ", serviceType=" + serviceType
				+ ", channelNo=" + channelNo + ", smsBeans="
				+ Arrays.toString(smsBeans) + "]";
	}
}
