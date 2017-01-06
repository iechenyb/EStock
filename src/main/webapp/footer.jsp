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
<link href="css/style.css" rel="stylesheet" type="text/css" media="all" />
<link href="css/bootstrap.css" rel="stylesheet" type="text/css" media="all">
<link rel="stylesheet" href="<%=basePath%>amazeui/css/amazeui.min.css">
<link rel="stylesheet" href="<%=basePath%>amazeui/css/app.css">
<link rel="stylesheet" href="<%=basePath%>/css/estock.css">
</head>
<body style='margin-left:10%;margin-right:10%'>
<center>
<a href="http://quote.eastmoney.com" target="window.top">东方财富</a>&nbsp;&nbsp;
<a href="http://www.10jqka.com.cn" target="window.top">同花顺</a>&nbsp;&nbsp;
<a href="http://gupiao.baidu.com" target="window.top">百度股市通</a>&nbsp;&nbsp;
</center>
<center>Copyright &copy; 2015.iechenyb All rights reserved.</center>
</body>
</html>