<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%
	String basePath = "http://" + request.getServerName() + ":"
			+ request.getServerPort() + request.getContextPath() + "/";
/*
<include file="../public.jsp" ><!-- 翻译阶段执行 -->
*/
%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%-- <jsp:include page="../public.jsp" flush="true" >
	<jsp:param name="fitstParamer" value="firstValue">
	<jsp:param name="lastParamer" value="lastValue">
</jsp:include>  
--%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>文件下载测试</title>
</head>
<body>
<a href="<%=basePath%>Download?name=default.html&type=1">点击下载看看type=1</a>&nbsp;
<a href="<%=basePath%>Download?name=default.html&type=2">点击下载看看type=2</a>
   <table width="100%" border=1>
       <tr>
		<th align=center width="10%">序号</th>
		<th align=center width="80%">文件名</th>
		<th align=center>操作（servlet）</th>
		</tr>
      <c:forEach var="file" items="${lst}" step="1" varStatus="s">
		<tr>
		<td align=center>${s.index}</td>
		<td align=center>${file.name}</td>
		<td align=center><a href="<%=basePath%>Download?name=${file.name}&type=2">下载</a></td>
		</tr>
	  </c:forEach>
  </table>
  <table width="100%" border=1>
       <tr>
		<th align=center width="10%">序号</th>
		<th align=center width="80%">文件名</th>
		<th align=center>操作（spring下载）</th>
		</tr>
      <c:forEach var="file" items="${lst}" step="1" varStatus="s">
		<tr>
		<td align=center>${s.index}</td>
		<td align=center>${file.name}</td>
		<td align=center><a href="<%=basePath%>/file/download.zc?fileName=${file.name}&type=2">下载</a></td>
		</tr>
	  </c:forEach>
  </table>
  <table width="100%" border=1>
       <tr>
		<th align=center width="10%">序号</th>
		<th align=center width="80%">文件名</th>
		<th align=center>操作（struts2下载）</th>
		</tr>
      <c:forEach var="file" items="${lst}" step="1" varStatus="s">
		<tr>
		<td align=center>${s.index}</td>
		<td align=center>${file.name}</td>
		<td align=center><a href="<%=basePath%>Download?name=${file.name}&type=2">下载</a></td>
		</tr>
	  </c:forEach>
  </table>
</body>
</html>