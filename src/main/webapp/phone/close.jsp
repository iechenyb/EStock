<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%  
	String basePath = "http://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath()+"/";  
%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html ng-app="app">
<head>
<link rel="icon" href="<%=basePath%>img/favicon.ico" type="image/x-icon">
<link rel="shortcut icon" href="<%=basePath%>img/favicon.ico" type="image/x-icon">
<meta   http-equiv="Expires"   CONTENT="0">
<meta   http-equiv="Cache-Control"   CONTENT="no-cache">
<meta   http-equiv="Pragma"   CONTENT="no-cache">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link rel="stylesheet" href="<%=basePath%>css/estock.css">
<title>股往金来-${code}-分钟行情</title>
<link rel="stylesheet" href="<%=basePath%>amazeui/css/amazeui.min.css">
<link rel="stylesheet" href="<%=basePath%>amazeui/css/app.css">
<link rel="stylesheet" href="<%=basePath%>/css/estock.css">
<style type="text/css"></style>
</head>
<input type="hidden" id="meta" value=<%=request.getContextPath()%>/>
<input type="hidden" id="basePath" value=<%=basePath%>/>
<input type="hidden" value="<%=request.getParameter("code")%>" id='code' name='code'/>
<body  ng-controller='controller' style="background-color:black;" >
 <!-- 为ECharts准备一个具备大小（宽高）的Dom window.location.href=<%=basePath%>wait.jsp-->
  <div class="am-g">
	  <div id="closeChart" class="am-u-sm-12" style="height:750px;"></div>
  </div> 
    <!--[if (gte IE 9)|!(IE)]><!-->
    <script src="<%=basePath%>js/jquery.min.js"></script>
    <script src="<%=basePath%>js/jquery.js"></script>
    <script src="<%=basePath%>echarts/echarts-all.js"></script>
	<script src="<%=basePath%>amazeui/js/jquery.min.js"></script>
	<!--<![endif]-->
	<!--[if lte IE 8 ]>
	<script src="http://libs.baidu.com/jquery/1.11.3/jquery.min.js"></script>
	<script src="http://cdn.staticfile.org/modernizr/2.8.3/modernizr.js"></script>
	<script src="assets/js/amazeui.ie8polyfill.min.js"></script>
	<![endif]-->
	<script src="<%=basePath%>amazeui/js/amazeui.min.js"></script>
    <!-- ECharts单文件引入 -->
    <%-- <script src="<%=basePath%>js/jquery.min.js"></script> --%>
    <%-- <script src="<%=basePath%>js/jquery.js"></script> --%>
    <script src="<%=basePath%>angular/angular-1.0.1.min.js"></script>      
    <script src="<%=basePath%>phone/js/close.js"></script>  
    <script src="<%=basePath%>js/tongji.js?version=Math.random()"></script>  
  </body>
  </html>