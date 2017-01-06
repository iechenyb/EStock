package com.cyb;

public class PutTask implements Runnable{
    public PutTask(String name){
    	
    }
	@Override
	public void run() {
		while(true){
			for(int i=1;i<MainTask.lenOfBatch;i++){
				String val = MainTask.x+++"";
				MainTask.queue.offer(val);
				//System.out.println("put,size="+MainTask.queue.size()+",value="+val);
			}
			try {
				Thread.sleep(MainTask.sleep);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
