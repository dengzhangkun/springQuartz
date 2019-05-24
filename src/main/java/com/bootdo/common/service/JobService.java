package com.bootdo.common.service;

import com.bootdo.common.domain.TaskDO;

import java.util.List;
import java.util.Map;

import org.quartz.SchedulerException;

/**
 * 
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2017-09-26 20:53:48
 */

public interface JobService {
	
	TaskDO get(Long id);


	int update(TaskDO taskDO);

	void initSchedule() throws SchedulerException;

	void changeStatus(Long jobId, String cmd) throws SchedulerException;



}
