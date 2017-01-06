<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'suc.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
  </head>
  
  <body>
    
    <center><div>
    WelCome,${sessionScope.user},you can do anything! Long Long ago,I have a dream !<br>
    <table border="1px" bordercolor="blue" id="tbColor">
	    <caption>作者gjy的图书</caption>
	    <c:forEach var="books" items="${book}" >
	    <tr >
	    	<td>书名：</td>
	    	<td>${books[0] }</td>
	    </tr>
	   <tr>
	    	<td>书名：</td>
	    	<td>${books[1] }</td>
	    </tr>
	    <tr>
	    	<td>书名：</td>
	    	<td>${books[2] }</td>
	    </tr>
	    <tr>
	    	<td>书名：</td>
	    	<td>${books[3] }</td>
	    </tr>
	    </c:forEach>
    </table>
    </div></center>
  </body>
</html>
