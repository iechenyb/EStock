<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%
	String basePath = "http://" + request.getServerName() + ":"
			+ request.getServerPort() + request.getContextPath() + "/";
	Object object = request.getSession().getAttribute("username");
	String username = "";
	if (object != null) {
		username = object.toString();
	}
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>head</title>
<link href="css/style.css" rel="stylesheet" type="text/css" media="all" />
<link href="css/bootstrap.css" rel="stylesheet" type="text/css"
	media="all">
<link rel="stylesheet" href="<%=basePath%>amazeui/css/amazeui.min.css">
<link rel="stylesheet" href="<%=basePath%>amazeui/css/app.css">
<link rel="stylesheet" href="<%=basePath%>css/estock.css">
</head>
<body style="margin-top:10px;">
	<div class="am-g">
		<div class="am-u-sm-2">&nbsp;</div>		
		<div class="am-u-sm-1 am-u-end "><img alt="首页-搜索" src="<%=basePath%>img/logo.jpg" width="80px" height="50px"/></div>
		<div class="am-u-sm-6">&nbsp;</div>		
		<div class="am-u-sm-3 am-u-end bottom0">
			<%
				if (username == null || "".equals(username)) {
			%>
			<a href="<%=basePath%>register.jsp" target="window.top">注册</a>&nbsp;&nbsp;&nbsp;&nbsp;
			<a href="<%=basePath%>login.jsp" target="window.top">登陆</a>
			<%
				} else {
			%>
			&nbsp;<%=username%>&nbsp;&nbsp;&nbsp;&nbsp;
			<a href="<%=basePath%>user/logout" target="window.top">退出</a>
			<%
				}
			%>
		</div>
		
	</div>
	<hr style="margin-top:0px;height:1px;border:none;border-top:1px ridge gray;" />
</body>
</html>