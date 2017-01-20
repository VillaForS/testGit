package com.maiyajf.base.monitor.task.manager.dao;

import org.apache.ibatis.annotations.Param;
import com.maiyajf.base.monitor.task.manager.po.TaskManagerPO;

/**
 * 
 * @author JiaXX
 * @date 2016年4月11日 下午4:09:12
 * @version 1.0
 */
public interface TaskManagerDAO {
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
