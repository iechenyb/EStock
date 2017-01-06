package com.cyb.push;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.sf.json.JSONObject;

import com.corundumstudio.socketio.AckRequest;
import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.SocketIOServer;
import com.corundumstudio.socketio.listener.DataListener;

public class PushListener implements DataListener<Object>{
  public static Logger log = LoggerFactory.getLogger(PushListener.class);
  SocketIOServer server;
  public PushListener(SocketIOServer server){
  	this.server = server;
  }
  public void onData(SocketIOClient client, Object action, AckRequest req)  {
	  try{
		log.info("有客户主动请求数据，"+action+",client:"+client+","+JSONObject.fromObject(action));
		client.sendEvent("estock",JSONObject.fromObject(action) );
	  }catch(Exception e){
		  e.printStackTrace();
	  }
  }
}