package com.maiyajf.base.monitor.task.manager.dao;

import org.apache.ibatis.annotations.Param;

import com.maiyajf.base.monitor.task.manager.po.TaskManagerPO;

/**   
 * @Title TaskManagerForCreditDAO.java
 * @Package com.maiyajf.base.monitor.task.manager.dao
 * @Description 
 * @author zhangww
 * @date 2016年5月5日 上午10:48:07
 * @version V1.0   
 */
public interface TaskManagerForCreditDAO {
	
	/**
	 * 
	 * @param sTaskNumber
	 * @return
	 */
	public TaskManagerPO queryTaskLock(@Param("sTaskNumber") String sTaskNumber);

	/**
	 * 
	 * @param sTaskNumber 任务编号
	 * @param iVersion 任务版本号
	 * @param unlocked 任务是否锁定
	 * @return
	 */
	public int lockTask(@Param("sTaskNumber") String sTaskNumber, @Param("iVersion") int iVersion,
			@Param("unlocked") boolean unlocked);

	/**
	 * 
	 * @param sTaskNumber
	 * @return
	 */
	public int unlockTask(@Param("sTaskNumber") String sTaskNumber);
}
