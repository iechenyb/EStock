package com.cyb;

public class PrintTask implements Runnable {
  public String str = null;	
  public Object obj = new Object();
  PrintTask(String str){
	  this.str = str;
  }
	@Override
	public void run() {
		synchronized (this) {//保证线程有序进行
			for(int i=0;i<5;i++){
				System.out.println(Thread.currentThread().getName()+",i="+i);
			}
			System.out.println(Thread.currentThread().getName()+" over!");
		}
	}

}
