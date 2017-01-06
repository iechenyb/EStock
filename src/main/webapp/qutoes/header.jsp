<%@ page language="java" contentType="text/html; charset=utf-8"    pageEncoding="utf-8"%>
<%  
String basePath = "http://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath()+"/";  
%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title></title>
<link rel="icon" href="<%=basePath%>img/favicon.ico" type="image/x-icon">
<link rel="shortcut icon" href="<%=basePath%>img/favicon.ico" type="image/x-icon">
<link href="<%=basePath%>css/style.css" rel="stylesheet" type="text/css" media="all" />
<link href="<%=basePath%>css/bootstrap.css" rel="stylesheet" type="text/css" media="all">
<link rel="stylesheet" href="<%=basePath%>amazeui/css/amazeui.min.css">
<link rel="stylesheet" href="<%=basePath%>amazeui/css/app.css">
<link rel="stylesheet" href="<%=basePath%>css/estock.css">
<script type="text/javascript">
function wait(){
	parent.frames["middle"].location='<%=basePath%>wait.jsp';
	//document.getElementById("szzs").click();
}
function forward(url){
	 parent.frames["middle"].location='<%=basePath%>wait.jsp';
	 setTimeout(function(){
		 parent.frames["middle"].location=url;
	  },100);
}
</script>
</head>
<body style='margin-left:10%;margin-right:10%'>
<div id="header-nav" class="header-nav"> 
		 <div class="top-nav am-link-muted">		
			 <div class="logo">
				<a href="<%=basePath%>qutoes/index.jsp" target="middle"></a>
			 </div> 
				<ul>
					<li ><a href="<%=basePath%>qutoes/index.jsp" target="window.top">主页</a></li>
					<li ><a  style="display:block;" onclick="forward('<%=basePath%>ws/line.zc?code=<%=request.getParameter("code")%>')" target='middle'>分时行情</a></li>					
					<li><a  style="display:block;" onclick="forward('<%=basePath%>ws/line.zc?code=sh000001')" target='middle'>上证指数</a></li>
					<li><a  style="display:block;" onclick="forward('<%=basePath%>ws/line.zc?code=sz399001')" target='middle'>深证成指</a></li>
					<li><a  style="display:block;" onclick="forward('<%=basePath%>ws/line.zc?code=sz399102')" target='middle'>创业板综</a></li>
				    <li id='wait'> <a target='middle' style="display:block;" onclick="forward('<%=basePath%>qutoes/center.jsp')">股票常识</a> </li>
				    <li id='wait'> <a  target='window.top'style="display:block;"onclick="forward('<%=basePath%>qutoes/idear.jsp')">意见箱</a> </li>
				</ul>
		 </div>
 </div> 
</body>
</html>