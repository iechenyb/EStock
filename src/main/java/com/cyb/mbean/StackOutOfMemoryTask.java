package com.cyb.mbean;

public class StackOutOfMemoryTask {
	 public static int count = 1;

	    public  void noStop() {

	        while (true) {

	        }

	    }

	    public  void newThread() {

	        while (true) {

	            Thread t = new Thread(new Runnable() {

	                public void run() {

	                    System.out.println("已创建第"+count+++"个线程");

	                    noStop();

	                }

	            });

	            t.start();

	        }
	    }

	    public static void main(String[] args){
	        new StackOutOfMemoryTask().newThread();
	    }

	}
