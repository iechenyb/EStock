package com.cyb.qutoes.websocket;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;


public class SystemWebSocketHandler implements WebSocketHandler {
	 
    private static final Log log =LogFactory.getLog(SystemWebSocketHandler.class);
 
    public static final ArrayList<WebSocketSession> users;
    static {
        users = new ArrayList<WebSocketSession>();
    }
 
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        System.out.println("SystemWebSocketHandler.afterConnectionEstablished connect to the websocket success......");
        users.add(session);
        String userName = (String) session.getHandshakeAttributes().get("username");
        if(userName!= null){
            int count =3;
            String str = "你好，"+userName+",你有共有"+count+"条信息未读！";
            session.sendMessage(formateMessage("text", str));
        }
//        pushTheUsersOnline();//推送在线用户
    }
 
    
    public void pushTheUsersOnline(){
    	System.out.println("pushTheUsersOnline");
    	Map msg = new HashMap();
    	msg.put("msgType", "users");
    	msg.put("lst", getOnlineUsers());
    	sendMessageToUsers(new TextMessage(JSONObject.fromObject(msg).toString()));
    }
    public List<String> getOnlineUsers(){
    	List<String> lst = new ArrayList<String>();
    	try {
	    	for (WebSocketSession user : users) {
	        	String userName = (String) user.getHandshakeAttributes().get("username");
	            	lst.add(userName);
	    	}
           } catch (Exception e) {
                e.printStackTrace();
            }
    	return lst;
    }
    public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) throws Exception {
    	System.out.println("SystemWebSocketHandler.handleMessage"+message);
    	String curUser = (String) session.getHandshakeAttributes().get("username");
    	JSONObject data = JSONObject.fromObject(message.getPayload());//
    	String content=curUser+"["+new Date().toString()+"]say:"+data.getString("msg").trim();
    	String toUser = data.getString("toUser");
    	if(toUser==null||"".equals(toUser)){
    		sendMessageToUsers(formateMessage("text", content));
    	}else{
    		sendMessageToUser(toUser,formateMessage("text", content));
    	}
    }
    
    
    public  TextMessage formateMessage(String type,String content){
    	Map msg = new HashMap();
    	msg.put("msgType", type);
    	msg.put("content",content);
    	return new TextMessage(JSONObject.fromObject(msg).toString());
    }
    public String getUserName(WebSocketSession session){
    	return (String) session.getHandshakeAttributes().get("username");
    }
    public static void main(String[] args) {
    	String str =  "{msg:'Here is a message!',toUser:4}";
    	JSONObject XX = JSONObject.fromObject(str);
    	System.out.println(XX.getString("msg"));
    	System.out.println(XX.getString("toUser"));
    	double x = -0.00068+0.000205;
    	double y =1/2.0;
    	System.out.println(x);
	}
 
    public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
       
    	if(session.isOpen()){
            session.close();
        }
        System.out.println("SystemWebSocketHandler.handleTransportError websocket connection closed......");
        users.remove(session);
        String exitUser = getUserName(session);
        String content = "用户"+exitUser+"退出聊天室！";
        sendMessageToUsers(formateMessage("text", content));
    }
 
    public void afterConnectionClosed(WebSocketSession session, CloseStatus closeStatus) throws Exception {
    	System.out.println("SystemWebSocketHandler.afterConnectionClosed websocket connection closed......");
        users.remove(session);
        String exitUser = getUserName(session);
        String content = "用户"+exitUser+"退出聊天室！";
        sendMessageToUsers(formateMessage("text", content));
    }
 
    public boolean supportsPartialMessages() {
        return false;
    }

    public void sendMessageToUsers(TextMessage message) {
    	System.out.println("sendMessageToUsers");
        for (WebSocketSession user : users) {
        	String userName = (String) user.getHandshakeAttributes().get("username");
        	System.out.println("to user:"+userName);
            try {
                if (user.isOpen()) {
                    user.sendMessage(message);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
 
    public void sendMessageToUser(String userName, TextMessage message) {
    	System.out.println("sendMessageToUser");
        for (WebSocketSession user : users) {
            if (user.getHandshakeAttributes().get("username").equals(userName)) {
                try {
                    if (user.isOpen()) {
                        user.sendMessage(message);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            }
        }
    }
}