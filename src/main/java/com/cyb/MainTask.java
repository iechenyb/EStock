package com.cyb;

import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class MainTask {
 public static double x=0;
 public static  ConcurrentLinkedQueue<String> queue = new ConcurrentLinkedQueue<String>();
 public static int lenOfBatch = 1000;//放入1000条数据
 public static int sleep = 10;//每隔10毫秒
 public static int num = 5;
 final static ExecutorService fixedThreadPool = Executors.newFixedThreadPool(num); 
 final static ScheduledThreadPoolExecutor pool = new ScheduledThreadPoolExecutor(5);
 
 public static void main(String[] args) {
	 /*ExecutorService fixedThreadPool = Executors.newFixedThreadPool(3);  
	 fixedThreadPool.execute(new Thread(new PutTask("put thread0"),"put thread0") );*/
	 exe1(num);
	 //new Thread(new CheckCpuRate()).start();
	 //exe2(100);
}
 

 public  static void exe3(){
	 PrintTask task = new PrintTask("object");
	 fixedThreadPool.execute(task);
	 fixedThreadPool.execute(task);
	 fixedThreadPool.execute(task);
	 fixedThreadPool.execute(task);
	 fixedThreadPool.execute(task);
	 fixedThreadPool.execute(task);
	 System.out.println("over!");
 }
 public  static void exe1(int num){
	 pool.schedule(new Thread(new PutTask("put thread"),"put thread"), 0, TimeUnit.SECONDS);
	 final  ScheduledThreadPoolExecutor pool1 = new ScheduledThreadPoolExecutor(5);
	 pool1.schedule(new Thread(new GetTask(""),""), 1, TimeUnit.SECONDS);
	/* for(int i=0;i<num;i++){
		 String name="eator"+i;
		 pool.schedule(new Thread(new GetTask(name),name), 1, TimeUnit.SECONDS);
	 }*/
	 /*pool.schedule(new Thread(new GetTask("eator 2"),"eator 2"), 2, TimeUnit.SECONDS);
	 pool.schedule(new Thread(new GetTask("eator 3"),"eator 3"), 3, TimeUnit.SECONDS);
	 pool.schedule(new Thread(new GetTask("eator 4"),"eator 4"), 4, TimeUnit.SECONDS);
	 pool.schedule(new Thread(new GetTask("eator 5"),"eator one"), 5, TimeUnit.SECONDS);
	 pool.schedule(new Thread(new GetTask("eator 6"),"eator two"), 6, TimeUnit.SECONDS);
	 pool.schedule(new Thread(new GetTask("eator 7"),"eator three"), 7, TimeUnit.SECONDS);
	 pool.schedule(new Thread(new GetTask("eator 8"),"eator four"), 8, TimeUnit.SECONDS);*/	 
 }
 public  static void exe2(int num){
	 Thread puttask = new Thread(new PutTask("put thread"),"put thread");
	 Thread task = new Thread(new GetTask("eat thread"),"eat thread");
	 pool.schedule(puttask, 0, TimeUnit.SECONDS);
	 for(int i=0;i<num;i++){
		 String name="eator"+i;
		 pool.schedule(task, 1, TimeUnit.SECONDS);
	 }
	 
	/* pool.schedule(task, 2, TimeUnit.SECONDS);
	 pool.schedule(task, 3, TimeUnit.SECONDS);
	 pool.schedule(task, 4, TimeUnit.SECONDS);
	 pool.schedule(task, 5, TimeUnit.SECONDS);
	 pool.schedule(task, 6, TimeUnit.SECONDS);
	 pool.schedule(task, 7, TimeUnit.SECONDS);
	 pool.schedule(task, 8, TimeUnit.SECONDS);
	 pool.schedule(task, 9, TimeUnit.SECONDS);
	 pool.schedule(task, 10, TimeUnit.SECONDS);
	 pool.schedule(task, 11, TimeUnit.SECONDS);
	 pool.schedule(task, 12, TimeUnit.SECONDS);*/
 }
}
