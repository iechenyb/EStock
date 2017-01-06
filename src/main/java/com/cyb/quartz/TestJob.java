package com.cyb.quartz;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.scheduling.annotation.Scheduled;

import com.cyb.phone.service.PhoneStockServiceImpl;

public class TestJob {
	
	Log log = LogFactory.getLog(TestJob.class);
	@Resource(name = "phoneStockService")
	PhoneStockServiceImpl phoneStockService;
	//@Scheduled(cron="*/1 * 07-18 ? * MON-FRI")
	public void testMethod(){
		  /*log.info("I am a shecadual job!");*/
	}
}
