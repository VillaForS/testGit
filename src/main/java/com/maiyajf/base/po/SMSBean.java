package com.maiyajf.base.po;

import java.io.Serializable;

/**
 * @ClassName: SMSBean
 * @Description: 定时短信-po
 * @author: yunlei.hua
 * @date: 2016年2月16日 下午3:29:29
 */
public class SMSBean implements Serializable {
	private static final long serialVersionUID = 708609660779110539L;
	
	private String smsID;
	private String smsContent;
	private String sMobile;
	private String smsAbstract;
	private String smsPlanBeginDate;
	private String smsSendEndDate;

	public String getSmsPlanBeginDate() {
		return smsPlanBeginDate;
	}

	public void setSmsPlanBeginDate(String smsPlanBeginDate) {
		this.smsPlanBeginDate = smsPlanBeginDate;
	}

	public String getSmsSendEndDate() {
		return smsSendEndDate;
	}

	public void setSmsSendEndDate(String smsSendEndDate) {
		this.smsSendEndDate = smsSendEndDate;
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

	public String getSmsAbstract() {
		return smsAbstract;
	}

	public void setSmsAbstract(String smsAbstract) {
		this.smsAbstract = smsAbstract;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((smsID == null) ? 0 : smsID.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SMSBean other = (SMSBean) obj;
		if (smsID == null) {
			if (other.smsID != null)
				return false;
		} else if (!smsID.equals(other.smsID))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "SMSBean [smsID=" + smsID + ", smsContent=" + smsContent
				+ ", sMobile=" + sMobile + ", smsAbstract=" + smsAbstract
				+ ", smsPlanBeginDate=" + smsPlanBeginDate
				+ ", smsSendEndDate=" + smsSendEndDate + "]";
	}
}
