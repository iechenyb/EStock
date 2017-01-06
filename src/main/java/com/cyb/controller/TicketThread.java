package com.cyb.controller;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.Semaphore;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.cyb.utils.PropertyUtil;

public class TicketThread  implements  Callable<String>{
	Log log = LogFactory.getLog(TicketThread.class);
	private   int client_num = 50;
	private   int max_tickets = 100;
	private int semaphore = 1;
	volatile  int number = 10;//volatile
	private int window = -1;
	public TicketThread(){
		
	}
	public TicketThread(int clients,int tickesNum,int window,int sema){
		this.max_tickets = tickesNum;//在这个停车场系统中，车位是公共资源，每辆车好比一个线程，看门人起的就是信号量的作用。
		this.client_num =clients;
		this.window = window;
		this.semaphore = sema;
	}
	public String call() throws Exception {
		ExecutorService exec = Executors.newCachedThreadPool();
		final Semaphore semp = new Semaphore(semaphore,true);
		try{
			
			for (int index = 1; index <= client_num; index++) {
				final int NO = index;
				Callable<String> run = new Callable<String>() {
					public  String  call() throws Exception{//
						Thread.currentThread().setName("窗口["+NO+"]");
						/*try {*/
							semp.acquire();
							try {
								Thread.sleep(1);
							} catch (InterruptedException e1) {
								e1.printStackTrace();
							}
							log.info("Thread:" + NO);
							String host = PropertyUtil.get("basePath")+"ticket/buy.zc?";
							int count = (int)(Math.random()*max_tickets+1);
							String para = "threadNo="+NO+"&number="+count+"&window="+window;
							log.info(host + para);
							URL url = new URL(host+para);
							HttpURLConnection connection = (HttpURLConnection) url
									.openConnection();
							connection.setConnectTimeout(50*10000);
							connection.setReadTimeout(50*10000);
							connection.setDoOutput(true);
							connection.setDoInput(true);
							PrintWriter out = new PrintWriter(
									connection.getOutputStream());
							out.print(para);
							out.flush();
							out.close();
							BufferedReader in = new BufferedReader(new InputStreamReader(
											connection.getInputStream(),"utf-8"));
							String line = "";
							String result = "";
							while ((line = in.readLine()) != null) {
								result += line;
							}
							semp.release();
							return "scuess";
						/*} catch (Exception e) {
							e.printStackTrace();
							return "failed";
						} 不能捕获异常，否则上层进程无法获取异常，从而导致客户端一直等待，发现异常，直接抛出*/
					}
				};//end run
				try{
					Future<String> result = exec.submit(run);
					log.info("执行结果："+result.get());
				}catch(Exception e){
					log.info("failed!"+e.toString());
					return "failed!"+e.toString();
				}
			}//end for
		}catch(Exception e){
			log.info("failed!"+e.toString());
			return "failed!"+e.toString();
		}
		exec.shutdown();
		return "success sale!";
	}
	 public static void main(String[] args){
		 
	 }
}
