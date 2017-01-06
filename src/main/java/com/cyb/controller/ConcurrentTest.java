package com.cyb.controller;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
public class ConcurrentTest {
	private static  int thread_num = 1;
	private static  int client_num = 50;
	volatile static int number = 10;//volatile
	public ConcurrentTest(){
		
	}
	public ConcurrentTest(int reqs,int limit){
		this.thread_num = limit;
		this.client_num =reqs;
	}
	public static void startSale(){
		ExecutorService exec = Executors.newCachedThreadPool();// 50���߳̿���ͬʱ����
		final Semaphore semp = new Semaphore(thread_num,true);// ģ��2000���ͻ��˷���
		for (int index = 0; index < client_num; index++) {
			final int NO = index;
			Runnable run = new Runnable() {
				public  void  run() {
					try {
						semp.acquire();
						try {
							Thread.sleep(1);
						} catch (InterruptedException e1) {
							e1.printStackTrace();
						}
						System.out.println("Thread:" + NO);
						String host = "http://localhost:8888/EStock/ticket/buy.zc?";
						int count = (int)(Math.random()*10+1);
						String para = "threadNo="+NO+"&number="+count;
						System.out.println(host + para);
						URL url = new URL(host+para);
						HttpURLConnection connection = (HttpURLConnection) url
								.openConnection();
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
						/*if(--number>0){
							System.out.println("Thread:" + NO+","+number);
						}*/
						// �ͷ�
						semp.release();
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			};
			exec.execute(run);
		}
		exec.shutdown();
	}
	public static void main(String[] args) {
		new ConcurrentTest(100,1).startSale();
	}
}
