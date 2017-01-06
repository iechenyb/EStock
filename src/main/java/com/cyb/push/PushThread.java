package com.cyb.push;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PushThread  implements Runnable{
	Logger log = LoggerFactory.getLogger(PushThread.class);
	public void run() {
		Thread.currentThread().setName("消息推送线程");
		while (true) {
			try {
				try {
					PushServer.sendBroadcast("trading",null);
					Thread.sleep(5*1000);
				} catch (Exception e) {
					log.info("push ocur exception don't care....");
				}//广播推送消息
			} catch (Exception e) {
				log.info("push logic ocur exception, don't care....");
			}
		}
	}
}
