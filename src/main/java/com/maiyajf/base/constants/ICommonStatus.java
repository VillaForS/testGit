package com.maiyajf.base.constants;

/**
 * @ClassName: ICommonStatus
 * @Description: 公用状态
 * @author: yunlei.hua
 * @date: 2015年10月27日 下午5:32:43
 */
public interface ICommonStatus {
	
	/** 记录状态：无效 */
	int DEL_FLAG_DISABLED = 0;
	/** 记录状态：有效 */
	int DEL_FLAG_ENABLED = 1;
	
	/** 用户状态：正常 */
	int USER_STATUS_NORMAL = 0;
	/** 用户状态：停用 */
	int USER_STATUS_STOP = 1;
	/** 用户状态：冻结 */
	int USER_STATUS_FROZEN = 2;
	
	
	/** 合同状态：生效 */
	int CONSTRACT_STATUS_ACTIVE = 10;
	/** 合同状态：结束 */
	int CONSTRACT_STATUS_FINISH = 20;
	/** 合同状态：中止 */
	int CONSTRACT_STATUS_SUSPEND = 30;
	
	/** 合同表中放款状态：待放款 */
	int CONSTRACT_LOAN_STATUS_WAITING = 10;
	/** 合同表中放款状态：放款中 */
	int CONSTRACT_LOAN_STATUS_LOANING = 20;
	/** 合同表中放款状态：放款成功 */
	int CONSTRACT_LOAN_STATUS_SUCCESS = 30;
	
	/** 合同表中还款状态：未还款 */
	int CONSTRACT_REPAY_STATUS_NO = 10;
	/** 合同表中还款状态：部分还款 */
	int CONSTRACT_REPAY_STATUS_PART = 20;
	/** 合同表中还款状态：已还清 */
	int CONSTRACT_REPAY_STATUS_PAYOFF = 30;
	
	/** 放款状态：待放款 */
	int GRANT_STATUS_WAITING = 0;
	/** 放款状态：放款成功 */
	int GRANT_STATUS_SUCCESS = 10;
	/** 放款状态：放款失败 */
	int GRANT_STATUS_FAIL = 20;
	/** 放款状态：放款中 */
	int GRANT_STATUS_LOANING = 30;
	/** 放款状态：处理异常 */
	int GRANT_STATUS_EXCEPTION = 40;
	
	
	/** 人工信审任务：待审核 */
	int MANUAL_AUDIT_TASK_STATUS_WAITING = 0;
	/** 人工信审任务：审核中 */
	int MANUAL_AUDIT_TASK_STATUS_AUDITING = 10;
	/** 人工信审任务：审核结束 */
	int MANUAL_AUDIT_TASK_STATUS_EDN = 20;
}
