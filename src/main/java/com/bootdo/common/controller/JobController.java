package com.bootdo.common.controller;

import com.bootdo.common.service.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


/**
 * @author dengzhangkun
 * 启动类入口
 */
@Controller
@RequestMapping("/common/job")
public class JobController {
	@Autowired
	private JobService taskScheduleJobService;

	/**
	 * http://localhost:8888/common/job/changeJobStatus
	 * @param id:1,2
	 * @param cmd:start,stop
	 * @return
	 */
	@PostMapping(value = "/changeJobStatus")
	@ResponseBody
	public void changeJobStatus(Long id,String cmd ) {

		String label = "停止";
		if ("start".equals(cmd)) {
			label = "启动";
		} else {
			label = "停止";
		}
		try {
			taskScheduleJobService.changeStatus(id, cmd);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
