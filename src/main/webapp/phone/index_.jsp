<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%  
String basePath = "http://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath()+"/";  
%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>mobile</title>
</head>
<body>
<h2><a href="<%=basePath%>phone/accord.jsp" target='_self'>图片插件</a></h2><br>
<h2><a href="<%=basePath%>phone/header.jsp" target='_self'>header</a></h2><br>
<h2><a href="<%=basePath%>phone/index1.jsp" target='_self'>菜单-1</a></h2><br>
<h2><a href="<%=basePath%>phone/index2.jsp" target='_self'>菜单-2</a></h2><br>
<h2><a href="<%=basePath%>phone/index3.jsp" target='_self'>菜单-3</a></h2><br>
<h2><a href="<%=basePath%>phone/index4.jsp" target='_self'>菜单-4</a></h2><br>
<h2><a href="<%=basePath%>phone/index5.jsp" target='_self'>菜单-5</a></h2><br>
</body>
</html>