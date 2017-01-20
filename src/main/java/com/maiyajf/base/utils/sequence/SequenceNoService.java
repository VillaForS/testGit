package com.maiyajf.base.utils.sequence;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.maiyajf.base.spring.SpringContextHolder;
import com.maiyajf.base.utils.sequence.dao.ISequenceNoDao;

/**
 * @ClassName: SequenceNoService
 * @Description: 
 * @author: yunlei.hua
 * @date: 2016年1月19日 上午11:59:53
 */
@Service
public class SequenceNoService {
	
	/**
	 * @Title: getSequenceNo
	 * @Description: 调用存储过程获得序号
	 * @return: String
	 */
	@Transactional(propagation = Propagation.NOT_SUPPORTED)
	public String getSequenceNo (String tableName) {
		ISequenceNoDao sequenceNoDao = SpringContextHolder.getBean(ISequenceNoDao.class);
		
		Map<String, String> params = new HashMap<String, String>();
		params.put("tableName", tableName);
		params.put("sequenceNo", "");
		sequenceNoDao.getSequenceNo(params);
		return params.get("sequenceNo");
	}

}
