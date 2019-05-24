package com.bootdo.common.service.impl;

import com.bootdo.common.config.Constant;
import com.bootdo.common.dao.TaskDao;
import com.bootdo.common.domain.TaskDO;
import com.bootdo.common.quartz.utils.QuartzManager;
import com.bootdo.common.service.JobService;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author dengzhangkun
 * 实现类
 */
@Service
public class JobServiceImpl implements JobService {

	@Autowired
	private TaskDao taskDaoMapper;

	@Autowired
	QuartzManager quartzManager;

	@Override
	public TaskDO get(Long id) {
		return taskDaoMapper.get(id);
	}

	/**
	 * 更新数据库状态
	 */
	@Override
	public int update(TaskDO taskDO) {
		return taskDaoMapper.update(taskDO);
	}


	@Override
	public void initSchedule() throws SchedulerException {
		// 这里获取任务信息数据
		List<TaskDO> jobList = taskDaoMapper.list(new HashMap<String, Object>(16));
		for (TaskDO taskDO : jobList) {
			if ("start".equals(taskDO.getJobStatus())) {
				quartzManager.addJob(taskDO);
			}

		}
	}

	/**
	 * 修改定时器和数据库的状态
	 * @param jobId
	 * @param cmd
	 * @throws SchedulerException
	 */
	@Override
	public void changeStatus(Long jobId, String cmd) throws SchedulerException {
		TaskDO taskDO = get(jobId);
		if (taskDO == null) {
			return;
		}
		if (Constant.STATUS_RUNNING_STOP.equals(cmd)) {
			taskDO.setJobStatus(Constant.STATUS_RUNNING_STOP);
			quartzManager.deleteJob(taskDO);
		} else {
			if (Constant.STATUS_RUNNING_START.equals(cmd)) {
				taskDO.setJobStatus(Constant.STATUS_RUNNING_START);
                quartzManager.addJob(taskDO);
            }
		}
		
		update(taskDO);
	}



}
