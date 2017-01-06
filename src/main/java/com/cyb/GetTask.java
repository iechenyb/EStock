package com.cyb;

public class GetTask implements Runnable{
    public GetTask(String name){
    	
    }
	@Override
	public void run() {
		String log = Thread.currentThread().getName()+" over!";
		print(log);
		/*while(true){
				if(MainTask.queue.size()>1000){
					//synchronized(MainTask.queue){
					for(int i=1;i<=1000;i++){
						synchronized(this){//MainTask.queue
							String val = MainTask.queue.poll();
							if(val!=null){
								String log = Thread.currentThread().getName()+" eat at "+i+","+val;
								print(log);
							}
						}
				    }
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
			}
		}*/
	}
  public void print(String str){
	  System.out.println(str);
  }
}