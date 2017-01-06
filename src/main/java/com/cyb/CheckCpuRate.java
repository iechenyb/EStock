package com.cyb;

import com.cyb.thread.IMonitorService;
import com.cyb.thread.MonitorInfoBean;
import com.cyb.thread.MonitorServiceImpl;

public class CheckCpuRate implements Runnable{
  public void test(){
	  
  }
	public void run() {
		IMonitorService service = new MonitorServiceImpl();   
        MonitorInfoBean monitorInfo = null;
		try {
			monitorInfo = service.getMonitorInfoBean();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
		while(true){
	        //System.out.println("cpu占有率=" + monitorInfo.getCpuRatio()); 
			double rate = monitorInfo.getCpuRatio();
			if(rate>=98.0){
				System.out.println("System is over,because cpu is used to "+rate+" %");
				MainTask.fixedThreadPool.shutdown();
				MainTask.pool.shutdown();
				break;
			}
		}
	}
	 
 }