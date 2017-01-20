package com.maiyajf.base.utils.sequence.dao;

import java.util.Map;

/**
 * @ClassName: ISequenceNoDao
 * @Description: 生成序号
 * @author: yunlei.hua
 * @date: 2016年1月19日 上午10:25:21
 */
public interface ISequenceNoDao {

	/**
	 * @Title: getSequenceNo
	 * @Description: 调用存储过程获得序号
	 * @return: void
	 */
	void getSequenceNo(Map<String, String> params);
}
