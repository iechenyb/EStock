<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%  
String basePath = "http://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath()+"/";  
%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="icon" href="<%=basePath%>img/favicon.ico" type="image/x-icon">
<link rel="shortcut icon" href="<%=basePath%>img/favicon.ico" type="image/x-icon">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>股往金来</title>
</head>
<!-- frameset 必须写在body之外 -->
<frameset rows="10%,5%,*,8%" border="0"  id="main" framespacing="0" >
   <frame src="<%=basePath%>top.jsp" name="top" scrolling="No">
   <frame src="<%=basePath%>qutoes/header.jsp?code=<%=request.getParameter("code")%>" name="top" id="top" scrolling="No">
   <frame src="<%=basePath%>ws/line?code=<%=request.getParameter("code")%>" id="middle" name="middle" scrolling="No">
   <frame src="<%=basePath%>footer.jsp" name="bottom" id="bottom" scrolling="No">
</frameset> 
<noframes><body style="margin-left:100px;">很抱歉，阁下使用的浏览器不支援框架功能，请转用新的浏览器</body></noframes>
</html>