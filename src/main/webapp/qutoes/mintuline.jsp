<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%  
	String basePath = "http://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath()+"/";  
%> 
<!doctype html>
<html ng-app="app" class="no-js">
<head>
<link rel="icon" href="<%=basePath%>img/favicon.ico" type="image/x-icon">
<link rel="shortcut icon" href="<%=basePath%>img/favicon.ico" type="image/x-icon">
<meta   http-equiv="Expires"   CONTENT="0">
<meta   http-equiv="Cache-Control"   CONTENT="no-cache">
<meta   http-equiv="Pragma"   CONTENT="no-cache">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link rel="stylesheet" href="<%=basePath%>css/estock.css">
<script type="text/javascript" src="<%=basePath%>push/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="<%=basePath%>push/socket.io.js"></script>
<title>股往金来-${code}-分钟行情</title>
<script>
/* var _hmt = _hmt || [];
(function() {
  var hm = document.createElement("script");
  hm.src = "//hm.baidu.com/hm.js?"+Math.random();
  var s = document.getElementsByTagName("script")[0]; 
  s.parentNode.insertBefore(hm, s);
})(); */
</script>
<link rel="stylesheet" href="<%=basePath%>amazeui/css/amazeui.min.css">
<link rel="stylesheet" href="<%=basePath%>amazeui/css/app.css">
<link rel="stylesheet" href="<%=basePath%>/css/estock.css">
<style type="text/css"></style>
</head>
<input type="hidden" id="meta" value=<%=request.getContextPath()%>/>
<input type="hidden" id="basePath" value=<%=basePath%>/>
<input type="hidden" value="${code}" id='code' name='code'/>
<body  ng-controller='controller' >
<div data-am-sticky="{top:0;}">
 	 <!-- <button class="am-btn am-btn-primary">Stick 80px below the top</button> -->
 	 <div class="am-g">
 	   <div  class="am-u-sm-1">&nbsp;</div>
	   <div class="am-u-sm-3"><a href="<%=basePath%>">
               <div style="color: #353535; font-family: 微软雅黑; font-size: 36px; padding:0px; font-weight:bold;height:60px;">
    			<span style="text-shadow: 2px 2px 2px #dd0,2px 2px 2px #ff0, 0px 0px 10px #0ff, 0px 0px 20px #f0f;">
    			股往<img alt="首页-搜索" src="<%=basePath%>img/logo.jpg" width="50px" height="40px"/>今来
    			</span>
 				</div>
	   </a></div>
	   <div  class="am-u-sm-8 ">&nbsp;</div>
     </div> 
</div>
  <!-- <div data-am-sticky>
    <button class="am-btn am-btn-primary am-btn-block">Stick to top</button>
  </div> -->
<div id='init'></div>
<div id="console" class="am-u-sm-1 am-md-text-left"></div>
 <!-- 为ECharts准备一个具备大小（宽高）的Dom window.location.href=<%=basePath%>wait.jsp-->
 <div class="am-g am-g-fixed">
   <div id="time" class="am-u-sm-6 am-md-text-left"></div>
   <div id="dpzs" class="am-u-sm-6 am-md-text-left"></div>
  </div>
   <div class="am-g am-g-fixed">
   <div id="infor" class="am-u-sm-10 am-md-text-left">&nbsp;</div>
   <div  class="am-u-sm-2 am-md-text-left" ng-click='add()'><a id="addZx" href="javascript:void(0)">添加到自选股</a></div>
  </div>
  <div class="am-g am-g-fixed">
	  <div id="lineChart" class="am-u-sm-12 echart"></div>
  </div> 
   <div class="am-g am-g-fixed">
	  <div id="kChart" class="am-u-sm-12 echart"></div>
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
    <script src="<%=basePath%>js/my/line.js"></script>  
    <script src="<%=basePath%>js/tongji.js?version=Math.random()"></script>  
  
  </body>
  </html>