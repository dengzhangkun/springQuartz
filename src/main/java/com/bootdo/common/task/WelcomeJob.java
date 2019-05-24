package com.bootdo.common.task;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.stereotype.Component;


/**
 * @author dengzhangkun
 * 具体执行类
 */
@Component
public class WelcomeJob implements Job{

    @Override
    public void execute(JobExecutionContext arg0) throws JobExecutionException {
        System.out.println("自定义定时任务启动中。。。。" + arg0.getJobDetail());

    }
}