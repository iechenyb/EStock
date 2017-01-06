package com.cyb.qutoes.websocket;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.WebSocketHttpHeaders;

public class WebSocketHandshakeInterceptor implements org.springframework.web.socket.server.HandshakeInterceptor {

	@Override
	public boolean beforeHandshake(ServerHttpRequest request,
			ServerHttpResponse response, WebSocketHandler wsHandler,
			Map<String, Object> attributes) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void afterHandshake(ServerHttpRequest request,
			ServerHttpResponse response, WebSocketHandler wsHandler,
			Exception exception) {
		// TODO Auto-generated method stub
		
	}/*
	 HttpSession session = null; 
	 Map<String, Object> params = null;
    private static Log logger = LogFactory.getLog(WebSocketHandshakeInterceptor.class);
    public boolean beforeHandshake(ServerHttpRequest request, ServerHttpResponse response, 
    		WebSocketHandler wsHandler, Map<String, Object> attributes) throws Exception {
    	System.out.println("WebSocketHandshakeInterceptor.beforeHandshake");
        if (request instanceof ServletServerHttpRequest) {
            ServletServerHttpRequest servletRequest = (ServletServerHttpRequest) request;
            String paraName = ((ServletServerHttpRequest) request).getServletRequest().getParameter("name");
            System.out.println("paraName from ws url name="+paraName);
            this.session = servletRequest.getServletRequest().getSession(false);
            if (session != null) {
//                String userName = (String) session.getAttribute("username");//servlet��½�Ĳ���
//                System.out.println("add username="+userName);
                attributes.put("username",paraName);
                attributes.put("password","test1");
            }
            params = attributes;
            WebSocketHttpHeaders x;
        }
        return true;
    }
 
    public void afterHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler, Exception exception) {
    	System.out.println("WebSocketHandshakeInterceptor.afterHandshake");
    	System.out.println("***"+params.get("username")+","+params.get("password"));
    }
*/}