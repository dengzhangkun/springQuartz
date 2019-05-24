package com.bootdo.common.listenner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.bootdo.common.quartz.utils.QuartzManager;
import com.bootdo.common.service.JobService;

/**
 * @author  dengzhangkun
 * 监听类
 *
 */
@Component
@Order(value = 1)
public class ScheduleJobInitListener implements CommandLineRunner {

	@Autowired
	JobService jobService;

	@Override
	public void run(String... arg0) throws Exception {
		try {
			jobService.initSchedule();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}