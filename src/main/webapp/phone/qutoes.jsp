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

 <style type="text/css">
body{
/*width:100px;*/
height:1200px;
overflow:auto;/*自动出现滚动条，如果要出现竖直滚动条则改成：overflow-y:auto，如果横向出现滚动条则改成：overflow-x:auto*/
scrollbar-face-color:#F00;/*滚动条凸出部分的颜色(前景色),包括两端的方形按钮、水平或竖直滑动的滑块的颜色*/
scrollbar-track-color:#0FF;/*滚动条的背景颜色,如果省略的话将出现虚点，颜色将采用face-color的颜色*/
scrollbar-arrow-color:#FFF;/*按钮(上下或者左右可以点击使滑块滚动的方形按钮)上三角箭头的颜色*/
scrollbar-3dlight-color:#0F0;/*滚动条亮边的颜色,形成3D效果，有层次感，肉眼观察在滚动条左边及上边出现一条有色线（竖直方向滚动）*/
scrollbar-darkshadow-color:#00F;/*滚动条强阴影的颜色,肉眼观察出现滚动条下边及右边*/
scrollbar-highlight-color:#F0F;/*滚动条空白部分的颜色,肉眼观察改变不明显，具体颜色改变出现在左边和上边空白处，介于face-color效果与3dlingt-color效果之间有个空白颜色（默认为白色）。此外，滚动条前景色有种凹陷的感觉，周边线条颜色凸起*/
scrollbar-shadow-color:#006600;/*立体滚动条阴影的颜色，具体出现在滑块及方形按钮的右边及下边，形成阴影效果，颜色介于face-color效果和darkshadow-color效果之间，不是很明显*/
scrollbar-base-color:#0f0;/*滚动条的基本颜色,当前面7个效果出现时，滚动条基本颜色设置肉眼很难观察到，如果不设置前面7个效果,系统将根据base-color自动设置，其中前景色，背景色(虚点表示)颜色一致，其他效果(阴影以黑色填充),没有什么要求时，建议不设置此效果*/

scrollbar-face-color:#F00;
scrollbar-track-color:#FFF;
scrollbar-arrow-color:#FFF;
/*这3个效果建议必须设置*/
scrollbar-arrow-color: #f4ae21; 
scrollbar-face-color: #333; 
scrollbar-3dlight-color: #666; 
scrollbar-highlight-color: #666; 
scrollbar-shadow-color: #999; 
scrollbar-darkshadow-color: #666; 
scrollbar-track-color: #666; 
}
.font{ color:#006600}
</style>
</head>
<input type="hidden" id="meta" value=<%=request.getContextPath()%>/>
<input type="hidden" id="basePath" value=<%=basePath%>/>
<input type="hidden" value="<%=request.getParameter("code")%>" id='code' name='code'/>
<body  ng-controller='controller'  style="background-color:black;">
  <div class="am-g" id="lineChart"  style="height:650px;">
  </div> 
   <div class="am-g" id="kChart" style="height:650px;">	 
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
    <script src="<%=basePath%>phone/js/qutoes.js"></script>  
    <script src="<%=basePath%>js/tongji.js?version=Math.random()"></script>  
  </body>
  </html>