<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<script type="text/javascript" src="http://code.hs-cn.com/jquery/jquery-1.7.1.min.js"></script>
<script type="text/javascript" src="http://cdn.sockjs.org/sockjs-0.3.min.js"></script>
<title>Insert title here</title>
<title>Insert title here</title>
</head>
<body>
<CENTER>欢迎来到springmvc学习！</center>
<P>1,配置文件   applicationContext。xml bean容器  ； spring-servlet。xml 视图解析器    </P>
<p>2,传参</p>
<p>3,AOP</p>
<p>4,quartz</p>
<p>5,annotation</p>
        <script>
            var websocket;
            if ('WebSocket' in window) {
                websocket = new WebSocket("ws://localhost:8080/springmvc/webSocketServer");
            } else if ('MozWebSocket' in window) {
                websocket = new MozWebSocket("ws://localhost:8080/springmvc/webSocketServer");
            } else {
                websocket = new SockJS("http://localhost:8080/springmvc/sockjs/webSocketServer");
            }
            websocket.onopen = function (evnt) {
            };
            websocket.onmessage = function (evnt) {
                $("#msgcount").html("(<font color='red'>"+evnt.data+"</font>)")
            };
            websocket.onerror = function (evnt) {
            };
            websocket.onclose = function (evnt) {
            }
        </script>
        <div id='msgcount'></div>
</body>
</html>