<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%  
String basePath = "http://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath()+"/";  
%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
<%-- <link href="<%=basePath%>/css/style.css" rel="stylesheet" type="text/css" media="all" />
<link href="<%=basePath%>/css/bootstrap.css" rel="stylesheet" type="text/css" media="all"> --%>
<link rel="stylesheet" href="<%=basePath%>amazeui/css/amazeui.min.css">
<link rel="stylesheet" href="<%=basePath%>amazeui/css/app.css">
<%-- <link rel="stylesheet" href="<%=basePath%>/css/estock.css"> --%>
</head>
<body style='margin-left:0;margin-bottom:15%'>
<%-- <div class="am-g" style="height:24px;">
	<div class="am-u-sm-2">&nbsp;</div>
	<div class="am-u-sm-2"><a href="<%=basePath%>phone/index.jsp" target="window.top">手机版</a></div>
	<div class="am-u-sm-2"><a href="http://quote.eastmoney.com" target="window.top">东方财富</a></div>
	<div class="am-u-sm-2"><a href="http://www.10jqka.com.cn" target="window.top">同花顺</a></div>
	<div class="am-u-sm-2"><a href="http://gupiao.baidu.com" target="window.top">百度股市通</a></div>
	<div class="am-u-sm-2">&nbsp;</div>
</div> --%>
<div class="am-g" style="height:24px;">
	<div class="am-u-sm-5">&nbsp;</div>
	<div class="am-u-sm-3">
	   <font size=2> <a>Copyright &copy; 2015.iechenyb All rights reserved.</a></font>
	</div>
	<div class="am-u-sm-4">&nbsp;</div>
</div>
</body>
</html>