package com.maiyajf.base.monitor.task.manager.po;

import java.util.Date;

/**
 * 
 * @author JiaXX
 * @date 2016年4月11日 下午2:28:47
 * @version 1.0
 */
public class TaskManagerPO {
	private String sGuid;
	private String sTaskNumber;
	private String sTaskName;
	private int iLockStatus;
	private Date dLockDate;
	private Date dUnlockDate;
	private int iValidLockPeriod;
	private String sRemark;
	private int iVersion;
	private int iDelFlag;

	public String getsGuid() {
		return sGuid;
	}

	public void setsGuid(String sGuid) {
		this.sGuid = sGuid;
	}

	public String getsTaskNumber() {
		return sTaskNumber;
	}

	public void setsTaskNumber(String sTaskNumber) {
		this.sTaskNumber = sTaskNumber;
	}

	public String getsTaskName() {
		return sTaskName;
	}

	public void setsTaskName(String sTaskName) {
		this.sTaskName = sTaskName;
	}

	public int getiLockStatus() {
		return iLockStatus;
	}

	public void setiLockStatus(int iLockStatus) {
		this.iLockStatus = iLockStatus;
	}

	public Date getdLockDate() {
		return dLockDate;
	}

	public void setdLockDate(Date dLockDate) {
		this.dLockDate = dLockDate;
	}

	public Date getdUnlockDate() {
		return dUnlockDate;
	}

	public void setdUnlockDate(Date dUnlockDate) {
		this.dUnlockDate = dUnlockDate;
	}

	public int getiValidLockPeriod() {
		return iValidLockPeriod;
	}

	public void setiValidLockPeriod(int iValidLockPeriod) {
		this.iValidLockPeriod = iValidLockPeriod;
	}

	public String getsRemark() {
		return sRemark;
	}

	public void setsRemark(String sRemark) {
		this.sRemark = sRemark;
	}

	public int getiVersion() {
		return iVersion;
	}

	public void setiVersion(int iVersion) {
		this.iVersion = iVersion;
	}

	public int getiDelFlag() {
		return iDelFlag;
	}

	public void setiDelFlag(int iDelFlag) {
		this.iDelFlag = iDelFlag;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("TaskManager [sGuid=").append(sGuid).append(", sTaskNumber=").append(sTaskNumber)
				.append(", sTaskName=").append(sTaskName).append(", iLockStatus=").append(iLockStatus)
				.append(", dLockDate=").append(dLockDate).append(", dUnlockDate=").append(dUnlockDate)
				.append(", iValidLockPeriod=").append(iValidLockPeriod).append(", sRemark=").append(sRemark)
				.append(", iVersion=").append(iVersion).append(", iDelFlag=").append(iDelFlag).append("]");
		return builder.toString();
	}

}
