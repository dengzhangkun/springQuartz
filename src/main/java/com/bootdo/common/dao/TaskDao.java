package com.bootdo.common.dao;

import com.bootdo.common.domain.TaskDO;

import java.util.List;
import java.util.Map;


/**
 * @author dengzhangkun
 * DAO
 */
public interface TaskDao {

	TaskDO get(Long id);

	List<TaskDO> list(Map<String,Object> map);

	int update(TaskDO task);
}
