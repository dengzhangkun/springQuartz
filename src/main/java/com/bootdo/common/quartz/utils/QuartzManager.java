package com.bootdo.common.quartz.utils;

import com.bootdo.common.domain.TaskDO;
import org.apache.log4j.Logger;
import org.quartz.*;
import org.quartz.DateBuilder.IntervalUnit;
import org.quartz.impl.matchers.GroupMatcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * @author dengzhangkun
 * @title: QuartzManager.java
 * @description: 计划任务管理
 */
@Service
public class QuartzManager {
    public final Logger log = Logger.getLogger(this.getClass());

    @Autowired
    private Scheduler scheduler;

    /**
     * 添加任务
     *
     * @throws SchedulerException
     */

    public void addJob(TaskDO taskDO) {
        try {
            // 创建jobDetail实例，绑定Job实现类
            // 指明job的名称，所在组的名称，以及绑定job类

            Class<? extends Job> jobClass = (Class<? extends Job>) (Class.forName(taskDO.getBeanClass())
                    .newInstance()
                    .getClass());
            JobDetail jobDetail = JobBuilder.newJob(jobClass).withIdentity(taskDO.getJobName(), taskDO
                    .getJobGroup())// 任务名称和组构成任务key
                    .build();
            // 定义调度触发规则
            // 使用cornTrigger规则
            Trigger trigger = TriggerBuilder.newTrigger().withIdentity(taskDO.getJobName(), taskDO.getJobGroup())
                    // 触发器key
                    .startAt(DateBuilder.futureDate(1, IntervalUnit.SECOND))
                    .withSchedule(CronScheduleBuilder.cronSchedule(taskDO.getCronExpression())).startNow()
                    .build();
            // 把作业和触发器注册到任务调度中
            scheduler.scheduleJob(jobDetail, trigger);
            // 启动
            if (!scheduler.isShutdown()) {
                scheduler.start();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * 删除一个job
     *
     * @param taskDO
     * @throws SchedulerException
     */
    public void deleteJob(TaskDO taskDO) throws SchedulerException {
        JobKey jobKey = JobKey.jobKey(taskDO.getJobName(), taskDO.getJobGroup());
        scheduler.deleteJob(jobKey);

    }


}