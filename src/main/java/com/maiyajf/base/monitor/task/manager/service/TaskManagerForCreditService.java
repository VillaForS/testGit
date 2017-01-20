package com.maiyajf.base.monitor.task.manager.service;

import org.springframework.stereotype.Component;

import com.maiyajf.base.monitor.task.manager.dao.TaskManagerForCreditDAO;
import com.maiyajf.base.monitor.task.manager.po.TaskManagerPO;
import com.maiyajf.base.spring.SpringContextHolder;
import com.maiyajf.base.utils.log.ExceptionLogger;

/**   
 * @Title TaskManagerForCreditService.java
 * @Package com.maiyajf.base.monitor.task.manager.service
 * @Description 
 * @author zhangww
 * @date 2016年5月5日 上午11:14:23
 * @version V1.0   
 */
@Component
public class TaskManagerForCreditService {
	private static final int LOCKED = 1;
	/**
	 * 锁定任务编号对应的定时任务，以阻止并发操作可能引起的数据混乱
	 * 。
	 * @param sTaskNumber 任务唯一编号
	 * @return true 锁定成功 false 锁定失败
	 */
	public boolean lockTask(String sTaskNumber) {
		// taskMgrDAO不可作为属性用@Autowired，有的模块用不到该功能就不会配置该DAO，一旦用@Autowired就会报错。
		TaskManagerForCreditDAO taskMgrDAO = SpringContextHolder.getBean(TaskManagerForCreditDAO.class);
		TaskManagerPO tm = taskMgrDAO.queryTaskLock(sTaskNumber);
		if (tm == null) {
			ExceptionLogger.error(sTaskNumber, "该任务编号没有对应的任务！");
			return false;
		}
		boolean unlocked = true; // 任务为锁定
		if (LOCKED == tm.getiLockStatus()) { // 任务锁定
			// 任务锁定有效期，如果未设置锁定有效期这里默认10分钟（600s）
			int iValidLockPeriod = tm.getiValidLockPeriod() == 0 ? 600 : tm.getiValidLockPeriod();
			if ((System.currentTimeMillis() - tm.getdLockDate().getTime()) <= iValidLockPeriod * 1000) { // 任务还在锁定有效期
				ExceptionLogger.error(sTaskNumber, "该任务锁定失败！");
				return false;
			} else { // 过了锁定有效期
				ExceptionLogger.error(sTaskNumber, "该任务编号对应的任务已过了锁定有效期仍未被解锁！");
				unlocked = false;
			}
		}
		if (0 == taskMgrDAO.lockTask(tm.getsTaskNumber(), tm.getiVersion(), unlocked)) {
			ExceptionLogger.error(sTaskNumber, "该任务锁定失败！");
			return false;
		}
		return true;
	}

	/**
	 * 解锁任务编号对应的定时任务。
	 * 
	 * @param sTaskNumber 任务唯一编号
	 */
	public void unlockTask(String sTaskNumber) {
		TaskManagerForCreditDAO taskMgrDAO = SpringContextHolder.getBean(TaskManagerForCreditDAO.class);
		if (taskMgrDAO.unlockTask(sTaskNumber) == 0) {
			ExceptionLogger.error(sTaskNumber, "该任务解锁失败");
		}
	}
}
